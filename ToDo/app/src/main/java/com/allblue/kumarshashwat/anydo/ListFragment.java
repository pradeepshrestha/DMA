package com.allblue.kumarshashwat.anydo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

public class ListFragment extends Fragment {

    ArrayList<String> al=new ArrayList<>();
    EditText to_valve;
    MyDBHelper myDBHelper;
    String kop;
    ArrayAdapter<String> aa;
    String allblue;
    ArrayList<String> ide=new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview=inflater.inflate(R.layout.fragment_list, container, false);
        final ListView lv=(ListView)rootview.findViewById(R.id.todo_list);
        EditText to_submit=(EditText)rootview.findViewById(R.id.to_submit);
        to_valve=to_submit;
        //final TextView tv=(TextView)rootview.findViewById(R.id.nothing);
        Button submit_button=(Button)rootview.findViewById(R.id.submit_button);
        myDBHelper=new MyDBHelper(getContext());
        String selection=getArguments().getString("From Activity");
        assert selection != null;
        final String kop=selection.replace(" ","").trim().toLowerCase();
        Cursor c=myDBHelper.myselect(kop);
        while(c.moveToNext()){
            String a=(c.getString(0));
            al.add(a);
            String integer=c.getString(1);
            ide.add(integer);
        }
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                String k=to_valve.getText().toString();
                String date=cal.get(Calendar.DAY_OF_MONTH) + "/"
                        + (cal.get(Calendar.MONTH)+1)
                        + "/" + cal.get(Calendar.YEAR);
                String time=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
                Snackbar.make(getView(),k+" added on "+date+" at "+time,Snackbar.LENGTH_LONG).show();
                ContentValues cv=new ContentValues();
                cv.put("task",k);
                myDBHelper.myinsert(cv,kop);
                al.clear();ide.clear();
                Cursor c=myDBHelper.myselect(kop);
                while(c.moveToNext()){
                    String a=c.getString(0);
                    al.add(a);
                    String integer=c.getString(1);
                    ide.add(integer);
                }
                    lv.setAdapter(aa);
                    to_valve.getText().clear();
                    aa.notifyDataSetChanged();
            }});

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String allblue=al.get(position).toString();
                myDBHelper.getWritableDatabase().delete(kop, ide.get(position) + "=" + MyDBHelper.KEY_ID, null);
                Snackbar.make(view,"DELETED",Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ContentValues cv=new ContentValues();
                        cv.put("task",allblue);
                        myDBHelper.myinsert(cv,kop);
                        al.add(allblue);
                        aa.notifyDataSetChanged();
                    }
                }).show();
                al.remove(position);
                aa.notifyDataSetChanged();
                return false;
            }
        });
        return rootview;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

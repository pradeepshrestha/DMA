package com.allblue.kumarshashwat.anydo;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Home extends AppCompatActivity {

    Context context;
    private List<Todo> alpha;
    private String[] myString;
    public static final Random rgenerator=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        TextView tv=(TextView)findViewById(R.id.ter);
        myString=getResources().getStringArray(R.array.myArray);
        String q=myString[rgenerator.nextInt(myString.length)];
        tv.setText(q);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        alpha=new ArrayList<>();
        alpha.add(new Todo("GROCERY",R.drawable.grocery3));
        alpha.add(new Todo("WORK",R.drawable.work3));
        alpha.add(new Todo("MOVIES",R.drawable.movies2));
        alpha.add(new Todo("SCHOOL",R.drawable.school2));
        alpha.add(new Todo("PLACES",R.drawable.places1));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i=new Intent(Home.this,List_Todo.class);
                        String name=alpha.get(position).cardtext;
                        i.putExtra("name",name);
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Snackbar.make(view,"Do Nothing",Snackbar.LENGTH_SHORT).setActionTextColor(12).show();
                    }
                })
        );

        RVAdapter rvAdapter=new RVAdapter(alpha);
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch(item.getItemId()){
           case R.id.action_favorite:
               Intent i=new Intent(this,about.class);
               startActivity(i);
               return true;
           default:
               return super.onOptionsItemSelected(item);
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}

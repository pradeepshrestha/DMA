package com.example.andriod.recyclerviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WorldViewHolder> {
    private List<String> data;
    Context context;
    public WordListAdapter(List<String> data, Context context){
        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public WorldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.wordlist_item, parent, false);
        return new WorldViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WorldViewHolder holder, int position) {
        String item = data.get(position);
        holder.bind(item);
//        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class WorldViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public WorldViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.wordlist_item, parent, false));
            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            int mPosition = getLayoutPosition();
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
        }

        public void bind(String item) {
            textView.setText(item);
        }
    }
}




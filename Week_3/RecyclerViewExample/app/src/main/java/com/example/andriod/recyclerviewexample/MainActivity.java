package com.example.andriod.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private  ArrayList<String> wordList;
    private WordListAdapter adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wordList = new ArrayList<>();
        for (int i =0; i < 30; i++){
            wordList.add("WordList"+ i);
        }


        mRecyclerView = findViewById(R.id.word_list);
        adapter = new WordListAdapter(wordList, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                int worldListSize = wordList.size();
                wordList.add("+ word" + worldListSize);
                mRecyclerView.getAdapter().notifyItemInserted(worldListSize);
                mRecyclerView.smoothScrollToPosition(worldListSize);
            }

        });
    }
}

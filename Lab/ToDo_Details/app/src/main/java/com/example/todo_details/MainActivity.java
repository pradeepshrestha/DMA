package com.example.todo_details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.Instant;
import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    private String[] tasks;
    //    ={"coffee", "lunch", "college", "listening music", "playing", "dinner", "sleep"};
    private TextView textView;
    int currentIndex = 0;
    //    private static final String TODO_INDEX = "todoIndex";
    public static final String TODO_INDEX = "com.example.todoIndex";

    private Button detailButton;

    @Override
    protected void onStart() {
        Log.d("MainActivity", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume()");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity", "onDestory()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity", "onPause()");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle onSaveInstanceState) {
        super.onSaveInstanceState(onSaveInstanceState);
        onSaveInstanceState.putInt("TODO_INDEX", currentIndex);
        Log.d("MainActivity", "onSaveInstanceState");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt("TODO_INDEX");
        }
        Resources res = getResources();
        tasks = res.getStringArray(R.array.tasks);

        textView = findViewById(R.id.textView);
        textView.setText(tasks[currentIndex]);


        detailButton = findViewById(R.id.btn_detail);
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Todo_DetailActivity.class);
                intent.putExtra(TODO_INDEX, currentIndex);
                startActivity(intent);


            }
        });
    }



    public void preButton(View view){
        if(currentIndex > 0){
            currentIndex = currentIndex % tasks.length;
        } else{
            currentIndex = tasks.length;
        }
        currentIndex = --currentIndex % tasks.length;
        textView.setText(tasks[currentIndex]);
        Log.d("MainActivity", "value of current index:" +currentIndex);
    }

    public void nextButton(View view){
        currentIndex = ++currentIndex % tasks.length;
        textView.setText(tasks[currentIndex]);
        Log.d("MainActivity", "Value of current index:" +currentIndex);
    }
}

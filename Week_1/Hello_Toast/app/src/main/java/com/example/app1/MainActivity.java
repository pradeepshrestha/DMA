package com.example.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =findViewById(R.id.textView);
    }

    public void toastClick(View view){
        Toast.makeText(this, "Hello Toast!", Toast.LENGTH_LONG).show();
    }

    public void Toast(View view){
        count++;
        textView.setText(""+count);
    }
}

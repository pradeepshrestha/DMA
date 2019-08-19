package com.example.my_first_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText first;
    EditText second;

    public void add(View view) {
        double F_value;
        double S_value;
        double R_value;

        F_value = Double.parseDouble(first.getText().toString());
        S_value = Double.parseDouble(second.getText().toString());
        R_value = F_value + S_value;
        result.setText(Double.toString(R_value));
    }

    public void sub(View view) {
        double F_value;
        double S_value;
        double R_value;

        F_value = Double.parseDouble(first.getText().toString());
        S_value = Double.parseDouble(second.getText().toString());
        R_value = F_value - S_value;
        result.setText(Double.toString(R_value));
    }

    public void mul(View view) {
        double F_value;
        double S_value;
        double R_value;

        F_value = Double.parseDouble(first.getText().toString());
        S_value = Double.parseDouble(second.getText().toString());
        R_value = F_value * S_value;
        result.setText(Double.toString(R_value));
    }

    public void div(View view) {
        double F_value;
        double S_value;
        double R_value;

        F_value = Double.parseDouble(first.getText().toString());
        S_value = Double.parseDouble(second.getText().toString());
        R_value = F_value / S_value;
        result.setText(Double.toString(R_value));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = (EditText) findViewById(R.id.first);
        second = (EditText) findViewById(R.id.second);
    }
}

package com.example.fragments_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private boolean toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(BlankFragment.class);
    }

    private void showFragment(Class fragmentClass) {
        Fragment frag = null;
        try{
            frag = (Fragment) fragmentClass.newInstance();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InstantiationException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, frag).commit();
    }

    public void changeFragment(View view) {
        if(toggle)
            showFragment(BlankFragment.class);
        else
            showFragment(BlankFragment2.class);
        toggle = !toggle;
    }
}

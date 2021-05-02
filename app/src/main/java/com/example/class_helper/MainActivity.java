package com.example.class_helper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private fragment_login fragment_login = new fragment_login();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active;
    Fragment firstFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("강의를 부탁해");
        firstFragment = new fragment_login();
        fm.beginTransaction().replace(R.id.fragment_container, fragment_login).commit();
    }
}
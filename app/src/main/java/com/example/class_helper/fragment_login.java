package com.example.class_helper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class fragment_login extends Fragment {
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_login, container, false);
        MainActivity activity = (MainActivity) getActivity();
        Button login = view.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragment_home fragment_home = new fragment_home();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment_home);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}

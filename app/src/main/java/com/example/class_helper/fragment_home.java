package com.example.class_helper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class fragment_home extends Fragment {

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_home, container, false);
        Button go_noti = view.findViewById(R.id.go_noti);
        Button go_memo = view.findViewById(R.id.go_memo);
        Button go_map = view.findViewById(R.id.go_map);
        Button go_covid19 = view.findViewById(R.id.go_covid19);
        Button go_webmail = view.findViewById(R.id.go_mail);
        ImageView go_setting = view.findViewById(R.id.go_setting);
        MainActivity activity = (MainActivity) getActivity();
        go_noti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragment_notice fragment_notice = new fragment_notice();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment_notice);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        go_memo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragment_memo fragment_memo = new fragment_memo();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment_memo);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        go_map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragment_map fragment_map = new fragment_map();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment_map);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        go_covid19.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragment_covid19 fragment_covid19 = new fragment_covid19();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment_covid19);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        go_webmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragment_webmail fragment_webmail = new fragment_webmail();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment_webmail);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        go_setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragment_setting fragment_setting = new fragment_setting();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment_setting);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}

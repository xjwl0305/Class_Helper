package com.example.class_helper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class fragment_memo extends Fragment {
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_memo, container, false);
        MainActivity activity = (MainActivity) getActivity();
        ImageView back_button = view.findViewById(R.id.memo_back_button);
        ImageView go_setting = view.findViewById(R.id.memo_go_setting);
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragment_home fragment_home = new fragment_home();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment_home);
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

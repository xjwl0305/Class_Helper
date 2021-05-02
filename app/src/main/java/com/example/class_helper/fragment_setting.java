package com.example.class_helper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class fragment_setting extends Fragment {
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_setting, container, false);
        MainActivity activity = (MainActivity) getActivity();
        ImageView back_button = view.findViewById(R.id.setting_back_button);
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                assert activity != null;
                activity.onBackPressed();
            }
        });
        return view;
    }
}

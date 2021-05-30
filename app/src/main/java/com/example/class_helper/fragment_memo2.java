package com.example.class_helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
public class fragment_memo2 extends AppCompatActivity {
//    EditText et_save;
//    String shared = "file";
//    SharedPreferences sharedPreferences;
//    Button btn_save;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        sharedPreferences =
//                inflater.getContext().getSharedPreferences(shared,Context.MODE_PRIVATE);
//        return inflater.inflate(R.layout.fragment_memo, container, false);
//    }
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle
//            savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        et_save = (EditText)view.findViewById(R.id.et_save);
//        String value = sharedPreferences.getString("key","");
//        et_save.setText(value);
//    }
//    @Override
//    public void onDestroy() {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        String value = et_save.getText().toString();
//        editor.putString("key", value);
//        editor.commit();
//        super.onDestroy();
//    }
}
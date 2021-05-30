package com.example.class_helper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Main";
    fragment_notice fragment_notice;
    Button btn_Update;
    Button btn_Insert;
    EditText edit_ID;
    EditText edit_Lecture;
    EditText edit_Day;
    TextView text_ID;
    TextView text_Lecture;
    TextView text_Day;
    TextView text_Style;
    CheckBox check_A;
    CheckBox check_B;
    CheckBox check_C;
    Button go_noti;

    long nowIndex;
    String ID;
    String lecture;
    String day;
    String style = "";
    String sort = "userid";

    ArrayAdapter<String> arrayAdapter;

    static ArrayList<String> arrayIndex =  new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();
    private DbOpenHelper mDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FragmentManager fm = getSupportFragmentManager();
        setContentView(R.layout.fragment_home);
        btn_Insert = (Button) findViewById(R.id.btn_insert);
        btn_Insert.setOnClickListener((View.OnClickListener) this);
        btn_Update = (Button) findViewById(R.id.btn_update);
        btn_Update.setOnClickListener(this);
        edit_ID = (EditText) findViewById(R.id.edit_id);
        edit_Lecture = (EditText) findViewById(R.id.edit_lecture);
        edit_Day = (EditText) findViewById(R.id.edit_day);
        text_ID = (TextView) findViewById(R.id.text_id);
        text_Lecture = (TextView) findViewById(R.id.text_lecture);
        text_Day = (TextView) findViewById(R.id.text_day);
        text_Style= (TextView) findViewById(R.id.text_style);
        check_A = (CheckBox) findViewById(R.id.check_A);
        check_A.setOnClickListener(this);
        check_B = (CheckBox) findViewById(R.id.check_B);
        check_B.setOnClickListener(this);
        check_C = (CheckBox) findViewById(R.id.check_C);
        check_C.setOnClickListener(this);
        Intent intent = new Intent(this, fragment_home.class);
        go_noti = (Button) findViewById(R.id.go_noti);
        Button go_memo = (Button) findViewById(R.id.go_memo);
        Button go_map = (Button) findViewById(R.id.go_map);
        Button go_covid = (Button) findViewById(R.id.go_covid19);
        Button go_webmail = (Button) findViewById(R.id.go_mail);
        ImageView go_setting = (ImageView) findViewById(R.id.go_setting);
        go_noti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), fragment_notice.class);
                startActivity(intent);
            }
        });
        go_memo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), fragment_memo.class);
                startActivity(intent);
            }
        });
        go_map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), fragment_map.class);
                startActivity(intent);
            }
        });
        go_covid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), fragment_covid19.class);
                startActivity(intent);
            }
        });
        go_webmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), fragment_webmail.class);
                startActivity(intent);
            }
        });
        go_setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), fragment_setting.class);
                startActivity(intent);
            }
        });



        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView listView = (ListView) findViewById(R.id.db_list_view);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(onClickListener);
        listView.setOnItemLongClickListener(longClickListener);

        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        showDatabase(sort);

        btn_Insert.setEnabled(true);
        btn_Update.setEnabled(false);
    }
    public void setInsertMode(){
        edit_ID.setText("");
        edit_Lecture.setText("");
        edit_Day.setText("");
        check_A.setChecked(false);
        check_B.setChecked(false);
        btn_Insert.setEnabled(true);
        btn_Update.setEnabled(false);
    }
    private AdapterView.OnItemClickListener onClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("On Click", "position = " + position);
            nowIndex = Long.parseLong(arrayIndex.get(position));
            Log.e("On Click", "nowIndex = " + nowIndex);
            Log.e("On Click", "Data: " + arrayData.get(position));
            String[] tempData = arrayData.get(position).split("\\s+");
            Log.e("On Click", "Split Result = " + tempData);
            edit_ID.setText(tempData[0].trim());
            edit_Lecture.setText(tempData[1].trim());
            edit_Day.setText(tempData[2].trim());
            if(tempData[3].trim().equals("대면")){
                check_A.setChecked(true);
                style = "대면";
            }else if(tempData[3].trim().equals("비대면")){
                check_B.setChecked(true);
                style = "비대면";
            }
            else {
                check_C.setChecked(true);
                style = "온라인";
            }
            btn_Insert.setEnabled(false);
            btn_Update.setEnabled(true);
        }
    };
    private AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("Long Click", "position = " + position);
            nowIndex = Long.parseLong(arrayIndex.get(position));
            String[] nowData = arrayData.get(position).split("\\s+");
            String viewData = nowData[0] + ", " + nowData[1] + ", " + nowData[2] + ", " + nowData[3];
            AlertDialog.Builder dialog = new AlertDialog.Builder(fragment_home.this);
            dialog.setTitle("데이터 삭제")
                    .setMessage("해당 데이터를 삭제 하시겠습니까?" + "\n" + viewData)
                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(fragment_home.this, "데이터를 삭제했습니다.", Toast.LENGTH_SHORT).show();
                            mDbOpenHelper.deleteColumn(nowIndex);
                            showDatabase(sort);
                            setInsertMode();
                        }
                    })
                    .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(fragment_home.this, "삭제를 취소했습니다.", Toast.LENGTH_SHORT).show();
                            setInsertMode();
                        }
                    })
                    .create()
                    .show();
            return false;
        }
    };

    public void showDatabase(String sort){
        Cursor iCursor = mDbOpenHelper.sortColumn(sort);
        Log.d("showDatabase", "DB Size: " + iCursor.getCount());
        arrayData.clear();
        arrayIndex.clear();
        while(iCursor.moveToNext()){
            String tempIndex = iCursor.getString(iCursor.getColumnIndex("_id"));
            String tempID = iCursor.getString(iCursor.getColumnIndex("userid"));
            tempID = setTextLength(tempID,10);
            String tempLecture = iCursor.getString(iCursor.getColumnIndex("lecture"));
            tempLecture = setTextLength(tempLecture,10);
            String tempDay = iCursor.getString(iCursor.getColumnIndex("day"));
            tempDay = setTextLength(tempDay,10);
            String tempStyle = iCursor.getString(iCursor.getColumnIndex("style"));
            tempStyle = setTextLength(tempStyle,10);

            String Result = tempID + tempLecture + tempDay + tempStyle;
            arrayData.add(Result);
            arrayIndex.add(tempIndex);
        }
        arrayAdapter.clear();
        arrayAdapter.addAll(arrayData);
        arrayAdapter.notifyDataSetChanged();
    }

    public String setTextLength(String text, int length){
        if(text.length()<length){
            int gap = length - text.length();
            for (int i=0; i<gap; i++){
                text = text + " ";
            }
        }
        return text;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                ID = edit_ID.getText().toString();
                lecture = edit_Lecture.getText().toString();
                day = edit_Day.getText().toString();
                mDbOpenHelper.open();
                mDbOpenHelper.insertColumn(ID, lecture, day, style);
                showDatabase(sort);
                setInsertMode();
                edit_ID.requestFocus();
                edit_ID.setCursorVisible(true);
                break;

            case R.id.btn_update:
                ID = edit_ID.getText().toString();
                lecture = edit_Lecture.getText().toString();
                day = edit_Day.getText().toString();
                mDbOpenHelper.updateColumn(nowIndex,ID, lecture, day, style);
                showDatabase(sort);
                setInsertMode();
                edit_ID.requestFocus();
                edit_ID.setCursorVisible(true);
                break;

            case R.id.check_A:
                check_B.setChecked(false);
                check_C.setChecked(false);
                style = "대면";
                break;

            case R.id.check_B:
                check_A.setChecked(false);
                check_C.setChecked(false);
                style = "비대면";
                break;

            case R.id.check_C:
                check_A.setChecked(false);
                check_B.setChecked(false);
                style = "온라인";
                break;
            case R.id.go_noti:
                Intent intent = new Intent(v.getContext(), fragment_notice.class);
                startActivity(intent);
                break;

        }
    }
}

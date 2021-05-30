package com.example.class_helper;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns{
        public static final String USERID = "userid";
        public static final String LECTURE = "lecture";
        public static final String DAY = "day";
        public static final String STYLE= "style";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +USERID+" text not null , "
                +LECTURE+" text not null , "
                +DAY+" text not null , "
                +STYLE+" text not null );";
    }
}
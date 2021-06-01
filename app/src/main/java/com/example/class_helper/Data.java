package com.example.class_helper;

public class Data {
    public String Text;
    public String Url;

    public Data(String text, String url) {
        Text = text;
        Url = url;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}

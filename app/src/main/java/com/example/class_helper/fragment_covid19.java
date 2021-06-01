package com.example.class_helper;

//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//public class fragment_covid19 extends AppCompatActivity {
//    protected void onCreate(@Nullable Bundle saveInstanceState) {
//        super.onCreate(saveInstanceState);
//        setContentView(R.layout.fragment_covid19);
//        ImageView back_button = (ImageView) findViewById(R.id.covid_back_button);
//        ImageView go_setting = (ImageView) findViewById(R.id.covid_go_setting);
//        back_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        go_setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), fragment_setting.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fragment_covid19 extends AppCompatActivity {

    private final String BASETARGET = "https://www.kw.ac.kr/";
    private final String TARGET = BASETARGET + "ko/life/corona.jsp?srCategoryId=&mode=list&searchKey=1&searchVal=%EA%B5%AC%EC%84%B1%EC%9B%90+%ED%99%95%EC%A7%84%EC%9E%90+%EB%B0%9C%EC%83%9D&x=0&y=0";

    private ListView listView;
    private ListAdapter adapter;
    private List<Data> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this::onItemClickListener);

        ParserTask task = new ParserTask();
        task.execute();
    }

    private class ParserTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(TARGET).get();

                Elements elements = doc.select(".board-list-box li");

                for (Element element : elements) {
                    datas.add(new Data(element.select("a").text().trim(), element.select("a").attr("href").trim()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            bindData();
        }
    }

    private void bindData() {
        adapter = new ListAdapter(this.datas);
        listView.setAdapter(adapter);
    }

    private void onItemClickListener(AdapterView<?> adapterView, View view, int position, long l) {
        StartBrowser(BASETARGET + this.datas.get(position).Url);
    }

    private void StartBrowser(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
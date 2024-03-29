package com.example.again;


import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        userID = getIntent().getStringExtra("userID");



        noticeListView=(ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));
        noticeList.add(new Notice("공지사항입니다.", "최윤영", "2020-11-10"));


        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);


        final Button searchButton=(Button) findViewById(R.id.searchButton);
        final Button mypageButton=(Button) findViewById(R.id.mypageButton);
        final Button scheduleButton=(Button) findViewById(R.id.scheduleButton);
        final Button friendpageButton=(Button)findViewById(R.id.friendpageButton);
        final LinearLayout notice=(LinearLayout)findViewById(R.id.notice);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);//공지사항부분이 보이지 않도록, fragment로 바꿔줄수 있게
                searchButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mypageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                friendpageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new SearchFragment());
                fragmentTransaction.commit();
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);//공지사항부분이 보이지 않도록, fragment로 바꿔줄수 있게
                searchButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mypageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                friendpageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new SchedulepageFragment());
                fragmentTransaction.commit();
            }
        });

        mypageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);//공지사항부분이 보이지 않도록, fragment로 바꿔줄수 있게
                searchButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mypageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                friendpageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new MypageFragment());
                fragmentTransaction.commit();
            }
        });

        friendpageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);//공지사항부분이 보이지 않도록, fragment로 바꿔줄수 있게
                searchButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mypageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                friendpageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new FriendpageFragment());
                fragmentTransaction.commit();
            }
        });

        

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            //super.onPreExecute();
            target="https://choiyy0313.cafe24.com/NoticeList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url=new URL(target);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder=new StringBuilder();
                while((temp=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result)
        {
            try{
                JSONObject jsonObject=new JSONObject(result);
                JSONArray jsonArray=jsonObject.getJSONArray("response");
                int count=0;
                String noticeContent, noticeName, noticeDate;
                while(count<jsonArray.length()){
                    JSONObject object=jsonArray.getJSONObject(count);
                    noticeContent=object.getString("noticeContent");
                    noticeName=object.getString("noticeName");
                    noticeDate=object.getString("noticeDate");
                    Notice notice=new Notice(noticeContent,noticeName, noticeDate);
                    noticeList.add(notice);
                    adapter.notifyDataSetChanged();
                    count++;
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-lastTimeBackPressed<1500){
            finish();
            return;
        }
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 눌러 종료합니다.",Toast.LENGTH_SHORT);
        lastTimeBackPressed=System.currentTimeMillis();
    }

}

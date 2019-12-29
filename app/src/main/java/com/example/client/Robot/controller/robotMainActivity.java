package com.example.client.Robot.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.client.R;
import com.example.client.Robot.controller.DTO.ListData;
import com.example.client.Robot.controller.controller.HttpData;
import com.example.client.Robot.controller.controller.HttpGetDataListener;
import com.example.client.Robot.controller.utils.TextAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class robotMainActivity extends Activity implements HttpGetDataListener, View.OnClickListener {
    private HttpData httpData;
    private List<ListData> lists;
    private ListView lv;
    private EditText sendtext;
    private Button send_btn;
    private String content_str = "";
    private TextAdapter adapter;
    private String[] welcome_array;
    private double currentTime=0, oldTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);
        initView();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        sendtext = (EditText) findViewById(R.id.sendText);
        send_btn = (Button) findViewById(R.id.send_btn);
        lists = new ArrayList<ListData>();
        //给按钮添加事件
        send_btn.setOnClickListener(this);

        adapter = new TextAdapter(lists, this);
        lv.setAdapter(adapter);
        ListData listData;
        listData = new ListData(getRandomWelcomeTips(), ListData.RECEIVER,
                getTime());
        lists.add(listData);
    }
    private String getRandomWelcomeTips() {
        String welcome_tip = null;
        welcome_array = this.getResources()
                .getStringArray(R.array.welcome_tips);
        int index = (int) (Math.random() * (welcome_array.length - 1));
        welcome_tip = welcome_array[index];
        return welcome_tip;
    }
    @Override
    public void getDataUrl(String result) {
        System.out.println(result);
        parseText(result);
    }

    public void parseText(String result) {
        try {

            ListData listData;

            listData = new ListData(result, ListData.RECEIVER,
                    getTime());
            lists.add(listData);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        getTime();
        content_str = sendtext.getText().toString();
        sendtext.setText("");
        String dropk = content_str.replace(" ", "");
        String droph = dropk.replace("\n", "");
        ListData listData;
        listData = new ListData(content_str, ListData.SEND, getTime());
        lists.add(listData);
        if (lists.size() > 30) {
            for (int i = 0; i < lists.size(); i++) {
                lists.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
        httpData = (HttpData) new HttpData(
                "http://api.tianapi.com/txapi/robot/index?key=639401798d19da239d2478dc1f3b8481&question="+droph,
             //"http://www.tuling123.com/openapi/api?key=6af9822f5491fadfc142b53818bbd63a&info="+droph,
                this).execute();

    }

    private String getTime() {
        currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        if (currentTime - oldTime >= 300) {
            oldTime = currentTime;
            return str;
        } else {
            return "";
        }

    }
}

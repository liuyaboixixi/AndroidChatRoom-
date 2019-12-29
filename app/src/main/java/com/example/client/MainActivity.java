package com.example.client;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.ChatRoom.ChatRoomMainActivity;
import com.example.client.Robot.controller.robotMainActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_simchat;
    private Button btn_chatroot;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        //初始化监听
        initListener();
        
    }

    private void initListener() {
        //模式一
        btn_simchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, robotMainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //模式二
        btn_chatroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ChatRoomMainActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
    }

    private void initView() {
    btn_chatroot=findViewById(R.id.btn_chatroom);
    btn_simchat=findViewById(R.id.btn_simchat);
    }

}

package com.example.client.Robot.controller.DTO;

import com.google.gson.annotations.SerializedName;

/**
 * 调用图灵机器人返回的json数据对象
 */
public class ListData {
    @SerializedName("reply")
    private String content;
    public static final int SEND = 1;
    public static final int RECEIVER = 2;
    private int flag;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public ListData(String content, int flag, String time) {
        this.content = content;
        this.flag = flag;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

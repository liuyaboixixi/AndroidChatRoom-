package com.example.client.Robot.controller.model;

import com.example.client.Robot.controller.DTO.ListData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsList {

    public int code;

    public String msg;

    @SerializedName("newslist")
    public List<ListData> newsList ;

}
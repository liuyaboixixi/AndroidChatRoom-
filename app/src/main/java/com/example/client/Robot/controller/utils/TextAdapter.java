package com.example.client.Robot.controller.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.client.R;
import com.example.client.Robot.controller.DTO.ListData;

import java.util.List;

public class TextAdapter extends BaseAdapter {
    private List<ListData> lists;
    private Context mContext;
    private RelativeLayout layout;

    public TextAdapter(List<ListData> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    /**
     * 返回listview 所能有的条数
     *
     * @param
     * @return
     */
    @Override
    public int getCount() {
        return lists.size();
    }


    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 重复加载视图
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //收到消息  加载左视图
        if (lists.get(position).getFlag() == ListData.RECEIVER) {
            layout = (RelativeLayout) inflater.inflate(R.layout.leftitem, null);
        }
        //发送消息 加载左视图
        if (lists.get(position).getFlag() == ListData.SEND) {
            layout = (RelativeLayout) inflater.inflate(R.layout.rightitem, null);
        }
        TextView time = (TextView) layout.findViewById(R.id.time);
        TextView tv = (TextView) layout.findViewById(R.id.tv);
        tv.setText(lists.get(position).getContent());
        time.setText(lists.get(position).getTime());
        return layout;
    }
}

package com.example.capstonmaster.board.free_board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonmaster.dto.List_item;
import com.example.capstonmaster.R;

import java.util.ArrayList;

public class FreeListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<List_item> free_itemList;
    TextView nickname_textView;
    TextView title_textView;
    TextView date_textView;
    TextView content_textView;
    public FreeListViewAdapter(Context context, ArrayList<List_item> free_itemList) {
        this.context = context;
        this.free_itemList = free_itemList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_free, null);

            title_textView = convertView.findViewById(R.id.f_title);
            content_textView = (TextView) convertView.findViewById(R.id.f_content);
            date_textView = (TextView) convertView.findViewById(R.id.f_date);
            nickname_textView = (TextView) convertView.findViewById(R.id.f_nickname);

            String content=free_itemList.get(position).getContent();
            if(content.length()>20){
                content=content.substring(0,20)+"...";
            }

            nickname_textView.setText(free_itemList.get(position).getNickname());
            title_textView.setText(free_itemList.get(position).getTitle());
            content_textView.setText(content);
            date_textView.setText(free_itemList.get(position).getWrite_date());
        }
        return convertView;
    }
    @Override
    public int getCount() {
        return free_itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.free_itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



}

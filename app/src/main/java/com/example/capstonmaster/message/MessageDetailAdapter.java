package com.example.capstonmaster.message;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.capstonmaster.R;
import com.example.capstonmaster.dto.MessageVO;

import java.util.ArrayList;


public class MessageDetailAdapter extends BaseAdapter {
    private ArrayList<MessageVO> list;
    Context context;
    TextView name_textView;
    TextView date_textView;
    TextView content_textView;

    public MessageDetailAdapter(Context context, ArrayList<MessageVO> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_message_detail, parent,false);
        }
        date_textView=convertView.findViewById(R.id.mesde_date);
        content_textView=convertView.findViewById(R.id.mesde_content);

        date_textView.setText(list.get(position).getCreated());
        content_textView.setText(list.get(position).getContent());
        return convertView;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}

package com.example.capstonmaster.board.free_board;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonmaster.dto.Board;
import com.example.capstonmaster.R;

import java.util.ArrayList;

public class FreeListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Board> free_itemList;
    TextView nickname_textView;
    TextView title_textView;
    TextView date_textView;
    TextView content_textView;
    public FreeListViewAdapter(Context context, ArrayList<Board> free_itemList) {
        this.context = context;
        this.free_itemList = free_itemList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_free, parent,false);
            Log.d("포지션은 ",position+"");
//            System.out.println(position +"과연" +convertView);

        }
        title_textView = convertView.findViewById(R.id.f_title);
        content_textView =  convertView.findViewById(R.id.f_content);
        date_textView =  convertView.findViewById(R.id.f_date);
        nickname_textView =  convertView.findViewById(R.id.f_nickname);

        String content=free_itemList.get(position).getContents();
        if(content.length()>20){
            content=content.substring(0,20)+"...더보기.";
        }

        nickname_textView.setText(free_itemList.get(position).getAuthor().getName());
        title_textView.setText(free_itemList.get(position).getTitle());
        content_textView.setText(content);
//            date_textView.setText(free_itemList.get(position).getWrite_date());
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

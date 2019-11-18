package com.example.capstonmaster.board.department_board;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonmaster.R;
import com.example.capstonmaster.dto.ArticleVO;

import java.util.ArrayList;

public class DepartListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<ArticleVO> depart_itemList;
    TextView nickname_textView;
    TextView title_textView;
    TextView date_textView;
    TextView content_textView;

    public DepartListViewAdapter(Context context, ArrayList<ArticleVO> depart_itemList) {
        this.context = context;
        this.depart_itemList = depart_itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_depart, parent,false);
            Log.d("포지션은 ",position+"");
//            System.out.println(position +"과연" +convertView);

        }
        title_textView = convertView.findViewById(R.id.d_title);
        content_textView =  convertView.findViewById(R.id.d_content);
        date_textView =  convertView.findViewById(R.id.d_date);
        nickname_textView =  convertView.findViewById(R.id.d_nickname);

        String content=depart_itemList.get(position).getContents();
        if(content.length()>20){
            content=content.substring(0,20)+"...더보기.";
        }

        nickname_textView.setText(depart_itemList.get(position).getAuthor().getName());
        title_textView.setText(depart_itemList.get(position).getTitle());
        content_textView.setText(content);
//            date_textView.setText(free_itemList.get(position).getWrite_date());
        return convertView;
    }
    @Override
    public int getCount() {
        return depart_itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.depart_itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}

//package com.example.capstonmaster.board.free_board;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.capstonmaster.dto.List_item;
//import com.example.capstonmaster.R;
//
//import java.util.ArrayList;
//
//public class FLDetailCommentAdapter extends BaseAdapter {
//  Context context;
//  ArrayList<List_item> Comment_List;
//  TextView nickname_content;
//  TextView title_textView;
//  TextView date_content;
//  TextView content_content;
//  public FLDetailCommentAdapter(Context context, ArrayList<List_item> free_itemList) {
//    this.context = context;
//    this.comment_List = free_itemList;
//  }
//  @Override
//  public View getcomment(int position, View convertView, ViewGroup parent) {
//    if (convertView == null) {
//      convertView = LayoutInflater.from(context).inflate(R.layout.activity_free_list_detail_comment_list, null);
//
//
//      content_content = (TextView) convertView.findViewById(R.id.c_content);
//      date_content = (TextView) convertView.findViewById(R.id.c_date);
//      nickname_content = (TextView) convertView.findViewById(R.id.c_nickname);
//
//      String content=free_itemList.get(position).getContent();
//      if(content.length()>20){
//        content=content.substring(0,20)+"...더보기.";
//      }
//
//      nickname_content.setText(free_itemList.get(position).getNickname());
//      content_content.setText(content);
//      date_content.setText(free_itemList.get(position).getWrite_date());
//    }
//    return convertView;
//  }
//  @Override
//  public int getCount() {
//    return free_itemList.size();
//  }
//
//  @Override
//  public Object getItem(int position) {
//    return this.free_itemList.get(position);
//  }
//
//  @Override
//  public long getItemId(int position) {
//    return position;
//  }
//
//
//
//}

package com.example.capstonmaster.board.free_board;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.capstonmaster.dto.List_item;
import com.example.capstonmaster.R;

import java.util.ArrayList;
import java.util.Date;

public class Freefragment extends Fragment {
//    private FreeViewModel freeViewModel;
ListView listView;
    FreeListViewAdapter adapter;
    ArrayList<List_item> list_itemArrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_free, container, false);
        listView=root.findViewById(R.id.free_list);
        list_itemArrayList=new ArrayList<>();
        list_itemArrayList.add(new List_item("보라돌이","안녕","10월 9일","반가워요 하하하ㅏㅋㅋㅋㅋㅋㅋㅋㅋㅠㅠㅠㅠㅠㄴㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("나나","반가워","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("뽀","ㅋㅋㅋㅋㅋㅋㅋ","10월 9일","ㅎㅎ"));
        list_itemArrayList.add(new List_item("보라돌이","안녕","10월 9일","반가워요 하하하ㅏㅋㅋㅋㅋㅋㅋㅋㅋㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("나나","반가워","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("뽀","ㅋㅋㅋㅋㅋㅋㅋ","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("보라돌이","안녕","10월 9일","반가워요 하하하ㅏㅋㅋㅋㅋㅋㅋㅋㅋㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("나나","반가워","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("뽀","ㅋㅋㅋㅋㅋㅋㅋ","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("보라돌이","안녕","10월 9일","반가워요 하하하ㅏㅋㅋㅋㅋㅋㅋㅋㅋㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("나나","반가워","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("뽀","ㅋㅋㅋㅋㅋㅋㅋ","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("보라돌이","안녕","10월 9일","반가워요 하하하ㅏㅋㅋㅋㅋㅋㅋㅋㅋㅠㅠㅠㅠㅠㅠㅠㅠㅠㅁㄴㅇ"));
        list_itemArrayList.add(new List_item("나나","반가워","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
        list_itemArrayList.add(new List_item("뽀","ㅋㅋㅋㅋㅋㅋㅋ","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));



        adapter= new FreeListViewAdapter(root.getContext(),list_itemArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(root.getContext(),list_itemArrayList.get(position).getNickname(),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(root.getContext(),FreeListDetail.class);
                intent.putExtra("detail",list_itemArrayList.get(position));
                startActivity(intent);
            }
        });
//        freeViewModel =
//                ViewModelProviders.of(this).get(FreeViewModel.class);
//        final TextView textView = root.findViewById(R.id.freeboard);
//        freeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

}

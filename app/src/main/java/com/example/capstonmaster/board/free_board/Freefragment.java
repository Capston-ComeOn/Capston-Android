package com.example.capstonmaster.board.free_board;

import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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


import com.example.capstonmaster.MainActivity;
import com.example.capstonmaster.dto.List_item;
import com.example.capstonmaster.R;
import com.google.android.material.tabs.TabItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Freefragment extends Fragment {
    ListView listView;
    FreeListViewAdapter adapter;
    ArrayList<List_item> list_itemArrayList;
    SharedPreferences sf;
    String userToken;

    public static String id;
    public static String category;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_free, container, false);

        sf= root.getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        userToken=sf.getString("userToken","");
        getBoard();

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

        return root;
    }
    public void getBoard(){
        System.out.println(id + "카테고리 아이디값~~~~~~~~~~~~~~~~~~~~~");
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer "+userToken)
                .url(getString(R.string.ip)+"/api/article/"+ id)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
                System.out.println("실패");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
//                    JSONArray jsonArray = new JSONArray(response.body().string());
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonobject = jsonArray.getJSONObject(i);
//                        String contents = jsonobject.getString("contents");
//                        String title = jsonobject.getString("title");
//                        System.out.println(title + " " + contents);
//                    }
                    JSONObject jsonobject =new JSONObject(response.body().string());
                    String m = jsonobject.getString("content");
                    System.out.println(m+" 뭐라도찍어봐");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });
    }

}

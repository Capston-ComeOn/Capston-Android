package com.example.capstonmaster.board.free_board;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.capstonmaster.dto.Board;
import com.example.capstonmaster.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Freefragment extends Fragment {
    ListView listView;
    FreeListViewAdapter adapter;
    ArrayList<Board> list_itemArrayList;
    SharedPreferences sf;
    String userToken;

    Board[] board;
    public static String id;
    public static String category;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_free, container, false);

        sf = root.getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        userToken = sf.getString("userToken", "");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getBoard();
        if (board == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            listView = root.findViewById(R.id.free_list);
            list_itemArrayList = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                list_itemArrayList.add(new Board(board[i].getTitle(), board[i].getContents(), board[i].getId(), board[i].getAuthor()));

            }
            list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1, board[0].getAuthor()));
//        list_itemArrayList.add(new Board("나나","반가워","10월 9일","ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ"));
//        list_itemArrayList.add(new Board("뽀","ㅋㅋㅋㅋㅋㅋㅋ","10월 9일","ㅎㅎ"));


            adapter = new FreeListViewAdapter(root.getContext(), list_itemArrayList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(root.getContext(), FreeListDetail.class);
                    intent.putExtra("detail", list_itemArrayList.get(position));
                    startActivity(intent);
                }
            });


        return root;
    }

    public void getBoard() {

        System.out.println(id + "카테고리 아이디값~~~~~~~~~~~~~~~~~~~~~");
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer " + userToken)
                .url(getString(R.string.ip) + "/api/article/" + id)
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

                    JSONObject jsonobject = new JSONObject(response.body().string());
                    String m = jsonobject.optString("content");
//                    System.out.println(m+" 뭐라도찍어봐");
                    Gson gson = new Gson();
                    board = gson.fromJson(m, Board[].class);
                    System.out.println(board[0].getId() + " " + board[0].getTitle() + " " + board[0].getContents() + " " + board[0].getAuthor().getName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });
    }

}

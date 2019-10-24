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


import com.example.capstonmaster.dto.Author;
import com.example.capstonmaster.dto.Board;
import com.example.capstonmaster.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
//https://material.io/resources/icons/?style=baseline
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
        FloatingActionButton fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"눌림",Toast.LENGTH_LONG).show();
                Intent intent= new Intent(getContext(),FreeWriteActivity.class);
                startActivity(intent);
            }
        });
//       while (board == null) {
//           getBoard();
//           try {
//               Thread.sleep(1000);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
//        }
            listView = root.findViewById(R.id.free_list);
            list_itemArrayList = new ArrayList<>();
//            for (int i = 0; i < board.length; i++) {
//                list_itemArrayList.add(new Board(board[i].getTitle(), board[i].getContents(), board[i].getId(), board[i].getAuthor()));
//
//            }
            list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("ddd", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("as", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("gggg", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("zz", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));
        list_itemArrayList.add(new Board("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1,new Author("a","a","a")));


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
//    Value {"content":[{"id":101,"title":"제목100","contents":"내용100","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":102,"title":"제목101","contents":"내용101","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":103,"title":"제목102","contents":"내용102","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":104,"title":"제목103","contents":"내용103","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":105,"title":"제목104","contents":"내용104","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":106,"title":"제목105","contents":"내용105","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":107,"title":"제목106","contents":"내용106","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":108,"title":"제목107","contents":"내용107","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":109,"title":"제목108","contents":"내용108","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":110,"title":"제목109","contents":"내용109","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":111,"title":"제목110","contents":"내용110","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":112,"title":"제목111","contents":"내용111","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":113,"title":"제목112","contents":"내용112","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":114,"title":"제목113","contents":"내용113","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":115,"title":"제목114","contents":"내용114","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":116,"title":"제목115","contents":"내용115","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":117,"title":"제목116","contents":"내용116","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":118,"title":"제목117","contents":"내용117","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":119,"title":"제목118","contents":"내용118","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}},{"id":120,"title":"제목119","contents":"내용119","author":{"name":"donggyu","email":"user@email.com"},"category":{"id":2,"name":"자유 게시판"}}],"pageable":{"sort":{"sorted":false,"unsorted":true,"empty":true},"offset":0,"pageSize":20,"pageNumber":0,"paged":true,"unpaged":false},"totalPages":5,"totalElements":100,"last":false,"number":0,"size":20,"sort":{"sorted":false,"unsorted":true,"empty":true},"numberOfElements":20,"first":true,"empty":false} of type org.json.JSONObject cannot be converted to JSONArray
//
//[{"id":1,"name":"학과 게시판"},{"id":2,"name":"자유 게시판"},{"id":3,"name":"학부생 게시판"},{"id":4,"name":"학년별 게시판"}]
//
//
//    GET /api/article/category
//
///api/article/category?size=10&page=0
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
//                    System.out.println(board[0].getId() + " " + board[0].getTitle() + " " + board[0].getContents() + " " + board[0].getAuthor().getName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });
    }

}

package com.example.capstonmaster.board.free_board;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.capstonmaster.MainActivity;
import com.example.capstonmaster.dto.ArticleVO;
import com.example.capstonmaster.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//https://material.io/resources/icons/?style=baseline
public class Freefragment extends Fragment {
    ListView listView;
    FreeListViewAdapter adapter;
    ArrayList<ArticleVO> list_itemArrayList;
    SharedPreferences sf;
    String userToken;

    int page;
    private int REQUEST_TEST = 1;

    ArticleVO[] board;
    public static String id;
    public static String category;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TEST) {
            if (resultCode == -1) {
                ((MainActivity)getActivity()).InitializeLayout();
                ((MainActivity)getActivity()).viewPager.setCurrentItem(((MainActivity)getActivity()).tabLayout.getSelectedTabPosition());
                System.out.println("onActivityResult 성공");
            }
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_free, container, false);

        sf = root.getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        userToken = sf.getString("userToken", "");

        page = 2;
        while (id == null) {
            try {
                Thread.sleep(500);
                System.out.println("id 무한대기중");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        getBoard();
        int c = 0;
        while (board == null) {
            try {
                if (c == 5) {
                    getBoard();
                }
                c++;
                Thread.sleep(500);
                System.out.println("board 무한대기중");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        listView = root.findViewById(R.id.free_list);
        list_itemArrayList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            list_itemArrayList.add(new ArticleVO(board[i].getId(), board[i].getTitle(), board[i].getContents(), board[i].getCategoryId(), board[i].getAuthor()));
        }
//        list_itemArrayList.add(new ArticleVO("안녕", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1, new Author("a", "a", "a")));


        adapter = new FreeListViewAdapter(root.getContext(), list_itemArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(root.getContext(), FreeListDetail.class);
                intent.putExtra("detail", list_itemArrayList.get(position));
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("articleId",position);
                startActivityForResult(intent, REQUEST_TEST);
            }
        });


        System.out.println("프래그먼트온크리뷰");
        FloatingActionButton fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "눌림", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), FreeWriteActivity.class);
                startActivityForResult(intent, REQUEST_TEST);
            }
        });

        final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(root.getContext(), "새로고침", Toast.LENGTH_LONG).show();
                getBoard();
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                list_itemArrayList.clear();
                for (int i = 0; i < board.length; i++) {
                    list_itemArrayList.add(new ArticleVO(board[i].getId(), board[i].getTitle(), board[i].getContents(), board[i].getCategoryId(), board[i].getAuthor()));
                }
                adapter = new FreeListViewAdapter(root.getContext(), list_itemArrayList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int position = firstVisibleItem + visibleItemCount;
                int limit = totalItemCount;
                System.out.println(firstVisibleItem + " " + visibleItemCount + " " + totalItemCount);
//&& !mSwipeRefreshLayout.isRefreshing()
                if (position >= limit && totalItemCount > 0 && !mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    getBoardAddition();
                    if (position / 10 >= page) {
                        try {
                            Thread.sleep(1000);
                            System.out.println("페이지대기중" + position + " " + page);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < board.length; i++) {
                        list_itemArrayList.add(new ArticleVO(board[i].getId(), board[i].getTitle(), board[i].getContents(), board[i].getCategoryId(), board[i].getAuthor()));
                        System.out.println(board[i].getTitle() + "~~~10개 추가");
                    }
//                    list_itemArrayList.add(new ArticleVO("추가1", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1, new Author("a", "a", "a")));
//                    list_itemArrayList.add(new ArticleVO("추가2", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1, new Author("a", "a", "a")));
//                    list_itemArrayList.add(new ArticleVO("추가3", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1, new Author("a", "a", "a")));
//                    list_itemArrayList.add(new ArticleVO("추가4", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1, new Author("a", "a", "a")));
//                    list_itemArrayList.add(new ArticleVO("추가5", "ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ", 1, new Author("a", "a", "a")));
                    Toast.makeText(root.getContext(), "새로고침", Toast.LENGTH_LONG).show();
                    adapter = new FreeListViewAdapter(root.getContext(), list_itemArrayList);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(position - 3);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("프래그정지");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("프래그디스뷰");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("프래그재개");
        adapter.notifyDataSetChanged();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.detach(this).attach(this).commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("프래그일시정지");

    }

    public void getBoardAddition() {

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer " + userToken)
                .url(getString(R.string.ip) + "/api/article/" + id + "?size=10&page=" + page)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
                System.out.println("페이지추가실패");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
//                    JSONObject jsonobject = new JSONObject(response.body().string());
//                    String m = jsonobject.optString("content");
//                    System.out.println(m+" 뭐라도찍어봐");
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    Gson gson = new Gson();
                    board = gson.fromJson(jsonArray.toString(), ArticleVO[].class);
//                    for (int i = 0; i < board.length; i++) {
//                        list_itemArrayList.add(new ArticleVO(board[i].getTitle(), board[i].getContents(), board[i].getId(), board[i].getAuthor()));
//                        System.out.println(board[i].getTitle()+"~~~10개 추가");
//                    }
                    System.out.println("page=" + page);
                    page += 1;

//                    System.out.println(board[0].getId() + " " + board[0].getTitle() + " " + board[0].getContents() + " " + board[0].getAuthor().getName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });
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
                System.out.println("getBoard실패");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonobject = jsonArray.getJSONObject(i);
//                        String contents = jsonobject.getString("contents");
//                        String title = jsonobject.getString("title");
//                        System.out.println(title + " " + contents);
//                    }

//                    JSONObject jsonobject = new JSONObject(response.body().string());
//                    String m = jsonobject.optString("content");
//                    System.out.println(m+" 뭐라도찍어봐");
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    Gson gson = new Gson();
                    board = gson.fromJson(jsonArray.toString(), ArticleVO[].class);
                    System.out.println(board[0].getId() + " " + board[0].getTitle() + " " + board[0].getContents() + " " + board[0].getAuthor().getName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });
    }

}

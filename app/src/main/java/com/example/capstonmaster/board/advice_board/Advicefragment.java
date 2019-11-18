package com.example.capstonmaster.board.advice_board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.capstonmaster.R;
import com.example.capstonmaster.Util.PreferenceUtil;
import com.example.capstonmaster.board.department_board.DepartListViewAdapter;
import com.example.capstonmaster.dto.ArticleVO;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Advicefragment extends Fragment {
    AdviceListViewAdapter adapter;
    ListView listView;
    ArrayList<ArticleVO> list_itemArrayList;
    String userToken;
    ArticleVO[] board;
    public static String id;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_advice, container, false);
        System.out.println("조언프래그먼트온크리에이트뷰");
        userToken =  PreferenceUtil.getInstance(root.getContext()).getStringExtra("userToken");

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
                c++;
                Thread.sleep(500);
                if (c % 5==0) {
                    getBoard();
                }
                System.out.println("board 무한대기중");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        listView = root.findViewById(R.id.advice_list);
        list_itemArrayList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            list_itemArrayList.add(new ArticleVO(board[i].getId(), board[i].getTitle(), board[i].getContents(), board[i].getCategoryId(), board[i].getAuthor()));
        }

        adapter = new AdviceListViewAdapter(root.getContext(), list_itemArrayList);
        listView.setAdapter(adapter);


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
                System.out.println("getBoard실패");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
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


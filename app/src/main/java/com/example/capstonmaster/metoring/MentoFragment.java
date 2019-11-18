package com.example.capstonmaster.metoring;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.capstonmaster.R;
import com.example.capstonmaster.Util.PreferenceUtil;
import com.example.capstonmaster.dto.MentoResponseVO;
import com.example.capstonmaster.dto.MentoVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MentoFragment extends Fragment {

    RecyclerView recyclerView;
    MentoRecycleAdapter adapter;
    ArrayList<MentoResponseVO> mentoList;
    String userToken;
    MentoResponseVO[] vo;
    LinearLayoutManager layoutManager;
    private int REQUEST_CODE=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View root=inflater.inflate(R.layout.fragment_mento, container, false);
        // Inflate the layout for this fragment
        userToken =  PreferenceUtil.getInstance(root.getContext()).getStringExtra("userToken");
        Button writeButton = root.findViewById(R.id.mento_write);
        recyclerView = root.findViewById(R.id.mento_recycle);
        mentoList=new ArrayList<>();

        getMentoringList();
        int c = 0;
        while (vo==null) {
            try {
                c++;
                Thread.sleep(500);
                if (c %5==0) {
                    getMentoringList();
                }
                System.out.println("getMentoringList 대기중");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MentoRecycleAdapter(mentoList);
        recyclerView.setAdapter(adapter);

       final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.mento_swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMentoringList();
                mentoList.clear();
                for (int i = 0; i < vo.length; i++) {
                    mentoList.add(vo[i]);
                }
                layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                adapter=new MentoRecycleAdapter(mentoList);
                adapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MentoWriteActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        return root;
    }
    public void getMentoringList() {

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer " + userToken)
                .url(getString(R.string.ip) + "/api/mentoring")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
                System.out.println("getMentoringList실패");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());

                    System.out.println(jsonArray.optString(1)+"뭐라도찍어봐");
//                    Gson gson = new GsonBuilder()
////                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                            .create();
//                    Gson gson = new Gson();
                    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                        @Override public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); } }).create();

                    vo = gson.fromJson(jsonArray.toString(),MentoResponseVO[].class);
                    if(vo.length==0){

                    }else {
                        System.out.println(vo[0].getTitle() + " " + vo[0].getContent() + " " + vo[0].getStartTime()+" "+vo[0].getIntroduce());
                        for (int i = 0; i < vo.length; i++) {
//                        mentoList.add(new MentoVO(vo[i].getContent(),vo[i].getEndTime(),vo[i].getIntroduce(),vo[i].getStartTime(),vo[i].getTitle(),vo[i].getId()));
                            mentoList.add(vo[i]);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("파싱에러??");
                }
            }
        });
    }
}

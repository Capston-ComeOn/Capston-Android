package com.example.capstonmaster.metoring;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.capstonmaster.R;
import com.example.capstonmaster.dto.MentoVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
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
    ArrayList<MentoVO> mentoList;
    SharedPreferences sf;
    String userToken;
    MentoVO[] vo;
    LinearLayoutManager layoutManager;
    private int REQUEST_CODE=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_mento, container, false);
        // Inflate the layout for this fragment
        sf = root.getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        userToken = sf.getString("userToken", "");
        Button writeButton = root.findViewById(R.id.mento_write);
        recyclerView = root.findViewById(R.id.mento_recycle);
        mentoList=new ArrayList<>();

        getMentoringList();
        int c = 0;
        while (mentoList.size() == 0) {
            try {
                Thread.sleep(500);
                if (c == 5) {
                    getMentoringList();
                }
                c++;
                System.out.println("getMentoringList 대기중");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MentoRecycleAdapter(mentoList);
        recyclerView.setAdapter(adapter);

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
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .create();
                    vo = gson.fromJson(jsonArray.toString(),MentoVO[].class);
                    System.out.println(vo[0].getTitle()+" "+vo[0].getContent()+" "+vo[0].getStartTime());
                    for (int i=0; i<vo.length; i++) {
//                        mentoList.add(new MentoVO(vo[i].getContent(),vo[i].getEndTime(),vo[i].getIntroduce(),vo[i].getStartTime(),vo[i].getTitle(),vo[i].getId()));
                        mentoList.add(vo[i]);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("파싱에러??");
                }
            }
        });
    }
}

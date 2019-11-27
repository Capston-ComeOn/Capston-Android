package com.example.capstonmaster.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.example.capstonmaster.MainActivity;
import com.example.capstonmaster.R;
import com.example.capstonmaster.Util.PreferenceUtil;
import com.example.capstonmaster.dto.MessageVO;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MessageActivity extends AppCompatActivity {
    String userToken;
    MessageVO[] list;
    ListView listView;
    MessageAdapter adapter;
    Handler handler=new Handler();
    ArrayList<MessageVO> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        userToken =  PreferenceUtil.getInstance(this).getStringExtra("userToken");
        Toolbar toolbar = findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);
        setTitle("쪽지함");
        listView=findViewById(R.id.message_list);
        getMessageList();

    }
    public void getMessageList() {
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer " + userToken)
                .url(getString(R.string.ip) + "/api/message")
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
//                    String a=response.body().string();
                    System.out.println(jsonArray.toString()+"~~~~~~~~~~~~");
                    list = gson.fromJson(jsonArray.toString(), MessageVO[].class);
//                    list = gson.fromJson(a, MessageVO[].class);
                    System.out.println(list[0].getContent() + "내용~~~");
                    for (int i = 0; i < list.length; i++) {
                        if(list[i].getTo().getName().equals(MainActivity.userName))
                        arrayList.add(list[i]);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter=new MessageAdapter(getApplicationContext(),arrayList);
                            listView.setAdapter(adapter);
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

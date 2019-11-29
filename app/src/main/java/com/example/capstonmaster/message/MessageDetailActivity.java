package com.example.capstonmaster.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

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

public class MessageDetailActivity extends AppCompatActivity {
    String userToken;
    MessageVO[] list;
    ListView listView;
    MessageDetailAdapter adapter;
    Handler handler=new Handler();
    ArrayList<MessageVO> arrayList=new ArrayList<>();
    long fromId;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        userToken = PreferenceUtil.getInstance(this).getStringExtra("userToken");
        listView=findViewById(R.id.messageDe_list);
        Toolbar toolbar=findViewById(R.id.mdtoolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        fromId=intent.getLongExtra("fromId",0);
        name=intent.getStringExtra("fromName");
        setTitle("'"+name+"'님과의 쪽지함");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getMessageDetailList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_send, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                Intent intent=new Intent(this, MessageWriteActivity.class);
                intent.putExtra("fromId",fromId);
                intent.putExtra("from",arrayList.get(0).getFrom());
                intent.putExtra("to",arrayList.get(0).getTo());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getMessageDetailList() {
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer " + userToken)
                .url(getString(R.string.ip) + "/api/message/"+fromId)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
                System.out.println("getMessageDetail실패");
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
                            adapter=new MessageDetailAdapter(getApplicationContext(),arrayList);
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

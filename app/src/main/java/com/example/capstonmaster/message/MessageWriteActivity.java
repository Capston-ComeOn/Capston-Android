package com.example.capstonmaster.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstonmaster.R;
import com.example.capstonmaster.Util.PreferenceUtil;
import com.example.capstonmaster.dto.ArticleVO;
import com.example.capstonmaster.dto.MessageVO;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageWriteActivity extends AppCompatActivity {
    String userToken;
    EditText editText;
    long fromId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_write);
        userToken = PreferenceUtil.getInstance(this).getStringExtra("userToken");
        Toolbar toolbar=findViewById(R.id.mdwtoolbar);
        toolbar.setTitle("쪽지 보내기");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_delete);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editText=findViewById(R.id.message_write);
        Intent intent=getIntent();
        fromId=intent.getLongExtra("fromId",0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_mes_write, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_mes_write:
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String json = gson.toJson(new MessageVO(editText.getText().toString()));
                    final Request request = new Request.Builder()
                            .header(getString(R.string.Authorization), "Bearer " + userToken)
                            .url(getString(R.string.ip) + "/api/message/" + fromId)
                            .post(RequestBody.create(MediaType.parse("application/json"), json))
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(getApplicationContext(), "쪽지등록실패", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println("쪽지성공?" + response.body() + response.message());
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

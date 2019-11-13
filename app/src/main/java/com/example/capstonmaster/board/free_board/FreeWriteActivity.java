package com.example.capstonmaster.board.free_board;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstonmaster.MainActivity;
import com.example.capstonmaster.R;
import com.example.capstonmaster.dto.ArticleVO;
import com.example.capstonmaster.dto.Author;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FreeWriteActivity extends AppCompatActivity {
    EditText title;
    EditText contents;
    SharedPreferences sf;
    String userToken;
    Intent intent;
    long articleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_write);
        Toolbar toolbar = findViewById(R.id.w_toolbar);
        toolbar.setTitle("글 쓰기");
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_delete);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title = findViewById(R.id.free_title);
        contents = findViewById(R.id.free_contents);
        sf = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        userToken = sf.getString("userToken", "");

        intent = getIntent();
        articleId = intent.getLongExtra("articleId", 0);
        if (articleId != 0) {
            title.setText(intent.getStringExtra("title"));
            contents.setText(intent.getStringExtra("contents"));
            System.out.println(articleId +"articleId 수정값받아온거~~~~~~~~~~~");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_write, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_write:
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String json = gson.toJson(new ArticleVO(title.getText().toString(), contents.getText().toString()));
                if (articleId == 0) {
                    final Request request = new Request.Builder()
                            .header(getString(R.string.Authorization), "Bearer " + userToken)
                            .url(getString(R.string.ip) + "/api/article/" + 2)
                            .post(RequestBody.create(MediaType.parse("application/json"), json))
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(getApplicationContext(), "글등록실패", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println("글등록성공?" + response.body() + response.message());
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
                } else {
                    final Request request = new Request.Builder()
                            .header(getString(R.string.Authorization), "Bearer " + userToken)
                            .url(getString(R.string.ip) + "/api/article/" + articleId)
                            .put(RequestBody.create(MediaType.parse("application/json"), json))
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(getApplicationContext(), "글수정실패", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println("글수정성공?" + response.body() + response.message());
                            Intent intent=new Intent(getApplicationContext(),FreeListDetail.class);
                            intent.putExtra("title",title.getText().toString());
                            intent.putExtra("contents",contents.getText().toString());
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    });

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

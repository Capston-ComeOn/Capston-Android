package com.example.capstonmaster.board.free_board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    Button submit;
    SharedPreferences sf;
    String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_write);

        title = findViewById(R.id.free_title);
        contents = findViewById(R.id.free_contents);
        submit = findViewById(R.id.free_submit);
        sf = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        userToken = sf.getString("userToken", "");

//        JSONObject jsonInput = new JSONObject();

//        try {
//            jsonInput.put("id", id);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return;
//        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String json = gson.toJson(new ArticleVO(title.getText().toString(),contents.getText().toString(),2));
//                RequestBody requestBody = new FormBody.Builder()
////                        .add("grant_type", "")
//                        .add("categoryId", Freefragment.id)
//                        .add("contents",contents.getText().toString())
//                        .add("title",title.getText().toString())
//                        .build();
//                final Request request = new Request.Builder()
////                        .header(getString(R.string.Authorization), "Bearer " + userToken)
////                        .url(getString(R.string.ip)+"/api/article")
////                        .post(requestBody)
////                        .build();
                final Request request = new Request.Builder()
                        .header(getString(R.string.Authorization), "Bearer " + userToken)
                        .url(getString(R.string.ip) + "/api/article")
                        .post(RequestBody.create(MediaType.parse("application/json"), json))
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(getApplicationContext(), "글등록실패", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println("글등록성공?"+response.body().toString());
                        finish();
                    }
                });

            }
        });
    }
}

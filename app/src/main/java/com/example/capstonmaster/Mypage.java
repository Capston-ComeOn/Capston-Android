package com.example.capstonmaster;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstonmaster.dto.Author;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Mypage extends AppCompatActivity {

  String userToken;
  SharedPreferences sf;
  TextView mypage_email;
  TextView mypage_name;
  TextView mypage_std_id;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mypage);

    sf = getSharedPreferences("pref", MODE_PRIVATE);
    userToken = sf.getString("userToken", "");
    mypage_email = findViewById(R.id.mypage_email);
    mypage_name = findViewById(R.id.mypage_name);
    mypage_std_id = findViewById(R.id.mypage_std_id);

    getAccount();
  }

  public void getAccount() {
    //System.out.println("=======" + access_token);
    OkHttpClient client = new OkHttpClient();
    final Request request = new Request.Builder()
      .header(getString(R.string.Authorization), "Bearer " + userToken)
      .url(getString(R.string.ip) + "/api/accounts/login")
      .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        System.out.println(e);
        System.out.println("getAccount 실패");
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        try {
          Gson gson = new Gson();
          Author account = gson.fromJson(response.body().string(), Author.class);
          mypage_email.setText(account.getEmail());
          mypage_name.setText(account.getName());
          mypage_std_id.setText(account.getStudentId());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}

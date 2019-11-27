package com.example.capstonmaster;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.capstonmaster.Util.PreferenceUtil;
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
  TextView mypage_email;
  TextView mypage_name;
  TextView mypage_std_id;
  ImageView mypage_img;
  Author account;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mypage);

    userToken =  PreferenceUtil.getInstance(getApplicationContext()).getStringExtra("userToken");
    mypage_email = findViewById(R.id.mypage_email);
    mypage_name = findViewById(R.id.mypage_name);
    mypage_std_id = findViewById(R.id.mypage_std_id);
    mypage_img = findViewById(R.id.mypage_image);
    getAccount();
    int c=0;
    while (account==null ) {
      try {
        c++;
        Thread.sleep(500);
        if (c % 5==0) {
          getAccount();
        }
        System.out.println("getImgSrc 대기중");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Glide.with(this).load(account.getImgSrc()).override(300,200).error(R.drawable.circle).into(mypage_img);
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
          account = gson.fromJson(response.body().string(), Author.class);

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

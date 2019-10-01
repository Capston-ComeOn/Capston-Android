package com.example.capstonmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

  TextView idText;
  TextView passText;
  String access_token;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    Button btn_login = (Button) findViewById(R.id.btn_login) ;
    Button btn_register = (Button) findViewById(R.id.btn_register) ;
    idText= (TextView)findViewById(R.id.et_id);
    passText= (TextView)findViewById(R.id.et_pass);
    btn_login.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.out.println("액세스 토큰 발급요청");
        getAccessToken();
      }
    });
    btn_register.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.out.println("리스트 호출");
        getBoardList();
      }
    });
  }

  public void getBoardList(){

    OkHttpClient client = new OkHttpClient();

    final Request request = new Request.Builder()
      .header(getString(R.string.Authorization), "Bearer "+access_token)
      .url(getString(R.string.ip)+"/api/article")
      .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(final Request request, final IOException e) {
        System.out.println(e);
        System.out.println("실패");
      }

      @Override
      public void onResponse(Response response) throws IOException {
        try {
          JSONObject jsonObject = new JSONObject(response.body().string());
          final String message = jsonObject.toString(5);
          System.out.println(message);
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    });
  }

  public void getAccessToken(){
    OkHttpClient client = new OkHttpClient();
    RequestBody requestBody = new FormEncodingBuilder()
      .add("grant_type", "password")
      .add("username",idText.getText().toString())
      .add("password",passText.getText().toString())
      .build();
    final Request request = new Request.Builder()
      .header(getString(R.string.Authorization), Credentials.basic("id","secret"))
      .url(getString(R.string.ip)+"/oauth/token")
      .post(requestBody)
      .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(final Request request, final IOException e) {
        System.out.println(e);
        System.out.println("실패");
      }

      @Override
      public void onResponse(Response response) throws IOException {
        try {
          JSONObject jsonObject = new JSONObject(response.body().string());
          access_token = jsonObject.get("access_token").toString();
          System.out.println(access_token);
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    });
  }
}

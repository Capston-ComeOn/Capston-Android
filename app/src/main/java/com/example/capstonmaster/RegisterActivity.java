package com.example.capstonmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
  TextView RgidText;
  TextView RgpassText;
  TextView RgnameText;
  TextView RgemailText;
  String access_token;
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  //private EditText et_id, et_pass, et_name, et_age;
  //private Button btn_register;

  @Override
  protected void onCreate(Bundle savedInstanceState) { //액티비티 시작시 처음으로 실행되는 생명주기
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    Button Rgbtn_register = (Button) findViewById(R.id.Rgbtn_register);
    RgidText = (TextView) findViewById(R.id.rg_id);
    RgpassText = (TextView) findViewById(R.id.rg_pass);
    RgnameText = (TextView) findViewById(R.id.rg_name);
    RgemailText = (TextView) findViewById(R.id.rg_email);

    Rgbtn_register.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.out.println("회원등록.");
        putRegister();
      }
    });
  }

  public void putRegister() {

    OkHttpClient client = new OkHttpClient();
    JSONObject json = new JSONObject();
    RequestBody requestBody = new FormBody.Builder()
      .add("grant_type", "password")
      .add("email", RgidText.getText().toString())
      .add("name", RgnameText.getText().toString())
      .add("password", RgpassText.getText().toString())
      .add("id", RgidText.getText().toString())
      .build();
    final Request request = new Request.Builder()
      .header(getString(R.string.Authorization), Credentials.basic("id", "secret"))
      .url(getString(R.string.ip) + "/oauth/token")
      .put(requestBody)
      .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        System.out.println(e);
        System.out.println("실패");
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        try {
          JSONObject jsonObject = new JSONObject(response.body().string());
          access_token = jsonObject.get("access_token").toString();
          System.out.println("성공");
          System.out.println(access_token);
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    });
  }
}

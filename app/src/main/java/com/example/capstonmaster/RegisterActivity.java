package com.example.capstonmaster;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstonmaster.dto.AccountDto;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
  TextView RgpassText;
  TextView RglastnameText;
  TextView RgfirstnameText;
  TextView RgemailText;
  TextView RgrepassText;

  String access_token;
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  //private EditText et_id, et_pass, et_name, et_age;
  //private Button btn_register;

  @Override
  protected void onCreate(Bundle savedInstanceState) { //액티비티 시작시 처음으로 실행되는 생명주기
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    Button Rgbtn_register = (Button) findViewById(R.id.Rgbtn_register);
    RgpassText = (TextView) findViewById(R.id.rg_pass);
    RglastnameText = (TextView) findViewById(R.id.rg_lastname);
    RgfirstnameText = (TextView) findViewById(R.id.rg_firstname);
    RgemailText = (TextView) findViewById(R.id.rg_email);
    RgrepassText = (TextView) findViewById(R.id.rg_repass);

    Rgbtn_register.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.out.println("회원등록 버튼을 눌렀습니다.");
        System.out.println(RgpassText.getText());
        System.out.println(RgrepassText.getText());
        if(RgpassText.getText().equals(RgrepassText.getText()) ){
          System.out.println("비밀번호가 동일하지 않습니다. 다시 입력해주세요.");
        }else {
          postRegister();
        }
      }
    });
  }

  public void postRegister() {
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    String email = RgemailText.getText().toString();
    String lastname = RglastnameText.getText().toString();
    String firstname = RgfirstnameText.getText().toString();
    String password = RgpassText.getText().toString();
    String json = gson.toJson(new AccountDto(email,password,lastname+firstname));


    final Request request = new Request.Builder()
            .url(getString(R.string.ip) +"/api/accounts/join")
            .post(RequestBody.create(MediaType.parse("application/json"), json))
            .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Log.d("error", "Connect Server Error is " + e.toString());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        Log.d("aaaa", "Response Body is " + response.body().string());
      }
    });
  }
}
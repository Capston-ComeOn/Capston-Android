package com.example.capstonmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstonmaster.Util.PreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

  TextView idText;
  TextView passText;
  String access_token;
  Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    context=this.getApplicationContext();
//    if(!PreferenceUtil.getInstance(getApplicationContext()).getStringExtra("userToken").equals("")){
//      System.out.println(PreferenceUtil.getInstance(getApplicationContext()).getStringExtra("userToken"));
//      Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//      startActivity(intent);
//    }
    Button btn_login = (Button) findViewById(R.id.btn_login) ;
    Button btn_register = (Button) findViewById(R.id.btn_register) ;
    idText= (TextView)findViewById(R.id.et_id);
    passText= (TextView)findViewById(R.id.et_pass);

    btn_login.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.out.println("액세스 토큰 발급요청");
        getAccessToken();
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
          int c = 0;
          while (access_token==null) {
              try {
                  Thread.sleep(500);
                  if (c == 5) {
                      getAccessToken();
                      break;
                  }
                  c++;
                  System.out.println("access_token 대기중");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }

        if(access_token!=null) {
          Intent intent = new Intent(LoginActivity.this, MainActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(intent);
        }else{
          Toast.makeText(LoginActivity.this, "토큰없음", Toast.LENGTH_SHORT).show();
        }
      }
    });

    btn_register.setOnClickListener(new Button.OnClickListener(){
      public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
      }
    });


  }



  public void getAccessToken(){
    OkHttpClient client = new OkHttpClient();
    RequestBody requestBody = new FormBody.Builder()
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
      public void onFailure(Call call, IOException e) {
        System.out.println(e);
        System.out.println("getacesstoken실패");
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        try {
          JSONObject jsonObject = new JSONObject(response.body().string());
          access_token = jsonObject.get("access_token").toString();

          PreferenceUtil.getInstance(context).putStringExtra("userToken",access_token);

//          sf= getSharedPreferences("pref", MODE_PRIVATE);
//          SharedPreferences.Editor editor=sf.edit();
//          editor.putString("userToken", access_token);
//          editor.commit();

          System.out.println("성공");
          System.out.println(access_token);
        } catch (JSONException e) {
          System.out.println("토큰가져오기응답에서오류");
          e.printStackTrace();
        }
      }
    });
  }
}

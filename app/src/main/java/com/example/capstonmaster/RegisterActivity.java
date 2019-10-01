package com.example.capstonmaster;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

  private EditText et_id, et_pass, et_name, et_age;
  private Button btn_register;

  @Override
  protected void onCreate(Bundle savedInstanceState) { //액티비티 시작시 처음으로 실행되는 생명주기
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    //아이디 값 찾아주기
    et_id = findViewById(R.id.et_pass);
    et_pass = findViewById(R.id.et_pass);
    et_name = findViewById(R.id.et_name);
    et_age = findViewById(R.id.et_age);

  }
}

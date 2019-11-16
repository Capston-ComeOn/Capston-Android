package com.example.capstonmaster.metoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonmaster.R;
import com.example.capstonmaster.dto.ArticleVO;
import com.example.capstonmaster.dto.Author;
import com.example.capstonmaster.dto.MentoInfo;
import com.example.capstonmaster.dto.MentoVO;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MentoWriteActivity extends AppCompatActivity {
    String title;
    String content;
    LocalDateTime start;
    LocalDateTime end;
    String target;
    String metting;
    String etc;
    EditText e_title, e_content, e_metting, e_etc;
    CheckBox a1, a2, a3, a4;
    CalendarView calendarView;
    Button register;
    SharedPreferences sf;
    String userToken;
    Author account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mento_write);
        sf = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        userToken = sf.getString("userToken", "");
        e_title = findViewById(R.id.mento_w_title);
        e_content = findViewById(R.id.mento_w_content);
        e_metting = findViewById(R.id.mento_w_metting);
        e_etc = findViewById(R.id.mento_w_etc);
        a1 = (CheckBox) findViewById(R.id.mento_w_1);
        a2 = findViewById(R.id.mento_w_2);
        a3 = findViewById(R.id.mento_w_3);
        a4 = findViewById(R.id.mento_w_4);
        calendarView = findViewById(R.id.mento_w_date);
        register = findViewById(R.id.mento_w_register);
        getAccount();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String s = year + "-" + (month + 1) + "-" + dayOfMonth + " 00:00:00.0000";
                String e = year + "-" + (month + 1) + "-" + (dayOfMonth+ 6) + " 00:00:00.0000";
//                ""+year+"/"+(month+1)+"/" +dayOfMonth
//                try {
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                    Date parsedDate = dateFormat.parse(s);
//                    start = new java.sql.Timestamp(parsedDate.getTime());
//                    parsedDate = dateFormat.parse(e);
//                    end = new java.sql.Timestamp(parsedDate.getTime());
                    System.out.println(s);
                    System.out.println(e);
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    start=LocalDateTime.of(year,(month + 1),dayOfMonth,0,0,0,0000);
              end=  LocalDateTime.of(year,(month + 1),(dayOfMonth+6),0,0,0,0000);
                    System.out.println(end);
//                    end=java.sql.Timestamp.valueOf(e);
                    System.out.println(e);
//                } catch (Exception e1) {
//                    System.out.println("time 예외");
//                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                if (a1.isChecked()) {
                    target += a1.getText().toString();
                }

                String json = gson.toJson(new MentoVO(e_content.getText().toString(),end ,new MentoInfo(e_etc.getText().toString()
                        ,account.getName(),e_metting.getText().toString(),target),start,e_title.getText().toString()));
                final Request request = new Request.Builder()
                        .header(getString(R.string.Authorization), "Bearer " + userToken)
                        .url(getString(R.string.ip) + "/api/mentoring")
                        .post(RequestBody.create(MediaType.parse("application/json"), json))
                        .build();
                client.newCall(request).
                        enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Toast.makeText(getApplicationContext(), "멘토링등록실패", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                System.out.println("멘토링등록성공?" + response.body() + response.message());
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
            }
        });

    }
    void getAccount() {
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

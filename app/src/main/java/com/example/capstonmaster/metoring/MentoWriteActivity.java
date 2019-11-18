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
import com.example.capstonmaster.Util.PreferenceUtil;
import com.example.capstonmaster.dto.ArticleVO;
import com.example.capstonmaster.dto.Author;
import com.example.capstonmaster.dto.MentoInfo;
import com.example.capstonmaster.dto.MentoVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
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
    LocalDateTime start;
    LocalDateTime end;
    EditText e_title, e_content, e_metting, e_etc,e_target,e_mento;
    CalendarView calendarView;
    Button register;
    String userToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mento_write);

        userToken =  PreferenceUtil.getInstance(getApplicationContext()).getStringExtra("userToken");
        e_title = findViewById(R.id.mento_w_title);
        e_content = findViewById(R.id.mento_w_content);
        e_metting = findViewById(R.id.mento_w_metting);
        e_etc = findViewById(R.id.mento_w_etc);
       e_target=findViewById(R.id.mento_w_target);
       e_mento=findViewById(R.id.mento_w_mento);
        calendarView = findViewById(R.id.mento_w_date);
        register = findViewById(R.id.mento_w_register);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String s = year + "-" + (month + 1) + "-" + dayOfMonth + " 00:00:00";
                String e = year + "-" + (month + 1) + "-" + (dayOfMonth+ 6) + " 00:00:00";
//                ""+year+"/"+(month+1)+"/" +dayOfMonth
//                try {
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                Date parsedDate = null;
//                try {
//                    parsedDate = dateFormat.parse(s);
//                } catch (ParseException ex) {
//                    ex.printStackTrace();
//                }
//                start = new Timestamp(parsedDate.getTime());
//                try {
//                    parsedDate = dateFormat.parse(e);
//                } catch (ParseException ex) {
//                    ex.printStackTrace();
//                }
//                end = new Timestamp(parsedDate.getTime());

                    System.out.println(s);
                    System.out.println(e);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    start=LocalDateTime.of(year,(month + 1),dayOfMonth,0,0,0,0000);
              end=  LocalDateTime.of(year,(month + 1),(dayOfMonth+6),0,0,0,0000);
                System.out.println(end);
                System.out.println(start);
//                } catch (Exception e1) {
//                    System.out.println("time 예외");
//                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(src));
                    }
                }).create();

                String json = gson.toJson(new MentoVO(e_content.getText().toString(),end ,new MentoInfo(e_etc.getText().toString()
                        ,e_mento.getText().toString(),e_metting.getText().toString(),e_target.getText().toString()),start,e_title.getText().toString()));
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
                                System.out.println("멘토링등록실패");
                                e.printStackTrace();
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


}

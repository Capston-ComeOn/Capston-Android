package com.example.capstonmaster.metoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonmaster.R;
import com.example.capstonmaster.Util.PreferenceUtil;
import com.example.capstonmaster.dto.MentoResponseVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MentoDetailActivity extends AppCompatActivity {
    MentoResponseVO mento=null;
    TextView title,sid,content;
    TextView mentoEx,etc,target,meeting,menteeC;
    TextView startEnd;
    Button register;
    String userToken;
    int index;
    MentoResponseVO vo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mento_detail);
        userToken =  PreferenceUtil.getInstance(this).getStringExtra("userToken");
        mento=new MentoResponseVO();
        Intent intent=getIntent();
        index=intent.getIntExtra("index",0);
        System.out.println(index+" index는~~~~~~~");
        mento= (MentoResponseVO) intent.getSerializableExtra("detail");
        System.out.println(mento.getContent()+" "+mento.getTitle());
        title=findViewById(R.id.md_title);
        sid=findViewById(R.id.md_fullName);
        startEnd=findViewById(R.id.md_time);
        menteeC=findViewById(R.id.md_mentee);
        mentoEx=findViewById(R.id.md_intro);
        content=findViewById(R.id.md_meeting);
        target=findViewById(R.id.md_target);
        etc=findViewById(R.id.md_etc);
        title.setText(mento.getTitle());
        sid.setText(mento.getMento().getStudentId()+" "+mento.getMento().getName());
        startEnd.setText("모집기간 : "+mento.getStartTime()+" "+mento.getEndTime());
        if(mento.getMentees()==null)menteeC.setText("수강인원 : 0 명");
        else menteeC.setText("수강인원 : "+ mento.getMentees().length+" 명");
        mentoEx.setText(mento.getIntroduce().getMento());
        content.setText(mento.getIntroduce().getMetting());
        target.setText(mento.getIntroduce().getTarget());
        etc.setText(mento.getIntroduce().getEtc());
        register=findViewById(R.id.md_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                registerMentoring();
                getMentoringDetail();
            }
        });
    }
    public void registerMentoring() {

        OkHttpClient client = new OkHttpClient();
String json=null;
        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer " + userToken)
                .url(getString(R.string.ip) + "/api/mentoring/"+index)
                .post(RequestBody.create(MediaType.parse("application/json"), json))
                .build();
        client.newCall(request).
                enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(getApplicationContext(), "멘토링신청실패", Toast.LENGTH_LONG).show();
                        System.out.println("멘토링등록실패");
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println("멘토링신청성공?" + response.body() + response.message());

                        finish();
                    }
                });
    }
    public void getMentoringDetail() {
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer " + userToken)
                .url(getString(R.string.ip) + "/api/mentoring/"+index)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
                System.out.println("getMentoringDetail실패");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string()+"-----------------");
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(response.body().string());
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println(jsonArray.optString(1)+"뭐라도찍어봐");
                    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                        @Override public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); } }).create();

                    vo = gson.fromJson(jsonArray.toString(),MentoResponseVO.class);

                        System.out.println(vo.getTitle() + " " + vo.getContent() + " " + vo.getStartTime()+" "+vo.getIntroduce());

            }
        });
    }
}

package com.example.capstonmaster.metoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.capstonmaster.R;
import com.example.capstonmaster.dto.MentoResponseVO;

import java.util.ArrayList;

public class MentoDetailActivity extends AppCompatActivity {
    MentoResponseVO mento=null;
    TextView title,sid,content;
    TextView mentoEx,etc,target,meeting,menteeC;
    TextView startEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mento_detail);
        mento=new MentoResponseVO();
        Intent intent=getIntent();
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
    }
}

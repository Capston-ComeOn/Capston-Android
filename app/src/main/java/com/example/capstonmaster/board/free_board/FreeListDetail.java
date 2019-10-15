package com.example.capstonmaster.board.free_board;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.capstonmaster.RegisterActivity;
import com.example.capstonmaster.dto.List_item;
import com.example.capstonmaster.R;

import org.w3c.dom.Text;

public class FreeListDetail extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_free_list_detail);
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitleTextColor(Color.parseColor("#B0BEC5"));
    toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    //getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);

    TextView nickname=findViewById(R.id.nickname_detail);
    TextView title =findViewById(R.id.title_detail);
    TextView content = findViewById(R.id.content_detail);
    //TextView commentwindow = findViewById(R.id.commentWindow);
    Button commentButton = (Button) findViewById(R.id.commentButton);

    Intent intent = getIntent();
    List_item list=(List_item) intent.getSerializableExtra("detail");

    nickname.setText(list.getNickname());
    title.setText(list.getTitle());
    content.setText(list.getContent());

    commentButton.setOnClickListener(new Button.OnClickListener(){
      public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
      }
    });
  }



}

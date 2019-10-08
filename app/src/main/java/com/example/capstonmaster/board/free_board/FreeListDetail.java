package com.example.capstonmaster.board.free_board;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.capstonmaster.dto.List_item;
import com.example.capstonmaster.R;

public class FreeListDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_list_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);

        TextView nickname=findViewById(R.id.nickname_detail);
        TextView title =findViewById(R.id.title_detail);
        TextView content = findViewById(R.id.content_detail);

        Intent intent = getIntent();
         List_item list=(List_item) intent.getSerializableExtra("detail");

        nickname.setText(list.getNickname());
        title.setText(list.getTitle());
        content.setText(list.getContent());
    }

}


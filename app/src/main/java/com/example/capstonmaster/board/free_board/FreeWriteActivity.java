package com.example.capstonmaster.board.free_board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.capstonmaster.R;

public class FreeWriteActivity extends AppCompatActivity {
    EditText title;
    EditText contents;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_write);

        title= findViewById(R.id.free_title);
        contents=findViewById(R.id.free_contents);
        submit = findViewById(R.id.free_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

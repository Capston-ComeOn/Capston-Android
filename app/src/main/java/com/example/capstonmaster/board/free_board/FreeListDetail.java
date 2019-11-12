package com.example.capstonmaster.board.free_board;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.capstonmaster.MainActivity;
import com.example.capstonmaster.RegisterActivity;
import com.example.capstonmaster.dto.ArticleVO;
import com.example.capstonmaster.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FreeListDetail extends AppCompatActivity {
    SharedPreferences sf;
    String userToken;
    ArticleVO list;
    long articleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_list_detail);
        Toolbar toolbar = findViewById(R.id.fd_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sf = getSharedPreferences("pref", MODE_PRIVATE);
        userToken = sf.getString("userToken", "");

        TextView nickname = findViewById(R.id.nickname_detail);
        TextView title = findViewById(R.id.title_detail);
        TextView content = findViewById(R.id.content_detail);
        //TextView commentwindow = findViewById(R.id.commentWindow);
        Button commentButton = (Button) findViewById(R.id.commentButton);

        Intent intent = getIntent();
        list = (ArticleVO) intent.getSerializableExtra("detail");
        articleId= list.getId();
        nickname.setText(list.getAuthor().getName());
        title.setText(list.getTitle());
        content.setText(list.getContents());

        commentButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
//        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
//        startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_freedetail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                Toast.makeText(getApplicationContext(), " 수정", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),FreeWriteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("title",list.getTitle());
                intent.putExtra("contents",list.getContents());
                intent.putExtra("articleId",articleId);
                startActivity(intent);
                return true;
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("삭제하시겠습니까?");
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteBoardList();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void deleteBoardList() {
        OkHttpClient client = new OkHttpClient();
        System.out.println("========================");
        System.out.println(articleId);
        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer " +userToken )
                .url(getString(R.string.ip) + "/api/article/"+articleId)
                .delete()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "글삭제실패", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("글삭제성공?" + response.body().toString());
//                Intent intent=new Intent(getApplicationContext(),Freefragment.class);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}

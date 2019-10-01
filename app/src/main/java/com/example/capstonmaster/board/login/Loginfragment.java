package com.example.capstonmaster.board.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.capstonmaster.R;

public class Loginfragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frament_login, container, false);
//      Button btn_register = findViewById(R.id.btn_register);
//      회원가입 버튼을 클릭 시 수행
//      btn_register.setOnClickListener(new View.OnClickListener(){
//      @Override
//      public void onClick(View view){
//      Intent intent = new Intent(Loginfragment.this, RegisterActivity.class);
//      }
//      });
        return root;
    }
}

package com.example.capstonmaster.board.advice_board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.capstonmaster.R;

public class Advicefragment extends Fragment {
    private AdviceViewModel noticeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        noticeViewModel =
                ViewModelProviders.of(this).get(AdviceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_advice, container, false);
        
        return root;
    }
}

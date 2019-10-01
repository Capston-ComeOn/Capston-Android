package com.example.capstonmaster.board.free_board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.capstonmaster.R;

public class Freefragment extends Fragment {
    private FreeViewModel freeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        freeViewModel =
                ViewModelProviders.of(this).get(FreeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_free, container, false);
        final TextView textView = root.findViewById(R.id.freeboard);
        freeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

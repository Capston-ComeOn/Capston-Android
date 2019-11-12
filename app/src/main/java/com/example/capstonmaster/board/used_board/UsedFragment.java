package com.example.capstonmaster.board.used_board;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.capstonmaster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsedFragment extends Fragment {


    public UsedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_used, container, false);
    }

}

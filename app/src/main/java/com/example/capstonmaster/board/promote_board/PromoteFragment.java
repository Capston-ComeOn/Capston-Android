package com.example.capstonmaster.board.promote_board;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.capstonmaster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromoteFragment extends Fragment {


    public PromoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promote, container, false);
    }

}

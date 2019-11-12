package com.example.capstonmaster.board.department_board;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.capstonmaster.R;
import com.hamza.slidingsquaresloaderview.SlidingSquareLoaderView;

public class Departmentfragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frament_department, container, false);
        SlidingSquareLoaderView slidingview = root.findViewById(R.id.sliding_view2);
        slidingview.start();    // starts the sliding
//        slidingview.stop();     // stops the sliding
        slidingview.setDuration(10);   // sets duration of sliding
        slidingview.setDelay(1);  // sets delay period before sliding
        slidingview.setColor(Color.parseColor("#2196F3"));
        return root;
    }
}

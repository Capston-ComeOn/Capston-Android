package com.example.capstonmaster.home;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.capstonmaster.R;
import com.hamza.slidingsquaresloaderview.SlidingSquareLoaderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        SlidingSquareLoaderView slidingview = root.findViewById(R.id.sliding_view2);
        slidingview.start();    // starts the sliding
//        slidingview.stop();     // stops the sliding
        slidingview.setDuration(10);   // sets duration of sliding
        slidingview.setDelay(1);  // sets delay period before sliding
        slidingview.setColor(Color.parseColor("#2196F3"));
        return root;
    }

}

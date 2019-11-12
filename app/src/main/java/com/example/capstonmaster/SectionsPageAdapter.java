package com.example.capstonmaster;

import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.capstonmaster.board.free_board.Freefragment;
import com.example.capstonmaster.board.login.Loginfragment;
import com.example.capstonmaster.board.notice_board.Noticefragment;
import com.example.capstonmaster.dto.Category;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class SectionsPageAdapter extends FragmentPagerAdapter {

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Loginfragment();
            case 1:
                return new Freefragment();
            case 2:
                return new Noticefragment();
            default:
                return new Freefragment();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}

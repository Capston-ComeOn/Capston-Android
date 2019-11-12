package com.example.capstonmaster;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.example.capstonmaster.board.free_board.Freefragment;
import com.example.capstonmaster.dto.Account;
import com.example.capstonmaster.dto.Category;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

  TextView h_email;
  TextView h_nickname;
  private AppBarConfiguration mAppBarConfiguration;
  public ViewPager viewPager;
  public TabLayout tabLayout;
  private SectionsPageAdapter sectionsPageAdapter;
  SharedPreferences sf;
  String access_token;
  private int REQUEST_TEST = 1;

  TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
  TabLayout.OnTabSelectedListener onTabSelectedListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    sf = getSharedPreferences("pref", MODE_PRIVATE);
    access_token = sf.getString("access_token", "");
    System.out.println(access_token + "토큰 있음 ㅇㅇ");
    this.InitializeLayout();
  }

  @Override
  protected void onStart() {
    super.onStart();
    getBoardList();
    getAccount();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {  //옵션 메뉴 설정
    getMenuInflater().inflate(R.menu.appbar_action, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) { //오른쪽 아이콘 영역
    switch (item.getItemId()) {
      case R.id.action_refresh:
        Toast.makeText(getApplicationContext(), "새로고침", Toast.LENGTH_SHORT).show();
        this.InitializeLayout();
        viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
        return true;
      case R.id.action_settings:
        Toast.makeText(getApplicationContext(), " 설정", Toast.LENGTH_SHORT).show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  public void InitializeLayout() {
    //toolBar를 통해 App Bar 생성
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

    sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
    viewPager = findViewById(R.id.viewpager);
    viewPager.setAdapter(sectionsPageAdapter);
   // tabLayout = findViewById(R.id.tabs);
//    tabLayout.setupWithViewPager(viewPager);

//    tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }
      @Override
      public void onTabUnselected(TabLayout.Tab tab) {
      }
      @Override
      public void onTabReselected(TabLayout.Tab tab) {
      }
    });

//        tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);

    //App Bar의 좌측 영영에 Drawer를 Open 하기 위한 Incon 추가
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    DrawerLayout drawLayout =findViewById(R.id.drawer_layout);
    NavigationView navigationView =  findViewById(R.id.nav_view);
    View headerView = navigationView.getHeaderView(0);
    h_email = headerView.findViewById(R.id.h_email);
    h_nickname = headerView.findViewById(R.id.h_nickname);

    ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
      this,
      drawLayout,
      toolbar,
      R.string.app_name,
      R.string.app_name
    );
    drawLayout.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();

    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
          case R.id.free:
            Toast.makeText(getApplicationContext(), "자유 게시판", Toast.LENGTH_SHORT).show();
            viewPager.setCurrentItem(1);
            break;
          case R.id.mento_menti:
            Toast.makeText(getApplicationContext(), "멘토/멘티 게시판", Toast.LENGTH_SHORT).show();
            viewPager.setCurrentItem(2);
            break;
          case R.id.project:
            Toast.makeText(getApplicationContext(), "프로젝트/공모전 게시판", Toast.LENGTH_SHORT).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new Loginfragment()).commit();
            break;
          case R.id.used_item:
            Toast.makeText(getApplicationContext(), "중고 거래", Toast.LENGTH_SHORT).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new Loginfragment()).commit();
            break;
          case R.id.advice:
            Toast.makeText(getApplicationContext(), "선배의 조언", Toast.LENGTH_SHORT).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new Loginfragment()).commit();
            break;
          case R.id.advertising:
            Toast.makeText(getApplicationContext(), "홍보 게시판", Toast.LENGTH_SHORT).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new Loginfragment()).commit();
            break;
        }
//                getSupportActionBar().setTitle();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
      }
    });
  }

  @Override
  protected void onPause() {
    super.onPause();
    System.out.println("메인일시정지");
  }

  @Override
  protected void onResume() {
    super.onResume();
    System.out.println("메인재개");
  }

  @Override
  protected void onRestart() {
    System.out.println("메인재시작");
    super.onRestart();
  }

  @Override
  protected void onStop() {
    System.out.println("메인스탑");
    super.onStop();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  protected void onDestroy() {
    SharedPreferences.Editor editor = sf.edit();
    editor.remove("pref");
    editor.commit();
    System.out.println("토큰지움");
    super.onDestroy();
  }

  public void getBoardList() {
    OkHttpClient client = new OkHttpClient();
    final Request request = new Request.Builder()
      .header(getString(R.string.Authorization), "Bearer " + access_token)
      .url(getString(R.string.ip) + "/api/article/category")
      .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        System.out.println(e);
        System.out.println("메인액티비티카테고리 가져오기 실패");
      }
      @Override
      public void onResponse(Call call, Response response) throws IOException {
        try {
          ArrayList<Category> list_category = new ArrayList<>();
          Gson gson = new Gson();
          JSONArray jsonArray = new JSONArray(response.body().string());
          int i=0;
          while(i < jsonArray.length()){
            System.out.println(jsonArray.length());
            System.out.println(i);
            Category category = gson.fromJson(jsonArray.get(i).toString(),
              Category.class);
            list_category.add(category);
            i++;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public void getAccount() {
    //System.out.println("=======" + access_token);
    OkHttpClient client = new OkHttpClient();
    final Request request = new Request.Builder()
      .header(getString(R.string.Authorization), "Bearer " + access_token)
      .url(getString(R.string.ip) + "/api/accounts/login")
      .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        System.out.println(e);
        System.out.println("getAccount 실패");
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        try {
          Gson gson = new Gson();
          Account account = gson.fromJson(response.body().string(), Account.class);
          h_email.setText(account.getEmail());
          h_nickname.setText(account.getName());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}

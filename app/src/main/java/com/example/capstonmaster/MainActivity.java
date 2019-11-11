package com.example.capstonmaster;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.example.capstonmaster.board.free_board.Freefragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    public ViewPager viewPager;
    public TabLayout tabLayout;
    private SectionsPageAdapter sectionsPageAdapter;
    SharedPreferences sf;
    String userToken;
    private int REQUEST_TEST = 1;

    TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
    TabLayout.OnTabSelectedListener onTabSelectedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sf = getSharedPreferences("pref", MODE_PRIVATE);
        userToken = sf.getString("userToken", "");
        System.out.println(userToken + "토큰 있음 ㅇㅇ");

        getBoardList();

        this.InitializeLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(getApplicationContext(), "새로고침", Toast.LENGTH_SHORT).show();
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.detach(sectionsPageAdapter.getItem(1)).attach(sectionsPageAdapter.getItem(1));
//                ft.commit();
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
        toolbar.setTitleTextColor(Color.parseColor("#B0BEC5"));
//    toolbar.setBackgroundColor(Color.parseColor("#ffffff"));

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPageAdapter);
        tabLayout = findViewById(R.id.tabs);
//    tabLayout.setupWithViewPager(viewPager);

//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
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
//    getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);

        DrawerLayout drawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name
        );

        drawLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

//    //프래그먼트 장착
//    mAppBarConfiguration = new AppBarConfiguration.Builder(
//      R.id.login,R.id.free,R.id.notice)
//      .setDrawerLayout(drawLayout)
//      .build();
//    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//
////         ActionBar에도 Top-Level-Destination 설정 하면,
////Top-Level-Destincation이 아니라면 Actionbar에 뒤로 가기[<-] 가 표현된다
//    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//    NavigationUI.setupWithNavController(navigationView, navController);

//    navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//
//      public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//
//        Toast.makeText(MainActivity.this,destination.getLabel(),Toast.LENGTH_LONG).show();
//      }
//    });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.free:
                        Toast.makeText(getApplicationContext(), "자유", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.notice:
                        Toast.makeText(getApplicationContext(), "공지", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.mento_menti:
                        Toast.makeText(getApplicationContext(), "멘토", Toast.LENGTH_SHORT).show();
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
        System.out.println("메인일시정지");    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("메인재개");    }

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
        SharedPreferences.Editor editor=sf.edit();
        editor.remove("pref");
        editor.commit();
        System.out.println("토큰지움");
        super.onDestroy();
    }
    public void getBoardList(){

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .header(getString(R.string.Authorization), "Bearer "+userToken)
                .url(getString(R.string.ip)+"/api/article/category")
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
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        String id   = jsonobject.getString("id");
                        String category = jsonobject.getString("name");
                        switch (id){
                            case "1":
                                break;
                            case "2":
                                Freefragment.id = id;
                                break;
                            case "3":
                                break;
                            case "4":
                                break;
                        }
                        System.out.println(id+" "+ category);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });
    }
}

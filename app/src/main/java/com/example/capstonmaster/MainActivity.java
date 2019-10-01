package com.example.capstonmaster;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
  private AppBarConfiguration mAppBarConfiguration;
  //private Button btn_register;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.InitializeLayout();

  // btn_register = findViewById(R.id.btn_register);
    //회원가입 버튼을 클릭 시 수행
    //btn_register.setOnClickListener(new View.OnClickListener(){
      //@Override
      //public void onClick(View view){
        //Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
      //}
    //});
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.appbar_action, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_search:
        Toast.makeText(getApplicationContext(), " 찾기", Toast.LENGTH_SHORT).show();
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

    //App Bar의 좌측 영영에 Drawer를 Open 하기 위한 Incon 추가
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);

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

    //프래그먼트 장착
    mAppBarConfiguration = new AppBarConfiguration.Builder(
      R.id.free, R.id.notice)
      .setDrawerLayout(drawLayout)
      .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    NavigationUI.setupWithNavController(navigationView, navController);


               /* navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.club_intro:
                                Toast.makeText(getApplicationContext(), "intro 2", Toast.LENGTH_SHORT).show();
                            case R.id.pro_intro:
                                Toast.makeText(getApplicationContext(), "intro 3", Toast.LENGTH_SHORT).show();
                        }
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });*/
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

}

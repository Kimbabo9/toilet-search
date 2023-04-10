package com.example.toilet_search_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


import android.Manifest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_DENIED){ //포그라운드 위치 권한 확인

            //위치 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        }


        if(permissionCheck == PackageManager.PERMISSION_DENIED){ //백그라운드 위치 권한 확인

            //위치 권한 요청
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 0);
            }
        }
        getAppKeyHash();




        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        settingSideNavBar();


    }

    public void settingSideNavBar()
    {
        // 툴바 생성

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 사이드 네브바 구현

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.closed
        );

        // 사이드 네브바 클릭 리스너
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // -> 사이드 네브바 아이템 클릭 이벤트 설정
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.navigation_home){
                    Intent intent = new Intent(getApplicationContext(),MapviewActivity.class);
                    startActivity(intent);
                }else if(id == R.id.navigation_dashboard){
                    Toast.makeText(getApplicationContext(), "위치찾기", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.navigation_info){
                    Toast.makeText(getApplicationContext(), "즐겨찾기", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.navigation_analysis){
                    Toast.makeText(getApplicationContext(), "앱 설정", Toast.LENGTH_SHORT).show();
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ImageButton button2 = findViewById(R.id.ib_menu);
        button2.setOnClickListener(v -> {
            drawerLayout.open();
        });

    }


    /***
     *  -> 뒤로가기시, 사이드 네브바 닫는 기능
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("name not found", e.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
package com.example.carapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import  androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class istpage extends AppCompatActivity {

    NavigationView nav;
    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istpage);
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.navi);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.close,R.string.open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Toast.makeText(istpage.this, "This is home page", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                    case R.id.contact:
                        Toast.makeText(istpage.this, "contact pannel is opemed", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.about:
                        Toast.makeText(istpage.this, "gallery pannel is opemed", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.login:
                        Toast.makeText(istpage.this, "login pannel is opemed", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.share:
                        Toast.makeText(istpage.this, "login pannel is opemed", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                    case R.id.rate:
                        Toast.makeText(istpage.this, "login pannel is opemed", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                    case R.id.service:
                        Toast.makeText(istpage.this, "login pannel is opemed", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);



                }
                return false;
            }
        });

    }
}

package com.example.covidhelp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class CategoriesActivity extends AppCompatActivity {
    private GridView categoryGV;
    private Context mContext;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private TextView shopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);
        setupWidgets();
    }

    private void setupGV() {
        final Category[] categories = {
                new Category("Grocery & Staples"),
                new Category("Vegetables & Fruits"),
                new Category("Personal Care"),
                new Category("Household Items"),
                new Category("Home & Kitchen"),
                new Category("Biscuits, Snacks & Chocolates"),
                new Category("Beverages"),
                new Category("Breakfast & Dairy"),
                new Category("Baby Care")

        };
        categoryAdapter categoryAdapter = new categoryAdapter(this, categories);
        categoryGV.setAdapter(categoryAdapter);
        categoryGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(mContext, categories[i].getCategory(), Toast.LENGTH_SHORT).show();
        }
    });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }
    private void setupWidgets(){
        categoryGV = (GridView) findViewById(R.id.cat_gv);
        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.cat_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Toast.makeText(CategoriesActivity.this, "", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        shopName = (TextView) findViewById(R.id.shop_name_TV);
        shopName.setText(intent.getStringExtra("shop"));
         
        setupGV();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}

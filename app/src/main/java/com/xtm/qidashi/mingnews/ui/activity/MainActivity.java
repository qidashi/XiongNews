package com.xtm.qidashi.mingnews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xtm.qidashi.mingnews.R;
import com.xtm.qidashi.mingnews.ui.adapter.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private TabLayout tabLayout;
    private ViewPager newsViewPager;
    private MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        newsViewPager.setAdapter(mainPagerAdapter);
        newsViewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(newsViewPager);
    }

    private void initView() {
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        newsViewPager = (ViewPager) findViewById(R.id.news_viewPager);
    }
    public native String getAppKey();
}

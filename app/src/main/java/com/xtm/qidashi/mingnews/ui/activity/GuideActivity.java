package com.xtm.qidashi.mingnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rd.PageIndicatorView;
import com.xtm.qidashi.mingnews.R;
import com.xtm.qidashi.mingnews.ui.adapter.GuideAdapter;
import com.xtm.qidashi.mingnews.utils.SpUtil;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnStart;
    private PageIndicatorView pageIndicatorView;
    private int[] imageIds = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpUtil.putBoolean(GuideActivity.this, "isFirst", false);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        initData();
    }

    private void initData() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imageViews.add(imageView);
        }

        GuideAdapter guideAdapter = new GuideAdapter(imageViews);
        viewPager.setAdapter(guideAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == imageIds.length - 1) {
                    btnStart.setVisibility(View.VISIBLE);
                } else {
                    btnStart.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btnStart = (Button) findViewById(R.id.btn_start);
        pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
    }
}

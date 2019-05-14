package com.xtm.qidashi.mingnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.xtm.qidashi.mingnews.R;
import com.xtm.qidashi.mingnews.utils.SpUtil;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initAnim();
    }

    private void initAnim() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpToNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivSplash.startAnimation(alphaAnimation);
    }

    private void jumpToNextPage() {
        boolean isFirst = SpUtil.getBoolean(this, "isFirst", true);
        Intent intent = new Intent();
        if(isFirst){
            intent.setClass(this,GuideActivity.class);
        }else {
            intent.setClass(this,MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void initView() {
        ivSplash = (ImageView) findViewById(R.id.iv_splash);
    }
}

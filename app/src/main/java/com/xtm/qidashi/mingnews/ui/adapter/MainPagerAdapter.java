package com.xtm.qidashi.mingnews.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xtm.qidashi.mingnews.ui.fragment.NewsFragment;

/**
 * @author qidashi
 * @version 1.0
 * @date 2019/5/14
 * @description: 类型, , top(头条 ， 默认), shehui(社会), guonei(国内), guoji(国际), yule(娱乐), tiyu(体育)
 * junshi(军事),keji(科技),caijing(财经),shishang(时尚)
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    private String[] mTypes = {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.newInstance(mTypes[position]);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

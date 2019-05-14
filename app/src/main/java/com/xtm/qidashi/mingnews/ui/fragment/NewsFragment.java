package com.xtm.qidashi.mingnews.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class NewsFragment extends BaseFragment {

    private static final String PARAM_KEY = "type";

    public static Fragment newInstance(String type) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_KEY, type);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    protected String getType() {
        return getArguments().getString(PARAM_KEY);
    }
}

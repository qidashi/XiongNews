package com.xtm.qidashi.mingnews.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.xtm.qidashi.mingnews.R;

public class RefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    private View mListViewFooter;
    private ListView mListView;
    private boolean isLoading = false;
    private boolean hasMore = true;
    private OnLoadListener mOnLoadListener;

    public RefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mListView == null)
            getListView();
    }

    private void getListView() {
        int childCount = getChildCount();
        if (childCount > 0) {
            View childView = getChildAt(0);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
                mListView.setOnScrollListener(this);
            }
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mListViewFooter);
        } else {
            mListView.removeFooterView(mListViewFooter);
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        if (hasMore && mListView.getLastVisiblePosition() == mListView.getAdapter().getCount() - 1
                && !isLoading){
            setLoading(true);
            if(mOnLoadListener != null)
            mOnLoadListener.onLoad();
        }
    }

    public interface OnLoadListener {
        void onLoad();
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
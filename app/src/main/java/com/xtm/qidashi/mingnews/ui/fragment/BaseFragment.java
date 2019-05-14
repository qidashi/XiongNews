package com.xtm.qidashi.mingnews.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xtm.qidashi.mingnews.R;
import com.xtm.qidashi.mingnews.bean.NewsBean;
import com.xtm.qidashi.mingnews.net.JNI;
import com.xtm.qidashi.mingnews.net.NetWork;
import com.xtm.qidashi.mingnews.ui.activity.WebViewActivity;
import com.xtm.qidashi.mingnews.ui.adapter.NewsListAdapter;
import com.xtm.qidashi.mingnews.ui.view.RefreshLayout;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public abstract class BaseFragment extends Fragment {

    private RefreshLayout refreshLayout;
    private ListView listView;
    private ProgressBar progress;
    private ImageView ivError;
    private List<NewsBean.ResultBean.DataBean> dataBeans;
    private NewsListAdapter newsListAdapter;

    public BaseFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toutiao, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {

        progress.setVisibility(View.VISIBLE);
        ivError.setVisibility(View.GONE);
        refresh();
        refreshLayout.setHasMore(false);
        ivError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsBean.ResultBean.DataBean dataBean = dataBeans.get(position);
                startActivity(WebViewActivity.newIntent(getContext(), dataBean.getUrl()));
            }
        });

        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                load();
            }
        });
    }

    private void initView(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.refresh_layout);
        listView = (ListView) view.findViewById(R.id.list_view);
        progress = (ProgressBar) view.findViewById(R.id.pb_progress);
        ivError = (ImageView) view.findViewById(R.id.iv_error);
    }

    private void load() {

    }

    private void refresh() {
        NetWork.createApi().getNews(getType(), JNI.stringFromJNI())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsBean>() {
                    @Override
                    public void call(NewsBean newsBean) {
                        refreshLayout.setEnabled(true);
                        progress.setVisibility(View.GONE);
                        refreshLayout.setRefreshing(false);
                        dataBeans = newsBean.getResult().getData();
                        newsListAdapter = new NewsListAdapter(dataBeans, getContext());
                        listView.setAdapter(newsListAdapter);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ivError.setVisibility(View.VISIBLE);
                        refreshLayout.setEnabled(false);
                        progress.setVisibility(View.GONE);
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();
                    }
                });
    }

    protected abstract String getType();


}

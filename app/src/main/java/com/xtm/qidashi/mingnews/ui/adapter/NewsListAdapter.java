package com.xtm.qidashi.mingnews.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xtm.qidashi.mingnews.R;
import com.xtm.qidashi.mingnews.bean.NewsBean;
import com.xtm.qidashi.mingnews.ui.view.RoundTransform;

import java.util.List;

/**
 * @author qidashi
 * @version 1.0
 * @date 2019/5/15
 * @description:
 */
public class NewsListAdapter extends BaseAdapter {
    private List<NewsBean.ResultBean.DataBean> dataBeans;
    private Context context;

    public NewsListAdapter(List<NewsBean.ResultBean.DataBean> dataBeans, Context context) {
        this.dataBeans = dataBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return dataBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
             viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.item_news,null);
            viewHolder.ivIcon = view.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = view.findViewById(R.id.tv_title);
            viewHolder.tvFrom = view.findViewById(R.id.tv_from);
            viewHolder.tvDate = view.findViewById(R.id.tv_date);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NewsBean.ResultBean.DataBean dataBean = dataBeans.get(i);
        viewHolder.tvTitle.setText(dataBean.getTitle());
        viewHolder.tvFrom.setText(dataBean.getCategory());
        viewHolder.tvDate.setText(dataBean.getDate());
        if(!TextUtils.isEmpty(dataBean.getThumbnail_pic_s())){
            Picasso.with(context)
                    .load(dataBean.getThumbnail_pic_s())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .transform(new RoundTransform())
                    .into(viewHolder.ivIcon);
        }
        return view;
    }

    private static class ViewHolder{
        ImageView ivIcon;
        TextView tvTitle,tvFrom,tvDate;
    }
}

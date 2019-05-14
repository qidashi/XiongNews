package com.xtm.qidashi.mingnews.net;

import com.xtm.qidashi.mingnews.bean.NewsBean;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author qidashi
 * @version 1.0
 * @date 2019/5/14
 * @description:
 */
public interface Api {
    @GET("toutiao/index")
    Observable<NewsBean> getNews(@Query("type")String type, @Query("key")String key);
}

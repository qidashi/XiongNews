package com.xtm.qidashi.mingnews.net;


import com.xtm.qidashi.mingnews.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author qidashi
 * @version 1.0
 * @date 2019/5/14
 * @description:
 */
public class NetWork {
    private static Retrofit retrofit;
    private static OkHttpClient httpClient;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            httpClient = builder.build();
            retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofit;

    }

    public static Api createApi(){
        return NetWork.getRetrofit().create(Api.class);
    }
}

package com.ahmedabdelmeged.githubarch.api;

import com.ahmedabdelmeged.githubarch.common.AutoValueGson_GithubTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

public class GithubServiceFactory {

    public static GithubService makeGithubService(boolean isDebug) {
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor(isDebug));
        return makeGithubService(okHttpClient, makeGson());
    }

    private static GithubService makeGithubService(OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(GithubService.class);
    }

    private static OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
    }

    private static Gson makeGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new AutoValueGson_GithubTypeAdapterFactory())
                .create();
    }

    private static HttpLoggingInterceptor makeLoggingInterceptor(boolean isDebug) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(Timber::i);
        httpLoggingInterceptor.setLevel(isDebug ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

}

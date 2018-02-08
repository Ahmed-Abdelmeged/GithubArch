package com.ahmedabdelmeged.githubarch.common;

import android.support.annotation.NonNull;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by Ahmed Abd-Elmeged on 2/5/2018.
 */

@GsonTypeAdapterFactory
public abstract class GithubTypeAdapterFactory implements TypeAdapterFactory {

    @NonNull
    public static TypeAdapterFactory create() {
        return new AutoValueGson_GithubTypeAdapterFactory();
    }

}

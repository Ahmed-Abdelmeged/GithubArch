package com.ahmedabdelmeged.githubarch.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.common.GlideApp;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */
public final class UserBindingAdapter {

    private UserBindingAdapter() {
    }

    @BindingAdapter("app:avatarUrl")
    public static void setUserAvatar(ImageView view, final String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    @BindingAdapter("app:visibility")
    public static void setSiteAdminVisibility(ImageView view, boolean isSiteAdmin) {
        view.setVisibility(isSiteAdmin ? View.VISIBLE : View.GONE);
    }

}

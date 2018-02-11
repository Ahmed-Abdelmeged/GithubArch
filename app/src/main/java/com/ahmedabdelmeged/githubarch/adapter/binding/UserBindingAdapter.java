package com.ahmedabdelmeged.githubarch.adapter.binding;

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

    @BindingAdapter("avatarUrl")
    public static void setUserAvatar(ImageView view, final String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    @BindingAdapter("visibility")
    public static void setVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}

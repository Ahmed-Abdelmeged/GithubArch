package com.ahmedabdelmeged.githubarch.adapter;

import android.support.v7.widget.RecyclerView;

import com.ahmedabdelmeged.githubarch.databinding.ItemUserBinding;
import com.ahmedabdelmeged.githubarch.model.User;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    private final ItemUserBinding binding;

    UserViewHolder(ItemUserBinding binding, UserClickHandler userClickHandler) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.setUserClickHandler(userClickHandler);
    }

    public void bind(User user) {
        binding.setUser(user);
        binding.executePendingBindings();
    }

}


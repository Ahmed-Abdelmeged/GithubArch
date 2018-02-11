package com.ahmedabdelmeged.githubarch.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.adapter.callback.UserClickHandler;
import com.ahmedabdelmeged.githubarch.databinding.ItemUserBinding;
import com.ahmedabdelmeged.githubarch.model.User;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    private final ItemUserBinding binding;

    private UserViewHolder(ItemUserBinding binding, UserClickHandler userClickHandler) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.setUserClickHandler(userClickHandler);
    }

    public void bindTo(User user) {
        binding.setUser(user);
        binding.executePendingBindings();
    }

    public static UserViewHolder create(ViewGroup parent, UserClickHandler userClickHandler) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_user, parent, false);
        return new UserViewHolder(itemUserBinding, userClickHandler);
    }


}


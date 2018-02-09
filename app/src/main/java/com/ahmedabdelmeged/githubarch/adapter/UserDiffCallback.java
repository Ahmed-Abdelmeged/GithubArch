package com.ahmedabdelmeged.githubarch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

import com.ahmedabdelmeged.githubarch.model.User;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */
public class UserDiffCallback extends DiffCallback<User> {

    @Inject
    UserDiffCallback() {
    }

    public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return oldItem.id() == newItem.id();
    }

    public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return Objects.equals(oldItem, newItem);
    }

}


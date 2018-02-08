package com.ahmedabdelmeged.githubarch.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.ahmedabdelmeged.githubarch.model.User;

import java.util.List;
import java.util.Objects;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */
public class UserDiffCallback extends DiffUtil.Callback {

    private final List<User> oldUserList;

    private final List<User> newUserList;

    UserDiffCallback(List<User> oldUserList, List<User> newUserList) {
        this.oldUserList = oldUserList;
        this.newUserList = newUserList;
    }

    @Override
    public int getOldListSize() {
        return oldUserList.size();
    }

    @Override
    public int getNewListSize() {
        return newUserList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUserList.get(oldItemPosition).id() == newUserList.get(newItemPosition).id();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldUserList.get(oldItemPosition), newUserList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

}

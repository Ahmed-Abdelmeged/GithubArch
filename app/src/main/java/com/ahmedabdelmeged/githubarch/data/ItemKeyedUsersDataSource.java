package com.ahmedabdelmeged.githubarch.data;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.model.User;

/**
 * Created by Ahmed Abd-Elmeged on 2/8/2018.
 */
public class ItemKeyedUsersDataSource extends ItemKeyedDataSource<Long, User> {

    private GithubService githubService;

    public ItemKeyedUsersDataSource(GithubService githubService) {
        this.githubService = githubService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<User> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {
        // ignored, since we only ever append to our initial load
    }

    @NonNull
    @Override
    public Long getKey(@NonNull User item) {
        return item.id();
    }
}

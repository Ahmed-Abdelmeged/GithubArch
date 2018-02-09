package com.ahmedabdelmeged.githubarch.data;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.ahmedabdelmeged.githubarch.model.User;

import io.reactivex.disposables.CompositeDisposable;

public class GitHubUserDataSourceFactory implements DataSource.Factory<Long, User> {

    private UserRepository userRepository;

    private CompositeDisposable compositeDisposable;


    MutableLiveData<ItemKeyedUsersDataSource> mutableLiveData;


    public GitHubUserDataSourceFactory(UserRepository userRepository, CompositeDisposable compositeDisposable) {
        this.userRepository = userRepository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Long, User> create() {
        return new ItemKeyedUsersDataSource(userRepository, compositeDisposable);
    }

    public MutableLiveData<ItemKeyedUsersDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}

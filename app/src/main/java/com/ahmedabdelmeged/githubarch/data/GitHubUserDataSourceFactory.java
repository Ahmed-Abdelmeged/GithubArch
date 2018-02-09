package com.ahmedabdelmeged.githubarch.data;


import android.arch.paging.DataSource;

import com.ahmedabdelmeged.githubarch.model.User;

import io.reactivex.disposables.CompositeDisposable;

public class GitHubUserDataSourceFactory implements DataSource.Factory<Long, User> {

    private UserRepository userRepository;

    private CompositeDisposable compositeDisposable;

    public GitHubUserDataSourceFactory(UserRepository userRepository, CompositeDisposable compositeDisposable) {
        this.userRepository = userRepository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Long, User> create() {
        return new ItemKeyedUsersDataSource(userRepository, compositeDisposable);
    }

}

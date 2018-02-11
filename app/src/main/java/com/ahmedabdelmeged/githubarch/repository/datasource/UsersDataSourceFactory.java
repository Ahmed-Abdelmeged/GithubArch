package com.ahmedabdelmeged.githubarch.repository.datasource;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.api.GithubService;
import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.model.UserMapper;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/8/2018.
 */

/**
 * A simple data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
public class UsersDataSourceFactory implements DataSource.Factory<Long, User> {

    @NonNull
    private final GithubService githubService;

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final CompositeDisposable compositeDisposable;

    @NonNull
    private final MutableLiveData<UsersDataSource> usersDataSourceLiveData = new MutableLiveData<>();

    public UsersDataSourceFactory(@NonNull GithubService githubService, @NonNull UserMapper userMapper,
                                  @NonNull CompositeDisposable compositeDisposable) {
        this.githubService = githubService;
        this.userMapper = userMapper;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Long, User> create() {
        UsersDataSource usersDataSource = new UsersDataSource(githubService, userMapper, compositeDisposable);
        usersDataSourceLiveData.postValue(usersDataSource);
        return usersDataSource;
    }

    @NonNull
    public MutableLiveData<UsersDataSource> getUsersDataSourceLiveData() {
        return usersDataSourceLiveData;
    }

}

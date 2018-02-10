package com.ahmedabdelmeged.githubarch.repository.datasource;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.model.User;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/8/2018.
 */

public class UsersDataSourceFactory implements DataSource.Factory<Long, User> {

    @NonNull
    private final UsersDataSourceProvider usersDataSourceProvider;

    @NonNull
    private final CompositeDisposable compositeDisposable;

    @NonNull
    private final MutableLiveData<UsersDataSource> usersDataSourceLiveData = new MutableLiveData<>();

    public UsersDataSourceFactory(@NonNull UsersDataSourceProvider usersDataSourceProvider,
                                  @NonNull CompositeDisposable compositeDisposable) {
        this.usersDataSourceProvider = usersDataSourceProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Long, User> create() {
        UsersDataSource usersDataSource = new UsersDataSource(usersDataSourceProvider, compositeDisposable);
        usersDataSourceLiveData.postValue(usersDataSource);
        return usersDataSource;
    }

    @NonNull
    public MutableLiveData<UsersDataSource> getUsersDataSourceLiveData() {
        return usersDataSourceLiveData;
    }

}

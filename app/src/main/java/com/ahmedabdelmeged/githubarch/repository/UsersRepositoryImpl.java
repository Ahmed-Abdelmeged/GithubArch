package com.ahmedabdelmeged.githubarch.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.repository.datasource.UsersDataSource;
import com.ahmedabdelmeged.githubarch.repository.datasource.UsersDataSourceFactory;
import com.ahmedabdelmeged.githubarch.repository.datasource.UsersDataSourceProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

public class UsersRepositoryImpl implements UsersRepository {

    @NonNull
    private final UsersDataSourceProvider usersDataSourceProvider;

    @NonNull
    private final CompositeDisposable compositeDisposable;


    private static final int pageSize = 15;

    private UsersDataSourceFactory usersDataSourceFactory;

    public UsersRepositoryImpl(@NonNull UsersDataSourceProvider usersDataSourceProvider,
                               @NonNull CompositeDisposable compositeDisposable) {
        this.usersDataSourceProvider = usersDataSourceProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public Listing<User> fetchUsers() {
        usersDataSourceFactory = new UsersDataSourceFactory(usersDataSourceProvider, compositeDisposable);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build();
        LiveData<PagedList<User>> pagedList = new LivePagedListBuilder<>(usersDataSourceFactory, config).build();

        return new Listing<>(
                pagedList,
                Transformations.switchMap(usersDataSourceFactory.getUsersDataSourceLiveData(), UsersDataSource::getNetworkState),
                Transformations.switchMap(usersDataSourceFactory.getUsersDataSourceLiveData(), UsersDataSource::getInitialLoad)
        );
    }

    @Override
    public void retry() {
        usersDataSourceFactory.getUsersDataSourceLiveData().getValue().retry();
    }

    @Override
    public void refresh() {
        usersDataSourceFactory.getUsersDataSourceLiveData().getValue().invalidate();
    }

    @NonNull
    @Override
    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

}

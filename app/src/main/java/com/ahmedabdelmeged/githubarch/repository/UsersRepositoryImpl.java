package com.ahmedabdelmeged.githubarch.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.api.GithubService;
import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.model.UserMapper;
import com.ahmedabdelmeged.githubarch.repository.datasource.UsersDataSource;
import com.ahmedabdelmeged.githubarch.repository.datasource.UsersDataSourceFactory;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

/**
 * Repository implementation that returns a Listing that loads data directly from the network
 * and uses the Item's name as the key to discover prev/next pages.
 */
public class UsersRepositoryImpl implements UsersRepository {

    @NonNull
    private final GithubService githubService;

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final CompositeDisposable compositeDisposable;


    private static final int pageSize = 15;

    private UsersDataSourceFactory usersDataSourceFactory;

    public UsersRepositoryImpl(@NonNull GithubService githubService, @NonNull UserMapper userMapper,
                               @NonNull CompositeDisposable compositeDisposable) {
        this.githubService = githubService;
        this.userMapper = userMapper;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public Listing<User> fetchUsers() {
        usersDataSourceFactory = new UsersDataSourceFactory(githubService, userMapper, compositeDisposable);

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

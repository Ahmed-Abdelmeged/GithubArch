package com.ahmedabdelmeged.githubarch.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.data.GitHubUserDataSourceFactory;
import com.ahmedabdelmeged.githubarch.data.UserRepository;
import com.ahmedabdelmeged.githubarch.model.User;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

public class UsersViewModel extends ViewModel {

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<PagedList<User>> userList;

    private static final int pageSize = 15;

    @Inject
    UsersViewModel(@NonNull UserRepository userRepository) {
        GitHubUserDataSourceFactory gitHubUserDataSourceFactory = new GitHubUserDataSourceFactory(userRepository, compositeDisposable);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build();

        userList = new LivePagedListBuilder<>(gitHubUserDataSourceFactory, config).build();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

}

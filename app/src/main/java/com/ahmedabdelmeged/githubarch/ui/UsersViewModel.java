package com.ahmedabdelmeged.githubarch.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.data.GitHubUserDataSourceFactory;
import com.ahmedabdelmeged.githubarch.data.UserRepository;
import com.ahmedabdelmeged.githubarch.model.User;

import java.util.List;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

public class UsersViewModel extends ViewModel {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<PagedList<User>> userList;

    private static final int pageSize = 15;

    @Inject
    UsersViewModel(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
        // compositeDisposable.add(getUsers());
        GitHubUserDataSourceFactory gitHubUserDataSourceFactory = new GitHubUserDataSourceFactory(userRepository, compositeDisposable);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build();

        userList = new LivePagedListBuilder<>(gitHubUserDataSourceFactory, config).build();
        compositeDisposable.add(getUsers());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    @NonNull
    LiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }

    @NonNull
    private Disposable getUsers() {
        return userRepository.fetchUsers()
                .subscribe(usersLiveData::postValue, throwable -> Timber.e(throwable.getMessage()));
    }

}

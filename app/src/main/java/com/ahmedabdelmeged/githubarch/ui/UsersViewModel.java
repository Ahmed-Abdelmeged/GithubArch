package com.ahmedabdelmeged.githubarch.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

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



    private LiveData<PagedList<User>> usersPagedLiveData;



    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    /***
     * The last user id in the fetched list to get on the next page
     */
    private long lastUserId;

    @Inject
    UsersViewModel(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
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
                .subscribe(this::postApiValue, throwable -> Timber.e(throwable.getMessage()));
    }

    public void loadNextPage() {
        compositeDisposable.add(userRepository.fetchNextPageUsers(lastUserId)
                .subscribe(this::postApiValue, throwable -> Timber.e(throwable.getMessage())));
    }

    private void postApiValue(List<User> users) {
        getLastUserId(users);
        usersLiveData.postValue(users);
    }

    private void getLastUserId(List<User> users) {
        try {
            lastUserId = users.get(users.size() - 1).id();
        } catch (IndexOutOfBoundsException e) {
            Timber.e(e.getMessage());
        }
    }




}

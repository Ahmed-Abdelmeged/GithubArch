package com.ahmedabdelmeged.githubarch.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.repository.Listing;
import com.ahmedabdelmeged.githubarch.repository.NetworkState;
import com.ahmedabdelmeged.githubarch.repository.UsersRepository;
import com.ahmedabdelmeged.githubarch.model.User;

import javax.inject.Inject;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

public class UsersViewModel extends ViewModel {

    public LiveData<PagedList<User>> userList;

    @NonNull
    private final UsersRepository usersRepository;

    @NonNull
    private Listing<User> userListing;

    @Inject
    UsersViewModel(@NonNull UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        userListing = usersRepository.fetchUsers();
        userList = userListing.getPagedList();
    }

    public void retry() {
        usersRepository.retry();
    }

    public void refresh() {
        usersRepository.refresh();
    }

    public LiveData<NetworkState> getNetworkState() {
        return userListing.getNetworkState();
    }

    public LiveData<NetworkState> getRefreshState() {
        return userListing.getRefreshState();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        usersRepository.getCompositeDisposable().dispose();
    }

}

package com.ahmedabdelmeged.githubarch.repository;

import com.ahmedabdelmeged.githubarch.model.User;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */
public interface UsersRepository {

    Listing<User> fetchUsers();

    void retry();

    void refresh();

    void clear();

}

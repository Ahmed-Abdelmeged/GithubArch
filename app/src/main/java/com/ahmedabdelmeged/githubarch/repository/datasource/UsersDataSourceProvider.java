package com.ahmedabdelmeged.githubarch.repository.datasource;

import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.api.GithubService;
import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.model.UserMapper;
import com.ahmedabdelmeged.githubarch.model.UserRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */
public class UsersDataSourceProvider {

    @NonNull
    private final GithubService githubService;

    @NonNull
    private final UserMapper userMapper;

    public UsersDataSourceProvider(@NonNull GithubService githubService, @NonNull UserMapper userMapper) {
        this.githubService = githubService;
        this.userMapper = userMapper;
    }

    /**
     * Fetch Github users after check them
     *
     * @return list of users
     */
    Single<List<User>> fetchUsers() {
        return getAndMapUsers(githubService.getUsers());
    }

    /**
     * Fetch next page Github users after check them
     *
     * @param userId the user id to get the users after
     * @return list of users
     */
    Single<List<User>> fetchNextPageUsers(long userId) {
        return getAndMapUsers(githubService.getUsers(userId));
    }

    private Single<List<User>> getAndMapUsers(Single<List<UserRaw>> userRawList) {
        return userRawList.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMapObservable(Observable::fromIterable)
                .map(userMapper)
                .toList();
    }

}

package com.ahmedabdelmeged.githubarch.data;

import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.model.UserMapper;
import com.ahmedabdelmeged.githubarch.model.UserRaw;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

public class UserRepository {

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final GithubService githubService;

    @Inject
    public UserRepository(@NonNull UserMapper userMapper, @NonNull GithubService githubService) {
        this.userMapper = userMapper;
        this.githubService = githubService;
    }

    /**
     * Fetch Github users after check them
     *
     * @return list of users
     */
    public Single<List<User>> fetchUsers() {
        return getAndMapUsers(githubService.getUsers());
    }

    /**
     * Fetch next page Github users after check them
     *
     * @param userId the user id to get the users after
     * @return list of users
     */
    public Single<List<User>> fetchNextPageUsers(long userId) {
        return getAndMapUsers(githubService.getUsers(userId));
    }

    private Single<List<User>> getAndMapUsers(Single<List<UserRaw>> userRawList) {
        return userRawList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapObservable(Observable::fromIterable)
                .map(userMapper)
                .toList();
    }

}

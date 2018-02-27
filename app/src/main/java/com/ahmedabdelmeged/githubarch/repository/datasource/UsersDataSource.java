package com.ahmedabdelmeged.githubarch.repository.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.api.GithubService;
import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.model.UserMapper;
import com.ahmedabdelmeged.githubarch.model.UserRaw;
import com.ahmedabdelmeged.githubarch.repository.NetworkState;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Ahmed Abd-Elmeged on 2/8/2018.
 */

/**
 * A data source that uses the "id" field of posts as the key for next/prev pages.
 */
public class UsersDataSource extends ItemKeyedDataSource<Long, User> {

    @NonNull
    private final GithubService githubService;

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final CompositeDisposable compositeDisposable;

    @NonNull
    private final MutableLiveData<NetworkState> networkState = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<NetworkState> initialLoad = new MutableLiveData<>();

    /**
     * Keep Completable reference for the retry event
     */
    private Completable retryCompletable;

    UsersDataSource(@NonNull GithubService githubService, @NonNull UserMapper userMapper,
                    @NonNull CompositeDisposable compositeDisposable) {
        this.githubService = githubService;
        this.userMapper = userMapper;
        this.compositeDisposable = compositeDisposable;
    }

    public void retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                    }, throwable -> Timber.e(throwable.getMessage())));
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<User> callback) {
        // update network states.
        // we also provide an initial load state to the listeners so that the UI can know when the
        // very first list is loaded.
        networkState.postValue(NetworkState.LOADING);
        initialLoad.postValue(NetworkState.LOADING);

        //get the initial users from the api
        compositeDisposable.add(getAndMapUsers(githubService.getUsers()).subscribe(users -> {
                    // clear retry since last request succeeded
                    setRetry(null);
                    networkState.postValue(NetworkState.LOADED);
                    initialLoad.postValue(NetworkState.LOADED);
                    callback.onResult(users);
                },
                throwable -> {
                    // keep a Completable for future retry
                    setRetry(() -> loadInitial(params, callback));
                    NetworkState error = NetworkState.error(throwable.getMessage());
                    // publish the error
                    networkState.postValue(error);
                    initialLoad.postValue(error);
                }));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {
        // set network value to loading.
        networkState.postValue(NetworkState.LOADING);

        //get the users from the api after id
        compositeDisposable.add(getAndMapUsers(githubService.getUsers(params.key)).subscribe(users -> {
                    // clear retry since last request succeeded
                    setRetry(null);
                    networkState.postValue(NetworkState.LOADED);
                    callback.onResult(users);
                },
                throwable -> {
                    // keep a Completable for future retry
                    setRetry(() -> loadAfter(params, callback));
                    // publish the error
                    networkState.postValue(NetworkState.error(throwable.getMessage()));
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {
        // ignored, since we only ever append to our initial load
    }

    /**
     * The id field is a unique identifier for users.
     */
    @NonNull
    @Override
    public Long getKey(@NonNull User item) {
        return item.id();
    }

    @NonNull
    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    @NonNull
    public MutableLiveData<NetworkState> getInitialLoad() {
        return initialLoad;
    }

    private void setRetry(final Action action) {
        if (action == null) {
            this.retryCompletable = null;
        } else {
            this.retryCompletable = Completable.fromAction(action);
        }
    }

    /**
     * Map the user raw to the safe user
     *
     * @param userRawList the user raw list from the api
     * @return list of the safe user list after mapping
     */
    private Single<List<User>> getAndMapUsers(Single<List<UserRaw>> userRawList) {
        return userRawList.flatMapObservable(Observable::fromIterable)
                .map(userMapper)
                .toList();
    }

}

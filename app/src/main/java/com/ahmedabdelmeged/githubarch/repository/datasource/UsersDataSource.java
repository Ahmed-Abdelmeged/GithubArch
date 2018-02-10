package com.ahmedabdelmeged.githubarch.repository.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.repository.NetworkState;


import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import timber.log.Timber;

/**
 * Created by Ahmed Abd-Elmeged on 2/8/2018.
 */

public class UsersDataSource extends ItemKeyedDataSource<Long, User> {

    @NonNull
    private final UsersDataSourceProvider usersDataSourceProvider;

    @NonNull
    private final CompositeDisposable compositeDisposable;

    @NonNull
    private final MutableLiveData<NetworkState> networkState = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<NetworkState> initialLoad = new MutableLiveData<>();

    private Completable retryCompletable;

    public void retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable
                    .subscribe(() -> {
                    }, throwable -> Timber.e(throwable.getMessage())));
        }
    }

    UsersDataSource(@NonNull UsersDataSourceProvider usersDataSourceProvider,
                    @NonNull CompositeDisposable compositeDisposable) {
        this.usersDataSourceProvider = usersDataSourceProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<User> callback) {
        networkState.postValue(NetworkState.LOADING);
        initialLoad.postValue(NetworkState.LOADING);

        //get the initial users from the api
        compositeDisposable.add(usersDataSourceProvider.fetchUsers().subscribe(users -> {
                    setRetry(null);
                    networkState.postValue(NetworkState.LOADED);
                    initialLoad.postValue(NetworkState.LOADED);
                    callback.onResult(users);
                },
                throwable -> {
                    setRetry(() -> loadInitial(params, callback));
                    NetworkState error = NetworkState.error(throwable.getMessage());
                    networkState.postValue(error);
                    initialLoad.postValue(error);
                }));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {
        networkState.postValue(NetworkState.LOADING);

        //get the users from the api after id
        compositeDisposable.add(usersDataSourceProvider.fetchNextPageUsers(params.key).subscribe(users -> {
                    setRetry(null);
                    networkState.postValue(NetworkState.LOADED);
                    callback.onResult(users);
                },
                throwable -> {
                    setRetry(() -> loadAfter(params, callback));
                    networkState.postValue(NetworkState.error(throwable.getMessage()));
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {
        // ignored, since we only ever append to our initial load
    }

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

}

package com.ahmedabdelmeged.githubarch.data;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.ahmedabdelmeged.githubarch.model.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by Ahmed Abd-Elmeged on 2/8/2018.
 */
public class ItemKeyedUsersDataSource extends ItemKeyedDataSource<Long, User> {

    private UserRepository userRepository;

    private CompositeDisposable compositeDisposable;

    ItemKeyedUsersDataSource(UserRepository userRepository, CompositeDisposable compositeDisposable) {
        this.userRepository = userRepository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<User> callback) {
        Timber.e("init request");
        compositeDisposable.add(userRepository.fetchUsers().subscribe(users -> {
                    Timber.e("hey the data arrived");
                    Timber.e(Thread.currentThread().getName());
                    callback.onResult(users);
                },
                throwable -> Timber.e(throwable.getMessage())));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {
        compositeDisposable.add(userRepository.fetchNextPageUsers(params.key).subscribe(callback::onResult,
                throwable -> Timber.e(throwable.getMessage())));
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
}

package com.ahmedabdelmeged.githubarch.common;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */

/**
 * Lifecycle aware compositeDisposable
 */
public class AutoCompositeDisposable implements LifecycleObserver {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AutoCompositeDisposable(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    public void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public CompositeDisposable get() {
        return compositeDisposable;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void dispose() {
        compositeDisposable.dispose();
    }

}

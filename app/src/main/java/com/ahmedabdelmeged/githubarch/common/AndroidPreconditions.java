package com.ahmedabdelmeged.githubarch.common;

import android.os.Looper;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */
public class AndroidPreconditions {

    @Inject
    AndroidPreconditions() {
    }

    /**
     Asserts that the current thread is the Main Thread.
     */
    public void assertUiThread() {
        if (!isMainThread()) {
            throw new IllegalStateException(
                    "This task must be run on the Main thread and not on a worker thread.");
        }
    }

    /**
     Returns whether the current thread is the Android main thread
     @return true if the current thread is the main thread, otherwise; false.
     */
    private boolean isMainThread() {
        return Objects.equals(Looper.getMainLooper().getThread(), Thread.currentThread());
    }

}

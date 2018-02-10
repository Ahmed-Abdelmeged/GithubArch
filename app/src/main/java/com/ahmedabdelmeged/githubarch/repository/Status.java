package com.ahmedabdelmeged.githubarch.repository;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */

@IntDef({Status.RUNNING, Status.SUCCESS, Status.FAILED})
@Retention(RetentionPolicy.SOURCE)
public @interface Status {
    int RUNNING = 0;
    int SUCCESS = 1;
    int FAILED = 2;
}

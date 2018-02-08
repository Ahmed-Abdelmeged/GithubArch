package com.ahmedabdelmeged.githubarch.di.module;

import com.ahmedabdelmeged.githubarch.di.scops.PerActivity;
import com.ahmedabdelmeged.githubarch.ui.UsersActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

@Module
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = UsersActivityModule.class)
    abstract UsersActivity usersActivity();

}

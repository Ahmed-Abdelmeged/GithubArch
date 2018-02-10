package com.ahmedabdelmeged.githubarch.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.ahmedabdelmeged.githubarch.common.GithubViewModelFactory;
import com.ahmedabdelmeged.githubarch.di.ViewModelKey;
import com.ahmedabdelmeged.githubarch.di.module.user.UserViewModelModule;
import com.ahmedabdelmeged.githubarch.ui.UsersViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Ahmed Abd-Elmeged on 2/5/2018.
 */

@Module(includes = UserViewModelModule.class)
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel.class)
    abstract ViewModel bindUserViewModel(UsersViewModel usersViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(GithubViewModelFactory factory);

}

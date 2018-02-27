package com.ahmedabdelmeged.githubarch.di.module.user;

import com.ahmedabdelmeged.githubarch.adapter.UserAdapter;
import com.ahmedabdelmeged.githubarch.adapter.callback.UserClickHandler;
import com.ahmedabdelmeged.githubarch.adapter.UserDiffCallback;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Abd-Elmeged on 2/9/2018.
 */

@Module
public class UsersActivityModule {

    @Provides
    static UserAdapter provideUserAdapter(UserDiffCallback userDiffCallback, UserClickHandler userClickHandler) {
        return new UserAdapter(userDiffCallback,userClickHandler);
    }

}
package com.ahmedabdelmeged.githubarch.di.module;

import com.ahmedabdelmeged.githubarch.adapter.UserAdapter;
import com.ahmedabdelmeged.githubarch.adapter.UserDiffCallback;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Abd-Elmeged on 2/9/2018.
 */

@Module
public class UsersActivityModule {

    @Provides
    public UserAdapter provideUserAdapter(UserDiffCallback userDiffCallback) {
        return new UserAdapter(userDiffCallback);
    }

}
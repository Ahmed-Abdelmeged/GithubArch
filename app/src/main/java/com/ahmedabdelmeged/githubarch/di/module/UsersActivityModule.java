package com.ahmedabdelmeged.githubarch.di.module;

import com.ahmedabdelmeged.githubarch.adapter.UserAdapter;
import com.ahmedabdelmeged.githubarch.common.AndroidPreconditions;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */

@Module
public class UsersActivityModule {

    @Provides
    public UserAdapter provideUserAdapter(AndroidPreconditions androidPreconditions) {
        return new UserAdapter(androidPreconditions);
    }

}

package com.ahmedabdelmeged.githubarch.di.module.user;

import com.ahmedabdelmeged.githubarch.adapter.UserAdapter;
import com.ahmedabdelmeged.githubarch.adapter.UserClickHandler;
import com.ahmedabdelmeged.githubarch.adapter.UserDiffCallback;
import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.ui.UsersActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Abd-Elmeged on 2/9/2018.
 */

@Module
public class UsersActivityModule {

    @Provides
    static UserClickHandler userClickHandler(UsersActivity usersActivity){
        return new UserClickHandler(usersActivity);
    }


    @Provides
    static UserAdapter provideUserAdapter(UserDiffCallback userDiffCallback, UserClickHandler userClickHandler) {
        return new UserAdapter(userDiffCallback,userClickHandler);
    }

}
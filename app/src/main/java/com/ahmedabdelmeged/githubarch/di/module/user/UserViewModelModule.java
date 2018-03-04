package com.ahmedabdelmeged.githubarch.di.module.user;

import com.ahmedabdelmeged.githubarch.repository.UsersRepository;
import com.ahmedabdelmeged.githubarch.repository.UsersRepositoryImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */

@Module
public abstract class UserViewModelModule {

    @Binds
    public abstract UsersRepository bindUsersRepository(UsersRepositoryImpl usersRepository);

}

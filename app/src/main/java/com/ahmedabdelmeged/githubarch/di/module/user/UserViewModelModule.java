package com.ahmedabdelmeged.githubarch.di.module.user;

import com.ahmedabdelmeged.githubarch.api.GithubService;
import com.ahmedabdelmeged.githubarch.model.UserMapper;
import com.ahmedabdelmeged.githubarch.repository.UsersRepository;
import com.ahmedabdelmeged.githubarch.repository.UsersRepositoryImpl;
import com.ahmedabdelmeged.githubarch.repository.datasource.UsersDataSourceProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */

@Module
public class UserViewModelModule {

    @Provides
    static UsersDataSourceProvider usersDataSourceProvider(GithubService githubService, UserMapper userMapper) {
        return new UsersDataSourceProvider(githubService, userMapper);
    }

    @Provides
    static CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    static UsersRepository provideUsersRepository(UsersDataSourceProvider usersDataSourceProvider,
                                                  CompositeDisposable compositeDisposable) {
        return new UsersRepositoryImpl(usersDataSourceProvider, compositeDisposable);
    }

}

package com.ahmedabdelmeged.githubarch.di.module.user;

import com.ahmedabdelmeged.githubarch.api.GithubService;
import com.ahmedabdelmeged.githubarch.model.UserMapper;
import com.ahmedabdelmeged.githubarch.repository.UsersRepository;
import com.ahmedabdelmeged.githubarch.repository.UsersRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */

@Module
public class UserViewModelModule {

    @Provides
    static CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    static UsersRepository provideUsersRepository(GithubService githubService, UserMapper userMapper,
                                                  CompositeDisposable compositeDisposable) {
        return new UsersRepositoryImpl(githubService, userMapper, compositeDisposable);
    }

}

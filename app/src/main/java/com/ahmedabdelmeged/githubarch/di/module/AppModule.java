package com.ahmedabdelmeged.githubarch.di.module;

import com.ahmedabdelmeged.githubarch.BuildConfig;
import com.ahmedabdelmeged.githubarch.data.GithubService;
import com.ahmedabdelmeged.githubarch.data.GithubServiceFactory;
import com.ahmedabdelmeged.githubarch.data.UserRepository;
import com.ahmedabdelmeged.githubarch.di.scops.PerApplication;
import com.ahmedabdelmeged.githubarch.model.UserMapper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

@Module
public class AppModule {

    @Provides
    @PerApplication
    GithubService provideGithubService() {
        return GithubServiceFactory.makeGithubService(BuildConfig.DEBUG);
    }

    @Provides
    @PerApplication
    UserRepository provideUsersRepository(GithubService githubService, UserMapper userMapper) {
        return new UserRepository(userMapper, githubService);
    }

}

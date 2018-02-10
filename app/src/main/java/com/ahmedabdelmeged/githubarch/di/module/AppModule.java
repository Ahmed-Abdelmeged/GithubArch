package com.ahmedabdelmeged.githubarch.di.module;

import com.ahmedabdelmeged.githubarch.BuildConfig;
import com.ahmedabdelmeged.githubarch.api.GithubService;
import com.ahmedabdelmeged.githubarch.api.GithubServiceFactory;
import com.ahmedabdelmeged.githubarch.di.scops.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

@Module
public class AppModule {

    @Provides
    @PerApplication
    static GithubService provideGithubService() {
        return GithubServiceFactory.makeGithubService(BuildConfig.DEBUG);
    }

}

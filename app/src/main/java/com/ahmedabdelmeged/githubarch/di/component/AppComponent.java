package com.ahmedabdelmeged.githubarch.di.component;

import com.ahmedabdelmeged.githubarch.di.module.ActivityBindingModule;
import com.ahmedabdelmeged.githubarch.di.module.AppModule;
import com.ahmedabdelmeged.githubarch.di.module.ViewModelModule;
import com.ahmedabdelmeged.githubarch.di.scops.PerApplication;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

@PerApplication
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ViewModelModule.class,
        AppModule.class})
interface AppComponent extends AndroidInjector<DaggerApplication> {

}

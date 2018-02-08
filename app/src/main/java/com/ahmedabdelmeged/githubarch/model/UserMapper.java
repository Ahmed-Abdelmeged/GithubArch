package com.ahmedabdelmeged.githubarch.model;

import javax.inject.Inject;

import io.reactivex.functions.Function;

import static com.ahmedabdelmeged.githubarch.util.StringUtil.notNullOrEmpty;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

public class UserMapper implements Function<UserRaw, User> {

    @Inject
    UserMapper() {
    }

    @Override
    public User apply(UserRaw userRaw) {
        return User.builder()
                .login(notNullOrEmpty(userRaw.login()))
                .id(userRaw.id())
                .avatarUrl(notNullOrEmpty(userRaw.avatarUrl()))
                .url(notNullOrEmpty(userRaw.url()))
                .htmlUrl(notNullOrEmpty(userRaw.htmlUrl()))
                .followersUrl(notNullOrEmpty(userRaw.followersUrl()))
                .followingUrl(notNullOrEmpty(userRaw.followingUrl()))
                .gistsUrl(notNullOrEmpty(userRaw.gistsUrl()))
                .starredUrl(notNullOrEmpty(userRaw.starredUrl()))
                .organizationsUrl(notNullOrEmpty(userRaw.organizationsUrl()))
                .reposUrl(notNullOrEmpty(userRaw.reposUrl()))
                .siteAdmin(userRaw.siteAdmin())
                .build();
    }

}

package com.ahmedabdelmeged.githubarch.model;

/**
 * Created by Ahmed Abd-Elmeged on 2/6/2018.
 */
public class UserDataTestUtils {

    static UserRaw.Builder userRawTestBuilder() {
        return UserRaw.builder()
                .login("login")
                .id(0)
                .avatarUrl("avatarUrl")
                .gravatarId("gravatarId")
                .url("url")
                .htmlUrl("htmlUrl")
                .followersUrl("followersUrl")
                .followingUrl("followingUrl")
                .gistsUrl("gistsUrl")
                .starredUrl("starredUrl")
                .subscriptionsUrl("subscriptionsUrl")
                .organizationsUrl("organizationsUrl")
                .reposUrl("reposUrl")
                .eventsUrl("eventsUrl")
                .eventsUrl("eventsUrl")
                .receivedEventsUrl("receivedEventsUrl")
                .type("type")
                .siteAdmin(false);
    }

    static User.Builder userTestBuilder() {
        return User.builder()
                .login("login")
                .id(0)
                .avatarUrl("avatarUrl")
                .url("url")
                .htmlUrl("htmlUrl")
                .followersUrl("followersUrl")
                .followingUrl("followingUrl")
                .gistsUrl("gistsUrl")
                .starredUrl("starredUrl")
                .organizationsUrl("organizationsUrl")
                .reposUrl("reposUrl")
                .siteAdmin(false);
    }

}

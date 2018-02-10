package com.ahmedabdelmeged.githubarch.model;

import com.google.auto.value.AutoValue;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

@AutoValue
public abstract class User {

    public abstract String login();

    public abstract long id();

    public abstract String avatarUrl();

    public abstract String url();

    public abstract String htmlUrl();

    public abstract String followersUrl();

    public abstract String followingUrl();

    public abstract String gistsUrl();

    public abstract String starredUrl();

    public abstract String organizationsUrl();

    public abstract String reposUrl();

    public abstract boolean siteAdmin();

    static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public interface Builder {

        Builder login(final String login);

        Builder id(final long id);

        Builder avatarUrl(final String avatarUrl);

        Builder url(final String url);

        Builder htmlUrl(final String htmlUrl);

        Builder followersUrl(final String followersUrl);

        Builder followingUrl(final String followingUrl);

        Builder gistsUrl(final String gistsUrl);

        Builder starredUrl(final String starredUrl);

        Builder organizationsUrl(final String organizationsUrl);

        Builder reposUrl(final String reposUrl);

        Builder siteAdmin(final boolean siteAdmin);

        User build();
    }

}

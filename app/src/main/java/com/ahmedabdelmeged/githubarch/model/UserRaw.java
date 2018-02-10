package com.ahmedabdelmeged.githubarch.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

@AutoValue
public abstract class UserRaw {

    @Nullable
    abstract String login();

    abstract long id();

    @Nullable
    @SerializedName("avatar_url")
    abstract String avatarUrl();

    @Nullable
    @SerializedName("gravatar_id")
    abstract String gravatarId();

    @Nullable
    abstract String url();

    @Nullable
    @SerializedName("html_url")
    abstract String htmlUrl();

    @Nullable
    @SerializedName("followers_url")
    abstract String followersUrl();

    @Nullable
    @SerializedName("following_url")
    abstract String followingUrl();

    @Nullable
    @SerializedName("gists_url")
    abstract String gistsUrl();

    @Nullable
    @SerializedName("starred_url")
    abstract String starredUrl();

    @Nullable
    @SerializedName("subscriptions_url")
    abstract String subscriptionsUrl();

    @Nullable
    @SerializedName("organizations_url")
    abstract String organizationsUrl();

    @Nullable
    @SerializedName("repos_url")
    abstract String reposUrl();

    @Nullable
    @SerializedName("events_url")
    abstract String eventsUrl();

    @Nullable
    @SerializedName("received_events_url")
    abstract String receivedEventsUrl();

    @Nullable
    abstract String type();

    @SerializedName("site_admin")
    abstract boolean siteAdmin();

    @NonNull
    public static Builder builder() {
        return new AutoValue_UserRaw.Builder();
    }

    public static TypeAdapter<UserRaw> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_UserRaw.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public interface Builder {

        Builder login(final String login);

        Builder id(final long id);

        Builder avatarUrl(final String avatarUrl);

        Builder gravatarId(final String gravatarId);

        Builder url(final String url);

        Builder htmlUrl(final String htmlUrl);

        Builder followersUrl(final String followersUrl);

        Builder followingUrl(final String followingUrl);

        Builder gistsUrl(final String gistsUrl);

        Builder starredUrl(final String starredUrl);

        Builder subscriptionsUrl(final String subscriptionsUrl);

        Builder organizationsUrl(final String organizationsUrl);

        Builder reposUrl(final String reposUrl);

        Builder eventsUrl(final String eventsUrl);

        Builder receivedEventsUrl(final String receivedEventsUrl);

        Builder type(final String type);

        Builder siteAdmin(final boolean siteAdmin);

        UserRaw build();
    }

}

package com.ahmedabdelmeged.githubarch.api;

import com.ahmedabdelmeged.githubarch.model.UserRaw;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ahmed Abd-Elmeged on 2/4/2018.
 */

public interface GithubService {

    /**
     * Get Github users
     *
     * @return list of the users raw data
     */
    @GET("users")
    Single<List<UserRaw>> getUsers();

    /**
     * Get next Github users page
     *
     * @param sinceUserId the user id to get the users after
     * @return list of users raw data
     */
    @GET("users")
    Single<List<UserRaw>> getUsers(@Query("since") long sinceUserId);

}

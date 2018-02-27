package com.ahmedabdelmeged.githubarch.repository;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */
public class NetworkState {

    @Status
    private int status;

    private String message;

    private NetworkState(@Status int status, String message) {
        this.status = status;
        this.message = message;
    }

    private NetworkState(int status) {
        this.status = status;
    }

    public static NetworkState LOADED = new NetworkState(Status.SUCCESS);

    public static NetworkState LOADING = new NetworkState(Status.RUNNING);

    public static NetworkState error(String message) {
        return new NetworkState(Status.FAILED, message == null ? "unknown error" : message);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}

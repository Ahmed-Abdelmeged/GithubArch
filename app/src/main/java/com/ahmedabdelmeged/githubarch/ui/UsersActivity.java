package com.ahmedabdelmeged.githubarch.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.adapter.UserAdapter;
import com.ahmedabdelmeged.githubarch.databinding.ActivityUsersBinding;
import com.ahmedabdelmeged.githubarch.repository.NetworkState;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class UsersActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    UserAdapter userAdapter;

    private UsersViewModel usersViewModel;

    private ActivityUsersBinding activityUsersBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        usersViewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel.class);
        initAdapter();
        initSwipeToRefresh();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        activityUsersBinding.usersRecyclerView.setLayoutManager(linearLayoutManager);
        activityUsersBinding.usersRecyclerView.setAdapter(userAdapter);

        usersViewModel.userList.observe(this, userAdapter::setList);

        usersViewModel.getNetworkState().observe(this, userAdapter::setNetworkState);
    }

    private void initSwipeToRefresh() {
        usersViewModel.getRefreshState().observe(this, networkState ->
                activityUsersBinding.usersSwipeRefreshLayout.setRefreshing(networkState.getStatus() == NetworkState.LOADING.getStatus()));

        activityUsersBinding.usersSwipeRefreshLayout.setOnRefreshListener(() -> usersViewModel.refresh());
    }

}

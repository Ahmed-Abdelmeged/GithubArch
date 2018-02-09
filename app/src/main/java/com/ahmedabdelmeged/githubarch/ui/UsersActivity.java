package com.ahmedabdelmeged.githubarch.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.adapter.UserAdapter;
import com.ahmedabdelmeged.githubarch.databinding.ActivityUsersBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class UsersActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUsersBinding activityUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        UsersViewModel usersViewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel.class);

        //init users recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        activityUsersBinding.usersRecyclerView.setLayoutManager(linearLayoutManager);
        activityUsersBinding.usersRecyclerView.setAdapter(userAdapter);

        usersViewModel.userList.observe(this, userAdapter::setList);
    }

}

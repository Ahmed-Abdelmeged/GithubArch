package com.ahmedabdelmeged.githubarch.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.adapter.UserAdapter;
import com.ahmedabdelmeged.githubarch.adapter.UserAdapterPaging;
import com.ahmedabdelmeged.githubarch.databinding.ActivityUsersBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

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
        //initUsersRecyclerView();
        activityUsersBinding.usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityUsersBinding.usersRecyclerView.setHasFixedSize(true);
        UserAdapterPaging userAdapterPaging = new UserAdapterPaging();

        usersViewModel.userList.observe(this, users -> {
            userAdapterPaging.setList(users);
            Timber.e(userAdapterPaging.getCurrentList().toString());
            Timber.e("I'm observing");
        });

        usersViewModel.getUsersLiveData().observe(this, userAdapter::updateUsers);

        activityUsersBinding.usersRecyclerView.setAdapter(userAdapterPaging);
    }

}

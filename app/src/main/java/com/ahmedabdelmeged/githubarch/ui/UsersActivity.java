package com.ahmedabdelmeged.githubarch.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.adapter.UserAdapter;
import com.ahmedabdelmeged.githubarch.common.AutoCompositeDisposable;
import com.ahmedabdelmeged.githubarch.databinding.ActivityUsersBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class UsersActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    UserAdapter userAdapter;

    private ActivityUsersBinding activityUsersBinding;
    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        usersViewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel.class);
        initUsersRecyclerView();

        AutoCompositeDisposable compositeDisposable = new AutoCompositeDisposable(this);
        usersViewModel.getUsersLiveData().observe(this, users -> compositeDisposable.add(userAdapter.updateUsers(users)));
    }

    private void initUsersRecyclerView() {
        activityUsersBinding.usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityUsersBinding.usersRecyclerView.setHasFixedSize(true);
        activityUsersBinding.usersRecyclerView.setAdapter(userAdapter);

        activityUsersBinding.usersRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();
                int lastPosition = layoutManager
                        .findLastVisibleItemPosition();
                if (lastPosition == userAdapter.getItemCount() - 1) {
                    usersViewModel.loadNextPage();
                }
            }
        });
    }

}

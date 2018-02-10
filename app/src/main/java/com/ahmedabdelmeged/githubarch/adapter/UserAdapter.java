package com.ahmedabdelmeged.githubarch.adapter;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.databinding.ItemUserBinding;
import com.ahmedabdelmeged.githubarch.model.User;
import com.ahmedabdelmeged.githubarch.repository.NetworkState;
import com.ahmedabdelmeged.githubarch.ui.UsersActivity;

import javax.inject.Inject;


/**
 * Created by Ahmed Abd-Elmeged on 2/8/2018.
 */
public class UserAdapter extends PagedListAdapter<User, UserViewHolder> {

    private UserClickHandler userClickHandler;

    @Inject
    public UserAdapter(UserDiffCallback userDiffCallback, UserClickHandler userClickHandler) {
        super(userDiffCallback);
        this.userClickHandler = userClickHandler;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_user, parent, false);
        return new UserViewHolder(itemUserBinding,userClickHandler);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public void setNetworkState(NetworkState networkState) {

    }

}

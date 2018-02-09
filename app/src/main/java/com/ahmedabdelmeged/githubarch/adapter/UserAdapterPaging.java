package com.ahmedabdelmeged.githubarch.adapter;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.databinding.ItemUserBinding;
import com.ahmedabdelmeged.githubarch.model.User;


import timber.log.Timber;

/**
 * Created by Ahmed Abd-Elmeged on 2/8/2018.
 */
public class UserAdapterPaging extends PagedListAdapter<User, UserViewHolder> {

    public UserAdapterPaging() {
        super(diffCallback);
        Timber.e(getItemCount() + "");
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_user, parent, false);
        Timber.e("view holder created");
        return new UserViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(getItem(position));
        Timber.e(getItemCount() + "");
    }

    private static final DiffCallback<User> diffCallback = new DiffCallback<User>() {
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            Timber.e("are the same");
            return oldItem.id() == newItem.id();
        }

        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            Timber.e("are content the same");
            return oldItem.equals(newItem);
        }
    };

}

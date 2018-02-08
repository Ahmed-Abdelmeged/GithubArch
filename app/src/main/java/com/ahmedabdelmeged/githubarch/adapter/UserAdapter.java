package com.ahmedabdelmeged.githubarch.adapter;


import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.common.AndroidPreconditions;
import com.ahmedabdelmeged.githubarch.databinding.ItemUserBinding;
import com.ahmedabdelmeged.githubarch.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ahmed Abd-Elmeged on 2/7/2018.
 */
public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    @NonNull
    private List<User> users = new ArrayList<>();

    @NonNull
    private AndroidPreconditions androidPreconditions;

    @Inject
    public UserAdapter(@NonNull AndroidPreconditions androidPreconditions) {
        this.androidPreconditions = androidPreconditions;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_user, parent, false);
        return new UserViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * Updates current users stored users in the adapter with new users
     *
     * @param users the new users from api
     */
    public Disposable updateUsers(@NonNull final List<User> users) {
        androidPreconditions.assertUiThread();

        if (this.users.isEmpty()) {
            return updateAllUsers(users);
        } else {
            return updateDiffUsersOnly(users);
        }
    }

    /**
     * Only use for the first update of the adapter, whe it is still empty.
     */
    private Disposable updateAllUsers(@NonNull final List<User> users) {
        return Single.just(users)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::updateItemsInModel)
                .subscribe(__ -> notifyDataSetChanged());
    }

    /**
     * Do not use for first update of the adapter. The method {@link DiffUtil.DiffResult#dispatchUpdatesTo(RecyclerView.Adapter)}
     * is significantly slower than {@link UserAdapter#notifyDataSetChanged()}
     * when it comes to update all the items in the adapter.
     */
    private Disposable updateDiffUsersOnly(@NonNull final List<User> users) {
        return Single.fromCallable(() -> DiffUtil.calculateDiff(new UserDiffCallback(this.users, users)))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(__ -> updateItemsInModel(users))
                .subscribe(diffResult -> diffResult.dispatchUpdatesTo(this));
    }

    private void updateItemsInModel(@NonNull final List<User> users) {
        this.users.addAll(users);
    }

}

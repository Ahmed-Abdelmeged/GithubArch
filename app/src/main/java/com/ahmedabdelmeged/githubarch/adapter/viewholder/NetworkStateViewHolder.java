package com.ahmedabdelmeged.githubarch.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ahmedabdelmeged.githubarch.R;
import com.ahmedabdelmeged.githubarch.adapter.callback.RetryCallback;
import com.ahmedabdelmeged.githubarch.databinding.ItemNetworkStateBinding;
import com.ahmedabdelmeged.githubarch.repository.NetworkState;

/**
 * Created by Ahmed Abd-Elmeged on 2/11/2018.
 */
public class NetworkStateViewHolder extends RecyclerView.ViewHolder {

    private final ItemNetworkStateBinding binding;

    private final RetryCallback retryCallback;

    private NetworkStateViewHolder(ItemNetworkStateBinding binding, RetryCallback retryCallback) {
        super(binding.getRoot());
        this.binding = binding;
        this.retryCallback = retryCallback;
    }

    public void bindTo(NetworkState networkState) {
        binding.setNetworkState(networkState);
        binding.setCallback(retryCallback);
        binding.executePendingBindings();
    }

    public static NetworkStateViewHolder create(ViewGroup parent, RetryCallback retryCallback) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNetworkStateBinding itemNetworkStateBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_network_state, parent, false);
        return new NetworkStateViewHolder(itemNetworkStateBinding, retryCallback);
    }

}

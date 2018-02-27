package com.ahmedabdelmeged.githubarch.adapter.callback;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.ahmedabdelmeged.githubarch.model.User;

import javax.inject.Inject;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */
public class UserClickHandler {

    @Inject
    public UserClickHandler() {
    }

    public void onUserClicked(View view, User user) {
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
        openUrlIntent.setData(android.net.Uri.parse(user.htmlUrl()));
        if (openUrlIntent.resolveActivity(view.getContext().getPackageManager()) != null) {
            view.getContext().startActivity(openUrlIntent);
        } else {
            Toast.makeText(view.getContext(), "Can't open user website", Toast.LENGTH_SHORT).show();
        }
    }

}

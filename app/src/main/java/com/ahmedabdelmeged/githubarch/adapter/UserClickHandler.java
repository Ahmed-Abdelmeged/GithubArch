package com.ahmedabdelmeged.githubarch.adapter;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.ahmedabdelmeged.githubarch.model.User;

/**
 * Created by Ahmed Abd-Elmeged on 2/10/2018.
 */
public class UserClickHandler {

    private Activity activity;

    public UserClickHandler(Activity activity) {
        this.activity = activity;
    }

    public void onUserClicked(User user) {
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
        openUrlIntent.setData(android.net.Uri.parse(user.htmlUrl()));
        if (openUrlIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(openUrlIntent);
        } else {
            Toast.makeText(activity, "Can't open user website", Toast.LENGTH_SHORT).show();
        }
    }

}

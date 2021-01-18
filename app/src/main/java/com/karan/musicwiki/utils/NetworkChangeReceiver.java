package com.karan.musicwiki.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.karan.musicwiki.activities.BaseActivity;


public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try
        {
            BaseActivity.showOfflineStrip(NetworkUtils.isNetworkAvailable(context));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

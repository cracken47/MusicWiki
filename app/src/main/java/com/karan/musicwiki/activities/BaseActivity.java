package com.karan.musicwiki.activities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;


import com.karan.musicwiki.utils.NetworkChangeReceiver;
import com.karan.musicwiki.utils.NotifierUtil;

import dagger.android.support.DaggerAppCompatActivity;

@SuppressLint("Registered")
public class BaseActivity extends DaggerAppCompatActivity {

    private BroadcastReceiver mNetworkReceiver;

    private static boolean isOfflineStripVisible = false;

    public static AppCompatActivity currentActivity;

    public static AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }



    @Override
    protected void onStart() {
        super.onStart();
        currentActivity = this;
        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();
    }

    public static void showOfflineStrip(boolean isNetworkAvailable){
        if (isNetworkAvailable) {
            if(isOfflineStripVisible){
                NotifierUtil.removeOfflineStrip(currentActivity);
                isOfflineStripVisible = false;
            }
        }else {
            if (!isOfflineStripVisible) {
                NotifierUtil.showOfflineStrip(currentActivity);
                isOfflineStripVisible = true;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isOfflineStripVisible) {
            NotifierUtil.removeOfflineStrip(currentActivity);
            isOfflineStripVisible = false;
        }
        unregisterNetworkChanges();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }

    private void registerNetworkBroadcastReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


}

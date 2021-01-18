package com.karan.musicwiki.utils;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.karan.musicwiki.R;


public class NotifierUtil {
    public static void showAlertDialog(Context activity, String title, String message, boolean isCancellable, String positiveButtonTitle, String negativeButtonTitle,
                                       DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        new AlertDialog.Builder(activity)
                .setTitle(Html.fromHtml("<font color='#676A6C'>" + title + "</font>"))
                .setMessage(message)
                .setPositiveButton(positiveButtonTitle, positiveListener)
                .setNegativeButton(negativeButtonTitle, negativeListener)
                .setCancelable(isCancellable)
                .create()
                .show();
    }

    public static void showOfflineStrip(AppCompatActivity activity) {
        ActivityManager am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;

        LinearLayout viewGroupWithErrorMsg = new LinearLayout(activity);

        viewGroupWithErrorMsg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        viewGroupWithErrorMsg.setOrientation(LinearLayout.VERTICAL);

        // Create your top view here
        TextView errorMsgTv;
        errorMsgTv = new TextView(activity);
        errorMsgTv.setId(R.id.reservedNamedId);
        errorMsgTv.setMinimumWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        errorMsgTv.setMinimumHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        errorMsgTv.setBackgroundColor(Color.RED);
        errorMsgTv.setTextColor(Color.WHITE);
        errorMsgTv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        errorMsgTv.setText("No Internet Connection");


        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);

        ((ViewGroup) activity
                .findViewById(android.R.id.content)).removeView(viewGroup);

        viewGroupWithErrorMsg.addView(errorMsgTv, 0);
        viewGroupWithErrorMsg.addView(viewGroup, 1);
        activity.setContentView(viewGroupWithErrorMsg);
    }

    public static void removeOfflineStrip(AppCompatActivity activity) {
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.removeViewAt(0);
    }
}

package com.karan.musicwiki.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static final String USER_ACCESS_TOKEN = "authToken";
    private static final String USER_REFRESH_TOKEN = "referToken";


    public static void setUserAccessToken(Context ctx, String token) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(USER_ACCESS_TOKEN, Context.MODE_PRIVATE).edit();
        editor.putString(USER_ACCESS_TOKEN, token);
        editor.apply();

    }

    public static String getUserAccessToken(Context ctx) {
        return ctx.getSharedPreferences(USER_ACCESS_TOKEN, Context.MODE_PRIVATE).getString(USER_ACCESS_TOKEN, "");
    }

    public static void setUserRefreshToken(Context ctx, String token) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(USER_REFRESH_TOKEN, Context.MODE_PRIVATE).edit();
        editor.putString(USER_REFRESH_TOKEN, token);
        editor.apply();

    }

    public static String getUserRefreshToken(Context ctx) {
        return ctx.getSharedPreferences(USER_REFRESH_TOKEN, Context.MODE_PRIVATE).getString(USER_REFRESH_TOKEN, "");
    }

    public static void removeRefreshToken(Context ctx) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(USER_REFRESH_TOKEN, 0).edit();
        editor.clear().apply();
    }

    public static void removeAccessToken(Context ctx) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(USER_ACCESS_TOKEN, 0).edit();
        editor.clear().apply();
    }

    public static void removeALlSharedPreferences(Context ctx) {
        removeAccessToken(ctx);
        removeRefreshToken(ctx);

    }

}

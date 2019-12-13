package com.idts.notenotes.libs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {
    private static String LOGGED_IN = "IsLoggedin";
    private static String USERNAME = "Username";
    private static String TOKEN = "Token";
    private static String USERID = "UserId";

    static SharedPreferences.Editor editor;

    private static SharedPreferences getPrefrences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setIsLoggedin(Context context, boolean isLoggedin) {
        editor = getPrefrences(context).edit();
        editor.putBoolean(SessionManager.LOGGED_IN, true);
        editor.commit();
        editor.apply();
    }

    public static void setUsername(Context context, String username) {
        editor = getPrefrences(context).edit();
        editor.putString(SessionManager.USERNAME, username);
        editor.apply();
        editor.commit();
    }

    public static void setToken(Context context, String token) {
        editor = getPrefrences(context).edit();
        editor.putString(SessionManager.TOKEN, token);
        editor.commit();
        editor.apply();
    }

    public static void setUserId(Context context, int id) {
        editor = getPrefrences(context).edit();
        editor.putInt(USERID, id);
        editor.apply();
        editor.commit();
    }

    public static boolean isLoggedin(Context context) {
        return getPrefrences(context).getBoolean(SessionManager.LOGGED_IN, false);
    }

    public static String getUsername(Context context) {
        return getPrefrences(context).getString(SessionManager.USERNAME, "");
    }

    public static String getToken(Context context) {
        return getPrefrences(context).getString(SessionManager.TOKEN, "");
    }

    public static int getUserId(Context context) {
        return getPrefrences(context).getInt(USERID, 0);
    }

    public static void clear(Context context) {
        editor = getPrefrences(context).edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }
}

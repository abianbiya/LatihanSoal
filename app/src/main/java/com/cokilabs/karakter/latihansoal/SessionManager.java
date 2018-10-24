package com.cokilabs.karakter.latihansoal;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


@SuppressLint("CommitPrefEdits")
public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "appSession";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_NEMBE_NGINSTALL = "isNembe";
    public static final int LOGIN_FACEBOOK = 1;
    public static final int LOGIN_GOOGLE = 1;


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setSessionLevel(String level){
        editor.putString("level", level);
        editor.commit();
    }

    public String getSessionLevel(){
        return pref.getString("level", "");
    }

    public boolean isNembeNginstall() {
        return pref.getBoolean(KEY_IS_NEMBE_NGINSTALL, true);
    }

    public void setIsNembeNginstall(boolean isNembe) {
        editor.putBoolean(KEY_IS_NEMBE_NGINSTALL, isNembe);
        editor.commit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setLoginBy(int i){
        editor.putInt("loginBy", i);
        editor.commit();
    }

    public int getLoginBy(){
        return pref.getInt("loginBy", 0);
    }

    public void saveLogedInProfile(User profile) {
        Gson gson = new Gson();
        String obj = gson.toJson(profile);
        editor.putString("user", obj);
        editor.commit();
    }

    public User getLogedInProfile() {
        Gson gson = new Gson();
        String jsn = pref.getString("user", " ");

        return gson.fromJson(jsn, User.class);
    }

    public void deleteLoggedInProfile(){
        editor.remove("user");
        editor.commit();
    }


}

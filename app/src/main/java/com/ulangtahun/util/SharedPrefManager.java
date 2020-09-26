package com.ulangtahun.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ulangtahun.model.LoginRecord;


public class SharedPrefManager {

    private static SharedPrefManager INSTANCE;
    private static Context context;

    private static String PREF_NAME_LOGIN = "pref_name_login";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_NAMA = "key_nama";
    private static final String KEY_TGL = "key_tgl";
    private static final String KEY_TOKEN = "key_token";

//    private static final String KEY_TOKEN = "key_token";

    private SharedPrefManager(Context context) {
        SharedPrefManager.context = context;
    }

    public static synchronized  SharedPrefManager getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new SharedPrefManager(context);
        }
        return INSTANCE;
    }


    public void userLogin(LoginRecord loginRecord){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, loginRecord.getEmail());
        editor.putString(KEY_NAMA, loginRecord.getNama());
        editor.putString(KEY_TGL, loginRecord.getTglLahir());
        editor.putString(KEY_TOKEN, loginRecord.getToken());
        editor.apply();
    }

    public static LoginRecord getUser(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        return new LoginRecord(
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_TGL, null),
                sharedPreferences.getString(KEY_TOKEN, null)
        );
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAMA, null) != null;
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
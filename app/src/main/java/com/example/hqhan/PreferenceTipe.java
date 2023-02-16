package com.example.hqhan;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceTipe {
    static final String Tipe_TEREGISTER ="Tipe";


    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static void setRegisteredTipe(Context context, String tipe){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(Tipe_TEREGISTER, tipe);
        editor.apply();
    }
    public static String getRegisteredTipe(Context context){
        return getSharedPreference(context).getString(Tipe_TEREGISTER,"");
    }


}

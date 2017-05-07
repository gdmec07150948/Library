package com.example.z_h_q.mycollege.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Z-H-Q on 2017/3/25.
 */
public class PrefUtils {
    public static boolean getBoolean(Context context,String key,boolean defValue){
        SharedPreferences sp =  context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
    public static void setBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
}

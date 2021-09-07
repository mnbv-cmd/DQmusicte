package com.example.dqmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.dqmusic.constants.Spconstants;
import com.example.dqmusic.helps.sphelp;

public class sputils {
    public static boolean saveuser(Context context,String phone)
    {
       SharedPreferences sp= context.getSharedPreferences(Spconstants.sp_use,Context.MODE_PRIVATE);
     SharedPreferences.Editor editor=  sp.edit();
     editor.putString(Spconstants.sp_keyphone,phone);
      boolean result=editor.commit();
      return result;
    }
    public static  boolean isloginuser(Context context)
    {
         boolean result=false;
        SharedPreferences sp= context.getSharedPreferences(Spconstants.sp_use,Context.MODE_PRIVATE);
        String phone=sp.getString(Spconstants.sp_keyphone,"");
        if(!TextUtils.isEmpty(phone))
        {
            result=true;
            sphelp.getInstance().setPhone(phone);
        }
        return result;
    }
    public static boolean removeuser(Context context)
    {
        SharedPreferences sp= context.getSharedPreferences(Spconstants.sp_use,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=  sp.edit();
        editor.remove(Spconstants.sp_keyphone);
        return editor.commit();
    }
}

package com.example.dqmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.example.dqmusic.Activitys.LoginActivity;
import com.example.dqmusic.Activitys.meActivity;
import com.example.dqmusic.Models.UserModel;
import com.example.dqmusic.R;
import com.example.dqmusic.helps.Realmhelp;
import com.example.dqmusic.helps.sphelp;

import org.w3c.dom.Text;

import io.realm.RealmResults;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class Userutils {
    public static boolean isloginactivty(String phone, String password, Context context)
    {

        if(!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "手机号不合法", Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(context,"请输入密码",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!isregistusers(phone))
        {
            Toast.makeText(context,"手机号未注册",Toast.LENGTH_LONG).show();
            return false;
        }
       if(!isrightuser(phone,password))
        {
            Toast.makeText(context,"手机号或密码不正确",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!sputils.saveuser(context,phone))
        {
            Toast.makeText(context,"系统错误",Toast.LENGTH_LONG).show();
            return false;
        }
        //保存到sphelp中
        sphelp.getInstance().setPhone(phone);
        //保存音乐源数据
        Realmhelp realmhelp=new Realmhelp();
        realmhelp.setdatasouce(context);
        realmhelp.closeuser();
            return true;
    }
    public static  void outme(Context context)
    {
        if(!sputils.removeuser(context))
        {
            Toast.makeText(context,"系统错误",Toast.LENGTH_LONG).show();
            return ;
        }
        Realmhelp realmhelp=new Realmhelp();
        realmhelp.removedatasouce();
        realmhelp.closeuser();
        Intent intent=new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }
    public static  boolean register(Context context,String phone,String password,String comfirmpassword)
    {
        if(!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "手机号不合法", Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(password)||!password.equals(comfirmpassword))
        {
            Toast.makeText(context, "请确认密码", Toast.LENGTH_LONG).show();
            return false;
        }
        /**
         * 是否已经被注册
         */
         if(isregistusers(phone))
         {
             Toast.makeText(context, "手机号已被注册", Toast.LENGTH_LONG).show();
             return false;
         }
        /*
        保存密码
         */
        UserModel userModel=new UserModel();
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        userModel.setPhone(phone);
        saveuser(userModel);
        return true;
    }
    private static void saveuser(UserModel userModel)
    {
        Realmhelp realmhelp=new Realmhelp();
        realmhelp.saveuser(userModel);
        realmhelp.closeuser();
    }
    public static boolean isregistusers(String phone)
    {
        boolean result=false;
        Realmhelp realmhelp=new Realmhelp();
        RealmResults<UserModel> list=realmhelp.getalluser();
        for (UserModel use:list)
        {
            if(use.getPhone().equals(phone))
            {
                result=true;
                break;
            }
        }
        realmhelp.closeuser();
        return result;
    }
    public static boolean isrightuser(String phone,String password)
    {
        boolean result=false;
        Realmhelp realmhelp=new Realmhelp();
      UserModel userModel= realmhelp.getAuser(phone,password);
      if(userModel!=null)
      {
          result=true;
      }
        realmhelp.closeuser();
        return result;

    }
    public static boolean islogined(Context context)
    {
        return sputils.isloginuser(context);
    }
    public static boolean changeame(Context context,String oldpassword,String newpassword,String confirnewpassword)
    {
        if(TextUtils.isEmpty(oldpassword))
        {
            Toast.makeText(context,"请输入原密码",Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(newpassword))
        {
            Toast.makeText(context,"请输入新密码",Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(confirnewpassword)||!newpassword.equals(confirnewpassword))
        {
            Toast.makeText(context,"新密码与确认新密码不符合",Toast.LENGTH_LONG).show();
            return false;
        }
        Realmhelp realmhelp=new Realmhelp();
        UserModel userModel=realmhelp.getAuserpassword(sphelp.getInstance().getPhone());
        if(!userModel.getPassword().equals(EncryptUtils.encryptMD5ToString(oldpassword)))
        {
            Toast.makeText(context,"原密码错误",Toast.LENGTH_LONG).show();
            return false;
        }
        realmhelp.chagepassword(newpassword,sphelp.getInstance().getPhone());
        return  true;
    }


}

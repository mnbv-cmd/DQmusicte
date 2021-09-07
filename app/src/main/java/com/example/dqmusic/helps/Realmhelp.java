package com.example.dqmusic.helps;

import android.content.Context;

import com.blankj.utilcode.util.EncryptUtils;
import com.example.dqmusic.Models.UserModel;
import com.example.dqmusic.Models.albumModel;
import com.example.dqmusic.Models.musicmodel;
import com.example.dqmusic.utils.datautils;
import com.example.dqmusic.Models.souceModel;

import java.io.FileNotFoundException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import migration.Migration;

public class Realmhelp {
  private   Realm mrealm;
    public  Realmhelp()
    {
        mrealm=Realm.getDefaultInstance();
    }
    public void saveuser(UserModel model)
    {
        mrealm.beginTransaction();
        mrealm.insert(model);
        mrealm.commitTransaction();
    }
    public void  closeuser()
    {
        if(mrealm!=null&&!mrealm.isClosed())
        {
            mrealm.close();
        }
    }
    private static RealmConfiguration getrealmconf()
    {
        return  new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
    }
    /*
    告诉需要迁移，并更新最新配置
     */
    public static void migration()
    {
        RealmConfiguration configuration=getrealmconf();
        /*
        设置最新配置
         */
        Realm.setDefaultConfiguration(configuration);
        /*
        告诉需要转移
         */
        try {
            Realm.migrateRealm(configuration);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public RealmResults<UserModel> getalluser()
    {
        RealmQuery<UserModel> query=mrealm.where(UserModel.class);
        RealmResults<UserModel> list=query.findAll();
        return list;
    }
    public UserModel getAuser(String phone,String password)
    {
        RealmQuery<UserModel> query=mrealm.where(UserModel.class).equalTo("phone",phone).equalTo("password", EncryptUtils.encryptMD5ToString(password));
        return query.findFirst();
    }
    public UserModel getAuserpassword(String phone)
    {
        RealmQuery<UserModel> query=mrealm.where(UserModel.class).equalTo("phone",phone);
        return query.findFirst();
    }
    public void chagepassword(String password,String phone)
    {
        mrealm.beginTransaction();
        RealmQuery<UserModel> query=mrealm.where(UserModel.class).equalTo("phone",phone);
        UserModel userModel= query.findFirst();
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        mrealm.commitTransaction();
    }
    /*
    保存音乐源数据
    的到string
    然后createObjectfromjison()
     */
    public void setdatasouce(Context context)
    {
        String data= datautils.getdatajoson(context,"DataSource.json");
        mrealm.beginTransaction();
        mrealm.createObjectFromJson(souceModel.class,data);
        mrealm.commitTransaction();
    }
    /*
    删除数据源
     */
    public void removedatasouce()
    {
        mrealm.beginTransaction();
        mrealm.delete(souceModel.class);
        mrealm.delete(albumModel.class);
        mrealm.delete(musicmodel.class);
        mrealm.commitTransaction();
    }
    public souceModel getsouceModel()
    {
        return mrealm.where(souceModel.class).findFirst();
    }
    public albumModel getalbumModel(String albumid)
    {
        return mrealm.where(albumModel.class).equalTo("albumId",albumid).findFirst();
    }
    public musicmodel getmusicModel(String musicid)
    {
        return mrealm.where(musicmodel.class).equalTo("musicId",musicid).findFirst();
    }
}

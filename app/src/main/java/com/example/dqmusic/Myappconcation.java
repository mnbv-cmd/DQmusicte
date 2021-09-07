package com.example.dqmusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.dqmusic.helps.Realmhelp;

import io.realm.Realm;

public class Myappconcation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        Realm.init(this);
        Realmhelp.migration();
    }
}

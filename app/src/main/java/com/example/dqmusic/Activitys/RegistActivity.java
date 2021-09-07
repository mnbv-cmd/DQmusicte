package com.example.dqmusic.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dqmusic.R;
import com.example.dqmusic.utils.Userutils;
import com.example.dqmusic.views.Inputview;

public class RegistActivity extends Baseactivity {
private Inputview phone,password,confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initview();
    }
    private void initview()
    {
        initbar(true,"注册",false);
       phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.passwordconfrirn);
    }
    public void commit(View view)
    {
        /**
         * 1.验证用户合法性
         * 是否手机号合法
         * 是否密码与确认密码一直和空
         *是否已经被注册
         * 2.保存到realm数据库
         */
        String phone1=phone.gettext();
       String password1=password.gettext();
        String confirmpassword1=confirmpassword.gettext();
       boolean result= Userutils.register(this,phone1,password1,confirmpassword1);
       if(!result)return;
        onBackPressed();
    }
}
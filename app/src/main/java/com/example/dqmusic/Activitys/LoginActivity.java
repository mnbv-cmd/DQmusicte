package com.example.dqmusic.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dqmusic.R;
import com.example.dqmusic.utils.Userutils;
import com.example.dqmusic.views.Inputview;

public class LoginActivity extends Baseactivity {
private  Inputview phone;
private  Inputview password;
private String phone1,password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
    }
    private void initview()
    {
        initbar(false,"登陆",false);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
    }
    public void login(View view)
    {
        phone1=phone.gettext();
        password1=password.gettext();
       if(!Userutils.isloginactivty(phone1,password1,this))
            return;
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void reginst(View view)
    {
        Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(intent);
    }
}
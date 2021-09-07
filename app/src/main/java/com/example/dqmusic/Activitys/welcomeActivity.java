package com.example.dqmusic.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.dqmusic.R;
import com.example.dqmusic.utils.Userutils;

import java.util.Timer;
import java.util.TimerTask;

public class welcomeActivity extends AppCompatActivity {
private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        init();
    }
    /*
    *******初始化
     */
    private void  init()
    {
      final boolean islogin= Userutils.islogined(this);
       timer=new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
              if(islogin)
              ToMain();
               else {
               ToLogin();

           }
             //  Log.d("welcomeActivity", ""+Thread.currentThread());
           }
       },3*1000);
    }
    /*
    跳转到mainactivity
     */
    private void ToMain()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    /*
    跳转到loginactivity
     */
    private void ToLogin()
    {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
package com.example.dqmusic.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dqmusic.R;
import com.example.dqmusic.helps.sphelp;
import com.example.dqmusic.utils.Userutils;

public class meActivity extends Baseactivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initview();
        textView.setText("用户名："+sphelp.getInstance().getPhone());
    }
    private void initview()
    {
        initbar(true,"我的",false);
        textView=findViewById(R.id.userphone);
    }
    public void changeme (View view)
    {
       startActivity(new Intent(meActivity.this,Changeactivity.class));
    }
    public void backlogin (View view)
    {
        Userutils.outme(this);
    }
}
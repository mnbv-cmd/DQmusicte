package com.example.dqmusic.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dqmusic.R;
import com.example.dqmusic.utils.Userutils;
import com.example.dqmusic.views.Inputview;

public class Changeactivity extends Baseactivity{
private Inputview new1,old1,confirmnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeactivity);
        initview();
    }
    private void initview() {
        initbar(true, "修改密码", false);
        old1=findViewById(R.id.phone);
        new1=findViewById(R.id.password);
        confirmnew=findViewById(R.id.passwordconfrirn);
    }
    public void commit (View view)
    {
        String newpassword=new1.gettext();
        String  oldpassword=old1.gettext();
        String newconfirmpassword=confirmnew.gettext();
        if(!Userutils.changeame(this,oldpassword,newpassword,newconfirmpassword))
            return;

        Intent intent=new Intent(Changeactivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
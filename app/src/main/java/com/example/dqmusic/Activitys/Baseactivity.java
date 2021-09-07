package com.example.dqmusic.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dqmusic.R;

public class Baseactivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView,imageView2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    protected void initbar (Boolean isback,String text,Boolean isme)
    {
        textView=findViewById(R.id.texttile);
        imageView=findViewById(R.id.back);
        imageView2=findViewById(R.id.me);
        imageView.setVisibility(isback ? View.VISIBLE:View.GONE);
        imageView2.setVisibility(isme ? View.VISIBLE:View.GONE);
        textView.setText(text);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(Baseactivity.this,meActivity.class));
            }
        });
    }
}

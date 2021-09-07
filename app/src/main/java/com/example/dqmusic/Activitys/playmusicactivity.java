package com.example.dqmusic.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dqmusic.Models.albumModel;
import com.example.dqmusic.Models.musicmodel;
import com.example.dqmusic.R;
import com.example.dqmusic.adapters.MusicListadptar;
import com.example.dqmusic.helps.Realmhelp;
import com.example.dqmusic.views.playmusicview;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class playmusicactivity extends Baseactivity {
ImageView imageView;
private playmusicview playmusicview;
private musicmodel musicmodel;
private Realmhelp realmhelp;
private String musicid;
private TextView name,author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmusicactivity);
        /*
        隐藏stausbar
         */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initview();
    }
    private  void initData()
    {
        Intent intent=getIntent();
        musicid=intent.getStringExtra(MusicListadptar.musicid);
        realmhelp=new Realmhelp();
       musicmodel= realmhelp.getmusicModel(musicid);
    }
    private void  initview()
    {
        imageView=findViewById(R.id.bigplayimageview);
        name=findViewById(R.id.name);
        author=findViewById(R.id.author);
        Glide.with(this)
                .load(musicmodel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(imageView);
        playmusicview=findViewById(R.id.playmusic__view);
        playmusicview.setmuisciocio(musicmodel.getPoster());
        playmusicview.startmusic(musicmodel.getPath());
        name.setText(musicmodel.getName());
        author.setText(musicmodel.getAuthor());
    }
    public void backpaly(View view)
    {
        onBackPressed();
    }
}
//palymusicactivity中有playview的自定义控件的。里面由于多方法接口。供activity使用。如startmuic------setiocio加载circleImage的图片资源
//activity布局有一个全局的高斯模糊背景图片，一个返回的back Imageview,一个palyview,两个textview歌曲歌名。
//playview自定义控件，有一个单例类midiahelp,有一个miederplayer的类。里面监听。判断，如果在播音乐，就调用midiahlep.stop,如果没有没有就meidia.startmusic.
//startmusic。如果path和mediahelp.getpath()相等，接直接mediahelp.playmusic.如何不相等就mediahelp.setpath().meadiaplayer.reset().
//有一个接口，定义接受具体接受某一方法方法。然后监听（准备好）就执行的调用某一方法（onparepared）。然后如果监听到就执行具体做法palymuisc().
//动画：view.startAnimation(),AnimationUtils.loadAnimation(mcontext,R.anim.circleanmiation)得到animation,动画sacle伸缩。
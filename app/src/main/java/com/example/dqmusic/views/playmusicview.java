package com.example.dqmusic.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.dqmusic.R;
import com.example.dqmusic.helps.mediaplayhlep;
import com.example.dqmusic.utils.Userutils;

import static com.example.dqmusic.R.anim.circleanmiation;

public class playmusicview extends FrameLayout {
    private Context mcontext;
    private mediaplayhlep mediaplayhlep;
    private View mview;
    private ImageView imageView;
    private ImageView imageViewneedle;
    private ImageView play;
    private Animation circleanimation1,needlestartanimation1,needlestopanimation1;
    private Boolean isplaymuisc;
    private String mpath;
    public playmusicview(@NonNull Context context) {
        super(context);
        initview(context);
    }

    public playmusicview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public playmusicview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public playmusicview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initview(context);
    }
    private void initview(Context context)
    {
        mcontext=context;
        mview= LayoutInflater.from(mcontext).inflate(R.layout.playinputview,this,false);
        addView(mview);
        imageView=mview.findViewById(R.id.circlerimageview);
        imageViewneedle=mview.findViewById(R.id.needle);
        play=mview.findViewById(R.id.paly1);
        mediaplayhlep= com.example.dqmusic.helps.mediaplayhlep.getInstance(mcontext);
        /*
    定义动画执行的动画
    1.圆盘旋转的动画
    2.指针旋转的的动画
    3.指针停止的动画
    使用startanimation执行
     */
     circleanimation1= AnimationUtils.loadAnimation(mcontext,R.anim.circleanmiation);
     needlestartanimation1=AnimationUtils.loadAnimation(mcontext,R.anim.needleanimation);
     needlestopanimation1=AnimationUtils.loadAnimation(mcontext,R.anim.needlestopanmation);
        mview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });
    }
    private void trigger()
    {
        if(isplaymuisc)
        {
            stopmusic();
        }
        else {
            startmusic(mpath);
        }
    }
   public void startmusic(String path)
   {
       mpath=path;
       isplaymuisc=true;
       imageView.startAnimation(circleanimation1);
       imageViewneedle.startAnimation(needlestartanimation1);
       play.setVisibility(View.GONE);
       /*
       看播放音乐是否为当前音乐
       如果是start
       如果不是setpath
        */
         if(mediaplayhlep.getMpath()!=null&&mediaplayhlep.getMpath().equals(path))
         {
             mediaplayhlep.playmusic();
         }
         else {
             mediaplayhlep.setpath(path);
             mediaplayhlep.setInmediaplayhleplistenser(new mediaplayhlep.inmediaplayhleplistenser() {
                 @Override
                 public void onPrepared(MediaPlayer mp) {
                     mediaplayhlep.playmusic();
                 }
             });
         }
   }
   public void stopmusic()
   {
       isplaymuisc=false;
       imageView.clearAnimation();
       imageViewneedle.startAnimation(needlestopanimation1);
       play.setVisibility(View.VISIBLE);
       mediaplayhlep.pause();
   }
    public void  setmuisciocio(String icio)
    {
        Glide.with(mcontext)
                .load(icio)
                .into(imageView);
    }
}

package com.example.dqmusic.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class mediaplayhlep {
    private String mpath;
    private MediaPlayer mediaPlayer;
    private Context mcontext;
    private static mediaplayhlep intance;
    private inmediaplayhleplistenser inmediaplayhleplistenser;
    public static mediaplayhlep getInstance(Context context)
    {
        if(intance==null)
        {
            synchronized (mediaplayhlep.class)
            {
                if(intance==null)
                {
                    intance=new mediaplayhlep(context);
                }
            }
        }
        return intance;
    }
    private mediaplayhlep(Context context)
    {
        mcontext=context;
        mediaPlayer=new MediaPlayer();
    }
    public  void setpath(String path)
    {
        /*
        如果音乐正在播放重置
        设置播放路径
        准备
         */
        if(mediaPlayer.isPlaying()||mpath!=path)
        {
            mediaPlayer.reset();
        }
        mpath=path;
        /*
        路径相等不管
        不同reset
         */
        try {
            mediaPlayer.setDataSource(mcontext,Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                     if(inmediaplayhleplistenser!=null)
                     {
                         inmediaplayhleplistenser.onPrepared(mp);
                     }
            }
        });
    }

    public void setInmediaplayhleplistenser(mediaplayhlep.inmediaplayhleplistenser inmediaplayhleplistenser) {
        this.inmediaplayhleplistenser = inmediaplayhleplistenser;
    }

    public String getMpath() {
        return mpath;
    }

    public interface inmediaplayhleplistenser
    {
        void onPrepared(MediaPlayer mp);
    }
    public void playmusic()
    {
        if(mediaPlayer.isPlaying())return;
        mediaPlayer.start();
    }
    public void pause()
    {
        mediaPlayer.pause();
    }
}

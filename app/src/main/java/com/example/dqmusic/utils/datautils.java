package com.example.dqmusic.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.loader.AssetsProvider;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class datautils {
    public static String getdatajoson(Context context,String filename)
    {
        /*
        stringbuilder()
        context.getassets得到assetsmanger资源管理器
        assetsmanger.open()得到输入流
        inputstreamReader得到
        得到bufferReader()BufferedReader
        给stingbulder();
         */
        StringBuilder builder=new StringBuilder();
        AssetManager manager=context.getAssets();
        try {
            InputStream inputStream= manager.open(filename);
            InputStreamReader reader=new InputStreamReader(inputStream);
            BufferedReader bufferedInputStream=new BufferedReader(reader);
            String line;
            while ((line=bufferedInputStream.readLine())!=null)
            {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     return builder.toString();
    }
}

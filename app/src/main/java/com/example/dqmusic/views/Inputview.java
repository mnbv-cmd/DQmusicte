package com.example.dqmusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.dqmusic.R;

import java.lang.reflect.Type;

public class Inputview extends FrameLayout {
    private   View mview;
    private ImageView imageView;
    private EditText editText;
    private int icio;
    private String hint;
    private boolean ispassword;
    public Inputview(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public Inputview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public Inputview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Inputview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attr)
    {
       /*
       获取自定义的属性
        */
      TypedArray typedArray=context.obtainStyledAttributes(attr, R.styleable.Inputview);
      icio=typedArray.getResourceId(R.styleable.Inputview_icion,R.mipmap.image1);
      hint=typedArray.getString(R.styleable.Inputview_hint);
      ispassword=typedArray.getBoolean(R.styleable.Inputview_ispassword,false);
      typedArray.recycle();
      /*
      得到布局
       */
      mview= LayoutInflater.from(context).inflate(R.layout.inputview,this,false);
      imageView=mview.findViewById(R.id.icio);
      editText=mview.findViewById(R.id.edittext);
      /*
      绑定布局
       */
      imageView.setImageResource(icio);
      editText.setHint(hint);
      editText.setInputType(ispassword ? InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD: InputType.TYPE_CLASS_PHONE);
      addView(mview);
    }
    public String gettext()
    {
        return editText.getText().toString().trim();
    }
}

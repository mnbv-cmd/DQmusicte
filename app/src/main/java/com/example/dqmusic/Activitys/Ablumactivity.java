package com.example.dqmusic.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dqmusic.Models.albumModel;
import com.example.dqmusic.Models.musicmodel;
import com.example.dqmusic.Models.souceModel;
import com.example.dqmusic.R;
import com.example.dqmusic.adapters.MusicListadptar;
import com.example.dqmusic.adapters.Musicadpter;
import com.example.dqmusic.helps.Realmhelp;

import java.util.List;

public class Ablumactivity extends Baseactivity {
private RecyclerView recyclerView;
private MusicListadptar listadptar;
private albumModel albumModel;
private Realmhelp realmhelp;
private String albumid;
private List<musicmodel> list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ablumactivity);
        initData();
        initview();

    }
    private void initData()
    {
        Intent intent=getIntent();
        albumid=intent.getStringExtra(Musicadpter.abulmid);
        realmhelp=new Realmhelp();
       albumModel= realmhelp.getalbumModel(albumid);
       list1=albumModel.getList();
    }
    private void initview()
    {
        initbar(true,"专辑列表",false);
        recyclerView=findViewById(R.id.List_view);
        listadptar=new MusicListadptar(this,null,list1);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listadptar);
    }

    @Override
    protected void onDestroy() {
        realmhelp.closeuser();
        super.onDestroy();
    }
}
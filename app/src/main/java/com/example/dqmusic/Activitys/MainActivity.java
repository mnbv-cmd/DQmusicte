package com.example.dqmusic.Activitys;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dqmusic.Models.albumModel;
import com.example.dqmusic.Models.musicmodel;
import com.example.dqmusic.Models.souceModel;
import com.example.dqmusic.R;
import com.example.dqmusic.adapters.MusicListadptar;
import com.example.dqmusic.adapters.Musicadpter;
import com.example.dqmusic.helps.Realmhelp;
import com.example.dqmusic.views.GridspaceDecoration;

import java.util.List;

public class MainActivity extends Baseactivity {
private RecyclerView recyclerGrid,recrclerList;
private MusicListadptar muisclistAdpter;
private  Musicadpter musicadpter;
private souceModel souceModel;
private Realmhelp realmhelp;
private List<albumModel> list;
private List<musicmodel> list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initview();
    }
    private void initData()
    {
        realmhelp=new Realmhelp();
       souceModel= realmhelp.getsouceModel();
       list=souceModel.getAlbum();
       list1=souceModel.getHot();
    }

    private void initview()
    {
        initbar(false,"DQ音乐",true);
        recyclerGrid=findViewById(R.id.Grid_view);
        recyclerGrid.addItemDecoration(new GridspaceDecoration(getResources().getDimensionPixelSize(R.dimen.Gridspace)));
        recyclerGrid.setLayoutManager(new GridLayoutManager(this,3));
        musicadpter=new Musicadpter(this,list);
        recyclerGrid.setNestedScrollingEnabled(false);
        recyclerGrid.setAdapter(musicadpter);
        recrclerList=findViewById(R.id.List_view);
        muisclistAdpter=new MusicListadptar(this,recrclerList,list1);
        recrclerList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recrclerList.setLayoutManager(new LinearLayoutManager(this));
        recrclerList.setNestedScrollingEnabled(false);
       recrclerList.setAdapter(muisclistAdpter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmhelp.closeuser();
    }
}
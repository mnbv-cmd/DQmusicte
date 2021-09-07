package com.example.dqmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dqmusic.Activitys.Ablumactivity;
import com.example.dqmusic.Models.albumModel;
import com.example.dqmusic.R;

import java.util.List;

public class Musicadpter extends RecyclerView.Adapter<Musicadpter.Viewholder> {
    public static String abulmid="ablumid";
    private Context mcotext;
    private View mview;
    private List<albumModel> albumModelList;
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mview=LayoutInflater.from(mcotext).inflate(R.layout.music_recylegird,parent,false);
        Viewholder viewholder=new Viewholder(mview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final albumModel albumModel=albumModelList.get(position);
        holder.number.setText(albumModel.getPlayNum());
        holder.name.setText(albumModel.getName());
       Glide.with(mcotext)
                .load(albumModel.getPoster())
                .into(holder.imageView);
       mview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(mcotext, Ablumactivity.class);
               intent.putExtra(abulmid,albumModel.getAlbumId());
               mcotext.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return albumModelList.size();
    }

    public Musicadpter(Context context,List<albumModel> albumModelList) {
        mcotext=context;
        this.albumModelList=albumModelList;
    }

    class Viewholder extends RecyclerView.ViewHolder
    {
        TextView number;
        TextView name;
       ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
           imageView=mview.findViewById(R.id.imagegrid);
           name=mview.findViewById(R.id.name);
           number=mview.findViewById(R.id.number);
        }
    }
}

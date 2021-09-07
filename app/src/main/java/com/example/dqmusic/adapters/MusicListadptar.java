package com.example.dqmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dqmusic.Activitys.playmusicactivity;
import com.example.dqmusic.Models.musicmodel;
import com.example.dqmusic.R;

import java.util.List;

public class MusicListadptar extends RecyclerView.Adapter<MusicListadptar.ViewHolder> {
    private Context mcontext;
    private RecyclerView mrecyclerView;
    private View mview;
    private boolean isgetrecyclerhight;
    private List<musicmodel>list;
    public static String musicid="muiscid";
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mview= LayoutInflater.from(mcontext).inflate(R.layout.music_recylelist,parent,false);
        ViewHolder viewHolder= new ViewHolder(mview);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      getrecyclerheight();
      final musicmodel musicmodel=list.get(position);
      holder.author.setText(musicmodel.getAuthor());
      holder.name.setText(musicmodel.getName());
      mview.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(mcontext, playmusicactivity.class);
              intent.putExtra(musicid,musicmodel.getMusicId());
              mcontext.startActivity(intent);
          }
      });
        Glide.with(mcontext)
                .load(musicmodel.getPoster())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public MusicListadptar(Context context,RecyclerView recyclerView,List<musicmodel> list) {
        mcontext=context;
        mrecyclerView=recyclerView;
        this.list=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView name,author;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagelist);
            name=itemView.findViewById(R.id.name);
            author=itemView.findViewById(R.id.author);
        }
    }
  private void getrecyclerheight()
    {
          if(isgetrecyclerhight||mrecyclerView==null) return;
           isgetrecyclerhight=true;
         //   LinearLayout.LayoutParams itemlp = (LinearLayout.LayoutParams) mview.getLayoutParams();
            int count=getItemCount();
        //   int height1=itemlp.height*count;
            LinearLayout.LayoutParams rvlp = (LinearLayout.LayoutParams) mrecyclerView.getLayoutParams();
           rvlp.height = rvlp.height*3;
            mrecyclerView.setLayoutParams(rvlp);

    }
}

package com.example.dqmusic.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridspaceDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public GridspaceDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=space;
       /* if(parent.getChildLayoutPosition(view)%3==0)
            outRect.left=0;*/
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)parent.getLayoutParams();
        params.leftMargin=-space;
        parent.setLayoutParams(params);
    }
}


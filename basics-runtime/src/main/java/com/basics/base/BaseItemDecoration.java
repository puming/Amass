package com.basics.base;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author pm
 */
public class BaseItemDecoration extends RecyclerView.ItemDecoration {
    private int orientation = LinearLayoutManager.VERTICAL;
    private int defGap = 20;
    private int gap = defGap;

    public BaseItemDecoration() {
        this(LinearLayoutManager.VERTICAL, 10);
    }

    public BaseItemDecoration(int orientation) {
        this(orientation, 10);
    }

    public BaseItemDecoration(int orientation, int gap) {
        super();
        this.orientation = orientation;
        this.gap = gap;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (orientation == LinearLayoutManager.VERTICAL) {
            if (parent.getChildAdapterPosition(view) < parent.getLayoutManager().getChildCount() - 1) {
                outRect.set(0, gap, 0, gap);
            }else {
                outRect.set(0, gap, 0, gap);
            }
        } else if (orientation == LinearLayoutManager.HORIZONTAL) {
            if (parent.getChildAdapterPosition(view) < parent.getLayoutManager().getChildCount() - 1) {
                outRect.set(gap, 0, gap, 0);
            }else {
                outRect.set(gap, 0, 0, 0);
            }
        }

    }
}

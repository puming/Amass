package com.pm.amass.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author pm
 * @date 18-4-23
 */

public class ItemSpace extends RecyclerView.ItemDecoration {
    private Context mContext;
    /**
     * item之间的间距
     */
    private int mSpace = 0;
    /**
     * 边距
     */
    private int mEdgeSpace;

    public ItemSpace(Context context) {
        this(context, 0);
    }

    public ItemSpace(Context mContext, int edgeSpace) {
        this.mContext = mContext;
        this.mEdgeSpace = edgeSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        GridLayoutManager gridLayoutManager = null;
        LinearLayoutManager linearLayoutManager = null;
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        Rect rect = new Rect();
        parent.getLocalVisibleRect(rect);
        int right = rect.right;
        int width = parent.getWidth();
        int itemWidth = view.getWidth();
        if (itemWidth <= 0) {
//            itemWidth = mContext.getResources().getDimensionPixelSize(R.dimen.local_app_item_width);
        }


        if (layoutManager instanceof GridLayoutManager) {
            gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            //空隙
            int gap = width - (itemWidth * spanCount);
            mSpace = gap / (spanCount - 1);
            setGridOffset(GridLayoutManager.VERTICAL, spanCount, outRect, childAdapterPosition, gridLayoutManager.getItemCount());
        } else if (layoutManager instanceof LinearLayoutManager) {
            // TODO: 18-4-23
        }
    }

    /**
     * 设置GridLayoutManager 类型的 offest
     *
     * @param orientation   方向
     * @param spanCount     个数
     * @param outRect       padding
     * @param childPosition 在 list 中的 postion
     * @param itemCount     list size
     */
    private void setGridOffset(int orientation, int spanCount, Rect outRect, int childPosition, int itemCount) {
        // 总共的padding值
        float totalSpace = mSpace * (spanCount - 1) + mEdgeSpace * 2;
        // 分配给每个item的padding值
        float eachSpace = totalSpace / spanCount;
        // 列数
        int column = childPosition % spanCount;
        // 行数
        int row = childPosition / spanCount;
        float left;
        float right;
        float top;
        float bottom;
        if (orientation == GridLayoutManager.VERTICAL) {
            // 默认 top为0
            top = 0;
            // 默认bottom为间距值
            bottom = mSpace;
            if (mEdgeSpace == 0) {
                left = column * eachSpace / (spanCount - 1);
                right = eachSpace - left;
                // 无边距的话  只有最后一行bottom为0
                if (itemCount / spanCount == row) {
                    bottom = 0;
                }
            } else {
                if (childPosition < spanCount) {
                    // 有边距的话 第一行top为边距值
                    top = mEdgeSpace;
                } else if (itemCount / spanCount == row) {
                    // 有边距的话 最后一行bottom为边距值
                    bottom = mEdgeSpace;
                }
                left = column * (eachSpace - mEdgeSpace - mEdgeSpace) / (spanCount - 1) + mEdgeSpace;
                right = eachSpace - left;
            }
        } else {
            // orientation == GridLayoutManager.HORIZONTAL 跟上面的大同小异, 将top,bottom替换为left,right即可
            left = 0;
            right = mSpace;
            if (mEdgeSpace == 0) {
                top = column * eachSpace / (spanCount - 1);
                bottom = eachSpace - top;
                if (itemCount / spanCount == row) {
                    right = 0;
                }
            } else {
                if (childPosition < spanCount) {
                    left = mEdgeSpace;
                } else if (itemCount / spanCount == row) {
                    right = mEdgeSpace;
                }
                top = column * (eachSpace - mEdgeSpace - mEdgeSpace) / (spanCount - 1) + mEdgeSpace;
                bottom = eachSpace - top;
            }
        }
        outRect.set((int) left, (int) top, (int) right, (int) bottom);
    }

    private void setLinearOffset(int orientation, Rect outRect, int childPosition, int itemCount) {
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            if (childPosition == 0) {
                // 第一个要设置PaddingLeft
                outRect.set(mEdgeSpace, 0, mSpace, 0);
            } else if (childPosition == itemCount - 1) {
                // 最后一个设置PaddingRight
                outRect.set(0, 0, mEdgeSpace, 0);
            } else {
                outRect.set(0, 0, mSpace, 0);
            }
        } else {
            if (childPosition == 0) {
                // 第一个要设置PaddingTop
                outRect.set(0, mEdgeSpace, 0, mSpace);
            } else if (childPosition == itemCount - 1) {
                // 最后一个要设置PaddingBottom
                outRect.set(0, 0, 0, mEdgeSpace);
            } else {
                outRect.set(0, 0, 0, mSpace);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        drawOver(c, parent,state);
    }


    /**
     * 画条目分割线
     *
     * @param c
     * @param parent
     * @param state
     */
    private void drawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int[] attrs = {android.R.attr.listDivider};
        final TypedArray a = mContext.obtainStyledAttributes(attrs);
        Drawable divider = a.getDrawable(0);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            int top = view.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}

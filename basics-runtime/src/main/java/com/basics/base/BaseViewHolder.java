package com.basics.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author pm
 * @date 18-1-3
 */
public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<View>();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v,getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onItemLongClick(v,getAdapterPosition());
            }
        });
    }

    public BaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        this(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
    }

    public void setData(M data) {
    }

    public void onBind(M model, final int position) {

    }

    public <T extends View> T getItemView(@IdRes int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) (view);
    }

    protected Context getContext() {
        return itemView.getContext();
    }

    protected void onItemClick(View view, int pos){
    }
    protected boolean onItemLongClick(View view , int pos){
        return false;
    }

}

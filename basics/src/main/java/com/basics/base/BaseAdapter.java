package com.basics.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author pm
 * @date 18-3-9
 */

public class BaseAdapter<M, VH extends BaseViewHolder<M>> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    protected List<M> mDatas;
    protected RecyclerView mRecyclerView;
    protected OnItemClickListener mOnItemClickListener;

    public BaseAdapter(Context context, List<M> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void addData(List<M> data) {
        mDatas.addAll(data);
        notifyItemRangeInserted(mDatas.size(), data.size());
    }

    public void setData(List<M> data) {
        mDatas = data;
        notifyDataSetChanged();
    }

    public List<M> getData() {
        return mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        /**
         * 点击item回调
         *
         * @param itemView
         * @param pos
         */
        void onItemClick(View itemView, int pos);
    }
}

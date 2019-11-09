package com.pm.amass.ui.growth;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.pm.amass.R;

import java.util.List;

public class ListAdapter extends BaseAdapter<Moudle, ListAdapter.ListViewHolder> {
    public ListAdapter(Context context, List<Moudle> datas) {
        super(context, datas);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListViewHolder holder = new ListViewHolder(parent, R.layout.item_type_2_home);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Moudle moudle = mDatas.get(position);
//        TextView textView = holder.getItemView(R.id.tv_type_1_home);
//        ImageView imageView = holder.getItemView(R.id.iv_type_1_home);
//        textView.setText(moudle.getLabel());
    }

    static class ListViewHolder extends BaseViewHolder<Moudle> {
        public ListViewHolder(View itemView) {
            super(itemView);
        }

        public ListViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}

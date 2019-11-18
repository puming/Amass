package com.pm.amass.ui.growth.content;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.pm.amass.R;
import com.pm.amass.bean.Moudle;

import java.util.List;

/**
 * @author pmcho
 */
public class ContentListAdapter extends BaseAdapter<Moudle, ContentListAdapter.ListViewHolder> {
    public ContentListAdapter(Context context, List<Moudle> datas) {
        super(context, datas);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListViewHolder holder = new ListViewHolder(parent, R.layout.item_content_artcle);
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

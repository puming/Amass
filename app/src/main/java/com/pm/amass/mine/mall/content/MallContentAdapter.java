package com.pm.amass.mine.mall.content;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.pm.amass.R;

import java.util.List;

public class MallContentAdapter extends BaseAdapter<Object, MallContentAdapter.MallContentViewHolder> {
    public MallContentAdapter(Context context, List<Object> datas) {
        super(context, datas);
    }

    @Override
    public MallContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MallContentViewHolder(parent, R.layout.item_mall);
    }

    static class MallContentViewHolder extends BaseViewHolder<Object> {

        public MallContentViewHolder(View itemView) {
            super(itemView);
        }

        public MallContentViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}

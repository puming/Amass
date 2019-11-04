package com.pm.amass.ui.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.pm.amass.R;

import java.util.List;

public class NewsAdapter extends BaseAdapter<Object, NewsAdapter.NewsViewHolder> {
    public NewsAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent, R.layout.item_news_msg);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    static class NewsViewHolder extends BaseViewHolder<Object> {

        public NewsViewHolder(View itemView) {
            super(itemView);
        }

        public NewsViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}

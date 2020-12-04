package com.pm.amass.shelf.news;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.common.widget.BannerView;
import com.pm.amass.R;
import com.pm.amass.bean.NewResult;
import com.pm.amass.news.details.NewsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pmcho
 */
public class NewsAdapter extends BaseAdapter<NewResult.News, NewsAdapter.NewsViewHolder> {
    private ArrayList<String> banner;
    public NewsAdapter(Context context, List<NewResult.News> datas) {
        super(context, datas);
        banner = new ArrayList<>(4);
        banner.add("https://graph.baidu.com/resource/112be821ee7d2ec880e1e01573281669.jpg");
        banner.add("https://graph.baidu.com/resource/112be821ee7d2ec880e1e01573281669.jpg");
        banner.add("https://graph.baidu.com/resource/112be821ee7d2ec880e1e01573281669.jpg");
        banner.add("https://graph.baidu.com/resource/112be821ee7d2ec880e1e01573281669.jpg");
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 1){
            return new NewsViewHolder(parent,R.layout.item_news_type_ad);
        }
        return new NewsViewHolder(parent, R.layout.item_news_msg);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        if(position == 0){
            BannerView bannerView = holder.getItemView(R.id.banner_news_ad);
            bannerView.addData(banner);
        }
//        News news = mDatas.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    static class NewsViewHolder extends BaseViewHolder<NewResult.News> {

        public NewsViewHolder(View itemView) {
            super(itemView);
        }

        public NewsViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }

        @Override
        protected void onItemClick(View view, int pos) {
            super.onItemClick(view, pos);
            Context context = view.getContext();
            if(context == null){
                return;
            }
            context.startActivity(new Intent(context, NewsDetailsActivity.class));
        }
    }
}

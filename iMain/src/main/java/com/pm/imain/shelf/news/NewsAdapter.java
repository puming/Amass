package com.pm.imain.shelf.news;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.basics.route.GlobalRoutePath;
import com.common.widget.BannerView;
import com.pm.imain.R;
import com.pm.imain.bean.NewResult;

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
        banner.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F14036110236%2F641&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636012599&t=9e4d0b4a167ffb0a92df31c776364cd3");
        banner.add("https://img1.baidu.com/it/u=1152171067,474066094&fm=26&fmt=auto");
        banner.add("https://img2.baidu.com/it/u=3117515341,122700844&fm=26&fmt=auto");
        banner.add("https://img0.baidu.com/it/u=3117458176,3142002220&fm=26&fmt=auto");
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
            ARouter.getInstance().build(GlobalRoutePath.NEWS_DETAILS_ACTIVITY).navigation();
        }
    }
}

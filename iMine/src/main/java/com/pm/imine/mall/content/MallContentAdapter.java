package com.pm.imine.mall.content;

import android.content.Context;
import android.text.TextUtils;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseApplication;
import com.basics.base.BaseViewHolder;
import com.pm.imine.R;
import com.pm.imine.bean.ShopResult;
import com.pm.middleware.utils.ImageLoaderUtils;

import java.util.List;

/**
 * @author pmcho
 */
public class MallContentAdapter extends BaseAdapter<ShopResult.Shop, MallContentAdapter.MallContentViewHolder> {
    public MallContentAdapter(Context context, List<ShopResult.Shop> datas) {
        super(context, datas);

        BaseApplication.getAppComponent()
                .getImageLoader();
    }

    @Override
    public MallContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MallContentViewHolder(parent, R.layout.item_mall);
    }

    @Override
    public void onBindViewHolder(MallContentViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ShopResult.Shop shop = mDatas.get(position);
        TextView title = holder.getItemView(R.id.tv_item_mall_title);
        TextView label = holder.getItemView(R.id.tv_item_mall_label);
        ImageView image = holder.getItemView(R.id.iv_mall_img);
        Button button = holder.getItemView(R.id.btn_item_mall);
        ImageLoaderUtils.bindImage(image, shop.getThumb(), new Size(174, 96));
        title.setText(shop.getTitle());
        int priceMoney = shop.getPrice_money();
        String moneyStr = String.valueOf(priceMoney);
        if (TextUtils.isEmpty(moneyStr)) {
            label.setText(shop.getPrice_points());
        } else {
            label.setText(shop.getPrice_points() + "+" + moneyStr);
        }

    }

    static class MallContentViewHolder extends BaseViewHolder<ShopResult.Shop> {

        public MallContentViewHolder(View itemView) {
            super(itemView);
        }

        public MallContentViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}

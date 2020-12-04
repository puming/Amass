package com.pm.imain.shelf.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.common.ux.ToastHelper;
import com.common.widget.Tile;
import com.pm.imain.R;
import com.pm.imain.bean.TileInfo;
import com.pm.imain.mine.coin.VirtualCoinActivity;
import com.pm.imain.mine.order.OrderActivity;
import com.takephoto.TakePhotoOwner;

import java.util.List;

/**
 * @author pmcho
 */
public class MineAdapter extends BaseAdapter<TileInfo, MineAdapter.MineViewHolder> {
    private static final String TAG = "MineAdapter";
    public MineAdapter(Context context, List<TileInfo> datas) {
        super(context, datas);
    }

    @Override
    public MineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MineViewHolder(parent, R.layout.item_mine_header);
        }
        return new MineViewHolder(parent, R.layout.item_mine_tile);
    }

    @Override
    public void onBindViewHolder(MineViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        TileInfo tileInfo = mDatas.get(position);
        holder.tile.setTitle(tileInfo.getTitle());
        holder.tile.setLeading(tileInfo.getIcon());
        holder.tile.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        holder.tile.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: v="+v);
            Context context = mContext;
            if (context == null) {
                return ;
            }
            if(position == 1){
                new TakePhotoOwner().start((Activity) context,100);
            } else if (position == 2) {
                context.startActivity(new Intent(context, OrderActivity.class));
            } else if (position == 4) {
                context.startActivity(new Intent(context, VirtualCoinActivity.class));
            } else {
                ToastHelper.makeToast(context, "该功能升级中").show();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    static class MineViewHolder extends BaseViewHolder<TileInfo> {
        Tile tile;

        public MineViewHolder(View itemView) {
            super(itemView);
        }

        public MineViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            tile = getItemView(R.id.tile_mine);
        }

        @Override
        protected void onItemClick(View view, int pos) {
            super.onItemClick(view, pos);
           /* Context context = view.getContext();
            if (context == null) {
                return;
            }
            if (pos == 2) {
                context.startActivity(new Intent(context, OrderActivity.class));
            } else if (pos == 4) {
                context.startActivity(new Intent(context, VirtualCoinActivity.class));
            } else {
                ToastHelper.makeToast(context,"该功能升级中").show();
            }*/
        }
    }
}

package com.pm.amass.ui.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.common.widget.Tile;
import com.pm.amass.R;
import com.pm.amass.bean.TileInfo;

import java.util.List;

/**
 * @author pmcho
 */
public class MineAdapter extends BaseAdapter<TileInfo, MineAdapter.MineViewHolder> {
    public MineAdapter(Context context, List<TileInfo> datas) {
        super(context, datas);
    }

    @Override
    public MineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 1){
            return new MineViewHolder(parent,R.layout.item_mine_header);
        }
        return new MineViewHolder(parent, R.layout.item_mine_tile);
    }

    @Override
    public void onBindViewHolder(MineViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        TileInfo tileInfo = mDatas.get(position);
        holder.tile.setTitle(tileInfo.getTitle());
        holder.tile.setLeading(tileInfo.getIcon());
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
    }
}

package com.pm.amass.ui.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.common.widget.Tile;
import com.pm.amass.R;

import java.util.List;

public class MineAdapter extends BaseAdapter<Object, MineAdapter.MineViewHolder> {
    public MineAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    public MineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineViewHolder(parent, R.layout.item_mine_tile);
    }

    static class MineViewHolder extends BaseViewHolder<Object> {

        public MineViewHolder(View itemView) {
            super(itemView);
        }

        public MineViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}

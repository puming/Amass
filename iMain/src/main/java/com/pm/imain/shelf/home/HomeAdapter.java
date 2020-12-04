package com.pm.imain.shelf.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.pm.imain.R;

import java.util.List;

/**
 * @author pmcho
 */
public class HomeAdapter extends BaseAdapter<Object, HomeAdapter.HomeViewHolder> {
    private static final int TYPE_ONE = 0x01abc;
    private static final int TYPE_TWO = 0x02abc;
    private static final int TYPE_THREE = 0x03abc;

    public HomeAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                return new HomeViewHolder(parent, R.layout.item_home_type_one);
            case TYPE_TWO:
                return new HomeViewHolder(parent, R.layout.item_home_type_two);
            case TYPE_THREE:
            default:
                return new HomeViewHolder(parent, R.layout.item_home_type_three);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        } else if (position == 1) {
            return TYPE_TWO;
        }
        return TYPE_THREE;
    }

    class HomeViewHolder extends BaseViewHolder<Object> {
        public HomeViewHolder(View itemView) {
            super(itemView);
        }

        public HomeViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}

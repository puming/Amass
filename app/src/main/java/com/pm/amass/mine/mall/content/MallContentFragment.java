package com.pm.amass.mine.mall.content;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseFragment;
import com.basics.base.BaseItemDecoration;
import com.basics.repository.Resource;
import com.common.utils.DensityUtil;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.pm.amass.R;
import com.pm.amass.bean.ShopResult;
import com.pm.amass.shelf.growth.content.ContentFragment;
import com.pm.amass.widget.ItemSpace;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pmcho
 */
public class MallContentFragment extends BaseFragment {
    private static final String TAG = "MallContentFragment";

    private MallContentViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private List<ShopResult.Shop> mData = new ArrayList<>(12);
    private MallContentAdapter mAdapter;

    public static MallContentFragment newInstance(String cateId) {
        Bundle bundle = new Bundle();
        bundle.putString("cateId", cateId);
        MallContentFragment fragment = new MallContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MallContentViewModel.class);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mall_content_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_mall_content);
        mAdapter = new MallContentAdapter(getContext(), mData);
        Bundle arguments = getArguments();
        if(arguments==null){
            return;
        }
        mViewModel.getShopListData(arguments.getString("cateId")).observe(this, shopResultResource -> {
            switch (shopResultResource.status) {
                case SUCCEED:
                    mData = shopResultResource.data.getData();
                    Log.d(TAG, "onViewCreated: size=" + mData.size());
                    mAdapter.setData(mData);
                    break;
                case ERROR:
                    Log.d(TAG, "onCreate: " + shopResultResource.code);
                    break;
                default:
                    break;
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

//        mRecyclerView.addItemDecoration(new BaseItemDecoration());
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                int childCount = parent.getLayoutManager().getChildCount();
                if (childAdapterPosition < 2) {
                    if (childAdapterPosition % 2 == 0) {
                        //left
                        outRect.set(0, 0, DensityUtil.dp2px(getContext(), 5),
                                DensityUtil.dp2px(getContext(), 8));
                    } else {
                        //right
                        outRect.set(DensityUtil.dp2px(getContext(), 5), 0, 0,
                                DensityUtil.dp2px(getContext(), 8));
                    }
                } else if (childAdapterPosition >= 2 && childAdapterPosition < (childCount - 2)) {
                    if (childAdapterPosition % 2 == 0) {
                        //left
                        outRect.set(0, DensityUtil.dp2px(getContext(), 8),
                                DensityUtil.dp2px(getContext(), 5),
                                DensityUtil.dp2px(getContext(), 8));
                    } else {
                        //right
                        outRect.set(DensityUtil.dp2px(getContext(), 5),
                                DensityUtil.dp2px(getContext(), 8), 0,
                                DensityUtil.dp2px(getContext(), 8));
                    }
                } else {
                    if (childAdapterPosition / 2 == 0) {
                        //left
                        outRect.set(0, DensityUtil.dp2px(getContext(), 8), DensityUtil.dp2px(getContext(), 5),
                                0);
                    } else {
                        //right
                        outRect.set(DensityUtil.dp2px(getContext(), 5), DensityUtil.dp2px(getContext(), 8), 0,
                                0);
                    }
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MallContentViewModel.class);
    }

}

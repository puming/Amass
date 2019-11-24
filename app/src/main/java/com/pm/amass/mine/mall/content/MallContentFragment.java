package com.pm.amass.mine.mall.content;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
import com.pm.amass.R;
import com.pm.amass.bean.ShopResult;

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

    public static MallContentFragment newInstance() {
        return new MallContentFragment();
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

        mViewModel.getShopListData().observe(this, shopResultResource -> {
            switch (shopResultResource.status) {
                case SUCCEED:
                    mData = shopResultResource.data.getData();
                    Log.d(TAG, "onViewCreated: size=" + mData.size());
                    mAdapter.addData(mData);
                    break;
                case ERROR:
                    Log.d(TAG, "onCreate: " + shopResultResource.code);
                    break;
                default:
                    break;
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Object o = new Object();

        ArrayList<Object> objects = new ArrayList<>(12);
        for (int i = 0; i < 100; i++) {
            objects.add(o);
        }

        mRecyclerView.addItemDecoration(new BaseItemDecoration());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MallContentViewModel.class);
    }

}

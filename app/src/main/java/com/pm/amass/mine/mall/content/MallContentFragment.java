package com.pm.amass.mine.mall.content;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseFragment;
import com.pm.amass.R;

import java.util.ArrayList;

public class MallContentFragment extends BaseFragment {

    private MallContentViewModel mViewModel;
    private RecyclerView mRecyclerView;

    public static MallContentFragment newInstance() {
        return new MallContentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mall_content_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_mall_content);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        Object o = new Object();

        ArrayList<Object> objects = new ArrayList<>(12);
        for (int i = 0; i < 100; i++) {
            objects.add(o);
        }

        mRecyclerView.setAdapter(new MallContentAdapter(getContext(),objects));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MallContentViewModel.class);
    }

}

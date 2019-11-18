package com.pm.amass.ui.growth.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseFragment;
import com.pm.amass.R;
import com.pm.amass.bean.Moudle;
import com.pm.amass.ui.growth.Constant;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author pmcho
 */
public class ContentFragment extends BaseFragment {
    private RecyclerView mRecyclerView;

    public static ContentFragment newInstance() {
        return new ContentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_home);
        registerListener();
        ArrayList<Moudle> moudles = initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new ContentListAdapter(getContext(), moudles));
    }

    private void registerListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private ArrayList<Moudle> initData() {
        ArrayList<Moudle> moudles = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Moudle moudle = new Moudle();
            moudle.setId(i);
            moudle.setLabel("Label");
            moudle.setTitle("Title");
            if (i % 2 == 0) {
                moudle.setStyleType(Constant.StyleType.TYPE_ONE);
            } else if (i % 3 == 0) {
                moudle.setStyleType(Constant.StyleType.TYPE_TWO);
            } else {
                moudle.setStyleType(Constant.StyleType.TYPE_THREE);
            }
            moudles.add(moudle);
        }
        return moudles;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}

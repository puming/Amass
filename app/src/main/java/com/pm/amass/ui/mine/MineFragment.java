package com.pm.amass.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basics.base.BaseFragment;
import com.common.widget.AppBar;
import com.pm.amass.R;
import com.pm.amass.ui.news.NewsAdapter;
import com.pm.amass.ui.setting.SettingActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MineFragment extends BaseFragment {
    AppBar mAppBar;
    RecyclerView mRecyclerView;
    private ArrayList datas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppBar = view.findViewById(R.id.appbar);
        mAppBar.setAppbarMenuIcon(R.drawable.ic_setting)
                .showAppbarMenuIcon(true);
        mAppBar.getAppbarRightContainer().setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.action_mine_to_setting);
            startActivity(new Intent(getActivity(), SettingActivity.class));
        });
        mRecyclerView = view.findViewById(R.id.rv_mine_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Object obj = new Object();
        List<Object> list = Arrays.asList(obj, obj, obj, obj, obj, obj);
        mRecyclerView.setAdapter(new MineAdapter(getContext(), list));
    }
}

package com.pm.amass.ui.news;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.widget.AppBar;
import com.pm.amass.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pmcho
 */
public class NewsFragment extends Fragment {

    AppBar mAppBar;

    RecyclerView mRecyclerView;
    private ArrayList datas;
    private NewsViewModel notificationsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppBar = view.findViewById(R.id.appbar_news);
        AppBar.Text text = new AppBar.Builder().setText("通讯录")
                .setTextColor(Color.GRAY)
                .build();
        mAppBar.setAppbarMenuText(text)
                .showAppbarMenuIcon(false);

        mAppBar.getAppbarRightContainer().setOnClickListener(v -> {

        });

        mRecyclerView = view.findViewById(R.id.rv_news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        datas = new ArrayList(24);
        Object o = new Object();
        List<Object> objects = Arrays.asList(o, o, o, o);
        mRecyclerView.setAdapter(new NewsAdapter(getContext(), objects));
    }
}
package com.pm.amass.shelf.news;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basics.repository.Resource;
import com.common.widget.AppBar;
import com.pm.amass.R;
import com.pm.amass.bean.NewResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pmcho
 */
public class NewsFragment extends Fragment {

    AppBar mAppBar;

    RecyclerView mRecyclerView;
    private ArrayList<NewResult> datas;
    private NewsViewModel mViewModel;
    private NewsAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
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
        datas = new ArrayList(12);
        mAdapter = new NewsAdapter(getContext(), datas);
        mRecyclerView.setAdapter(mAdapter);

        mViewModel.getNewList().observe(this, newResultResource -> {
            switch (newResultResource.status) {
                case SUCCEED:
                    NewResult result = newResultResource.data;
                    if(result == null){
                        break;
                    }
                    List<NewResult.News> newsList = result.getData();
                    mAdapter.setData(newsList);
                    break;
                case ERROR:
                    break;
                default:
                    break;
            }
        });
    }
}
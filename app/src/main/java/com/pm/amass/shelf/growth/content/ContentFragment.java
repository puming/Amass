package com.pm.amass.shelf.growth.content;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseFragment;
import com.pm.amass.R;
import com.pm.amass.bean.ArticleResult;
import com.pm.amass.bean.Moudle;
import com.pm.amass.shelf.growth.GrowthViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author pmcho
 */
public class ContentFragment extends BaseFragment {
    private static final String TAG = "ContentFragment";
    private RecyclerView mRecyclerView;
    private GrowthViewModel mViewModel;
    private List<ArticleResult.Article> mData = new ArrayList<>(12);
    private ContentListAdapter mAdapter;

    public static ContentFragment newInstance(String cateId) {
        Bundle bundle = new Bundle();
        bundle.putString("cateId", cateId);
        ContentFragment contentFragment = new ContentFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(GrowthViewModel.class);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_growth);
        registerListener();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ContentListAdapter(getContext(), mData);
        mRecyclerView.setAdapter(mAdapter);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        mViewModel.getArticleList(arguments.getString("cateId")).observe(this, articleResource -> {
            switch (articleResource.status) {
                case SUCCEED:
                    mData = articleResource.data.getData();
                    mAdapter.setData(mData);
                    Log.d(TAG, "onChanged: size = " + mData.size());
                    break;
                case ERROR:
                    Log.d(TAG, "onChanged: " + articleResource.code);
                    Log.d(TAG, "onChanged: " + articleResource.message);
                    break;
                default:
                    break;
            }
        });
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}

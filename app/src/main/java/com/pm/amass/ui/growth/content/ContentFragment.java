package com.pm.amass.ui.growth.content;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseFragment;
import com.basics.repository.Resource;
import com.pm.amass.R;
import com.pm.amass.bean.ArticleResult;
import com.pm.amass.bean.Moudle;
import com.pm.amass.ui.grade.GradeViewModel;
import com.pm.amass.ui.growth.Constant;
import com.pm.amass.ui.growth.GrowthViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
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

    public static ContentFragment newInstance() {
        return new ContentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(GrowthViewModel.class);
        mViewModel.getArticleList().observe(this, articleResource -> {
            switch (articleResource.status) {
                case SUCCEED:
                    List<ArticleResult.Article> list = articleResource.data.getData();
                    Log.d(TAG, "onChanged: size = " + list.size());
                    break;
                case ERROR:
                    Log.d(TAG, "onChanged: "+articleResource.code);
                    Log.d(TAG, "onChanged: "+articleResource.message);
                    break;
                default:
                    break;
            }
        });


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_content;
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

package com.pm.amass.mission.task;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.AppBarFragment;
import com.common.widget.AppBar;
import com.google.android.material.tabs.TabLayout;
import com.pm.amass.R;
import com.pm.amass.mission.task.family.FamilyTaskFragment;
import com.pm.amass.mission.task.student.StudentTaskFragment;

/**
 * @author pmcho
 */
public class DailyTaskFragment extends AppBarFragment {
    private TabLayout mTabLayout;

    private DailyTaskViewModel mViewModel;

    public static DailyTaskFragment newInstance() {
        return new DailyTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.daily_task_fragment, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        super.initAppBar(appBar);
        appBar.setAppbarTitle("今日任务");
    }

    @Override
    protected void onClickAppBarRightView(View view) {
        super.onClickAppBarRightView(view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = view.findViewById(R.id.tab_layout_task);
        ViewGroup container = view.findViewById(R.id.fl_task_layout);
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.fl_task_layout, StudentTaskFragment.newInstance())
                .commit();

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    getChildFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_task_layout, StudentTaskFragment.newInstance())
                            .commit();
                } else if (position == 1) {
                    getChildFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_task_layout, FamilyTaskFragment.newInstance())
                            .commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.daily_task_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DailyTaskViewModel.class);
        // TODO: Use the ViewModel
    }

}

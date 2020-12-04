package com.pm.imain.mission.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.basics.base.AppBarFragment;
import com.common.widget.AppBar;
import com.google.android.material.tabs.TabLayout;
import com.pm.imain.R;
import com.pm.imain.mission.task.content.TaskListFragment;

/**
 * @author pmcho
 */
public class DailyTaskFragment extends AppBarFragment {
    private TabLayout mTabLayout;

    private DailyTaskViewModel mViewModel;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    private Fragment mCurrentFragment;

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
        mManager = getChildFragmentManager();
        mTransaction = mManager.beginTransaction();
        final TaskListFragment student = TaskListFragment.newInstance("student");
        final TaskListFragment family = TaskListFragment.newInstance("family");

        mCurrentFragment = student;
        mTransaction.add(R.id.fl_task_layout, student)
                .commit();

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    showFragment(student);
                } else if (position == 1) {
                    showFragment(family);
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

    private void showFragment(Fragment fragment){
        //  判断传入的fragment是不是当前的currentFragmentgit
        if (mCurrentFragment != fragment){
            FragmentTransaction transaction = mManager.beginTransaction();
            //  不是则隐藏
            transaction.hide(mCurrentFragment);
            mCurrentFragment = fragment;
            if (!fragment.isAdded()){
                transaction.add(R.id.fl_task_layout,fragment).show(fragment).commit();
            }else{
                transaction.show(fragment).commit();
            }
        }
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

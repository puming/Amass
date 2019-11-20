package com.pm.amass.home.task;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.AppBarFragment;
import com.common.widget.AppBar;
import com.pm.amass.R;

/**
 * @author pmcho
 */
public class DailyTaskFragment extends AppBarFragment {

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
//        super.initAppBar(appBar);
        appBar.showAppbarRightContainer(true)
                .showAppbarBackText(false)
                .showAppbarMenuIcon(false)
                .setAppbarMenuText("提交")
                .setAppbarTitle("今日任务");
    }

    @Override
    protected void onClickAppBarRightView(View view) {
        super.onClickAppBarRightView(view);
        Navigation.findNavController(view)
                .navigate(R.id.action_dailyTaskFragment_to_doTaskFragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

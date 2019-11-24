package com.pm.amass.home.task.submit;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.AppBarFragment;
import com.common.widget.AppBar;
import com.pm.amass.R;

/**
 * @author pmcho
 */
public class SubmitTaskFragment extends AppBarFragment {

    private SubmitTaskViewModel mViewModel;

    public static SubmitTaskFragment newInstance() {
        return new SubmitTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        appBar.showAppbarRightContainer(true)
                .showAppbarMenuIcon(false)
                .showAppbarBackText(false)
                .setAppbarTitle("提交任务")
                .setAppbarMenuText("提交");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.submit_task_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SubmitTaskViewModel.class);
        // TODO: Use the ViewModel
    }

}

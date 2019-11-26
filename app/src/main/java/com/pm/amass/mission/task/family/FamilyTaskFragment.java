package com.pm.amass.mission.task.family;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.BaseFragment;
import com.pm.amass.R;

/**
 * @author pmcho
 */
public class FamilyTaskFragment extends BaseFragment {

    private FamilyTaskViewModel mViewModel;

    public static FamilyTaskFragment newInstance() {
        return new FamilyTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.family_task_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FamilyTaskViewModel.class);
    }

}

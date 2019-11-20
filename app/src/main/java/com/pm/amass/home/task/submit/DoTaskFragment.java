package com.pm.amass.home.task.submit;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basics.base.AppBarFragment;
import com.pm.amass.R;

public class DoTaskFragment extends AppBarFragment {

    private DoTaskViewModel mViewModel;

    public static DoTaskFragment newInstance() {
        return new DoTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.do_task_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DoTaskViewModel.class);
        // TODO: Use the ViewModel
    }

}

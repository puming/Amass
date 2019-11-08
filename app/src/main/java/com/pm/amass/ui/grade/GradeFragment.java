package com.pm.amass.ui.grade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.basics.base.BaseFragment;
import com.pm.amass.R;
import com.pm.amass.ui.news.NewsViewModel;

/**
 * @author pmcho
 */
public class GradeFragment extends BaseFragment {
    GradeViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(GradeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_grade, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

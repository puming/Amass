package com.pm.amass.mission.task.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.basics.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.pm.amass.R;
import com.pm.amass.mission.task.content.StudentContentFragment;
import com.pm.amass.common.ContentFragmentPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author pmcho
 */
public class StudentTaskFragment extends BaseFragment {

    @BindView(R.id.tab_layout_task)
    TabLayout tabLayoutTask;
    @BindView(R.id.vp_student_task)
    ViewPager vpStudentTask;
    private StudentTaskViewModel mViewModel;

    public static StudentTaskFragment newInstance() {
        return new StudentTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.student_task_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerListener();
        ArrayList<Fragment> fragments = new ArrayList<>(3);
        fragments.add(StudentContentFragment.newInstance());
        fragments.add(StudentContentFragment.newInstance());
        fragments.add(StudentContentFragment.newInstance());
        vpStudentTask.setAdapter(new ContentFragmentPageAdapter(getChildFragmentManager(), fragments));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StudentTaskViewModel.class);
        // TODO: Use the ViewModel
    }

    private void registerListener(){
        vpStudentTask.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutTask));
        tabLayoutTask.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpStudentTask.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

}

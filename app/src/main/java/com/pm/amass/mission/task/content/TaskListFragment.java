package com.pm.amass.mission.task.content;

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
import com.pm.amass.common.ContentFragmentPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author pmcho
 */
public class TaskListFragment extends BaseFragment {

    @BindView(R.id.tab_layout_task)
    TabLayout tabLayoutTask;
    @BindView(R.id.vp_student_task)
    ViewPager vpStudentTask;

    public static TaskListFragment newInstance(String source) {
        Bundle bundle = new Bundle();
        bundle.putString("source", source);
        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        Bundle arguments = getArguments();
        if(arguments==null){
            return;
        }
        String source = arguments.getString("source");
        if("student".equals(source)){
            fragments.add(StudentContentFragment.newInstance(1));
            fragments.add(StudentContentFragment.newInstance(2));
            fragments.add(StudentContentFragment.newInstance(3));
        }else if("family".equals(source)){
            fragments.add(StudentContentFragment.newInstance(4));
            fragments.add(StudentContentFragment.newInstance(4));
            fragments.add(StudentContentFragment.newInstance(5));
        }
        vpStudentTask.setOffscreenPageLimit(2);
        vpStudentTask.setAdapter(new ContentFragmentPageAdapter(getChildFragmentManager(), fragments));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void registerListener() {
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

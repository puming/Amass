package com.pm.amass.mission.task;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.basics.base.BaseActivity;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.pm.amass.R;
import com.pm.amass.mission.task.content.TaskListFragment;

/**
 * @author pmcho
 */
public class DailyTaskActivity extends BaseActivity {

    /**
     * 学员任务
     */
    private TabItem mTabStudentTask;
    /**
     * 家庭任务
     */
    private TabItem mTabFamilyTask;
    private TabLayout mTabLayoutTask;
    private FrameLayout mFlTaskLayout;

    DailyTaskViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_task);
       /* mViewModel = ViewModelProviders.of(this).get(DailyTaskViewModel.class);
        initView();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_task_layout, TaskListFragment.newInstance())
                .commit();
        registerListener();*/
    }

    private void initView() {
        mTabStudentTask = (TabItem) findViewById(R.id.tab_student_task);
        mTabFamilyTask = (TabItem) findViewById(R.id.tab_family_task);
        mTabLayoutTask = (TabLayout) findViewById(R.id.tab_layout_task);
        mFlTaskLayout = (FrameLayout) findViewById(R.id.fl_task_layout);
    }

    private void registerListener() {
        mTabLayoutTask.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_task_layout, TaskListFragment.newInstance("student"))
                            .commit();
                } else if (position == 1) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_task_layout, TaskListFragment.newInstance("family"))
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

}

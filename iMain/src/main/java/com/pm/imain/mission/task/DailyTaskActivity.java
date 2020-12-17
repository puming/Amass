package com.pm.imain.mission.task;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.basics.base.BaseActivity;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.pm.imain.R;
import com.pm.imain.mission.task.content.TaskListFragment;

/**
 * @author pmcho
 */
public class DailyTaskActivity extends BaseActivity {
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
}

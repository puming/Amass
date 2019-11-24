package com.pm.amass.home.task.details;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.basics.base.AppBarFragment;
import com.common.widget.AppBar;
import com.pm.amass.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pmcho
 */
public class TaskDetailsFragment extends AppBarFragment {
    private static final String TAG = "TaskDetailsFragment";
    @BindView(R.id.btn_do_task)
    Button btnDoTask;
    private TaskDetailsViewModel mViewModel;

    public static TaskDetailsFragment newInstance() {
        return new TaskDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        super.initAppBar(appBar);
        appBar.showAppbarRightContainer(true)
                .showAppbarMenuIcon(true)
                .showAppbarBackText(false)
                .showAppbarTitle(false)
//                .setAccentColor(Color.WHITE)
                .setAppbarMenuIcon(R.drawable.ic_more_vector);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDoTask.setOnClickListener(v -> {
            Navigation.findNavController(btnDoTask)
                    .navigate(R.id.action_taskDetailsFragment_to_submitTaskFragment);
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.task_details_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TaskDetailsViewModel.class);
    }

    @OnClick(R.id.btn_do_task)
    public void onViewClicked() {
        Log.d(TAG, "onViewClicked: ");
        Navigation.findNavController(btnDoTask)
                .navigate(R.id.action_taskDetailsFragment_to_submitTaskFragment);
    }
}

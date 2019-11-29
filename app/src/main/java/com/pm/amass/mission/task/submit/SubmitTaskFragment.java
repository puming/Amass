package com.pm.amass.mission.task.submit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.basics.base.AppBarFragment;
import com.common.widget.AppBar;
import com.common.widget.Tile;
import com.pm.amass.R;
import com.pm.amass.mission.task.details.TaskDetailsViewModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pmcho
 */
public class SubmitTaskFragment extends AppBarFragment {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.ll_task_media_group)
    LinearLayout llTaskMediaGroup;
    @BindView(R.id.tile_sync)
    Tile tileSync;
    @BindView(R.id.tile_location)
    Tile tileLocation;
    @BindView(R.id.tile_remind_others)
    Tile tileRemindOthers;
    private TaskDetailsViewModel mViewModel;

    public static SubmitTaskFragment newInstance() {
        return new SubmitTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tileSync.setCustomTrailing(new Switch(getContext()));
        mViewModel = ViewModelProviders.of(this).get(TaskDetailsViewModel.class);

    }

    @Override
    protected void initAppBar(AppBar appBar) {
        /*Button button = new Button(getContext());
        button.setText("提交");
        button.setTextColor(Color.WHITE);
        int color;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = getContext().getColor(R.color.colorPrimary);
        } else {
            color = getContext().getResources().getColor(R.color.colorPrimary);
        }
        button.setBackgroundColor(color);*/
        View button = getLayoutInflater().inflate(R.layout.layout_button, null);
        button.setOnClickListener(v -> {
           getActivity().finish();
        });
        appBar.showAppbarRightContainer(true)
                .showAppbarMenuIcon(false)
                .showAppbarBackText(false)
                .setAppbarTitle("提交任务")
                .setCustomRightView(button);

    }

    @Override
    protected void onClickAppBarRightView(View view) {
        super.onClickAppBarRightView(view);
        getActivity().onBackPressed();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.submit_task_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick({R.id.tile_location, R.id.tile_remind_others})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tile_location:
                break;
            case R.id.tile_remind_others:
                break;
            default:
                break;
        }
    }
}

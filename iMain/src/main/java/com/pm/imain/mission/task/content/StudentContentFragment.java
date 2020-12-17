package com.pm.imain.mission.task.content;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basics.base.BaseFragment;
import com.basics.base.BaseItemDecoration;
import com.common.utils.DensityUtil;
import com.pm.imain.R2;
import com.pm.imain.R;
import com.pm.imain.bean.TaskResult;
import com.pm.imain.mission.task.DailyTaskViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author pmcho
 */
public class StudentContentFragment extends BaseFragment {
    private static final String TAG = "StudentContentFragment";

    @BindView(R2.id.rv_student_content_list)
    RecyclerView rvStudentContentList;
    private DailyTaskViewModel mViewModel;
    private StudentContentAdapter mAdapter;

    public static StudentContentFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        StudentContentFragment contentFragment = new StudentContentFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(DailyTaskViewModel.class);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStudentContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<TaskResult.Task> datas = new ArrayList<>(12);
        mAdapter = new StudentContentAdapter(getContext(), datas);
        BaseItemDecoration decor = new BaseItemDecoration(LinearLayoutManager.VERTICAL,
                DensityUtil.dp2px(getContext(), 17));
        rvStudentContentList.addItemDecoration(decor);
        rvStudentContentList.setAdapter(mAdapter);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        int type = arguments.getInt("type");
        mViewModel.getTaskListData(String.valueOf(3))
                .observe(this, taskResultResource -> {
                    switch (taskResultResource.status) {
                        case SUCCEED:
                            TaskResult result = taskResultResource.data;
                            List<TaskResult.Task> taskList = result.getData();
                            Log.d(TAG, "onViewCreated: "+taskList.size());
                            mAdapter.setData(taskList);
                            break;
                        case ERROR:
                            break;
                        default:
                            break;
                    }

                });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.student_content_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}

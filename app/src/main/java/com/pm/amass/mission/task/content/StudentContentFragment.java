package com.pm.amass.mission.task.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basics.base.BaseFragment;
import com.basics.base.BaseItemDecoration;
import com.basics.repository.Resource;
import com.common.utils.DensityUtil;
import com.pm.amass.R;
import com.pm.amass.bean.TaskResult;
import com.pm.amass.mission.task.DailyTaskViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author pmcho
 */
public class StudentContentFragment extends BaseFragment {

    @BindView(R.id.rv_student_content_list)
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
        mViewModel.getTaskListData(String.valueOf(type))
                .observe(this, taskResultResource -> {
                    switch (taskResultResource.status) {
                        case SUCCEED:
                            TaskResult result = taskResultResource.data;
                            List<TaskResult.Task> taskList = result.getData();
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

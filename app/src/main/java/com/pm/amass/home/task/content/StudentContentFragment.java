package com.pm.amass.home.task.content;

import android.os.Bundle;
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
import com.pm.amass.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author pmcho
 */
public class StudentContentFragment extends BaseFragment {

    @BindView(R.id.rv_student_content_list)
    RecyclerView rvStudentContentList;
    private StudentContentViewModel mViewModel;

    public static StudentContentFragment newInstance() {
        return new StudentContentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStudentContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Object> datas = new ArrayList<>(12);
        Object o = new Object();
        StudentContentAdapter adapter = new StudentContentAdapter(getContext(), datas);
        for (int i = 0; i < 20; i++) {
            datas.add(o);
        }
        BaseItemDecoration decor = new BaseItemDecoration(LinearLayoutManager.VERTICAL,
                DensityUtil.dp2px(getContext(), 17));
        rvStudentContentList.addItemDecoration(decor);
        rvStudentContentList.setAdapter(adapter);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.student_content_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StudentContentViewModel.class);
        // TODO: Use the ViewModel
    }

}

package com.pm.amass.shelf.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pm.amass.R;
import com.pm.amass.mission.raiders.RaidersActivity;
import com.pm.amass.mission.task.DailyTaskActivity;

import java.util.ArrayList;

/**
 * @author pmcho
 */
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_new, container, false);
        homeViewModel.getText().observe(this, s -> {

        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_open_drawer).setOnClickListener(v -> {
            // TODO: 2019/11/8
            //回调给activity
        });

        view.findViewById(R.id.btn_goto_task).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), DailyTaskActivity.class));
        });
        view.findViewById(R.id.fl_label_day).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), DailyTaskActivity.class));
        });
        view.findViewById(R.id.fl_label_week).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), DailyTaskActivity.class));
        });
        view.findViewById(R.id.fl_label_special).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), DailyTaskActivity.class));
        });
        view.findViewById(R.id.btn_goto_psp).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), RaidersActivity.class));
        });

        mRecyclerView = view.findViewById(R.id.rv_home_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Object> objects = new ArrayList<>(12);
        Object e = new Object();
        for (int i = 0; i < 10; i++) {
            objects.add(e);
        }
        mAdapter = new HomeAdapter(getContext(), objects);
        mRecyclerView.setAdapter(mAdapter);
    }

}
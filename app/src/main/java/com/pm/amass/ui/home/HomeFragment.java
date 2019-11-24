package com.pm.amass.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.pm.amass.R;
import com.pm.amass.home.raiders.RaidersActivity;
import com.pm.amass.home.task.DailyTaskActivity;

/**
 * @author pmcho
 */
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
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
        view.findViewById(R.id.btn_goto_psp).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), RaidersActivity.class));
        });
    }

}
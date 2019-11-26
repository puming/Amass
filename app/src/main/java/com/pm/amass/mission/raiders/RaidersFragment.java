package com.pm.amass.mission.raiders;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pm.amass.R;

/**
 * @author pmcho
 */
public class RaidersFragment extends Fragment {

    private RaidersViewModel mViewModel;

    public static RaidersFragment newInstance() {
        return new RaidersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.raiders_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RaidersViewModel.class);
        // TODO: Use the ViewModel
    }

}

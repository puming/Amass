package com.pm.amass.mission.task.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.pm.amass.R;

/**
 * @author pm
 */
public class DetailsActivity extends AppCompatActivity {
    private TaskDetailsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mViewModel = ViewModelProviders.of(this).get(TaskDetailsViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String tid = getIntent().getStringExtra("tid");
        mViewModel.setTid(tid);
    }
}

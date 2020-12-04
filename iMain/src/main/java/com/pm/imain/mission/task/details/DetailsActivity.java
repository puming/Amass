package com.pm.imain.mission.task.details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.pm.imain.R;

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

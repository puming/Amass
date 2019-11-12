package com.pm.amass.mine.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.common.widget.AppBar;
import com.pm.amass.R;

/**
 * @author pm
 */
public class SettingActivity extends AppCompatActivity {
    private SettingViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        AppBar mAppBar = findViewById(R.id.appbar_setting);
        mAppBar.showAppbarBackText(false);
        mAppBar.getAppbarLeftContainer().setOnClickListener(v -> {
            onBackPressed();
        });
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
    }
}

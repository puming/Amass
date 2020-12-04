package com.pm.imain.mine.mission;


import android.graphics.Color;
import android.os.Bundle;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.pm.imain.R;

/**
 * @author pm
 * 积分任务
 */
public class MissionActivity extends AppBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        AppBar.Text text = new AppBar.Builder().setText("积分明细")
                .setTextColor(Color.GRAY)
                .build();
        appBar.showAppbarMenuIcon(false)
                .showAppbarBackText(false)
                .setAppbarTitle("积分任务")
                .setAppbarMenuText(text);
    }
}

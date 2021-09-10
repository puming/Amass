package com.pm.imine.mission;


import android.graphics.Color;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.basics.base.AppBarActivity;
import com.basics.route.GlobalRoutePath;
import com.common.widget.AppBar;
import com.pm.imine.R;

/**
 * @author pm
 * 积分任务
 */
@Route(path = GlobalRoutePath.MISSION_ACTIVITY)
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

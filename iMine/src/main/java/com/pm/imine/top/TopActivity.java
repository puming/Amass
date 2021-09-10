package com.pm.imine.top;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.basics.base.AppBarActivity;
import com.basics.route.GlobalRoutePath;
import com.common.widget.AppBar;
import com.pm.imine.R;

/**
 * @author pm
 * 班级派排行榜
 */
@Route(path = GlobalRoutePath.TOP_ACTIVITY)
public class TopActivity extends AppBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        super.initAppBar(appBar);
        appBar.setAppbarTitle("本月班级排行榜");
    }
}

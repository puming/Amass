package com.pm.amass.mine.top;


import android.os.Bundle;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.pm.amass.R;

/**
 * @author pm
 * 班级派排行榜
 */
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

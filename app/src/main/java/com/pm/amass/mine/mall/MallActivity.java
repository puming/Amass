package com.pm.amass.mine.mall;


import android.os.Bundle;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.pm.amass.R;

/**
 * @author pm
 */
public class MallActivity extends AppBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        super.initAppBar(appBar);
        appBar.setAppbarTitle("积分商城");
    }
}

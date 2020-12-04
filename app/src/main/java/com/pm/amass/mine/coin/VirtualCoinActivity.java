package com.pm.amass.mine.coin;

import android.os.Bundle;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.pm.amass.R;

/**
 * @author pmcho
 */
public class VirtualCoinActivity extends AppBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_coin);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        super.initAppBar(appBar);
        appBar.setAppbarTitle("我的积也币");
    }
}

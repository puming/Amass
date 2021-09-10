package com.pm.imine.coin;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.basics.base.AppBarActivity;
import com.basics.route.GlobalRoutePath;
import com.common.widget.AppBar;
import com.pm.imine.R;

/**
 * @author pmcho
 */
@Route(path = GlobalRoutePath.VIRTUAL_COIN_ACTIVITY)
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

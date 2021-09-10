package com.pm.imine.order;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.basics.base.AppBarActivity;
import com.basics.route.GlobalRoutePath;
import com.common.widget.AppBar;
import com.pm.imine.R;

/**
 * @author pmcho
 */
@Route(path = GlobalRoutePath.ORDER_ACTIVITY)
public class OrderActivity extends AppBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        appBar.showAppbarRightContainer(true)
                .showAppbarBackText(false)
                .showAppbarMenuIcon(true)
                .setAppbarMenuIcon(R.drawable.ic_more_vector)
                .setAppbarTitle("订单支付");
    }
}

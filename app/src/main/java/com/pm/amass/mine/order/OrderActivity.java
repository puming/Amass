package com.pm.amass.mine.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.pm.amass.R;

/**
 * @author pmcho
 */
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

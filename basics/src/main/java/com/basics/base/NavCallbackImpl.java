package com.basics.base;

import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;

/**
 * @author pm
 * @date 2018/11/19
 * @email puming@zdsoft.cn
 */
public class NavCallbackImpl extends NavCallback {
    private static final String TAG = "NavCallbackImpl";
    @Override
    public void onArrival(Postcard postcard) {

    }

    public NavCallbackImpl() {
        super();
    }

    @Override
    public void onFound(Postcard postcard) {
        super.onFound(postcard);
        Log.d(TAG, "onFound: ");
    }

    @Override
    public void onLost(Postcard postcard) {
        super.onLost(postcard);
        Log.d(TAG, "onLost: ");
    }

    @Override
    public void onInterrupt(Postcard postcard) {
        super.onInterrupt(postcard);
        Log.d(TAG, "onInterrupt: ");
    }
}

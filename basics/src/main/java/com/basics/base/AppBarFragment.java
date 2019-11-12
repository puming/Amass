package com.basics.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.common.widget.AppBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * @author pm
 * @date 2019/11/12
 * @email puming@zdsoft.cn
 * 默认带有AppBar
 */
public abstract class AppBarFragment extends BaseFragment {
    private static final String TAG = "AppBarFragment";
    private static final boolean DEBUG = true;

    private AppBar mAppBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*View view = createView();
        if (view != null) {
            return view;
        }*/
        if (DEBUG) {
            Log.d(TAG, "onCreateView: container=" + container);
        }
        LinearLayout root = new LinearLayout(container.getContext());
        ViewGroup.LayoutParams matchParent = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mAppBar = new AppBar(container.getContext());
        root.addView(mAppBar, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //content
        FrameLayout content = new FrameLayout(getContext());
        inflater.inflate(getContentLayoutId(), content, true);
        root.addView(content, 1, matchParent);
        container.addView(root, matchParent);
        ButterKnife.bind(this, root);
        return root;
    }

    protected View createView() {
        return null;
    }

    protected abstract int getContentLayoutId();
}

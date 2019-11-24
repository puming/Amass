package com.basics.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * @author pm
 * @date 2018/12/20
 * @email puming@zdsoft.cn
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean autoBind = getButterKnifeAutoBind();
        View root = createView();
        if (null != root) {
            if (autoBind) {
                ButterKnife.bind(this, root);
            }
            return root;
        }
        root = inflater.inflate(getContentLayoutId(), container, false);
        if (autoBind) {
            ButterKnife.bind(this, root);
        }
        return root;
    }

    /**
     * 返回fragment的布局文件id
     *
     * @return layout id
     */
    protected abstract int getContentLayoutId();

    /**
     * 子类可以重写这个方法返回Fragment的ui内容,
     * 这个方法返回的ui的内容比getContentLayoutId返回的高。
     *
     * @return layout view
     */
    protected View createView() {
        return null;
    }

    /**
     * 如果子类不需要自动绑定ButterKnife,
     * 则重些此方法并返回 false
     *
     * @return true or false
     */
    protected boolean getButterKnifeAutoBind() {
        return true;
    }
}

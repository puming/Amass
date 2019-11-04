package com.basics.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author pm
 * @date 2019/1/16
 * @email puming@zdsoft.cn
 */
public abstract class BaseDialogFragment extends DialogFragment {
    public static final String ONESEIF_TAG = "dialog";
    public abstract int getResLayoutId();

    public abstract void initStyle();

    public abstract void initViews(View view);

    public abstract void initData();

    public abstract void setDialogParams(Dialog dialog);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStyle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != getDialog()) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        View view = inflater.inflate(getResLayoutId(), container, false);
        initViews(view);
        initData();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != getDialog()) {
            setDialogParams(getDialog());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public BaseDialogFragment showDialogFragment(AppCompatActivity activity, BaseDialogFragment dialogFragment, String tag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = dialogFragment;
        }
        if (!activity.isFinishing() && null != fragment && !fragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .add(dialogFragment, tag)
                    .commitAllowingStateLoss();
        }
        return (BaseDialogFragment) fragment;
    }
}

package com.pm.amass.diglog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.basics.base.BaseDialogFragment;
import com.pm.amass.R;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;

/**
 * @author pm
 * @date 2019/11/29
 * @email puming@zdsoft.cn
 */
public class BottomEditDialog extends BaseDialogFragment {
    private OnItemClickListener mItemClickListener;
    public static BottomEditDialog newInstance() {
        return new BottomEditDialog();
    }
    @Override
    public int getResLayoutId() {
        return R.layout.dialog_fragment_bottom_edit;
    }

    @Override
    public void initStyle() {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomFragmentDialogWithAnim);
    }

    @Override
    public void initViews(View view) {
        AppCompatEditText mEditText = view.findViewById(R.id.et_comment);
        Button button = view.findViewById(R.id.btn_post_comment);
        button.setOnClickListener(v -> {
            mItemClickListener.onItemClick(mEditText.getText().toString());
            if (getDialog().isShowing()) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void setDialogParams(Dialog dialog) {
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        // 设置底部弹出显示的DialogFragment窗口属性。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    public void setOnOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String content);
    }
}

package com.pm.imain.diglog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basics.base.BaseDialogFragment;
import com.basics.base.BaseViewHolder;
import com.pm.imain.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author pm
 * @date 2019/1/16
 * @email puming@zdsoft.cn
 */
public class BottomSelectDialog extends BaseDialogFragment {

    private ArrayList<String> mData = new ArrayList<>(4);
    private RecyclerView recyclerView;
    private TextView textView;

    private OnItemClickListener mItemClickListener;

    public static BottomSelectDialog newInstance(String[] data) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("data", data);
        BottomSelectDialog fragment = new BottomSelectDialog();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getResLayoutId() {
        return R.layout.dialog_fragment_bottom_select;
    }

    @Override
    public void initStyle() {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomFragmentDialogWithAnim);
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv_bottom_select_list);
        textView = view.findViewById(R.id.text_cancel);
        textView.setOnClickListener(v -> dismiss());
        RecyclerView.Adapter<BaseViewHolder> adapter = new RecyclerView.Adapter<BaseViewHolder>() {
            @NonNull
            @Override
            public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_goods_tabs_select_list, viewGroup, false);
                return new BaseViewHolder<String>(item) {
                    @Override
                    protected void onItemClick(View view, int pos) {
                        super.onItemClick(view, pos);
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick(pos, mData.get(pos));

                            if (getDialog().isShowing()) {
                                getDialog().dismiss();
                            }
                        }
                    }
                };
            }

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int i) {
                TextView textView = (TextView) viewHolder.getItemView(R.id.text_tab);
                textView.setText(mData.get(i));
            }

            @Override
            public int getItemCount() {
                return mData.size();
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        String[] data = getArguments().getStringArray("data");
        mData.addAll(Arrays.asList(data));
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
        void onItemClick(int pos, String content);
    }
}

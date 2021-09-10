package com.pm.imain.mission.task.content;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.pm.imain.R;
import com.pm.imain.bean.TaskResult;
import com.pm.imain.bean.TaskResult.Task;
import com.pm.imain.mission.task.details.DetailsActivity;
import com.pm.middleware.utils.ImageLoaderUtils;

import java.util.List;

/**
 * @author pmcho
 */
public class StudentContentAdapter extends BaseAdapter<TaskResult.Task, StudentContentAdapter.StudentContentViewHolder> {
    public StudentContentAdapter(Context context, List<TaskResult.Task> datas) {
        super(context, datas);
    }

    @Override
    public StudentContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudentContentViewHolder(parent, R.layout.item_student_task_content);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentContentViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        Task task = mDatas.get(position);
        String endtime = task.getEndtime();
        String tname = task.getTname();
        String icon = task.getIcon();
        ImageView imageView = holder.getItemView(R.id.iv_student_task_item_icon);
        ImageLoaderUtils.bindImage(imageView,icon);
        TextView textView = holder.getItemView(R.id.iv_student_task_item_title);
        textView.setText(tname);
        TextView label = holder.getItemView(R.id.iv_student_task_item_label);
        label.setText(endtime);
        holder.getItemView(R.id.btn_student_task_item).setOnClickListener(v -> {
           /* Navigation.findNavController(v)
                    .navigate(R.id.action_dailyTaskFragment_to_taskDetailsFragment);*/
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("tid", task.getId());
            mContext.startActivity(intent);
        });
    }

    static class StudentContentViewHolder extends BaseViewHolder<TaskResult.Task> {
        public StudentContentViewHolder(View itemView) {
            super(itemView);
        }

        public StudentContentViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}

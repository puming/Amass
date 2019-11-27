package com.pm.amass.mission.task.content;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.pm.amass.R;
import com.pm.amass.mission.task.details.DetailsActivity;

import java.util.List;

/**
 * @author pmcho
 */
public class StudentContentAdapter extends BaseAdapter<Object, StudentContentAdapter.StudentContentViewHolder> {
    public StudentContentAdapter(Context context, List<Object> datas) {
        super(context, datas);
    }

    @Override
    public StudentContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudentContentViewHolder(parent, R.layout.item_student_task_content);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentContentViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.getItemView(R.id.btn_student_task_item).setOnClickListener(v -> {
           /* Navigation.findNavController(v)
                    .navigate(R.id.action_dailyTaskFragment_to_taskDetailsFragment);*/
            mContext.startActivity(new Intent(mContext, DetailsActivity.class));
        });
    }

    static class StudentContentViewHolder extends BaseViewHolder<Object> {
        public StudentContentViewHolder(View itemView) {
            super(itemView);
        }

        public StudentContentViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}

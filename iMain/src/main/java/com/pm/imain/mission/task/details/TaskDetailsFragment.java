package com.pm.imain.mission.task.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.basics.base.AppBarFragment;
import com.common.widget.AppBar;
import com.pm.imain.R2;
import com.pm.imain.R;
import com.pm.imain.bean.TaskDetailsResult;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pmcho
 */
public class TaskDetailsFragment extends AppBarFragment {
    private static final String TAG = "TaskDetailsFragment";
    @BindView(R2.id.btn_do_task)
    Button btnDoTask;
    @BindView(R2.id.web_task_details)
    WebView mWebView;
    private TaskDetailsViewModel mViewModel;

    public static TaskDetailsFragment newInstance() {
        return new TaskDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        super.initAppBar(appBar);
        appBar.showAppbarRightContainer(true)
                .showAppbarMenuIcon(true)
                .showAppbarBackText(false)
                .showAppbarTitle(false)
//                .setAccentColor(Color.WHITE)
                .setAppbarMenuIcon(R.drawable.ic_more_vector);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TaskDetailsViewModel.class);
        mViewModel.getTaskDetailsData()
                .observe(this, taskDetailsResultResource -> {
                    switch (taskDetailsResultResource.status) {
                        case SUCCEED:
                            TaskDetailsResult data = taskDetailsResultResource.data;
                            if(data == null){
                                break;
                            }
                            TaskDetailsResult.TaskDetails taskDetails = data.getData();
                            String url = taskDetails.getDetail().getH5_url();
                            mWebView.loadUrl(url);
//                            mWebView.loadUrl("http://www.pmbloger.com/");
                            break;
                        case ERROR:
                            mWebView.loadUrl("http://www.pmbloger.com/");
                            break;
                        default:
                            break;
                    }

                });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.task_details_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R2.id.btn_do_task)
    public void onViewClicked() {
        Log.d(TAG, "onViewClicked: ");
        Navigation.findNavController(btnDoTask)
                .navigate(R.id.action_taskDetailsFragment_to_submitFragment);
    }
}

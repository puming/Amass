package com.pm.imain.mission.task.submit;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.basics.base.AppBarFragment;
import com.common.widget.AppBar;
import com.common.widget.Tile;
import com.pm.imain.R2;
import com.pm.imain.mission.task.details.TaskDetailsViewModel;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pmcho
 */
public class SubmitTaskFragment extends AppBarFragment {
    private static final String TAG = "SubmitTaskFragment";
    private static final int REQUEST_CODE_RECORD_VIDEO = 10;
    private static final int REQUEST_CODE_SELECT_MEDIA = 20;
    private static final int REQUEST_CODE_PERMISSIONS_CAMERA = 100;
    private static final int REQUEST_CODE_PERMISSIONS_EXTERNAL = 200;


    @BindView(R2.id.editText)
    EditText editText;
    @BindView(R2.id.ll_task_media_group)
    LinearLayout llTaskMediaGroup;
    @BindView(R2.id.tile_sync)
    Tile tileSync;
    @BindView(R2.id.tile_location)
    Tile tileLocation;
    @BindView(R2.id.tile_remind_others)
    Tile tileRemindOthers;
    @BindView(R2.id.ibtn_pic_media)
    ImageButton ibtnPicMedia;
    @BindView(R2.id.ibtn_open_camera)
    ImageButton ibtnOpenCamera;
    @BindView(R2.id.ibtn_open_voice)
    ImageButton ibtnOpenVoice;
    private TaskDetailsViewModel mViewModel;

    public static SubmitTaskFragment newInstance() {
        return new SubmitTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tileSync.setCustomTrailing(new Switch(getContext()));
        mViewModel = ViewModelProviders.of(this).get(TaskDetailsViewModel.class);

    }

    @Override
    protected void initAppBar(AppBar appBar) {
        /*Button button = new Button(getContext());
        button.setText("提交");
        button.setTextColor(Color.WHITE);
        int color;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = getContext().getColor(R2.color.colorPrimary);
        } else {
            color = getContext().getResources().getColor(R2.color.colorPrimary);
        }
        button.setBackgroundColor(color);*/
        View button = getLayoutInflater().inflate(R2.layout.layout_button, null);
        button.setOnClickListener(v -> {
            getActivity().finish();
        });
        appBar.showAppbarRightContainer(true)
                .showAppbarMenuIcon(false)
                .showAppbarBackText(false)
                .setAppbarTitle("提交任务")
                .setCustomRightView(button);

    }

    @Override
    protected void onClickAppBarRightView(View view) {
        super.onClickAppBarRightView(view);
        getActivity().onBackPressed();
    }

    @Override
    protected int getContentLayoutId() {
        return R2.layout.submit_task_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_RECORD_VIDEO:
                if (resultCode == Activity.RESULT_OK) {
                    handVideoResult(data);
                }
                break;
            case REQUEST_CODE_SELECT_MEDIA:
                if (resultCode == Activity.RESULT_OK) {
                    String photoPath = Objects.requireNonNull(data).getDataString();
                    Log.d(TAG, "onActivityResult: photoPath=" + photoPath);
                }
                break;
            default:
                break;
        }
    }

    private void handVideoResult(@Nullable Intent data) {
        String videoPath = Objects.requireNonNull(data).getDataString();
        Log.d(TAG, "onActivityResult: videoPath=" + videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer();
        Uri uri = Objects.requireNonNull(data.getData());
        try {
            mediaPlayer.setDataSource(getContext(), uri);
            mediaPlayer.prepare();
            // 获取到的是毫秒值
            int duration = mediaPlayer.getDuration() / 1000;
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaMetadataRetriever media = new MediaMetadataRetriever();
//        media.setDataSource(uri.getPath());
        media.setDataSource(getContext(), uri);
        // 视频的第一帧图片
        Bitmap bitmap = media.getFrameAtTime();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {

            }
        } else if (requestCode == REQUEST_CODE_PERMISSIONS_EXTERNAL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickMedia();
            } else {

            }
        }
    }


    @OnClick({R2.id.tile_location, R2.id.tile_remind_others,
            R2.id.ibtn_pic_media, R2.id.ibtn_open_camera,
            R2.id.ibtn_open_voice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.tile_location:
                break;
            case R2.id.tile_remind_others:
                break;
            case R2.id.ibtn_pic_media:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_PERMISSIONS_EXTERNAL);
                } else {
                    pickMedia();
                }
                break;
            case R2.id.ibtn_open_camera:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CODE_PERMISSIONS_CAMERA);
                } else {
                    openCamera();
                }
                break;
            case R2.id.ibtn_open_voice:
                break;
            default:
                break;
        }
    }

    private void pickMedia() {
        Intent intentPickVideo = new Intent();
        //com.android.gallery3d/com.huawei.gallery.app.ListAlbumPickerActivity
        intentPickVideo.setAction(Intent.ACTION_PICK);

        //com.android.documentsui/.picker.PickActivity
//                intentPickVideo.setAction(Intent.ACTION_GET_CONTENT);
//                intentPickVideo.addCategory(Intent.CATEGORY_OPENABLE);
//                intentPickVideo.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intentPickVideo.setType("image/*");
//        intentPickVideo.setType("video/*");
        startActivityForResult(intentPickVideo, REQUEST_CODE_SELECT_MEDIA);
    }

    private void openCamera() {
        //                String filePath = FileManager.get().getRootFile() + "/msc/" + itemId + ".mp4";
//                Uri uri = Uri.fromFile(new File(filePath));
        Intent intentVideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        // MediaStore.EXTRA_VIDEO_QUALITY 表示录制视频的质量，从 0-1，越大表示质量越好，同时视频也越大
        intentVideo.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
//                intentVideo.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intentVideo.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);
        startActivityForResult(intentVideo, REQUEST_CODE_RECORD_VIDEO);
    }
}

package com.pm.news.details;

import android.os.Bundle;
import android.widget.ImageButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.basics.base.AppBarActivity;
import com.basics.route.GlobalRoutePath;
import com.cameramodule.CameraOwner;
import com.pm.news.R;

/**
 * @author pmcho
 */
@Route(path = GlobalRoutePath.NEWS_DETAILS_ACTIVITY)
public class NewsDetailsActivity extends AppBarActivity {
    private ImageButton mImageButton, mImageButtonCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        mImageButton = findViewById(R.id.img_btn_voice);
        mImageButtonCamera = findViewById(R.id.ibtn_open_camera);
        mImageButton.setOnClickListener(v -> {
            ARouter.getInstance().build(GlobalRoutePath.TRTC_LAUNCH_ACTIVITY).navigation();
        });
        mImageButtonCamera.setOnClickListener(v -> new CameraOwner().start(NewsDetailsActivity.this));
    }
}

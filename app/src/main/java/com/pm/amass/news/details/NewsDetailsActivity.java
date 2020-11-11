package com.pm.amass.news.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.alibaba.android.arouter.launcher.ARouter;
import com.basics.base.AppBarActivity;
import com.pm.amass.R;
import com.pm.amass.RoutePath;

/**
 * @author pmcho
 */
public class NewsDetailsActivity extends AppBarActivity {
    private ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        mImageButton = findViewById(R.id.img_btn_voice);
        mImageButton.setOnClickListener(v -> {
            ARouter.getInstance().build(RoutePath.TRTC_LAUNCH_ACTIVITY).navigation();
        });
    }
}

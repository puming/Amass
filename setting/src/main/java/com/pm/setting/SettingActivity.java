package com.pm.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.basics.repository.Resource;
import com.common.ux.ToastHelper;
import com.common.widget.AppBar;
import com.common.widget.Tile;

/**
 * @author pm
 */
@Route(path = "/setting/setting/SettingActivity")
public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private SettingViewModel mViewModel;
    private Tile mTileNews;
    private Tile mTileAccountManager;
    private Tile mTileCheckbox;
    private Tile mTileFontSize;
    private Tile mTileInvite;
    private Tile mTileClearCache;
    private Tile mTileFeatures;
    private Tile mTileAboutUs;
    private Tile mTileDetectionVersion;
    /**
     * 退出登录
     */
    private Button mBtnLogout;
    private ScrollView mRvSettingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_setting);
        initView();
        AppBar mAppBar = findViewById(R.id.appbar_setting);
        mAppBar.showAppbarBackText(false);
        mAppBar.getAppbarLeftContainer().setOnClickListener(v -> {
            onBackPressed();
        });
        Tile tileTips = findViewById(R.id.tile_news);
        tileTips.showLeading(false);
        tileTips.setTitle("新消息提示");
        Tile tileAccountManager = findViewById(R.id.tile_account_manager);
        tileAccountManager.showLeading(false);
        tileAccountManager.setTitle("账号管理");
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
    }

    private void initView() {
        mTileNews = (Tile) findViewById(R.id.tile_news);
        mTileNews.setOnClickListener(this);
        mTileAccountManager = (Tile) findViewById(R.id.tile_account_manager);
        mTileAccountManager.setOnClickListener(this);
        mTileCheckbox = (Tile) findViewById(R.id.tile_checkbox);
        mTileCheckbox.setOnClickListener(this);
        mTileFontSize = (Tile) findViewById(R.id.tile_font_size);
        mTileFontSize.setOnClickListener(this);
        mTileInvite = (Tile) findViewById(R.id.tile_invite);
        mTileInvite.setOnClickListener(this);
        mTileClearCache = (Tile) findViewById(R.id.tile_clear_cache);
        mTileClearCache.setOnClickListener(this);
        mTileFeatures = (Tile) findViewById(R.id.tile_features);
        mTileFeatures.setOnClickListener(this);
        mTileAboutUs = (Tile) findViewById(R.id.tile_about_us);
        mTileAboutUs.setOnClickListener(this);
        mTileDetectionVersion = (Tile) findViewById(R.id.tile_detection_version);
        mTileDetectionVersion.setOnClickListener(this);
        mBtnLogout = (Button) findViewById(R.id.btn_logout);
        mBtnLogout.setOnClickListener(this);
        mRvSettingList = (ScrollView) findViewById(R.id.rv_setting_list);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tile_news) {
        } else if (id == R.id.tile_account_manager) {
        } else if (id == R.id.tile_checkbox) {
        } else if (id == R.id.tile_font_size) {
        } else if (id == R.id.tile_invite) {
        } else if (id == R.id.tile_clear_cache) {
        } else if (id == R.id.tile_features) {
        } else if (id == R.id.tile_about_us) {
        } else if (id == R.id.tile_detection_version) {
        } else if (id == R.id.btn_logout) {
            mViewModel.getLogoutData()
                    .observe(this, resultInfoResource -> {
                        if (resultInfoResource.status == Resource.Status.SUCCEED) {
//                            AppManager.getInstance().clear();
//                            startActivity(new Intent(this, LoginActivity.class));
                            this.finish();
                        } else if (resultInfoResource.status == Resource.Status.ERROR) {
                            ToastHelper.makeToast(this, resultInfoResource.message, Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}

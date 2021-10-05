package com.pm.imine.personal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.basics.base.AppBarActivity;
import com.basics.route.GlobalRoutePath;
import com.common.widget.Tile;
import com.mediapicker.MediaPickerOwner;
import com.pm.imine.R;
import com.pm.imine.databinding.ActivityPersonalBinding;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pmcho
 */
@Route(path = GlobalRoutePath.PERSONAL_ACTIVITY)
public class PersonalActivity extends AppBarActivity implements View.OnClickListener {
//    @BindView(R2.id.tile_person_head)
    Tile mTilePersonHead;
//    @BindView(R2.id.tile_person_name)
    Tile mTilePersonName;
//    @BindView(R2.id.tile_person_nickname)
    Tile mTilePersonNickname;
//    @BindView(R2.id.tile_person_gender)
    Tile mTilePersonGender;
//    @BindView(R2.id.tile_person_birthday)
    Tile mTilePersonBirthday;
//    @BindView(R2.id.tile_person_grade)
    Tile mTilePersonGrade;
//    @BindView(R2.id.ll_container)
    LinearLayout mContainer;
    private MediaPickerOwner mMediaPickerOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPersonalBinding personalBinding = ActivityPersonalBinding.inflate(getLayoutInflater());
        setContentView(personalBinding.getRoot());
        mTilePersonHead = personalBinding.tilePersonHead;
        mTilePersonName = personalBinding.tilePersonName;
        mTilePersonNickname = personalBinding.tilePersonNickname;
        mTilePersonGender = personalBinding.tilePersonGender;
        mTilePersonBirthday = personalBinding.tilePersonBirthday;
        mTilePersonGrade = personalBinding.tilePersonGrade;
        mContainer = personalBinding.llContainer;

        int childCount = mContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = mContainer.getChildAt(i);
            if (child instanceof Tile) {
                Tile tile = (Tile) child;
                tile.setTitleTextAppearance(R.style.IminePersonTileTitle);
                tile.setTrailingTextAppearance(R.style.ImineTileTrailingText);
            }
        }

        mTilePersonHead.setOnClickListener(this);
        mTilePersonName.setOnClickListener(this);
        mTilePersonNickname.setOnClickListener(this);
        mTilePersonGender.setOnClickListener(this);
        mTilePersonBirthday.setOnClickListener(this);
        mTilePersonGrade.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == mMediaPickerOwner) {
            mMediaPickerOwner = new MediaPickerOwner(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean isDone = mMediaPickerOwner.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isDone = mMediaPickerOwner.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tile_person_head) {
            mMediaPickerOwner.launchPickerActivity();
        } else if (id == R.id.tile_person_name) {
        } else if (id == R.id.tile_person_nickname) {
        } else if (id == R.id.tile_person_gender) {
        } else if (id == R.id.tile_person_birthday) {
        } else if (id == R.id.tile_person_grade) {
        }
    }
}

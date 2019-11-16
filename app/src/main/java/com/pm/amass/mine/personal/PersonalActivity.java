package com.pm.amass.mine.personal;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.basics.base.AppBarActivity;
import com.common.widget.Tile;
import com.pm.amass.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author pmcho
 */
public class PersonalActivity extends AppBarActivity {

    @BindView(R.id.tile_person_head)
    Tile mTilePersonHead;
    @BindView(R.id.tile_person_name)
    Tile mTilePersonName;
    @BindView(R.id.tile_person_nickname)
    Tile mTilePersonNickname;
    @BindView(R.id.tile_person_gender)
    Tile mTilePersonGender;
    @BindView(R.id.tile_person_birthday)
    Tile mTilePersonBirthday;
    @BindView(R.id.tile_person_grade)
    Tile mTilePersonGrade;
    @BindView(R.id.ll_container)
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        int childCount = mContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = mContainer.getChildAt(i);
            if (child instanceof Tile) {
                Tile tile = (Tile) child;
                tile.setTitleTextAppearance(R.style.PersonTileTitle);
                tile.setTrailingTextAppearance(R.style.TileTrailingText);
            }
        }
    }

    @OnClick({R.id.tile_person_head, R.id.tile_person_name, R.id.tile_person_nickname, R.id.tile_person_gender, R.id.tile_person_birthday, R.id.tile_person_grade})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tile_person_head:
                break;
            case R.id.tile_person_name:
                break;
            case R.id.tile_person_nickname:
                break;
            case R.id.tile_person_gender:
                break;
            case R.id.tile_person_birthday:
                break;
            case R.id.tile_person_grade:
                break;
        }
    }
}

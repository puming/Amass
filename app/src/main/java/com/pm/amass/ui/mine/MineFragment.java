package com.pm.amass.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basics.base.BaseFragment;
import com.common.widget.AppBar;
import com.pm.amass.R;
import com.pm.amass.bean.TileInfo;
import com.pm.amass.mine.mall.MallActivity;
import com.pm.amass.mine.mission.MissionActivity;
import com.pm.amass.mine.personal.PersonalActivity;
import com.pm.amass.mine.top.TopActivity;
import com.pm.amass.mine.setting.SettingActivity;

import java.util.ArrayList;

/**
 * @author pmcho
 */
public class MineFragment extends BaseFragment {
    AppBar mAppBar;
    RecyclerView mRecyclerView;
    private ArrayList<TileInfo> datas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new ArrayList(12);
        createData();
    }

    private void createData() {
        TileInfo info = new TileInfo();
        info.setIcon(R.drawable.ic_yaoqing_vector);
        info.setTitle("邀请家人");
        datas.add(info);
        TileInfo info1 = new TileInfo();
        info1.setIcon(R.drawable.ic_bdingbji_vector);
        info1.setTitle("绑定班级");
        info1.setBoundary(true);
        datas.add(info1);

        TileInfo info2 = new TileInfo();
        info2.setIcon(R.drawable.ic_wddingdan_vector);
        info2.setTitle("我的订单");
        datas.add(info2);
        TileInfo info3 = new TileInfo();
        info3.setIcon(R.drawable.ic_liquan_vector);
        info3.setTitle("我的礼券");
        datas.add(info3);
        TileInfo info4 = new TileInfo();
        info4.setIcon(R.drawable.ic_jiybi_vector);
        info4.setTitle("积也币");
        info4.setBoundary(true);
        datas.add(info4);

        TileInfo info5 = new TileInfo();
        info5.setIcon(R.drawable.ic_bdingfexiang_vector);
        info5.setTitle("绑定分享");
        datas.add(info5);
        TileInfo info6 = new TileInfo();
        info6.setIcon(R.drawable.ic_shoucang_vector);
        info6.setTitle("我的收藏");
        datas.add(info6);
        TileInfo info7 = new TileInfo();
        info7.setIcon(R.drawable.ic_dongtai_vector);
        info7.setTitle("我的动态");
        datas.add(info7);
        TileInfo info8 = new TileInfo();
        info8.setIcon(R.drawable.ic_xuexiao_vector);
        info8.setTitle("我的学校");
        info8.setBoundary(true);
        datas.add(info8);

        TileInfo info9 = new TileInfo();
        info9.setIcon(R.drawable.ic_caogxiang_vector);
        info9.setTitle("草稿箱");
        datas.add(info9);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppBar = view.findViewById(R.id.appbar);
        mAppBar.setAppbarMenuIcon(R.drawable.ic_setting)
                .showAppbarMenuIcon(true);
        mAppBar.getAppbarRightContainer().setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.action_mine_to_setting);
            startActivity(new Intent(getActivity(), SettingActivity.class));
        });
        ViewGroup mMineLayout = view.findViewById(R.id.mine_header_layout);
        mMineLayout.setOnClickListener(v -> startActivity(new Intent(getContext(), PersonalActivity.class)));

        view.findViewById(R.id.ll_goto_mission).setOnClickListener(v -> startActivity(new Intent(getContext(), MissionActivity.class)));
        view.findViewById(R.id.ll_goto_top).setOnClickListener(v -> startActivity(new Intent(getContext(), TopActivity.class)));
        view.findViewById(R.id.ll_goto_mall).setOnClickListener(v -> startActivity(new Intent(getContext(), MallActivity.class)));
        mRecyclerView = view.findViewById(R.id.rv_mine_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Object obj = new Object();
        ArrayList<Object> list = new ArrayList<>(10);
        for (int i = 0; i < 20; i++) {
            list.add(obj);
        }
        mRecyclerView.setAdapter(new MineAdapter(getContext(), datas));
    }
}

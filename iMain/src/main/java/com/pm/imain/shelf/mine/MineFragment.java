package com.pm.imain.shelf.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.basics.base.BaseFragment;
import com.basics.route.GlobalRoutePath;
import com.common.data.LiveDataManager;
import com.common.widget.AppBar;
import com.pm.imain.R;
import com.pm.imain.RoutePath;
import com.pm.imain.bean.TileInfo;
import com.qrcode.QrCodeOwner;

import java.util.ArrayList;

/**
 * @author pmcho
 */
public class MineFragment extends BaseFragment {
    private static final String TAG = "MineFragment";
    AppBar mAppBar;
    RecyclerView mRecyclerView;
    private ArrayList<TileInfo> datas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
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
        Log.d(TAG, "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        mAppBar = view.findViewById(R.id.appbar);
        mAppBar.setAppbarMenuIcon(R.drawable.ic_setting)
                .setAppbarBackIcon(R.drawable.ic_scan)
                .showAppbarLeftContainer(true)
                .showAppbarBackText(false)
                .showAppbarBackIcon(true)
                .showAppbarMenuIcon(true);
        mAppBar.getAppbarLeftContainer().setOnClickListener(v -> {
            new QrCodeOwner().start(getActivity());
        });
        mAppBar.getAppbarRightContainer().setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.action_mine_to_setting);
//            startActivity(new Intent(getActivity(), SettingActivity.class));
            ARouter.getInstance().build(RoutePath.SETTING_ACTIVITY).navigation(getActivity());
        });
        ViewGroup mMineLayout = view.findViewById(R.id.mine_header_layout);
//        mMineLayout.setOnClickListener(v -> startActivity(new Intent(getContext(), PersonalActivity.class)));
        mMineLayout.setOnClickListener(v -> ARouter.getInstance()
                .build(GlobalRoutePath.PERSONAL_ACTIVITY)
                .navigation(getActivity()));

        view.findViewById(R.id.ll_goto_mission).setOnClickListener(v -> ARouter.getInstance()
                .build(GlobalRoutePath.MISSION_ACTIVITY)
                .navigation(getActivity()));
        view.findViewById(R.id.ll_goto_top).setOnClickListener(v -> ARouter.getInstance()
                .build(GlobalRoutePath.TOP_ACTIVITY)
                .navigation(getActivity()));
        view.findViewById(R.id.ll_goto_mall).setOnClickListener(v -> ARouter.getInstance()
                .build(GlobalRoutePath.MALL_ACTIVITY)
                .navigation(getActivity()));
        mRecyclerView = view.findViewById(R.id.rv_mine_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Object obj = new Object();
        ArrayList<Object> list = new ArrayList<>(10);
        for (int i = 0; i < 20; i++) {
            list.add(obj);
        }
        mRecyclerView.setAdapter(new MineAdapter(getContext(), datas));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
        LiveDataManager.getInstance().getLiveData("token", String.class)
                .observe(getViewLifecycleOwner(), s -> {

                });
    }
}

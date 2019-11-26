package com.pm.amass.mine.mall;


import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.google.android.material.tabs.TabLayout;
import com.pm.amass.R;
import com.pm.amass.bean.ChannelResult.Channel;
import com.pm.amass.mine.mall.content.MallContentFragment;
import com.pm.amass.mine.mall.content.MallContentViewModel;
import com.pm.amass.common.ContentFragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pm
 */
public class MallActivity extends AppBarActivity {
    private static final String TAG = "MallActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    MallContentViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        initView();
        registerListener();
        mViewModel = ViewModelProviders.of(this).get(MallContentViewModel.class);
        mViewModel.getShopType().observe(this, shopTypeResource -> {
            switch (shopTypeResource.status) {
                case SUCCEED:
                    List<Channel> channelList = shopTypeResource.data.getData();
                    bindPage(channelList);
                    break;
                case ERROR:
                    Log.d(TAG, "onCreate: "+shopTypeResource.code);
                    break;
                default:
                    break;
            }
        });
//        bindPage();
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tabs_mall);
        mViewPager = findViewById(R.id.vp_mall);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        super.initAppBar(appBar);
        appBar.setAppbarTitle("积分商城");
    }

    private void registerListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {

                }
            }
        });
    }


    private void bindPage(List<Channel> channels) {
        final int size = channels.size();
        ArrayList<ViewGroup> pages = new ArrayList<>(size);
        ArrayList<Fragment> fragments = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Channel Channel = channels.get(i);
            //tab
            TabLayout.Tab tab = mTabLayout.newTab().setText(Channel.getTitle()).setTag(Channel.getId());
            mTabLayout.addTab(tab);
            //page
            /*FrameLayout layout = new FrameLayout(this);
            TextView child = new TextView(this);
            child.setText("第" + i + "页");
            layout.addView(child, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));
            pages.add(layout);*/
            MallContentFragment homeFragment = MallContentFragment.newInstance();
            fragments.add(homeFragment);
        }
//        mViewPager.setAdapter(new HomePageAdapter<ViewGroup>(pages));
        ContentFragmentPageAdapter adapter = new ContentFragmentPageAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
    }

    private ArrayList<Channel> initData() {
        ArrayList<Channel> Channels = new ArrayList<>(8);
        Channel Channel = new Channel();
        Channel.setId(0);
        Channel.setTitle("全部");
        Channels.add(Channel);

        Channel Channel1 = new Channel();
        Channel1.setId(1);
        Channel1.setTitle("玩具类");
        Channels.add(Channel1);

        Channel Channel2 = new Channel();
        Channel2.setId(2);
        Channel2.setTitle("学习用品");
        Channels.add(Channel2);

        Channel channe3 = new Channel();
        channe3.setId(3);
        channe3.setTitle("日常用品");
        Channels.add(channe3);
        return Channels;
    }


}

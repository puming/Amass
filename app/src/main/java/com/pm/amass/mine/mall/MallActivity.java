package com.pm.amass.mine.mall;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.google.android.material.tabs.TabLayout;
import com.pm.amass.R;
import com.pm.amass.bean.Channel;
import com.pm.amass.mine.mall.content.MallContentFragment;
import com.pm.amass.ui.growth.ContentFragmentPageAdapter;
import com.pm.amass.ui.growth.content.ContentFragment;

import java.util.ArrayList;

/**
 * @author pm
 */
public class MallActivity extends AppBarActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        initView();
        registerListener();
        bindPage();
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



    private void bindPage() {
        ArrayList<Channel> channels = initData();
        final int size = channels.size();
        ArrayList<ViewGroup> pages = new ArrayList<>(size);
        ArrayList<Fragment> fragments = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Channel channel = channels.get(i);
            //tab
            TabLayout.Tab tab = mTabLayout.newTab().setText(channel.getTitle()).setTag(channel.getId());
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
        ArrayList<Channel> channels = new ArrayList<>(8);
        Channel channel = new Channel();
        channel.setId(0);
        channel.setTitle("全部");
        channels.add(channel);

        Channel channel1 = new Channel();
        channel1.setId(1);
        channel1.setTitle("玩具类");
        channels.add(channel1);

        Channel channel2 = new Channel();
        channel2.setId(2);
        channel2.setTitle("学习用品");
        channels.add(channel2);

        Channel channe3 = new Channel();
        channe3.setId(3);
        channe3.setTitle("日常用品");
        channels.add(channe3);
        return channels;
    }


}

package com.pm.amass.ui.growth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pm.amass.R;
import com.pm.amass.bean.Channel;
import com.pm.amass.ui.growth.content.ContentFragment;

import java.util.ArrayList;

/**
 * @author pm
 */
public class GrowthFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private GrowthViewModel dashboardViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(GrowthViewModel.class);
        View root = inflater.inflate(R.layout.fragment_growth, container, false);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        registerListener();


        bindPage();
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
            ContentFragment homeFragment = ContentFragment.newInstance();
            fragments.add(homeFragment);
        }
//        mViewPager.setAdapter(new HomePageAdapter<ViewGroup>(pages));
        ContentFragmentPageAdapter adapter = new ContentFragmentPageAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
    }

    private ArrayList<Channel> initData() {
        ArrayList<Channel> channels = new ArrayList<>(8);
        Channel channel = new Channel();
        channel.setId(0);
        channel.setTitle("知识库");
        channels.add(channel);

        Channel channel1 = new Channel();
        channel1.setId(1);
        channel1.setTitle("推荐");
        channels.add(channel1);

        Channel channel2 = new Channel();
        channel2.setId(2);
        channel2.setTitle("启蒙教育");
        channels.add(channel2);

        Channel channe3 = new Channel();
        channe3.setId(3);
        channe3.setTitle("育儿助手");
        channels.add(channe3);
        return channels;
    }

    private void initView(View view) {
        mTabLayout = view.findViewById(R.id.tabs);
        mViewPager = view.findViewById(R.id.viewpager);
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
}
package com.pm.amass.shelf.growth;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.basics.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.pm.amass.R;
import com.pm.amass.bean.ChannelResult;
import com.pm.amass.bean.ChannelResult.Channel;
import com.pm.amass.shelf.growth.content.ContentFragment;

import java.util.ArrayList;
import java.util.List;

import com.pm.amass.common.ContentFragmentPageAdapter;

/**
 * @author pm
 */
public class GrowthFragment extends BaseFragment {
    private static final String TAG = "GrowthFragment";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private GrowthViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mViewModel = ViewModelProviders.of(this).get(GrowthViewModel.class);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_growth;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        initView(view);
        registerListener();
        mViewModel.getArticleChannel().observe(this, resultResource -> {
            switch (resultResource.status) {
                case SUCCEED:
                    ChannelResult result = resultResource.data;
                    List<Channel> channelList = result.getData();
                    Log.d(TAG, "onViewCreated: size" + channelList.size());
                    bindPage(channelList);
                    break;
                case ERROR:
                    Log.d(TAG, "onViewCreated: error:" + resultResource.message + "code:" + resultResource.code);
                    break;
                default:
                    break;
            }
        });
//        ArrayList<Channel> Channels = initData();
//        bindPage(Channels);
    }

    private void bindPage(List<Channel> Channels) {
        final int size = Channels.size();
        ArrayList<ViewGroup> pages = new ArrayList<>(size);
        ArrayList<Fragment> fragments = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Channel Channel = Channels.get(i);
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
            ContentFragment homeFragment = ContentFragment.newInstance();
            fragments.add(homeFragment);
        }
//        mViewPager.setAdapter(new HomePageAdapter<ViewGroup>(pages));
        ContentFragmentPageAdapter adapter = new ContentFragmentPageAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
    }

    private ArrayList<Channel> initData() {
        ArrayList<Channel> Channels = new ArrayList<>(8);
        Channel Channel = new Channel();
        Channel.setId(0);
        Channel.setTitle("知识库");
        Channels.add(Channel);

        Channel Channel1 = new Channel();
        Channel1.setId(1);
        Channel1.setTitle("推荐");
        Channels.add(Channel1);

        Channel Channel2 = new Channel();
        Channel2.setId(2);
        Channel2.setTitle("启蒙教育");
        Channels.add(Channel2);

        Channel channe3 = new Channel();
        channe3.setId(3);
        channe3.setTitle("育儿助手");
        Channels.add(channe3);
        return Channels;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }
}
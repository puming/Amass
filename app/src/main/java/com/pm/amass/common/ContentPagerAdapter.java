package com.pm.amass.common;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author puming
 * @date 2017/12/20
 */
public class ContentPagerAdapter<M extends ViewGroup> extends PagerAdapter {
    private List<M> totalList;
    private int mCurrentPosition = -1;

    public ContentPagerAdapter(List<M> totalList) {
        this.totalList = totalList;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (totalList != null) {
            ret = totalList.size();
        }
        return ret;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup page = totalList.get(position);
        ViewParent viewParent = page.getParent();
        if (viewParent != null) {
            ViewGroup parent = (ViewGroup) viewParent;
            parent.removeView(page);
        }
        container.addView(page);
       /* if (page instanceof AbsContainerView) {
            AbsContainerView containerView = (AbsContainerView) page;
            containerView.onAdd();
        }*/
        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewGroup page = totalList.get(position);
        container.removeView(page);
        /*if (page instanceof AbsContainerView) {
            AbsContainerView containerView = (AbsContainerView) page;
            containerView.onRemove();
        }*/
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (mCurrentPosition != position) {
            /*if (object instanceof AbsContainerView) {
                AbsContainerView containerView = (AbsContainerView) object;
//                containerView.onShow();
            }
            if (mCurrentPosition >= 0) {
                AbsContainerView page = (AbsContainerView) totalList.get(mCurrentPosition);
//                page.onHide();
            }*/
            mCurrentPosition = position;
        }
    }

    /**
     * 插入数据
     */
    public void insertData(List<M> viewGroups) {
        totalList.addAll(0, viewGroups);
        notifyDataSetChanged();
    }

    public void insertData(M viewGroups, int index) {
        totalList.add(index, viewGroups);
        notifyDataSetChanged();
    }

    public void deleteData(int position) {
        totalList.remove(position);
        notifyDataSetChanged();
    }

    public void addData(List<M> viewGroups) {
        totalList = viewGroups;
        notifyDataSetChanged();
    }

    public List<M> getTotalList() {
        return totalList;
    }
}

package com.hpu.rule.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * viewPager的adapter
 * Created by hjs on 2015/11/9.
 */
public class MyPagerAdapter extends PagerAdapter {
    private List<ImageView> mList;

    public MyPagerAdapter(ArrayList<ImageView> views) {
        mList = new ArrayList<>();
        for (ImageView view : views) {
            mList.add(view);
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    //添加下一页
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到view 的爹，之前已经记录了一个
        ViewParent parent = mList.get(position % mList.size()).getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(mList.get(position % mList.size()));
        }
        container.addView(mList.get(position % mList.size()));
        return mList.get(position % mList.size());
    }
    //销毁上一页
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object = null;
    }
}

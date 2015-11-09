package com.hpu.rule.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * viewPager的adapter
 * Created by hjs on 2015/11/9.
 */
public class MyPagerAdapter extends PagerAdapter {
    private List<View> mList;
    public MyPagerAdapter(Context mContext,List<Integer> list){
         mList=new ArrayList<View>();
       LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        for (int i=0;i<list.size();i++){
            ImageView mimageView=new ImageView(mContext);
            mimageView.setLayoutParams(mParams);
            mimageView.setBackgroundResource(list.get(i));
            mList.add(mimageView);
        }
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    //销毁上一页
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position=position%mList.size();
        container.removeView(mList.get(position));
    }
    //添加下一页
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position=position%mList.size();
        container.addView(mList.get(position));
        return mList.get(position);
    }
}

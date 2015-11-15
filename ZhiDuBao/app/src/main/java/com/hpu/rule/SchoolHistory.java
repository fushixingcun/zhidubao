package com.hpu.rule;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.ImageView;

import com.hpu.rule.adapter.MyPagerAdapter;
import com.hpu.rule.bease.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SchoolHistory extends BaseActivity {
    //小白点
    private ImageView[] dots;
    //小白点的id
    private int[] views={R.id.history_iv1,R.id.history_iv2,R.id.history_iv3,R.id.history_iv4,R.id.history_iv5};
    private ViewPager mViewPager;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            mHandler.sendEmptyMessageDelayed(0,3000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_history);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        mViewPager=(ViewPager)findViewById(R.id.school_history_viewPager);
        initDots();
        List<Integer> viewPagerList=new ArrayList<Integer>();
        viewPagerList.add(R.mipmap.ligong_nihao);
        viewPagerList.add(R.mipmap.hpu1);
        viewPagerList.add(R.mipmap.water);
        viewPagerList.add(R.mipmap.hpu2);
        viewPagerList.add(R.mipmap.hpu3);

        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(SchoolHistory.this,viewPagerList);
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int mPosition) {
                for (int i=0;i<5;i++){
                    dots[i].setImageResource(R.mipmap.white);
                }
                int positon = mPosition % views.length;
                dots[positon].setImageResource(R.mipmap.blue);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mHandler.sendEmptyMessageDelayed(0, 3000);

    }
    //初始化小白点
    private void initDots(){
        dots=new ImageView[views.length];
        for (int i=0;i<5;i++){
            dots[i]=(ImageView)findViewById(views[i]);
        }
    }

    //给返回键添加功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

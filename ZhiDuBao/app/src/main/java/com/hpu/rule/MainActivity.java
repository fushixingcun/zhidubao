package com.hpu.rule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hpu.rule.adapter.HomeAdapter;
import com.hpu.rule.adapter.MyPagerAdapter;
import com.hpu.rule.bease.BaseActivity;
import com.hpu.rule.view.Home;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{
    private ActionBar actionBar;
    //主页的ListView
    private ListView listView;
   // private List<View> views;
    //viewpager
    private ViewPager mPager;
    //设置viewpager的小点
    //private ImageView[] dots;
    //定义小白点的id
    //private int[] ids={R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4,R.id.iv5};
    //设置滑动的最大值
    int position=50000;
    //判断用户是否触摸
    private boolean isTouched=false;
    //用handler来处理图片的自动滑动
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mPager.setCurrentItem(position);
        }
    };
    //初始化主页的ListView
    private List<Home> homeList = new ArrayList<Home>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //actionBar = getSupportActionBar();
        //让actionbar的返回按钮显示出来
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //得到viewpager的实例
        mPager=(ViewPager)findViewById(R.id.viewPager);
        //初始化小白点
       // initDots();
        //定义一个list，用于viewpager图片的显示
        List<Integer> list=new ArrayList<Integer>();
        list.add(R.mipmap.ligong_nihao);
        list.add(R.mipmap.hpu1);
        list.add(R.mipmap.water);
        list.add(R.mipmap.hpu3);
        list.add(R.mipmap.hpu2);
        //得到pageradapter的实例
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(MainActivity.this,list);
        //设置adapter
        mPager.setAdapter(myPagerAdapter);
        mPager.setCurrentItem(position);
        //可以自动滑动，也可以用手指滑动
        mPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isTouched = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isTouched = false;
                    default:
                        break;
                }
                return false;
            }
        });
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int mPosition) {
               position = mPosition;
//                for (int i=0;i<ids.length;i++){
//                    if (mPosition==i){
//                        dots[i].setImageResource(R.mipmap.blue);
//                    }else {
//                        dots[i].setImageResource(R.mipmap.white);
//                    }
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//定义一个线程，用于处理图片的循环播放
        Thread change = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (!isTouched) {
                        position++;
                        mHandler.sendEmptyMessage(0);
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        change.start();

        //初始化主页的list数据
        initHome();
        //初始化主页的适配器
        HomeAdapter homeAdapter = new HomeAdapter(MainActivity.this, R.layout.home_item, homeList);
        //初始化ListView
        listView = (ListView) findViewById(R.id.main_listView);
        listView.setAdapter(homeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //跳转到不同的activity
                Intent itemIntent=new Intent();
                switch (position){
                    case 0:
                        itemIntent.setClass(getApplicationContext(), SchoolRule.class);
                        MainActivity.this.startActivity(itemIntent);
                        break;
                    case 1:
                        itemIntent.setClass(getApplicationContext(),HeadMaster.class);
                        MainActivity.this.startActivity(itemIntent);
                        break;
                    case 2:
                        itemIntent.setClass(getApplicationContext(), SchoolHistory.class);
                        MainActivity.this.startActivity(itemIntent);
                        break;
                    case 3:
                    itemIntent.setClass(getApplicationContext(),SchoolHistory.class);
                        MainActivity.this.startActivity(itemIntent);
                    break;
                }

            }
        });
    }
    //初始化小白点
//    private void initDots() {
//        dots=new ImageView[views.size()];
//        for (int i=0;i<views.size();i++){
//            dots[i]=(ImageView)findViewById(ids[i]);
//        }
//    }

    //向listV添加数据
    private void initHome() {
        Home school_rule = new Home("校规校纪", R.mipmap.school_rule);
        homeList.add(school_rule);
        Home my_favorite=new Home("校长寄语",R.mipmap.headmaster);
        homeList.add(my_favorite);
        Home school_history=new Home("悠悠校园",R.mipmap.school);
        homeList.add(school_history);
        Home school_master=new Home("我的收藏",R.mipmap.my_favorite);
        homeList.add(school_master);
    }
    private long exitTime = 0;
    //再按一次退出
    // 双击退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再次点击退出",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                // 将应用程序在后台运行
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

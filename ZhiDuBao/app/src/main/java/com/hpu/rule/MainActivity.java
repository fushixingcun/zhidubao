package com.hpu.rule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.hpu.rule.adapter.HomeAdapter;
import com.hpu.rule.adapter.MyPagerAdapter;
import com.hpu.rule.bease.BaseActivity;
import com.hpu.rule.view.Home;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //主页的ListView
    private ListView listView;
    //viewpager
    private ViewPager mPager;
    // 图片资源ID
    private final int[] imageIds = {R.drawable.first_viewpager, R.drawable.second_viewpager,
            R.drawable.third_viewpager};
    //用handler来处理图片的自动滑动
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }
    };
    //初始化主页的ListView
    private List<Home> homeList = new ArrayList<>();
    private ArrayList<ImageView> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageList = new ArrayList<>();
        //让actionbar的返回按钮显示出来
        //让Overflow永远显示在右边，不会因为有物理的menu键，而消失
        setOverflowShowingAlways();
        //得到viewpager的实例
        mPager = (ViewPager) findViewById(R.id.viewPager);
        //定义一个list，用于图片的显示
        for (int i = 0; i < imageIds.length; i++) {
            //初始化图片资源
            ImageView image = new ImageView(this);
            image.setBackgroundResource(imageIds[i]);
            imageList.add(image);
        }
        //得到pageradapter的实例
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(imageList);
        //设置adapter
        mPager.setAdapter(myPagerAdapter);
        mPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageList.size()));
        //启动循环
        mHandler.sendEmptyMessageDelayed(0, 3000);

        //初始化主页的list数据
        initHome();
        //初始化主页的适配器
        HomeAdapter homeAdapter = new HomeAdapter(MainActivity.this, R.layout.home_item, homeList);
        //初始化ListView
        listView = (ListView) findViewById(R.id.main_listView);
        listView.setAdapter(homeAdapter);
    }

    //向listV添加数据
    private void initHome() {
        Home school_rule = new Home("校规校纪", R.drawable.school_rule);
        homeList.add(school_rule);
        Home my_favorite = new Home("校长寄语", R.drawable.headmaster);
        homeList.add(my_favorite);
        Home school_history = new Home("悠悠校园", R.drawable.school);
        homeList.add(school_history);
        Home school_master = new Home("我的收藏", R.drawable.my_favorite);
        homeList.add(school_master);
    }

    //由于手机的不同，ActionBar最右边的overflow按钮有时候显示，有时候不显示，解决办法
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration configuration = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(configuration, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //让overflow中的选项显示图标
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                break;
            case R.id.contact:
                Intent contact_intent = new Intent(MainActivity.this, ActSendFeedback.class);
                startActivity(contact_intent);
                break;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

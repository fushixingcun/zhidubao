package com.hpu.rule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ShareActionProvider;

import com.hpu.rule.adapter.MyPagerAdapter;
import com.hpu.rule.bease.BaseActivity;
import com.hpu.rule.view.Home;
import com.hpu.rule.adapter.HomeAdapter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{
    //主页的ListView
    private ListView listView;
    //viewpager
    private ViewPager mPager;
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
        //让actionbar的返回按钮显示出来
        getActionBar().setDisplayHomeAsUpEnabled(true);
        //让Overflow永远显示在右边，不会因为有物理的menu键，而消失
        setOverflowShowingAlways();
        //得到viewpager的实例
        mPager=(ViewPager)findViewById(R.id.viewPager);
        //定义一个list，用于图片的显示
        List<Integer> list=new ArrayList<Integer>();
        list.add(R.drawable.first_viewpager);
        list.add(R.drawable.second_viewpager);
        list.add(R.drawable.third_viewpager);
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
                Home home=homeList.get(position);
            }
        });
    }

    //向listV添加数据
    private void initHome() {
        Home school_rule = new Home("校规校纪", R.drawable.school_rule);
        homeList.add(school_rule);
        Home my_favorite=new Home("校长寄语",R.drawable.headmaster);
        homeList.add(my_favorite);
        Home school_history=new Home("悠悠校园",R.drawable.school);
        homeList.add(school_history);
        Home school_master=new Home("我的收藏",R.drawable.my_favorite);
        homeList.add(school_master);
    }
    //由于手机的不同，ActionBar最右边的overflow按钮有时候显示，有时候不显示，解决办法
    private void setOverflowShowingAlways(){
        try {
            ViewConfiguration configuration=ViewConfiguration.get(this);
            Field menuKeyField=ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(configuration,false);
        }catch (Exception e){
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
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        //对actionbar的search进行设置
        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)searchItem.getActionView();
        //对share进行设置
        MenuItem shareItem=menu.findItem(R.id.action_share);
        ShareActionProvider provider=(ShareActionProvider)shareItem.getActionProvider();
        provider.setShareIntent(getDefaultIntent());
        return super.onCreateOptionsMenu(menu);
    }
    //设置一个可以分享image的应用
    private Intent getDefaultIntent(){
     Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return  intent;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                break;
            case R.id.action_setting:
                break;
            case R.id.update:
                break;
            case R.id.contact:
                Intent contact_intent=new Intent(MainActivity.this,ActSendFeedback.class);
                startActivity(contact_intent);
                break;
            case android.R.id.home:
                finish();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}

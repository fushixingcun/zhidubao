package com.hpu.rule.bease;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.hpu.rule.ActSendFeedback;
import com.hpu.rule.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;

/**
 * baseActivity
 * Created by hjs on 2015/11/7.
 */
public class BaseActivity extends Activity {
    public static String APPID = "a81a11c43e05047b50c03cb067e8401c";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setOverflowShowingAlways();
        // 初始化BmobSDK
        Bmob.initialize(this, APPID);
        BmobPush.startWork(this,APPID);
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
        menuInflater.inflate(R.menu.main, menu);
        //对actionbar的search进行设置
//        MenuItem searchItem=menu.findItem(R.id.action_search);
//        SearchView searchView=(SearchView)searchItem.getActionView();
        return super.onCreateOptionsMenu(menu);
    }
    //设置一个可以分享image的应用
//    private Intent getDefaultIntent(){
//     Intent intent=new Intent(Intent.ACTION_SEND);
//        intent.setType("image/*");
//        return  intent;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            case R.id.action_search:
//                break;
//            case R.id.action_setting:
//                break;
            case R.id.update:
                break;
            case R.id.contact:
                Intent contact_intent=new Intent(getApplicationContext(),ActSendFeedback.class);
                startActivity(contact_intent);
                break;
            case android.R.id.home:
                finish();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

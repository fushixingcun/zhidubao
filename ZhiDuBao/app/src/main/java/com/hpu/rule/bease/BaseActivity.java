package com.hpu.rule.bease;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

        // 初始化BmobSDK
        Bmob.initialize(this, APPID);
        BmobPush.startWork(this,APPID);
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
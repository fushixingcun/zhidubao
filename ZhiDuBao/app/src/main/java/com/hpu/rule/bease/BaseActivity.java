package com.hpu.rule.bease;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewConfiguration;
import android.widget.Toast;

import java.lang.reflect.Field;

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
        BmobPush.startWork(this, APPID);
    }





    //设置一个可以分享image的应用
//    private Intent getDefaultIntent(){
//     Intent intent=new Intent(Intent.ACTION_SEND);
//        intent.setType("image/*");
//        return  intent;
//    }


    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

package com.hpu.rule;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.hpu.rule.bease.BaseActivity;

/**
 * 用于实现校长寄语
 * Created by hjs on 2015/11/12.
 */
public class HeadMaster extends BaseActivity {
    private ActionBar actionBar;
    private TextView zhouYouFeng;
    private TextView yangXiaoLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headmaster);


        zhouYouFeng = (TextView) findViewById(R.id.headmaster_text_content6);
    }


}

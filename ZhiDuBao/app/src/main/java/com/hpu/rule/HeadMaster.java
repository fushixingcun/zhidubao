package com.hpu.rule;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.SpannableString;
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
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        zhouYouFeng=(TextView)findViewById(R.id.headmaster_text_content6);
       //创建一个 SpannableString对象
        SpannableString sp=new SpannableString("");
    }
}

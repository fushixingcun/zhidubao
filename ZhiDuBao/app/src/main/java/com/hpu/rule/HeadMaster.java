package com.hpu.rule;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
<<<<<<< HEAD
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.MenuItem;
=======
>>>>>>> b40853b42600415bed7b378949e3767ba8f90a8b
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

<<<<<<< HEAD
        zhouYouFeng=(TextView)findViewById(R.id.headmaster_text_content6);
        yangXiaoLin=(TextView)findViewById(R.id.headmaster_text_content7);
        //创建一个 SpannableString对象
        SpannableString sp1=new SpannableString("校党委书记：邹友峰");
        sp1.setSpan(new URLSpan("https://www.baidu.com/s?wd=%E9%82%B9%E5%8F%8B%E5%B3%B0&rsv_spt=1&rsv_iqid=0xe67a352100091eda&issp=1&f=3&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=3&rsv_sug1=3&rsv_sug2=1&rsp=0&rsv_sug9=es_0_1&inputT=2597&rsv_sug4=3105&rsv_sug=9"), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        zhouYouFeng.setText(sp1);
        zhouYouFeng.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString sp2=new SpannableString("校  长：杨小林");
        sp2.setSpan(new URLSpan("http://baike.baidu.com/link?url=yuyW3H5rJmpwCkZh1zMeV27rjljdDpwviBWCaJn5FVE6fp8xPDisH8Ux1vq_42uYEy_meoEW9S9e3Od7TrXDD0F7bBAwr7JBlSLrePZbmYW"),5,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        yangXiaoLin.setText(sp2);
        yangXiaoLin.setMovementMethod(LinkMovementMethod.getInstance());
    }
    //给返回键添加功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
=======

        zhouYouFeng = (TextView) findViewById(R.id.headmaster_text_content6);
>>>>>>> b40853b42600415bed7b378949e3767ba8f90a8b
    }


}

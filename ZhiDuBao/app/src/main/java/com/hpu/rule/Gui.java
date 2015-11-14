package com.hpu.rule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/*
用于实现引导界面
 */
public class Gui extends Activity{
    private TextView guiText;
    private int count=3;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gui);
        guiText=(TextView)findViewById(R.id.gui_text);
        mAnimation= AnimationUtils.loadAnimation(this,R.anim.anim_text);

        handler.sendEmptyMessageDelayed(0, 1000);
    }

    private Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                guiText.setText(getCount()+"");
                handler.sendEmptyMessageDelayed(0,1000);
                mAnimation.reset();
                guiText.startAnimation(mAnimation);
            }
        }
    };

    private int getCount(){
        count--;
        if(count==0){
            Intent intent = new Intent(Gui.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return count;
    }

}

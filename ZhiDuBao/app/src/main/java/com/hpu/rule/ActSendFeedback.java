package com.hpu.rule;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hpu.rule.bease.Feedback;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.SaveListener;

/**用于用户的反馈
 * Created by hjs on 2015/11/8.
 */
public class ActSendFeedback extends MainActivity implements View.OnClickListener {
    private EditText information_edt;
    private EditText content_edt;
    static String msg1;
    static String msg2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendfeedback);

        information_edt=(EditText)findViewById(R.id.information_edt);
        content_edt=(EditText)findViewById(R.id.content_edt);
    }

    @Override
    public void onClick(View view) {
        String information_String=information_edt.getText().toString();
        String content_String=content_edt.getText().toString();
        if(TextUtils.isEmpty(information_String)){
            Toast.makeText(this,"请输入你的联系方式",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(content_String)){
            Toast.makeText(this,"请输入您的建议",Toast.LENGTH_SHORT).show();
        }else {
            if(information_String.equals(msg1)&&content_String.equals(msg2)){
                Toast.makeText(this,"请勿重复提交反馈",Toast.LENGTH_SHORT).show();
            }else{
                msg1=information_String;
                msg2=content_String;
                //发送信息给开发者
//               sendMessage(information_String,content_String);
                //发送信息给服务器
                saveFeedbackMsg(msg1,msg2);
                Toast.makeText(this,"您的信息已经发送，谢谢您的参与",Toast.LENGTH_SHORT).show();
            }
        }
    }
//    /*
//    发送信息给开发者
//     */
//    private void sendMessage(String msg1,String msg2){
//        BmobPushManager bmobPushManager=new BmobPushManager(this);
//        BmobQuery<BmobInstallation> query=BmobInstallation.getQuery();
//        query.addWhereEqualTo("isDeveloper",true);
//        bmobPushManager.setQuery(query);
//        String message=msg1+msg2;
//        bmobPushManager.pushMessage(message);
//    }
    /*
    反馈信息发送给服务器
     */
    private  void saveFeedbackMsg(String message1,String message2){
        String message =message1+message2;
        Feedback feedback=new Feedback();
        feedback.setContent(message);
        feedback.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.e("feedback.save","success");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e("feedback.save", "failure");
            }
        });
    }
}

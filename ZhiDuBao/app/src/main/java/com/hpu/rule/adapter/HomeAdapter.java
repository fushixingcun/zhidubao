package com.hpu.rule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpu.rule.R;
import com.hpu.rule.view.Home;

import java.util.List;

/**
 * 为主页提供一个自定义的adapter
 * Created by hjs on 2015/11/7.
 */
public class HomeAdapter extends ArrayAdapter<Home> {
    private int resource;
    MyListener myListener=null;
    public HomeAdapter(Context context, int resource, List<Home> objects) {
        super(context, resource, objects);
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Home home=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resource,null);
            viewHolder=new ViewHolder();
            myListener=new MyListener(position);

            viewHolder.homeImage=(ImageView)view.findViewById(R.id.home_image_left);
            viewHolder.homeText=(TextView)view.findViewById(R.id.home_textView);
            viewHolder.homeButton=(Button)view.findViewById(R.id.home_button);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.homeImage.setImageResource(home.getImageId());
        viewHolder.homeText.setText(home.getName());
        viewHolder.homeButton.setTag(position);
        //给Button添加单击事件
        viewHolder.homeButton.setOnClickListener(myListener);
        return view;
    }

    public class MyListener implements View.OnClickListener{
        int mPosition;
        public  MyListener(int mPosition){
            this.mPosition=mPosition;
        }
        @Override
        public void onClick(View view) {
        switch (mPosition){

        }
        }
    }
    class ViewHolder{
        ImageView homeImage;
        TextView homeText;
        Button homeButton;
    }
}
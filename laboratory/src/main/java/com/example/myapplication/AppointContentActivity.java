package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jian.adapter.MyViewpagerAdapter;

/**
 * Created by jian on 2016/9/24.
 * //预约信息填写界面
 */

public class AppointContentActivity extends BaseActivity {
private MyViewpagerAdapter Adapter;//时间选择器Viewpager的适配器
private ViewPager viewPager;//时间选择器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointcontent);
        EnterTransitionslide();//初始化动画
        initData();
        initView();
        setupToolbar();//初始toolbar
    }

    private void initView() {
        findViewById(R.id.appointContent_tvCommit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
//绑定数据
    private void initData() {
        Adapter = new MyViewpagerAdapter(getSupportFragmentManager(),7);//初始化时间适配器，最多只能预订最近7天内
        viewPager= (ViewPager) findViewById(R.id.appointContent_viewpager);
        viewPager.setAdapter(Adapter);
    }

    /**
     * 提交成功对话框
     */
    private void showDialog(){
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);//dialog，meterial风格
        builder.setTitle("提交结果");
        builder.setMessage("提交成功!!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

}

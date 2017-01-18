package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Created by jian on 2016/9/1.
 * 预约界面
 */
public class AppointMentActivity extends BaseActivity implements View.OnClickListener{
private Button btSertch; //查询按钮
    private RadioGroup radioGroup;//实验室或设备
    private Button btAppoint;//预约按钮
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        buildEnterTransition();//初始化进入动画
        initView();//初始化视图
        initEvent();//初始化监听时间
    }
    private void initEvent() {
        btSertch.setOnClickListener(this);
        btAppoint.setOnClickListener(this);
    }
    private void initView() {
        btSertch = (Button) findViewById(R.id.appoint_btSertch);
        radioGroup = (RadioGroup) findViewById(R.id.appoint_radiogroup);
        btAppoint= (Button) findViewById(R.id.btAppoint);
    }
    @Override
    public void onClick(View v) {
        ActivityOptionsCompat oc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        switch (v.getId()){
            case R.id.appoint_btSertch:
                Intent intent = null;
              if(radioGroup.getCheckedRadioButtonId()==R.id.radioEquitment){//判断当前选择的是实验室还是设备
                  intent = new Intent(this, SearchEquipmentActivity.class);//选择的是设备，跳转到设备查询界面

              }else{
                  intent = new Intent(this, SearchLaboratoryActivity.class);//选择的是实验室，跳转到实验室查询界面
              }
                startActivity(intent, oc.toBundle());
                break;
            case R.id.btAppoint:
                intent = new Intent(this, AppointContentActivity.class);//点击预约按钮，跳转到预约界面
                startActivity(intent, oc.toBundle());
                break;
        }
    }
}

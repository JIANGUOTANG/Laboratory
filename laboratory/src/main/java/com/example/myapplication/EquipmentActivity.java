package com.example.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.myapplication.databinding.ActivityEquipmentBinding;
import com.jian.ectity.EquipmentInfo;

import java.util.ArrayList;

/**
 * Created by jian on 2016/9/2.
 */
public class EquipmentActivity extends BaseActivity {
    String[] urls ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bingdingDate();
        buildEnterTransition();//初始化动画
        initView();
        ArrayList<String> imageList= getIntent().getStringArrayListExtra("imagelist");//获得上一个界面传过来的图片地址
        urls = new String[imageList.size()];
        for(int i = 0,j = imageList.size();i<j;i++){
            urls[i] = imageList.get(i);
        }
        initViewpager(urls);//初始化Viewpager滚动图片栏
        setupToolbar();//初始化toolar
    }

    private void initView() {

    }

    /**
     * 利用DataBingding绑定数据，节省很多代码
     */
    private void bingdingDate() {
        ActivityEquipmentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_equipment);
        EquipmentInfo equipmentInfo = new EquipmentInfo("001", "显微镜", "708", " ", "观察微观世界", "二师造", " ", "无", "花都", "1995-12-10");
        binding.setEquipmentInfo(equipmentInfo);
    }
}

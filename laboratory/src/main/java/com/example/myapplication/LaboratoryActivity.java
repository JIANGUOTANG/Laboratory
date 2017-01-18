package com.example.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.myapplication.databinding.ActivityLaboratoryBinding;
import com.jian.ectity.LaboratoryInfo;

import java.util.ArrayList;

/**
 * Created by jian on 2016/9/2.
 */
public class LaboratoryActivity extends BaseActivity {
    String[] urls ;//图片的地址
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bingdingDate();
        buildEnterTransition();
        initView();
        ArrayList<String> imageList= getIntent().getStringArrayListExtra("imagelist");//获的添加的图片地址
        urls = new String[imageList.size()];
        for(int i = 0,j = imageList.size();i<j;i++){
            urls[i] = imageList.get(i);
        }
        initViewpager(urls);
        setupToolbar();
    }
    private void initView() {

    }
    private void bingdingDate() {
        ActivityLaboratoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_laboratory);//利用databinding。
        LaboratoryInfo Laboratoryinfo = new LaboratoryInfo("001", "705A", "02225 ", "计算机系", "使用中", "56 ", "56", "实验楼", " ");//虚拟数据
        binding.setLaboratory(Laboratoryinfo);
    }

}

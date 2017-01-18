package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jian on 2016/9/2.
 * 查询实验室
 */
public class SearchLaboratoryActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlaboratory);
        buildEnterTransition();
        setupToolbar();
        startSerch();
    }
}

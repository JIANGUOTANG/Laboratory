package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jian on 2016/8/12.
 * 查询审核状态
 */
public class SearchSub_Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchsubscribe);
        buildEnterTransition();
        setupToolbar();
    }
}

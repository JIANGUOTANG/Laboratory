package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jian on 2016/8/12.
 * 异常处理
 */
public class UnusualActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unusaul);
        buildEnterTransition();
        setupToolbar();
    }
}

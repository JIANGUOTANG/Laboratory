package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jian on 2016/9/1.
 * 查询用户
 */
public class SearchUserActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sertuser );
        buildEnterTransition();
         initView();
        startSerch();
        setupToolbar();
    }
    private void initView() {

    }
}

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by jian on 2016/9/2.
 * 查询设备
 */
public class SearchEquipmentActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton imageButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchequitment);
        buildEnterTransition();
        initView();
        setupToolbar();
        startSerch();
    }
    private void initView() {
        imageButton = (ImageButton) findViewById(R.id.imgbtSerch);
        imageButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        ActivityOptionsCompat oc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent intent;
        switch (v.getId()) {
            case R.id.imgbtSerch:
                intent = new Intent(this,SearchLaboratoryActivity.class);
                startActivity(intent, oc.toBundle());
                break;
        }
    }

}

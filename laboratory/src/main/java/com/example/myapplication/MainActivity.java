package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jian on 2016/8/4.
 * 主页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private CardView cardViews[] = new CardView[10];//主页的功能键
    private TextView tvNotifi;//右上角的“通知”
    private int idcardView[] = {R.id.mainCard1, R.id.mainCard2, R.id.mainCard3, R.id.mainCard4, R.id.mainCard5, R.id.mainCard6,
            R.id.mainCard7, R.id.mainCard8, R.id.mainCard9, R.id.mainCard10};//CardView的ID
    // 图片的地址，这里可以从服务器获取
    String[] urls = new String[]{
            "http://image9.huangye88.cn/2015/05/15/6bf34a6527727869bee5e6b253856703.png",
            "http://img01.taopic.com/141208/318754-14120PZ03520.jpg",
            "http://pic.58pic.com/58pic/15/26/31/91J58PICAGb_1024.jpg",
            "http://files.b2b.cn/product/ProductImages/2011_04/11/11130639629.jpg",
            "http://pic4.zhongsou.com/image/4806f2648c9780aa00d.jpg",
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initViewpager(urls);
        initData();
        intitEvent();
    }
    private void initData() {

    }
    private void intitEvent() {//初始化点击事件
        for (int i = 0; i < 10; i++) {
            cardViews[i].setOnClickListener(this);
        }
        tvNotifi.setOnClickListener(this);

    }
    private void initView() {//初始化View
        for (int i = 0; i < 10; i++) {
            cardViews[i] = (CardView) findViewById(idcardView[i]);
        }

        tvNotifi = (TextView) findViewById(R.id.main_tvNotifi);
    }
    Intent i3;
    @Override
    public void onClick(View v) {
        ActivityOptionsCompat oc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent intent;
        switch (v.getId()) {
            case R.id.mainCard1:
                intent = new Intent(this,InsertEquipmentActivity.class);
                startActivity(intent, oc.toBundle());
                break;
            case R.id.mainCard2:
                intent = new Intent(this,SearchEquipmentActivity.class);
                startActivity(intent, oc.toBundle());
                break;
            case R.id.mainCard3:
                intent = new Intent(this, AppointMentActivity.class);
                startActivity(intent, oc.toBundle());
                break;
            case R.id.mainCard4:
                intent = new Intent(this,InsertLaboratoryActivity.class);
                startActivity(intent, oc.toBundle());
                break;
            case R.id.mainCard5:
                intent = new Intent(this,SearchUserActivity.class);
                startActivity(intent, oc.toBundle());
                break;
            case R.id.mainCard6:
                intent = new Intent(this, SearchSub_Activity.class);
                startActivity(intent, oc.toBundle());
                break;
            case R.id.mainCard7:
                intent = new Intent(this, SearchLaboratoryActivity.class);
                startActivity(intent, oc.toBundle());
                break;
            case R.id.mainCard8:
                i3 = new Intent(this, UnusualActivity.class);
                startActivity(i3, oc.toBundle());
                break;
            case R.id.mainCard9:
                i3 = new Intent(this,NewsActivity.class);
                startActivity(i3, oc.toBundle());
                break;
            case R.id.mainCard10:
                isLoging(oc);
                break;
            case R.id.main_tvNotifi:
                intent = new Intent(this, NotificationActivity.class);
                startActivity(intent, oc.toBundle());
                break;
        }
    }

    /**
     * 初始化引导图标
     * 动态创建多个小圆点，然后组装到线性布局里
     */
    private void isLoging( ActivityOptionsCompat oc){
        SharedPreferences preferences = getSharedPreferences("app", Context.MODE_PRIVATE);
       boolean islogin = preferences.getBoolean("login",false);
        if(islogin==true) {
            i3 = new Intent(this, PertionalActivity.class);
        }
        else{
            i3 = new Intent(this, LoginActivity.class);
        }
        startActivity(i3, oc.toBundle());
    }
}

package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.databinding.ActivityPersonalcenterBinding;
import com.jian.ectity.UserInfo;
import com.pkmmte.view.CircularImageView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by jian on 2016/8/5.
 */
public class PertionalActivity extends BaseActivity implements View.OnClickListener{
    private DbManager db;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout ;
    private CircularImageView circularImageView;
    private ImageView imagebg;
    private Button btExit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindData();
        EnterTransitionslide();
        initView();
        initEvent();
        setupToolbar();
        appBarLayout = (AppBarLayout) findViewById(R.id.persionnal_appbar);
        collapsingToolbarLayout  = (CollapsingToolbarLayout) findViewById(R.id.persionnal_collapsingTbLayout);
        collapsingToolbarLayout.setTitle(" ");
    }

    private void initEvent() {
        btExit.setOnClickListener(this);
    }

    private void bindData(){
       ActivityPersonalcenterBinding
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_personalcenter);
        db = x.getDb(((com.example.myapplication.MyApplication)getApplicationContext()).getDaoConfig());UserInfo user = null;

        try {
            user = db.selector(UserInfo.class).orderBy("id",true).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        if(user==null) {
            UserInfo userInfo = new UserInfo("10000", "管理员", "管理员", "计算机系", "男", "1183328120@qq.com", "12-10", "无", " ", "学生", "通过");
            binding.setUsreInfo(userInfo);
            try {
                db.save(userInfo);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        else{
            binding.setUsreInfo(user);
        }
    }
  private void initView() {
      circularImageView = (CircularImageView) findViewById(R.id.circularimagview);
      imagebg = (ImageView) findViewById(R.id.background);
      circularImageView.setImageResource(R.mipmap.user);
      btExit = (Button) findViewById(R.id.btExit);
//      x.image().bind(circularImageView, "http://img1.imgtn.bdimg.com/it/u=2020968545,2014670048&fm=21&gp=0.jpg");
      x.image().bind(imagebg,"http://www.qqwangming.org/uploads/allimg/131020/0130434533-14.jpg" );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btExit:
                SharedPreferences preferences = getSharedPreferences("app", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("login",false);
                editor.commit();
             break;
        }
    }
    
}

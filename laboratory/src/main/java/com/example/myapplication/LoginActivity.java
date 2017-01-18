package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends BaseActivity {
    private Button btRegister;//注册按钮
    private Button btLogin;//登录按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buildEnterTransition();

        btRegister = (Button) findViewById(R.id.login_btRegister);
        btLogin = (Button) findViewById(R.id.login_btLogin);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
                    scaleUpAnimation(btRegister);
                }
              else{
                    startActivity( new Intent(LoginActivity.this, com.example.myapplication.RegisterActivity.class));
                }
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("app", Context.MODE_PRIVATE);//记录是否登录过了
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("login",true);editor.commit();
                //利用了transition动画
                startActivity( new Intent(LoginActivity.this,PertionalActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                finish();
                setupToolbar();
            }
        });

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void scaleUpAnimation(View view) {
        //让新的Activity从一个小的范围扩大到全屏
//        ActivityOptionsCompat options =
//                ActivityOptionsCompat.makeScaleUpAnimation(view, //The View that the new activity is animating from
//                        (int)view.getWidth()/2, (int)view.getHeight()/2, //拉伸开始的坐标
//                        0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(this,view,"buttontolin");

        startNewAcitivity(options);
    }

    private void startNewAcitivity(ActivityOptions options) {
        Intent intent = new Intent(this, com.example.myapplication.RegisterActivity.class);
       startActivity( intent, options.toBundle());
    }
}

package com.example.myapplication;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

/**
 * Created by jian on 2016/8/3.
 * 注册
 */
public class RegisterActivity extends BaseActivity {
private View register_root;
    private CardView register_carView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        register_root = findViewById(R.id.register_root);
        register_carView = (CardView) findViewById(R.id.register_carView);
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){//判断是否可以执行MD动画
            starTrantionAnimation();
        }

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void starTrantionAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.transition_login);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }


}

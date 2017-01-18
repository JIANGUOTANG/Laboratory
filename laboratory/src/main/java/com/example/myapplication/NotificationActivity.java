package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.jian.adapter.NotificationAdapter;
import com.jian.ectity.NotifavationInfo;
import com.jian.ectity.TransitionInformation;
import com.jian.util.ImageTransitionUtil;
import com.jian.util.VersionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian on 2016/8/5.
 */
public class NotificationActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private NotificationAdapter mAdapter;
    private List<NotifavationInfo> notifavationInfos;
    private SwipeRefreshLayout mSwipeRefresh;
    private LoadDataScrollController mController;
    private ProgressDialog pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        EnterTransitionslide();
        initView();

        initData();
        setupToolbar();
    }
    NotifavationInfo notifavationInfo;
    String[] urls = new String[]{
            "http://image9.huangye88.cn/2015/05/15/6bf34a6527727869bee5e6b253856703.png",
            "http://img01.taopic.com/141208/318754-14120PZ03520.jpg",
            "http://pic.58pic.com/58pic/15/26/31/91J58PICAGb_1024.jpg",
            "http://files.b2b.cn/product/ProductImages/2011_04/11/11130639629.jpg",
            "http://pic4.zhongsou.com/image/4806f2648c9780aa00d.jpg",
            "http://img01.taopic.com/141208/318754-14120PZ03520.jpg",
            "http://pic.58pic.com/58pic/15/26/31/91J58PICAGb_1024.jpg",
            "http://files.b2b.cn/product/ProductImages/2011_04/11/11130639629.jpg",
            "http://pic4.zhongsou.com/image/4806f2648c9780aa00d.jpg",
            "http://image9.huangye88.cn/2015/05/15/6bf34a6527727869bee5e6b253856703.png",
    };

    private void initData() {

        notifavationInfos = new ArrayList<>();

        notifavationInfo = new NotifavationInfo("预约申请状态更变", "2016-08-23","你于 2016-08-22 12:00 申请的设备--显微镜 现已通过审核，请在约定世间内借还" , urls[0], "预约成功");
        new Thread(new MyRunable(0)).start();
        notifavationInfos.add(notifavationInfo);
        notifavationInfo = new NotifavationInfo("新版预约规则已经更新", "2016-09-03","根据最新的实验室规程，该校实验室预约规则更新如下：" , urls[1], "2016年新版预约规定");
        new Thread(new MyRunable(1)).start();
        notifavationInfos.add(notifavationInfo);

        notifavationInfo = new NotifavationInfo("版本更新通知", "2016-09-15", "跟新内容：修复若干BUG\n新增通知栏功能", urls[2], "版本号2.13");
        new Thread(new MyRunable(2)).start();
        notifavationInfos.add(notifavationInfo);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NotificationAdapter(notifavationInfos,this);
        mAdapter.setOnArticleClickedListener(new NotificationAdapter.OnArticleClickedListener() {

            @Override
            public void onArticleClicked(NotifavationInfo notifavationInfos, NotificationAdapter.ViewHolder articleViewHolder, PointF touchPoint) {

                navigateToDetails(notifavationInfos, articleViewHolder, touchPoint);
            }
        });
        recyclerView.setAdapter(mAdapter);
        /**
         * 设置监听
         */
        mController = new LoadDataScrollController(new LoadDataScrollController.OnRecycleRefreshListener() {
            @Override
            public void refresh() {
                mSwipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.refresh();
                        mSwipeRefresh.setRefreshing(false);
                        mController.setLoadDataStatus(false);
                    }
                }, 2000);

            }

            @Override
            public void loadMore() {
                //加载更多的接口回调
                pd = new ProgressDialog(NotificationActivity.this);
                pd.show();
                mSwipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.add();
                        //设置数据加载结束的监听状态
                        mController.setLoadDataStatus(false);
                        pd.dismiss();
                    }
                }, 2000);
            }
        }, 20);
        recyclerView.addOnScrollListener(mController);
        mSwipeRefresh.setOnRefreshListener(mController);
    }
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.notification_list);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.notification_Swipe);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        /**
         * 创建控制器，同时使当前activity实现数据监听回调接口
         */

    }
    /**
     * 加载图片，防止阻塞UI线程
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Drawable drawable = ContextCompat.getDrawable(NotificationActivity.this, R.mipmap.ic_launcher);
                    if (notifavationInfos.get((Integer) msg.obj) != null)
                        notifavationInfos.get((Integer) msg.obj).setIamge(ImageTransitionUtil.convertIconToString(ImageTransitionUtil.DrawbletoBitmap(drawable)));
                    else
                        notifavationInfo.setIamge(ImageTransitionUtil.convertIconToString(ImageTransitionUtil.DrawbletoBitmap(drawable)));

                    break;
            }
        }
    };
    private class MyRunable implements Runnable {
        private int position;

        public MyRunable(int position) {
            this.position = position;
        }

        @Override
        public void run() {
            handler.obtainMessage(1, position).sendToTarget();
        }
    }

    /**
     * Current transition informations.
     */
    private final TransitionInformation mTransitionInformation = new TransitionInformation();

    private void navigateToDetails(final NotifavationInfo article, final NotificationAdapter.ViewHolder articleViewHolder, final PointF touchPoint) {
        synchronized (mTransitionInformation) {
            // - Disallow multiple transition at the same time.
            if (mTransitionInformation.getCoveringView() != null) {
                return;
            }
            // - Remember transition to reverse transition.
            mTransitionInformation.rememberTransition(articleViewHolder.ivConvering, touchPoint);
            // - Reveal new covering image.
            if (articleViewHolder.ivConvering == null) {

            }
            articleViewHolder.ivConvering.setVisibility(View.VISIBLE);
            if (VersionUtil.isMaterial()) {
                final Animator circularReveal = ViewAnimationUtils.createCircularReveal(articleViewHolder.ivConvering, (int) touchPoint.x, (int) touchPoint.y, 0, articleViewHolder.ivConvering.getWidth());
                circularReveal.setInterpolator(new AccelerateInterpolator());
                circularReveal.setDuration(150);
                circularReveal.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // - Start details activity.
                        final ActivityOptions options =
                                ActivityOptions.makeSceneTransitionAnimation(NotificationActivity.this, articleViewHolder.ivConvering, articleViewHolder.ivConvering.getTransitionName());
                        final Intent intent = new Intent(NotificationActivity.this, NotiDetailsActivity.class);
                        intent.putExtra(NotiDetailsActivity.EXTRA_ARTICLE, article);
                        startActivity(intent, options.toBundle());
                    }
                });

                circularReveal.start();
            } else {
                final Intent intent = new Intent(NotificationActivity.this, NotiDetailsActivity.class);
                intent.putExtra(NotiDetailsActivity.EXTRA_ARTICLE, article);
                startActivity(intent);
            }
        }
    }

    /**
     * Reverse transition from details.
     */

    private void reverseTransition() {

        synchronized (mTransitionInformation) {
            if (mTransitionInformation.getCoveringView() != null) {
                if (VersionUtil.isMaterial()) {
                    final Animator circularReveal = ViewAnimationUtils.createCircularReveal(mTransitionInformation.getCoveringView(), (int) mTransitionInformation.getTouchPoint().x, (int) mTransitionInformation.getTouchPoint().y, mTransitionInformation.getCoveringView().getWidth(), 0);
                    circularReveal.setInterpolator(new DecelerateInterpolator());
                    circularReveal.setDuration(150);
                    circularReveal.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mTransitionInformation.getCoveringView().setVisibility(View.INVISIBLE);
                            mTransitionInformation.clear();
                        }
                    });
                    circularReveal.start();
                } else {
                    mTransitionInformation.getCoveringView().setVisibility(View.INVISIBLE);
                    mTransitionInformation.clear();
                }
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        reverseTransition();
    }

}

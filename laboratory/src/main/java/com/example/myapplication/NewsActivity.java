package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jian.adapter.NewsAdapter;
import com.jian.ectity.newsInfo;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by jian on 2016/8/30.
 * 咨询
 */
public class NewsActivity extends BaseActivity {
    public static final int IMAGENEWS = 1;
    public static final int TEXT = 2;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<newsInfo> newsInfos = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        initData();
        setupToolbar();

    }
    private void initData() {
        for(int i = 0;i<3;i++) {
            newsInfo newsInfo = new newsInfo("2016实验室仪器跟新", "", "2016-09-10",
                    "广二师实验楼更新了一批新的化学设备，预约系统已更新",
                   "http://pic4.zhongsou.com/image/4806f2648c9780aa00d.jpg", IMAGENEWS);
            newsInfos.add(newsInfo);
            newsInfo newsInfo2 = new newsInfo("化学系肖建彬同学成功考研北大", "我校化学系应用班肖建彬同学通过考研，并被北大化学系录取。",
                    "2016-08-21", "继计算机系简国堂同学被名校录取后，我校又传来考研好消息，肖建彬同学通过使用本软件，轻松通过了考研。",
                    null, TEXT);
            newsInfos.add(newsInfo2);
        }
        newsAdapter = new NewsAdapter(newsInfos,this);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.news_recyclerView);
    }




}

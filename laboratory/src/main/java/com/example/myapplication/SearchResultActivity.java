package com.example.myapplication;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jian.adapter.SearchResultAdapter;
import com.jian.ectity.SerchResultInfo;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by jian on 2016/9/4.
 * 查询结果
 */
public class SearchResultActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;
    private List<SerchResultInfo> serchResultInfos;
    private SwipeRefreshLayout mSwipeRefresh;
    private LoadDataScrollController mController;
    private ProgressDialog pd;
    private String[] urls = new String[]{
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
    };//测试用的图片
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serchresult);
        initViwe();
        initData();
        setupToolbar();
    }
    private void initData() {
        serchResultInfos = new ArrayList<>();
        SerchResultInfo serchResultInfo = new SerchResultInfo("显微镜", "102210", urls[1]);
        serchResultInfos.add(serchResultInfo);
        adapter = new SearchResultAdapter(serchResultInfos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mController = new LoadDataScrollController(new LoadDataScrollController.OnRecycleRefreshListener() {
            @Override
            public void refresh() {
                mSwipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refresh();
                        mSwipeRefresh.setRefreshing(false);
                        mController.setLoadDataStatus(false);
                    }
                }, 2000);

            }
            @Override
            public void loadMore() {
                //加载更多的接口回调
                pd = new ProgressDialog(com.example.myapplication.SearchResultActivity.this);
                pd.show();
                mSwipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.add();
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
    private void initViwe() {
        recyclerView = (RecyclerView) findViewById(R.id.serchresult_list);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.serchresult_swipe);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
    }

}

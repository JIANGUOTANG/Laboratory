package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.jian.adapter.AddImageAdapter;
import com.jian.util.XUtilsImageLoader;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
/**
 * Created by jian on 2016/8/4.
 */
public class InsertLaboratoryActivity extends BaseActivity implements View.OnClickListener  {
    private List<PhotoInfo> mPhotoList;
    private int REQUEST_CODE_PICKER = 2000;
    private TextView tvCommit;
    private RecyclerView recyclerView;
    public AddImageAdapter adapter;
    public List<String> url;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertlaboratory);
        buildEnterTransition();
        initView();
        setData();
        initEvent();
        initGridView();
        setupToolbar();
    }
    private void initEvent() {
        tvCommit.setOnClickListener(this);


    }
    private void setData() {
        mPhotoList = new ArrayList<>();
        mPhotoList.add(new PhotoInfo());
        adapter = new AddImageAdapter(mPhotoList,this);
        adapter.setCallback(new AddImageAdapter.onCallback() {
            @Override
            public void choostImage() {
                StartChooseActivity();
            }
        });
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    private void initView() {
        tvCommit = (TextView) findViewById(R.id.insertLaboratory_tvommit);
        recyclerView = (RecyclerView) findViewById(R.id.laboratory_recyclerview);
    }
    private void StartChooseActivity(){
        ThemeConfig themeConfig =ThemeConfig.DEFAULT;
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        cn.finalteam.galleryfinal.ImageLoader imageLoader;
        imageLoader = new XUtilsImageLoader();
        functionConfigBuilder.setMutiSelectMaxSize(9);
        functionConfigBuilder.setEnableEdit(true);
        functionConfigBuilder.setEnableRotate(true);
        functionConfigBuilder.setRotateReplaceSource(true);
        functionConfigBuilder.setEnableCrop(true);
        functionConfigBuilder.setCropReplaceSource(true);
        functionConfigBuilder.setEnableCamera(true);
        functionConfigBuilder.setEnablePreview(true);
        functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
        final FunctionConfig functionConfig = functionConfigBuilder.build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
        ActionSheet.createBuilder(com.example.myapplication.InsertLaboratoryActivity.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消(Cancel)")
                .setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
                    }
                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        String path = "/sdcard/pk1-2.jpg";
                        switch (index) {
                            case 0:
                                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                break;
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                                break;
                        }
                    }
                })
                .show();
        initImageLoader(this);
        Log.i("jian","zzhig");
    }
    private void initGridView() {
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insertLaboratory_tvommit:
                ActivityOptionsCompat oc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Intent intent;
                intent = new Intent(this, LaboratoryActivity.class);
                ArrayList<String> arrayList = new ArrayList<>();

                for(int i = 1,j = mPhotoList.size();i<j;i++){
                    arrayList.add("file:/" +mPhotoList.get(i).getPhotoPath());
                }
                intent.putStringArrayListExtra("imagelist",arrayList);
                startActivity(intent, oc.toBundle());
                break;
        }
    }
    private void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config.build());
    }
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.addAll(resultList);
                adapter.notifyDataSetChanged();
            }
        }
        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {


        }
    };
}

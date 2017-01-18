package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageButton;
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

;

/**
 * Created by jian on 2016/8/20.
 */
public class InsertEquipmentActivity extends BaseActivity implements View.OnClickListener{

    private ImageButton imageButton;//查询按钮，跳转到查询实验室界面
    private List<PhotoInfo> mPhotoList;//接收悬着后的图片信息
    private RecyclerView recyclerView;//用于显示图片
    public AddImageAdapter adapter;
    private final int REQUEST_CODE_CAMERA = 1000;//打开相机的返回码
    private final int REQUEST_CODE_GALLERY = 1001;
    private TextView tVCommission;//提交按钮
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserqquipment);
        buildEnterTransition();
        initView();
        setData();
        initEvent();
        setupToolbar();//初始化toobar，具体代码在父类中
    }

    private void initEvent() {
        tVCommission.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void setData() {
        mPhotoList = new ArrayList<>();//初始化图片列表
        mPhotoList.add(new PhotoInfo());//添加一个空的图片。用于显示加号按钮
        adapter = new AddImageAdapter(mPhotoList,this);
        adapter.setCallback(new AddImageAdapter.onCallback() {//点击加号时的回调接口
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
        tVCommission = (TextView) findViewById(R.id.insertEquip_tVCommission);
        imageButton = (ImageButton) findViewById(R.id.imgbtSerch);
        imageButton.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.equ_recyclerview);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
        }
    }
    @Override
    public void onClick(View v) {
        ActivityOptionsCompat oc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent intent;
        switch (v.getId()) {

            case R.id.imgbtSerch:
                intent = new Intent(this, com.example.myapplication.SearchLaboratoryActivity.class);
                startActivity(intent, oc.toBundle());
                break;

            case R.id.insertEquip_tVCommission:
                intent = new Intent(this, EquipmentActivity.class);
                ArrayList<String> arrayList = new ArrayList<>();

                for(int i = 1,j = mPhotoList.size();i<j;i++){
                    arrayList.add("file:/" +mPhotoList.get(i).getPhotoPath());//添加一个空的图片路径这样可以显示一个默认的加号
                }
                intent.putStringArrayListExtra("imagelist",arrayList);//
                startActivity(intent, oc.toBundle());
                break;
        }
    }
    private void StartChooseActivity(){
        ThemeConfig themeConfig =ThemeConfig.DEFAULT;// 选择器的主题。为第三方类库
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
        ActionSheet.createBuilder(com.example.myapplication.InsertEquipmentActivity.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消(Cancel)")
                .setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
                    }
                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {;
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
    }
    private void initImageLoader(Context context) {
        //这种配置调整是自定义的。你可以调整每一个选项，你可以调整其中的一些，
        //您可以创建默认配置
        //  ImageLoaderConfiguration.createDefault(this);
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();
        //初始化imageloader配置。
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

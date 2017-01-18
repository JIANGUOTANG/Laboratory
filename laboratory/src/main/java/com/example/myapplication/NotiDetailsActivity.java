package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jian.ectity.NotifavationInfo;

import org.xutils.x;

public class NotiDetailsActivity extends BaseActivity {

    /**
     * Extra key for article.
     */
    public static final String EXTRA_ARTICLE = "article";
    TextView mTitle;
    TextView mDate;
    TextView mSecondTitle;
    ImageView mHeader;
    View mTitleContent;
    TextView mDetail;
    ViewGroup mDetailsContent;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notidetail);
        initView();
        bindArticleDatas();
        setupToolbar();
//        beginEnterTransition();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        mDate = (TextView) findViewById(R.id.date);
        mHeader = (ImageView) findViewById(R.id.header);
        mTitleContent = findViewById(R.id.title_content);
        mSecondTitle = (TextView) findViewById(R.id.detail_secondTitle);
        mDetail = (TextView) findViewById(R.id.detail);
        mDetailsContent = (ViewGroup) findViewById(R.id.details_content);
    }

    /**
     * Binds article datas.
     */
    private void bindArticleDatas() {
        final NotifavationInfo article = (NotifavationInfo) getIntent().getExtras().get(EXTRA_ARTICLE);
        String imagebg = article.getIamge();
        x.image().bind(mHeader, article.getUrl());
        mTitle.setText(article.getTitle());
        mDate.setText(article.getTime());
        mSecondTitle.setText(article.getSecontitle());
        mDetail.setText(article.getDetail());
        mTitleContent.setBackgroundResource(R.color.maincolor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Details Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

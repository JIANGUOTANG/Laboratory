package com.jian.adapter;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elyeproj.loaderviewlibrary.LoaderImageView;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.myapplication.NotificationActivity;
import com.example.myapplication.R;
import com.jian.ectity.NotifavationInfo;

import org.xutils.x;

import java.util.List;

/**
 * Created by jian on 2016/8/5.
 */
public class NotificationAdapter extends BaseRecyclerAdapter<NotifavationInfo,NotificationAdapter.ViewHolder> {
    private NotificationActivity activity;

    private Context mContext;
    public NotificationAdapter(List<NotifavationInfo> notifavationInfos, Context context) {

        super(notifavationInfos);
        this.mContext = context;
    }

    @Override
    public ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notifacation, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBaseBindViewHolder(final ViewHolder holder, final NotifavationInfo notifavationInfo) {
      new thread(holder,notifavationInfo).start();

    }

    /**
     * Sets on article clicked listener.
     *
     * @param onArticleClickedListener Article clicked listener.
     */
    OnArticleClickedListener mOnArticleClickedListener;

    public void setOnArticleClickedListener(final OnArticleClickedListener onArticleClickedListener) {
        this.mOnArticleClickedListener = onArticleClickedListener;
    }

//模拟刷新
    @Override
    public void refresh() {
        super.refreshData(new NotifavationInfo("你的有的通知到达","2016-9-12","只是一天测试","http://pic4.zhongsou.com/image/4806f2648c9780aa00d.jpg",null));
    }
    @Override
    public void addMord() {
       super.add();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LoaderTextView title, time, detail;
        public LoaderImageView image;
        public ImageView ivConvering;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (LoaderTextView) itemView.findViewById(R.id.tvTitle);
            time = (LoaderTextView) itemView.findViewById(R.id.tvTime);
            detail = (LoaderTextView) itemView.findViewById(R.id.tvDetile);
            image = (LoaderImageView) itemView.findViewById(R.id.notificationImage);
            ivConvering = (ImageView) itemView.findViewById(R.id.covering_image);
        }
    }
    /**
     * On item clicked callback.
     */
    public interface OnArticleClickedListener {
        void onArticleClicked(NotifavationInfo notifavationInfos, ViewHolder articleViewHolder, PointF touchPoint);

    }
    class handler extends Handler {
        private ViewHolder holder;
        private NotifavationInfo notifavationInfo;

        public handler(Looper looper, ViewHolder holder, NotifavationInfo notifavationInfo) {
            super(looper);
            this.holder = holder;
            this.notifavationInfo = notifavationInfo;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    holder.title.setText(notifavationInfo.getTitle());
                    holder.detail.setText(notifavationInfo.getDetail());
                    holder.time.setText(notifavationInfo.getTime());
                    if (notifavationInfo.getUrl() != null) {
                        x.image().bind(holder.image, notifavationInfo.getUrl());
                        x.image().bind(holder.ivConvering, notifavationInfo.getUrl());
                    }
                    holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_UP) {
                                if (mOnArticleClickedListener != null) {
                                    mOnArticleClickedListener.onArticleClicked(notifavationInfo, holder, new PointF(event.getX(), event.getY()));
                                }
                            }
                            return true;
                        }
                    });
                    break;
            }
        }
    }
    private int WAIT_DURATION = 1500;
    class thread extends Thread implements Runnable{
        private ViewHolder holder;
        private NotifavationInfo notifavationInfo;
        public thread(ViewHolder holder, NotifavationInfo notifavationInfo) {
            this.holder = holder;
            this.notifavationInfo = notifavationInfo;
        }
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(WAIT_DURATION);
                handler handler = new handler(mContext.getMainLooper(),holder,notifavationInfo);
                handler.sendEmptyMessage(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

package com.jian.adapter;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.myapplication.R;
import com.jian.ectity.newsInfo;

import org.xutils.x;

import java.util.List;
/**
 * Created by jian on 2016/8/30.
 */
public  class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<newsInfo> newsInfos;
    private Context mContext;
    public NewsAdapter(List<newsInfo> newsInfos, Context context) {
        this.newsInfos = newsInfos;
        this.mContext = context;
    }
    private boolean isImage = true;
    private final int WAIT_DURATION =0;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch (viewType) {
            case com.example.myapplication.NewsActivity.IMAGENEWS:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newimage, null, false);
                isImage = true;
                break;
            case com.example.myapplication.NewsActivity.TEXT:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newtext, null, false);
                isImage = false;
                break;
            default:
        }
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        new thread(holder,position).start();
    }
    @Override
    public int getItemCount() {
        return newsInfos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public LoaderTextView tvTitle, tvData, tvDetail, tvSecondTitle;
        public ImageView image;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (LoaderTextView) itemView.findViewById(R.id.new_tvTitle);
            tvData = (LoaderTextView) itemView.findViewById(R.id.new_tvData);
            tvDetail = (LoaderTextView) itemView.findViewById(R.id.new_tvDetail);
            cardView = (CardView) itemView.findViewById(R.id.newcardView);
            if(isImage)
                image = (ImageView) itemView.findViewById(R.id.new_img);
            else
                tvSecondTitle = (LoaderTextView) itemView.findViewById(R.id.new_tvSecondTitle);
        }
    }
    @Override
    public int getItemViewType(int position) {
        return newsInfos.get(position).getType();
    }
    class handler extends Handler{
        private ViewHolder holder;
        private int position;

        public handler(Looper looper, ViewHolder holder, int position) {
            super(looper);
            this.holder = holder;
            this.position = position;
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    newsInfo newsInfo = newsInfos.get(position);
                    holder.tvTitle.setText(newsInfo.getTitle());
                    holder.tvData.setText(newsInfo.getData());
                    holder.tvDetail.setText(newsInfo.getDetail());
                    int Type = getItemViewType(position);
                    if (Type == com.example.myapplication.NewsActivity.IMAGENEWS) {
                        x.image().bind(holder.image, newsInfo.getImageUrl());
                        //  holder.image.setImageBitmap(newsInfo.getBitmap());
                    } else if (Type == com.example.myapplication.NewsActivity.TEXT) {
                        holder.tvSecondTitle.setText(newsInfo.getSecondTitle());
                    }
                    break;
            }
        }
    }
    class thread extends Thread implements Runnable{
        private ViewHolder holder;
        private int position;
        public thread(ViewHolder holder, int position) {
            this.holder = holder;
            this.position = position;
        }
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(WAIT_DURATION);
                handler handler = new handler(mContext.getMainLooper(),holder,position);
                handler.sendEmptyMessage(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

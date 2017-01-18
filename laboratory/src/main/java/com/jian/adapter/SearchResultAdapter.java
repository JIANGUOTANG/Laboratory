package com.jian.adapter;

import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.jian.ectity.SerchResultInfo;

import org.xutils.x;

import java.util.List;

/**
 * Created by jian on 2016/8/5.
 */
public class SearchResultAdapter extends BaseRecyclerAdapter<SerchResultInfo, SearchResultAdapter.ViewHolder> {
    public SearchResultAdapter(List<SerchResultInfo> serchResultInfos) {
        super(serchResultInfos);

        this.serchResultInfos = serchResultInfos;
    }

    private List<SerchResultInfo> serchResultInfos;

    @Override
    public ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_serchresult, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBaseBindViewHolder(final ViewHolder holder, final SerchResultInfo SerchResultInfo) {
        holder.title.setText(SerchResultInfo.getTitle());
        holder.ID.setText(SerchResultInfo.getID());
        x.image().bind(holder.image, SerchResultInfo.getUrl());
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (mOnArticleClickedListener != null) {
                        mOnArticleClickedListener.onArticleClicked(SerchResultInfo, holder, new PointF(event.getX(), event.getY()));
                    }
                }
                return true;
            }
        });

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

    @Override
    public int getItemCount() {
        return serchResultInfos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, ID;
        public ImageView image;
        public ImageView ivConvering;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.serchresult_tvName);
            ID = (TextView) itemView.findViewById(R.id.serchresult_tvID);
            image = (ImageView) itemView.findViewById(R.id.serchresultImage);
            ivConvering = (ImageView) itemView.findViewById(R.id.serchresult_covering_image);
        }
    }

    /**
     * On item clicked callback.
     */
    public interface OnArticleClickedListener {

        void onArticleClicked(SerchResultInfo serchResultInfos, ViewHolder articleViewHolder, PointF touchPoint);
    }

    //模拟刷新
    public void refresh() {
        super.refreshData(new SerchResultInfo("Name", "ID", "http://pic4.zhongsou.com/image/4806f2648c9780aa00d.jpg"));

    }

    @Override
    public void addMord() {
        super.add();
    }


}

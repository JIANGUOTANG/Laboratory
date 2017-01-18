package com.jian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;

import org.xutils.x;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
/**
 * Created by jian on 2016/9/22.
 */
public class AddImageAdapter extends RecyclerView.Adapter<AddImageAdapter.MyViewHolder> {
    private List<PhotoInfo> path;//图片路径
    private Context mContext;
    public AddImageAdapter(List<PhotoInfo> path, Context context
    ) {
        this.path = path;
        this.mContext = context;
    }
    onCallback callback;
    public void setCallback(onCallback callback) {
        this.callback = callback;
    }
    public interface onCallback {
        void choostImage();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, null, false);

        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (position == 0) {
            holder.imageView.setImageResource(R.mipmap.add);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.choostImage();
                    }
                }
            });
        } else {
            PhotoInfo PhotoInfo = path.get(position);
            x.image().bind(holder.imageView, "file:/" + PhotoInfo.getPhotoPath());
        }
    }
    @Override
    public int getItemCount()


    {
        if(path.size()==0){
            return 1;
        }
        return path.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.addimage_image);
        }
    }

}

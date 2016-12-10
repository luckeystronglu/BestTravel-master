package com.qf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qf.besttravel.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/12/10.
 */
public class UserNote2Adapter extends RecyclerView.Adapter<UserNote2Adapter.MyViewHolder> {
    private Context context;
    private List<String> imgdatas;
    private List<String> imgdatas2 = new ArrayList<>();
    private int size = 15;

    public UserNote2Adapter(Context context) {
        this.context = context;
        this.imgdatas = new ArrayList<>();
    }

    public void setDatas(List<String> imgdatas){
        if (imgdatas.size() > size) {
            imgdatas2.addAll(imgdatas.subList(0,size));
            this.imgdatas = imgdatas2;
        }else {
            this.imgdatas = imgdatas;
        }

        this.notifyDataSetChanged();
    }

    public void addDatas(List<String> imgdatas){
        if (imgdatas.size() > size + 6) {
            imgdatas2.addAll(imgdatas.subList(size,size + 6));
            this.imgdatas = imgdatas2;
            size = size + 6;
        }else {
            this.imgdatas = imgdatas;
        }

        this.notifyDataSetChanged();
    }


    @Override
    public UserNote2Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.usernote2item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserNote2Adapter.MyViewHolder holder, int position) {


        Glide.with(context)
                .load(imgdatas.get(position))
                .crossFade(500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.image_app)
                .thumbnail(0.1f)
                .into(holder.imv);
    }


    @Override
    public int getItemCount() {
        return imgdatas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imv;

        public MyViewHolder(View view) {
            super(view);
            imv = (ImageView) view.findViewById(R.id.usernote2_item);
        }
    }

}

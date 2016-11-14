package com.qf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qf.besttravel.R;
import com.qf.entity.NearbyEntitity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/20.
 */
public class MoreDestinationAdapter extends RecyclerView.Adapter<MoreDestinationAdapter.OrderHolder> {

    private Context context;
    private List<NearbyEntitity.DataBean> list;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MoreDestinationAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void setDatas(List<NearbyEntitity.DataBean> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }


    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_more_destination, parent, false);
        return new OrderHolder(inflate);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {

            holder.tv_name.setText(list.get(position).getName());
            holder.tv_name_eng.setText(list.get(position).getName_en());

            Glide.with(context)
                    .load(list.get(position).getPhoto_url())
                    .crossFade(500)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.image_app)
//                    .transform(new GlideCircleTransform(context))
                    .thumbnail(0.1f)
                    .into(holder.iv_near);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name, tv_name_eng;
        ImageView iv_near;

        public OrderHolder(View itemView) {
            super(itemView);
            this.tv_name = (TextView) itemView.findViewById(R.id.near_textchn);
            this.tv_name_eng = (TextView) itemView.findViewById(R.id.near_texteng);
            this.iv_near = (ImageView) itemView.findViewById(R.id.near_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                onItemClickListener.onClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }
}

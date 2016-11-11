package com.qf.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qf.besttravel.LookPlanWebActivity;
import com.qf.besttravel.R;
import com.qf.entity.NearInspirationEntity;
import com.qf.utils.BaiduMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/15.
 */
public class NearInspiAdapter extends RecyclerView.Adapter<NearInspiAdapter.NearInspiHolder>{
    private Context context;
    private List<NearInspirationEntity.DataBean> pointlist;

    public NearInspiAdapter(Context context) {
        this.context = context;
        this.pointlist = new ArrayList<>();
    }

    public void setDatas(List<NearInspirationEntity.DataBean> pointlist){
        this.pointlist = pointlist;
        this.notifyDataSetChanged();
    }

    @Override
    public NearInspiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_inspiration_note, parent, false);
        return new NearInspiHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final NearInspiHolder holder, final int position) {
        if(pointlist.get(position).getIcon_type() == 2){
            holder.leftmark_img.setImageResource(R.drawable.icon_plan_scenery);

        }else{
            holder.leftmark_img.setImageResource(R.drawable.icon_plan_food);
        }

        /**
         *   scenery_bigimg;
         TextView,tv_last_distance,tv_scenery_summary,tv_planAndNote;
         LinearLayout travel_days_ll;
         RelativeLayout travel_days_relative;
         */
        holder.tv_scenery_title.setText(pointlist.get(position).getTopic());
        double v = BaiduMap.GetLongDistance(new BigDecimal(pointlist.get(position).getPois().get(0).getYouji_lat()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(),
                new BigDecimal(pointlist.get(position).getPois().get(0).getYouji_lng()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(),
                22.52, 114.02);
        holder.tv_last_distance.setText((int) v/1000 + "");
        holder.tv_scenery_summary.setText(pointlist.get(position).getIntroduce());

        holder.tv_planAndNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LookPlanWebActivity.class);
                intent.putExtra("web_url",pointlist.get(position).getPois().get(0).getH5_url());
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .load(pointlist.get(position).getPhoto().getPhoto_url())
                .crossFade(500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.home_user_place_holder)
//                .transform(new GlideCircleTransform(context))
                .thumbnail(0.1f)
                .into(holder.scenery_bigimg);

//        holder.travel_days_ll.setVisibility(View.GONE);
        holder.travel_days_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.travel_days_ll.getVisibility() == View.GONE){
                    holder.travel_days_ll.setVisibility(View.VISIBLE);
                }else {
                    holder.travel_days_ll.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return pointlist.size();
    }


    public class NearInspiHolder extends RecyclerView.ViewHolder{

        ImageView leftmark_img,scenery_bigimg;
        TextView tv_scenery_title,tv_last_distance,tv_scenery_summary,tv_planAndNote;
        LinearLayout travel_days_ll;
        RelativeLayout travel_days_relative;

        public NearInspiHolder(View itemView) {
            super(itemView);
            this.leftmark_img = (ImageView) itemView.findViewById(R.id.inspiration_mark_img);
            this.scenery_bigimg = (ImageView) itemView.findViewById(R.id.travel_days_scenery_bigimg);
            this.tv_scenery_title = (TextView) itemView.findViewById(R.id.tv_scenery_title);
            this.tv_last_distance = (TextView) itemView.findViewById(R.id.inspi_num);
            this.tv_scenery_summary = (TextView) itemView.findViewById(R.id.travel_days_scenery_summary);
            this.tv_planAndNote = (TextView) itemView.findViewById(R.id.travel_days_plan_note);
            this.travel_days_ll = (LinearLayout) itemView.findViewById(R.id.inspiration_ll);
            this.travel_days_relative = (RelativeLayout) itemView.findViewById(R.id.inspiration_relative);
//            this.travel_days_ll.setVisibility(View.GONE);
        }
    }
}

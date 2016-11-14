package com.qf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qf.besttravel.R;
import com.qf.entity.SummaryMapEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/15.
 */
public class SummaryMapAdapter2 extends RecyclerView.Adapter<SummaryMapAdapter2.NearInspiHolder>{
    private Context context;
    private List<SummaryMapEntity.DataBean.GroupingsBean.SectionsBean> list2;

    public SummaryMapAdapter2(Context context) {
        this.context = context;
        this.list2 = new ArrayList<>();
    }

    public void setDatas(List<SummaryMapEntity.DataBean.GroupingsBean.SectionsBean> list2){
        this.list2 = list2;
        this.notifyDataSetChanged();
    }

    @Override
    public NearInspiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_summary_type01, parent, false);
        return new NearInspiHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final NearInspiHolder holder, int position) {
        holder.summap_type01_title.setText(list2.get(position).getDestination().getName());
        holder.summap_type01_aftertitle.setText(" , " + list2.get(position).getDestination().getTitle());
        holder.summap_secondtv.setText(list2.get(position).getDestination().getVisit_tip());
        holder.sumll_summary.setText(list2.get(position).getDestination().getDescription());
        Glide.with(context)
                .load(list2.get(position).getDestination().getPhoto_url())
                .crossFade(500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.home_user_place_holder)
//                .transform(new GlideCircleTransform(context))
                .thumbnail(0.1f)
                .into(holder.scenery_bigimg);
        holder.summary_ll_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.summary_hide_ll.getVisibility() == View.GONE){
                    holder.summary_hide_ll.setVisibility(View.VISIBLE);
                }else {
                    holder.summary_hide_ll.setVisibility(View.GONE);
                }
            }
        });



       /* for (SummaryMapEntity.DataBean.GroupingsBean.SectionsBean sectionsBean : pointlist.get(position).getSections()) {
            holder.summap_type01_title.setText(sectionsBean.getDestination().getName());
            holder.summap_type01_aftertitle.setText(" , " + sectionsBean.getDestination().getTitle());
            holder.summap_secondtv.setText(sectionsBean.getDestination().getVisit_tip());
            holder.sumll_summary.setText(sectionsBean.getDestination().getDescription());
            Glide.with(context)
                    .load(sectionsBean.getDestination().getPhoto_url())
                    .crossFade(500)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.home_user_place_holder)
//                .transform(new GlideCircleTransform(context))
                    .thumbnail(0.1f)
                    .into(holder.scenery_bigimg);
            holder.summary_ll_show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.summary_hide_ll.getVisibility() == View.GONE){
                        holder.summary_hide_ll.setVisibility(View.VISIBLE);
                    }else {
                        holder.summary_hide_ll.setVisibility(View.GONE);
                    }
                }
            });


        }*/


      /*  holder.summary_hide_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LookPlanWebActivity.class);
                intent.putExtra("web_url",pointlist.get(position).getPois().get(0).getH5_url());
                context.startActivity(intent);
            }
        });*/


//        holder.travel_days_ll.setVisibility(View.GONE);



    }

    @Override
    public int getItemCount() {
        return list2.size();
    }


    public class NearInspiHolder extends RecyclerView.ViewHolder{

        ImageView scenery_bigimg;
        TextView summap_type01_title,summap_type01_aftertitle,summap_secondtv,sumll_summary,summary_map_lookmap;
        LinearLayout summary_ll_show,summary_hide_ll;

        public NearInspiHolder(View itemView) {
            super(itemView);
            this.scenery_bigimg = (ImageView) itemView.findViewById(R.id.sumll_hide_bigimg);
            this.summap_type01_title = (TextView) itemView.findViewById(R.id.summap_type01_title);
            this.summap_type01_aftertitle = (TextView) itemView.findViewById(R.id.summap_type01_aftertitle);
            this.summap_secondtv = (TextView) itemView.findViewById(R.id.summap_secondtv);
            this.sumll_summary = (TextView) itemView.findViewById(R.id.sumll_summary);
            this.summary_map_lookmap = (TextView) itemView.findViewById(R.id.summary_map_lookmap);
            this.summary_hide_ll = (LinearLayout) itemView.findViewById(R.id.summary_hide_ll);
            this.summary_ll_show = (LinearLayout) itemView.findViewById(R.id.summary_ll_show);
        }
    }
}

package com.qf.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qf.besttravel.R;
import com.qf.entity.SummaryMapEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/15.
 */
public class SummaryMapAdapter extends RecyclerView.Adapter<SummaryMapAdapter.NearInspiHolder> {
    private Context context;
    private List<SummaryMapEntity.DataBean.GroupingsBean> pointlist;
    private List<SummaryMapEntity.DataBean.GroupingsBean.SectionsBean> sectionlist;
    private SummaryMapAdapter2 adapter2;

    public SummaryMapAdapter(Context context) {
        this.context = context;
        this.pointlist = new ArrayList<>();
    }

    public void setDatas(List<SummaryMapEntity.DataBean.GroupingsBean> pointlist) {
        this.pointlist = pointlist;
        this.notifyDataSetChanged();
    }

    @Override
    public NearInspiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.summary_rv0, parent, false);
        return new NearInspiHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final NearInspiHolder holder, int position) {
        holder.tv0.setText(pointlist.get(position).getName());
        holder.rv0.setLayoutManager(new LinearLayoutManager(context));
        sectionlist = pointlist.get(position).getSections();
        adapter2 = new SummaryMapAdapter2(context);
        holder.rv0.setAdapter(adapter2);
        adapter2.setDatas(sectionlist);

    }

    @Override
    public int getItemCount() {
        return pointlist.size();
    }


    public class NearInspiHolder extends RecyclerView.ViewHolder {


        TextView tv0;
        RecyclerView rv0;

        public NearInspiHolder(View itemView) {
            super(itemView);
            this.tv0 = (TextView) itemView.findViewById(R.id.tv0_title);
            this.rv0 = (RecyclerView) itemView.findViewById(R.id.summary_rv0);
        }
    }
}

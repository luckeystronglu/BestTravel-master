package com.qf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.besttravel.DesInfoActivity;
import com.qf.besttravel.MoreDestinationActivity;
import com.qf.besttravel.NearbyActivity;
import com.qf.besttravel.R;
import com.qf.entity.DestinationEntity;
import com.qf.widget.planlv.PlanGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/7.
 */
public class BestPlanLvAdapter extends BaseAdapter{
    private Context context;
    private List<DestinationEntity.DataBean> destilist;
    private BestPlanGridAdapter bestPlanGridAdapter;
//    private double lat,lng;

    public BestPlanLvAdapter(Context context) {
        this.context = context;
//        this.lat = lat;
//        this.lng = lng;
        this.destilist = new ArrayList<>();
    }


    //设置数据源
    public void setDatas(List<DestinationEntity.DataBean> destilist){
        this.destilist = destilist;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return destilist.size();
    }


    @Override
    public Object getItem(int position) {
        return destilist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListViewHolder viewHolder;
        if(convertView != null){
            viewHolder = (ListViewHolder) convertView.getTag();
        } else {
            viewHolder = new ListViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_plan_lv_grid, null);
            viewHolder.lv_title = (TextView) convertView.findViewById(R.id.des_lv_title);
            viewHolder.lv_more = (TextView) convertView.findViewById(R.id.des_lv_tv_more);
            viewHolder.planGridView = (PlanGridView) convertView.findViewById(R.id.des_lv_gridv);
            convertView.setTag(viewHolder);
        }

        viewHolder.lv_title.setText(destilist.get(position).getName());
        viewHolder.lv_more.setText(destilist.get(position).getButton_text());


        bestPlanGridAdapter = new BestPlanGridAdapter(context);
        viewHolder.planGridView.setAdapter(bestPlanGridAdapter);

//        bestPlanGridAdapter.setDatas(destilist.get(position).getDestinations());

        final List<DestinationEntity.DataBean.DestinationsBean> da = destilist.get(position).getDestinations();
        bestPlanGridAdapter.setDatas(da);

        viewHolder.planGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DestinationEntity.DataBean.DestinationsBean destinationsBean = da.get(position);
                Intent intent = new Intent(context, DesInfoActivity.class);
                intent.putExtra("areaid",da.get(position).getDistrict_id());
                intent.putExtra("id",da.get(position).getId());
                intent.putExtra("lat",da.get(position).getLat());//纬度
                intent.putExtra("lng",da.get(position).getLng());//经度
                context.startActivity(intent);
            }
        });



        viewHolder.lv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        Intent inten = new Intent(context, NearbyActivity.class);
                        inten.putExtra("lat",22.58617);//纬度
                        inten.putExtra("lng",113.968483);//经度
                        context.startActivity(inten);
                        break;
                    case 1:
                        Intent in1 = new Intent(context, MoreDestinationActivity.class);
                        in1.putExtra("area","china");
                        in1.putExtra("title","内地");
                        context.startActivity(in1);
                        break;
                    case 3:
                        Intent in2 = new Intent(context, MoreDestinationActivity.class);
                        in2.putExtra("area","asia");
                        in2.putExtra("title","亚洲");
                        context.startActivity(in2);
                        break;
                    case 4:
                        Intent in3 = new Intent(context, MoreDestinationActivity.class);
                        in3.putExtra("area","europe");
                        in3.putExtra("title","欧洲");
                        context.startActivity(in3);
                        break;
                    default:
                        break;

                }

            }
        });


        return convertView;
    }



    class ListViewHolder{
        TextView lv_title,lv_more;
        PlanGridView planGridView;

    }

}

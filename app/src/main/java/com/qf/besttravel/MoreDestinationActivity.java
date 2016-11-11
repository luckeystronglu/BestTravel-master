package com.qf.besttravel;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.adapter.MoreDestinationAdapter;
import com.qf.contacts.Contact;
import com.qf.entity.NearbyEntitity;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import java.util.List;

/**
 * Created by lenovo on 2016/11/9.
 */
public class MoreDestinationActivity extends BaseActivity implements DownUtil.OnDownListener, MoreDestinationAdapter.OnItemClickListener {
    private MoreDestinationAdapter moreadapter;
    private RecyclerView nearbyRv;
    private TextView tv_title;
    private ImageView img_back;
    private List<NearbyEntitity.DataBean> data;
    private String area,title;

    @Override
    public int getContentViewId() {
        return R.layout.activity_more_destination;
    }

    @Override
    protected void init() {
        Intent in = getIntent();
        area = in.getStringExtra("area");
        title = in.getStringExtra("title");
        tv_title = findViewByIds(R.id.more_area);
        img_back = findViewByIds(R.id.scenery_web_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nearbyRv = findViewByIds(R.id.nearby_rv);
        nearbyRv.setLayoutManager(new LinearLayoutManager(this));
        moreadapter = new MoreDestinationAdapter(this);
        nearbyRv.setAdapter(moreadapter);
        moreadapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadDatas() {
        String near_url = String.format(Contact.AREA_DESTI,area);
        new DownUtil().setOnDownListener(this).downJSON(near_url);
    }



    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, NearbyEntitity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            NearbyEntitity entitity = (NearbyEntitity) object;
            tv_title.setText(title);
            data = entitity.getData();
            moreadapter.setDatas(data);
        }

    }


    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, DesInfoActivity.class);
        intent.putExtra("id",data.get(position).getId());
        intent.putExtra("lat",data.get(position).getLat());//纬度
        intent.putExtra("lng",data.get(position).getLng());//经度
        startActivity(intent);
    }
}

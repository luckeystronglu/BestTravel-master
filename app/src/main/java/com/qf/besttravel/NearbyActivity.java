package com.qf.besttravel;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.adapter.NearbyAdapter;
import com.qf.contacts.Contact;
import com.qf.entity.NearbyEntitity;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by lenovo on 2016/11/9.
 */
public class NearbyActivity extends BaseActivity implements DownUtil.OnDownListener, NearbyAdapter.OnItemClickListener {
    private NearbyAdapter adapter;
   private RecyclerView nearbyRv;
    private TextView textView;
    private RelativeLayout relative_item;
    private ImageView img_back;
    private List<NearbyEntitity.DataBean> data;
    private double lat;
    private double lng;

    @Override
    public int getContentViewId() {
        EventBus.getDefault().register(this);
        return R.layout.activity_nearby;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat", 22.61);
        lng = intent.getDoubleExtra("lng", 114.06);
        img_back = findViewByIds(R.id.scenery_web_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView = findViewByIds(R.id.num_neartext);
        relative_item = findViewByIds(R.id.rl_near_lingan);
        nearbyRv = findViewByIds(R.id.nearby_rv);
        nearbyRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NearbyAdapter(this);
        nearbyRv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadDatas() {
        String near_url = String.format(Contact.NEARBY_DIS,lat,lng);
        new DownUtil().setOnDownListener(this).downJSON(near_url);
        relative_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NearbyActivity.this,NearInspirationActivity.class));
            }
        });
    }

    //定义一个接收EventBus的方法,获得旅行灵感的条数
    @Subscribe
    public void onEvent(int notenum){
        textView.setText(notenum + "");
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

            data = entitity.getData();
//            textView.setText(data.size() + "");
            adapter.setDatas(data,lat,lng);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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

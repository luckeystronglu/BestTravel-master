package com.qf.besttravel;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.adapter.SummaryMapAdapter;
import com.qf.contacts.Contact;
import com.qf.entity.SummaryMapEntity;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import java.util.List;

/**
 * Created by lenovo on 2016/11/9.
 */
public class SummaryMapActivity extends BaseActivity implements DownUtil.OnDownListener {
    private RecyclerView ins_rv;
    private SummaryMapAdapter adapter;
    private ImageView iv_back;
    private TextView area_tv;
    private int areaid;
    private String areaname;
    private List<SummaryMapEntity.DataBean.GroupingsBean> data;

    @Override
    public int getContentViewId() {
        return R.layout.activity_summary_map;
    }

    @Override
    protected void init() {
        Intent it = getIntent();
        areaid = it.getIntExtra("areaid",-1);
        areaname = it.getStringExtra("areaname");
        area_tv = findViewByIds(R.id.summary_map_areaname);
        area_tv.setText(areaname);
        iv_back = findViewByIds(R.id.summarymap_web_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ins_rv = findViewByIds(R.id.summary_map_rv);
        ins_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SummaryMapAdapter(this);
        ins_rv.setAdapter(adapter);
    }

    @Override
    protected void loadDatas() {
        String summary_url = String.format(Contact.SUMMARY_MAP,areaid);
        new DownUtil().setOnDownListener(this).downJSON(summary_url);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, SummaryMapEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            SummaryMapEntity entity = (SummaryMapEntity) object;
            data = entity.getData().getGroupings();
            adapter.setDatas(data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        data.clear();
    }
}

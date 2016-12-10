package com.qf.besttravel;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.adapter.MeetWorldCAdapter;
import com.qf.contacts.Contact;
import com.qf.entity.MeetWorldEntity;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by lenovo on 2016/10/21.
 */
public class MeetWorldActivity extends BaseActivity implements DownUtil.OnDownListener {
    private RecyclerView worldnote_rv;
    private MeetWorldCAdapter adapter;

    private int id = -1;

    private ImageView worldnote_back;
    private TextView tv1,tv2;

    private List<MeetWorldEntity.DataBean.ItemsBean> meetlist;
    MeetWorldEntity meetWorldEntity;

    @Override
    public int getContentViewId() {
        return R.layout.activity_meetworld;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",90);

        worldnote_back = findViewByIds(R.id.scenery_web_back);
        worldnote_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        worldnote_rv = (RecyclerView) findViewById(R.id.meet_rv);
        worldnote_rv.setLayoutManager(new LinearLayoutManager(this));

        View headerView =  LayoutInflater.from(this).inflate(R.layout.meetworld_headview, null);
        tv1 = (TextView) headerView.findViewById(R.id.meet_top_title);
        tv2 = (TextView) headerView.findViewById(R.id.meet_top_summary);
        adapter = new MeetWorldCAdapter(this);
        //给RecycleView添加头部布局;类似装饰者模式，直接将原本的adapter传入，初始化一个HeaderAndFooterWrapper对象，然后调用相关API添加。
        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        worldnote_rv.setAdapter(headerAndFooterWrapper);
        headerAndFooterWrapper.addHeaderView(headerView);
        headerAndFooterWrapper.notifyDataSetChanged();
    }

    @Override
    protected void loadDatas() {
        String more_url = String.format(Contact.MEET_WORLD,id);
        Log.d("print", "loadDatas more_url: "+more_url);
        new DownUtil().setOnDownListener(this).downJSON(more_url);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json,MeetWorldEntity.class);

        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            meetWorldEntity = (MeetWorldEntity) object;
            tv1.setText(meetWorldEntity.getData().getTitle());
            if (meetWorldEntity.getData().getSummary() != null && meetWorldEntity.getData().getSummary() != "") {
                tv2.setText(meetWorldEntity.getData().getSummary());
            }else {
                tv2.setVisibility(View.GONE);
            }

            meetlist = meetWorldEntity.getData().getItems();
            adapter.setDatas(meetlist);


        }
    }
}

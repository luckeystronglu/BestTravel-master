package com.qf.besttravel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.qf.adapter.NearInspiAdapter;
import com.qf.contacts.Contact;
import com.qf.entity.NearInspirationEntity;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lenovo on 2016/11/9.
 */
public class NearInspirationActivity extends BaseActivity implements DownUtil.OnDownListener {
    private RecyclerView ins_rv;
    private NearInspiAdapter adapter;
    private ImageView iv_back;
    private int notenum;

    @Override
    public int getContentViewId() {
        return R.layout.activity_nearby_inspiration;
    }

    @Override
    protected void init() {
        iv_back = findViewByIds(R.id.scenery_web_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ins_rv = findViewByIds(R.id.nearby_insnote_rv);
        ins_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NearInspiAdapter(this);
        ins_rv.setAdapter(adapter);
    }

    @Override
    protected void loadDatas() {
        new DownUtil().setOnDownListener(this).downJSON(Contact.NEARBY_INSPI_NOTE);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, NearInspirationEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            NearInspirationEntity entity = (NearInspirationEntity) object;
            List<NearInspirationEntity.DataBean> data = entity.getData();
            notenum = data.size();
            EventBus.getDefault().post(notenum);
            adapter.setDatas(data);
        }
    }


}

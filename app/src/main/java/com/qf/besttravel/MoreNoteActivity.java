package com.qf.besttravel;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.adapter.MoreNoteAdapter;
import com.qf.adapter.MoreNoteEntity;
import com.qf.contacts.Contact;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import java.util.List;

/**
 * Created by lenovo on 2016/10/21.
 */
public class MoreNoteActivity extends BaseActivity implements DownUtil.OnDownListener {
    private RecyclerView worldnote_rv;
    private MoreNoteAdapter adapter;
    //下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;


    private int areaid;
    private String areaname;
    private int page = 1;

    private ImageView worldnote_back;
    private TextView area_tv;

    private List<MoreNoteEntity.DataBean.UserActivitiesBean> morelist;
    MoreNoteEntity moreNoteEntity;

    @Override
    public int getContentViewId() {
        return R.layout.activity_more_note;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        areaid = intent.getIntExtra("areaid",-1);
        areaname = intent.getStringExtra("areaname");

        area_tv = findViewByIds(R.id.morenote_areaname);
        area_tv.setText(areaname);
        worldnote_back = findViewByIds(R.id.morenote_back);
        worldnote_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        worldnote_rv = (RecyclerView) findViewById(R.id.morenote_rv);
        worldnote_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MoreNoteAdapter(this);
        worldnote_rv.setAdapter(adapter);
    }

    @Override
    protected void loadDatas() {
        String more_url = String.format(Contact.MORE_NOTE,areaid,page);
        Log.d("print", "loadDatas more_url: "+more_url);
        new DownUtil().setOnDownListener(this).downJSON(more_url);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json,MoreNoteEntity.class);

        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            moreNoteEntity = (MoreNoteEntity) object;
            morelist = moreNoteEntity.getData().getUser_activities();
            adapter.setDatas(morelist);

            swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.moreswipeFresh);
            swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.app_theme));
            swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Log.d("print", "------>开始刷新，加载数据");

                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            //数据加载完成
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //关闭刷新
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            });
                        }
                    }.start();
                }
            });

        }
    }
}

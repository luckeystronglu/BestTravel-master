package com.qf.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.qf.adapter.UserNote2Adapter;
import com.qf.besttravel.R;
import com.qf.contacts.Contact;
import com.qf.entity.UserNoteEntity;
import com.qfkf.base.BaseFragment;
import com.qfkf.util.DownUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/17.
 */
public class UserNoteFragment2 extends BaseFragment implements DownUtil.OnDownListener {

    private RecyclerView rv2;
    private UserNote2Adapter adapter;
    private List<String> datas = new ArrayList<>();
    private int useimgid;

    //下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getContentView() {
        return R.layout.fragment_usernote2;
    }

    public void setuserid(int id){
        this.useimgid = id;
    }

    @Override
    protected void init(View view) {
        rv2 = (RecyclerView) view.findViewById(R.id.usernote_rv2);
        rv2.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        adapter = new UserNote2Adapter(getContext());
        rv2.setAdapter(adapter);
    }

    @Override
    protected void loadDatas() {
        String userurl2 = String.format(Contact.USER_NOTE1, useimgid);
        new DownUtil().setOnDownListener(this).downJSON(userurl2);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, UserNoteEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        UserNoteEntity entity = (UserNoteEntity) object;
        List<UserNoteEntity.DataBean> data = entity.getData();
        for (int i = 0; i < data.size(); i++) {
            for (UserNoteEntity.DataBean.ContentsBean contentsBean : data.get(i).getContents()) {
                datas.add(contentsBean.getPhoto_url());
            }
        }
        adapter.setDatas(datas);

        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.usernote2_srl);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.app_theme));
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE,Color.BLUE,Color.RED);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("print", "------>开始刷新，加载数据");
                adapter.addDatas(datas);

                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //数据加载完成
                        getActivity().runOnUiThread(new Runnable() {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        datas.clear();
        datas.clear();
    }
}

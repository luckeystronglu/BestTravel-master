package com.qf.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.qf.adapter.TravelNoteAdapter2;
import com.qf.besttravel.R;
import com.qf.entity.TravelNote2Entity;
import com.qf.utils.GetJsonDatas;
import com.qfkf.base.BaseFragment;
import com.tencent.mm.sdk.platformtools.Log;

import java.util.List;


/**
 * Created by lenovo on 2016/9/30.
 */
public class TravelNoteFragment extends BaseFragment {
    private RecyclerView note_recycler;
    private TravelNoteAdapter2 adapter;
    FloatingActionButton fab;


   /* //下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;

    private int page = 1;*/

    //RecyclerView下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getContentView() {
        return R.layout.fragment_travelnote;
    }

    @Override
    protected void init(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.floating_abtn);
        note_recycler = (RecyclerView) view.findViewById(R.id.travelnote_rv);
        note_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TravelNoteAdapter2(getActivity());
        note_recycler.setAdapter(adapter);

    }

    @Override
    protected void loadDatas() {
        String travelnote = GetJsonDatas.getJSON2("travelnote",getContext());
        TravelNote2Entity travelNoteEntity = GetJsonDatas.getTravelNoteEntity(travelnote);
        List<TravelNote2Entity.DataBean> notelist = travelNoteEntity.getData();
        adapter.setDatas(notelist);
        if (fab != null){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "哼，我家洁儿又调皮了", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //RecyclerView自带的下拉刷新
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeFresh);
//        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.app_theme));
//        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        swipeRefreshLayout.setColorSchemeColors(R.color.app_theme);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("print", "------>开始刷新，加载数据");

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



       /* String note_url = String.format(Contact.TRAVEL_NOTE, page);
        new DownUtil().setOnDownListener(this).downJSON(note_url);*/
    }
/*
    @Override
    public Object paresJson(String json) {
        if (json != null) {

            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray ja = jsonObject.getJSONArray("data");
                TypeToken<List<TravelNoteEntity>> tt = new TypeToken<List<TravelNoteEntity>>() {
                };
                return new Gson().fromJson(ja.toString(), tt.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            List<TravelNoteEntity> list = (List<TravelNoteEntity>) object;
            adapter.setDatas(list);

            swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeFresh);
            swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.app_theme));
            swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Log.d("print", "------>开始刷新，加载数据");

                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(5000);
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
    }*/

}

package com.qf.besttravel;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.adapter.MeetWorldAdapter;
import com.qf.contacts.Contact;
import com.qf.entity.MeetWorldEntity;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/11/9.
 */
public class MeetWorldActivity extends BaseActivity implements DownUtil.OnDownListener {


    @Bind(R.id.meet_top_title)
    TextView meetTopTitle;
    @Bind(R.id.meet_top_summary)
    TextView meetTopSummary;
    /*@Bind(R.id.meet_rv)
    RecyclerView meetRv;*/
    private RecyclerView meet_ry;

    private MeetWorldAdapter adapter;

  /*  private Retrofit retrofit;
    private RetrofitTool retrofitTool;*/

    @Override
    public int getContentViewId() {
        return R.layout.activity_meetworld;
    }



    @Override
    protected void init() {
      /*  retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contact.BASE_URL)
                .build();
        retrofitTool = retrofit.create(RetrofitTool.class);*/
        meet_ry = findViewByIds(R.id.meet_rv);
        meet_ry.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MeetWorldAdapter(this);
        meet_ry.setAdapter(adapter);
    }

    @Override
    protected void loadDatas() {
        new DownUtil().setOnDownListener(this).downJSON(Contact.MEET_WORLD);
/*
        Call<M> call = retrofitTool.getMeetWorldEntity();
        call.enqueue(new Callback<M>() {
            @Override
            public void onResponse(Call<M> call, Response<M> response) {
                M body = response.body();

                adapter.setDatas(its);
            }

            @Override
            public void onFailure(Call<M> call, Throwable t) {

            }
        });
*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, MeetWorldEntity.class);
        }
        return null;

    }

    @Override
    public void downSucc(Object object) {
        MeetWorldEntity entity = (MeetWorldEntity) object;
        List<MeetWorldEntity.DataBean.ItemsBean> items = entity.getData().getItems();
        adapter.setDatas(items);

    }
}

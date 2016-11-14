package com.qf.besttravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.adapter.SceneryMapAdapter;
import com.qf.entity.SceneryEntity;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import java.util.List;

import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by lenovo on 2016/10/15.
 */
public class SceneryMapActivity extends BaseActivity implements DownUtil.OnDownListener {
//    @Bind(R.id.plan_mapdays_lv)
//    RecyclerView planMapdaysLv;
//    @Bind(R.id.travel_map_days_title)
//    TextView travelMapDaysTitle;
//    @Bind(R.id.travel_map_days_back)
//    ImageView travelMapDaysBack;

    private String mapurl;
    private double lat,lng;
    private SceneryMapAdapter adapter;

    private RecyclerView planMapdaysLv;
    private TextView travelMapDaysTitle;
    private ImageView travelMapDaysBack;
    private ImageView plan_maptravel_share;

    @Override
    public int getContentViewId() {
        return R.layout.activity_travelmap_scenery;
    }

    @Override
    protected void init() {
        travelMapDaysBack = findViewByIds(R.id.travel_map_days_back);
        plan_maptravel_share = findViewByIds(R.id.plan_maptravel_share);
        planMapdaysLv = findViewByIds(R.id.plan_mapdays_lv);
        travelMapDaysTitle = findViewByIds(R.id.travel_map_days_title);

        travelMapDaysBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        plan_maptravel_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSDK.initSDK(SceneryMapActivity.this);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.icon_guide_headimg, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("标题");
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite("ShareSDK");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

                // 启动分享GUI
                oks.show(SceneryMapActivity.this);
            }
        });
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat",22.61);//默认深圳经纬度
        lng = intent.getDoubleExtra("lng",114.06);
        mapurl = intent.getStringExtra("mapurl");
        Log.d("print", "init: mapurl"+mapurl);
    }

    @Override
    protected void loadDatas() {
        new DownUtil().setOnDownListener(this).downJSON(mapurl);
    }

    @Override
    public Object paresJson(String json) {
        if(json!=null){
            return new Gson().fromJson(json, SceneryEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            SceneryEntity sceneryEntity = (SceneryEntity) object;
            travelMapDaysTitle.setText(sceneryEntity.getData().getTitle());

            List<SceneryEntity.DataBean.DaysBean> daysBeanList = sceneryEntity.getData().getDays();

            planMapdaysLv.setLayoutManager(new LinearLayoutManager(this));
            adapter = new SceneryMapAdapter(this,daysBeanList);
            planMapdaysLv.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
            adapter.setDatas(daysBeanList,lat,lng);

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}

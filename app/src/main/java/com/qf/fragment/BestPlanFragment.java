package com.qf.fragment;

import android.content.Intent;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.qf.adapter.BestPlanLvAdapter;
import com.qf.besttravel.MeetWorldActivity;
import com.qf.besttravel.R;
import com.qf.entity.BannerItem;
import com.qf.entity.Carousel2Entity;
import com.qf.entity.DestinationEntity;
import com.qf.utils.GetJsonDatas;
import com.qf.widget.NetworkImageHolderView;
import com.qf.widget.planlv.PlanListview;
import com.qfkf.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/30.
 */
public class BestPlanFragment extends BaseFragment{


    private PlanListview bestplanLv;
    private BestPlanLvAdapter adapter;
    private double lat,lng;
    private String city_now;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {

            //接收定位返回结果
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");


                //获得经纬度
                lat = location.getLatitude();
                lng = location.getLongitude();
                city_now = location.getCity();


//                Log.d("print", "定位坐标Latitude： "+ lat +" Longitude:"+ lng);
//                Toast.makeText(getActivity(), "客官，你现在位于:"+city_now + "，当前位置为北纬：" + lat + "，东经:" + lng, Toast.LENGTH_LONG).show();


            }
        }

    };

    //顶部轮播图
    private ConvenientBanner convenientBanner;
    private List<String> pics;
    private List<String> titles;
    private List<BannerItem> bannerItems = new ArrayList<>();
    private Intent in;
    /* private List<String> imglist = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<BannerItem> bannerItems = new ArrayList<>();

    private String[] is;
    private String[] ts;*/



    @Override
    protected int getContentView() {
        return R.layout.fragment_bestplan;
    }

    @Override
    protected void init(View view) {

        mLocationClient = new LocationClient(getActivity().getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        mLocationClient.start();//开始定位

        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner_showGoods);
        bestplanLv = (PlanListview) view.findViewById(R.id.bestplan_lv_);
        adapter = new BestPlanLvAdapter(getContext());
        bestplanLv.setAdapter(adapter);

    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0; //仅定位一次
//        int span= 5000; //每隔5秒定位一次
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

//    //下载图片的封装方法
//    private void downimg(String url, ImageView img) {
//        Glide.with(getContext())
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.img_placeholder)
//                .thumbnail(0.1f)
//                .crossFade(500)
//                .into(img);
//    }



    //加载listview数据
    @Override
    protected void loadDatas() {
        //头部的轮播图
        initCarousel();

        String destination = GetJsonDatas.getJSON2("destinations",getContext());
        DestinationEntity destinationEntity = GetJsonDatas.getDestinationEntity(destination);
        List<DestinationEntity.DataBean> dataBeanList = destinationEntity.getData();
        adapter.setDatas(dataBeanList);
      /*  //下载listview数据
        new DownUtil().setOnDownListener(this).downJSON(Contact.PLAN_DESTINATIONS);*/

    }


    //头部的轮播图
    private void initCarousel() {
        in = new Intent(getActivity(), MeetWorldActivity.class);
        String json_causel = GetJsonDatas.getJSON2("carousel", getContext());
        List<Carousel2Entity> capics = GetJsonDatas.getFoodsPic(json_causel);
        pics = new ArrayList<>();
        titles = new ArrayList<>();
        for (int i = 0; i < capics.size(); i++) {
            pics.add(capics.get(i).getPhoto().getPhoto_url());
            titles.add(capics.get(i).getTopic());
        }
        for (int i = 0; i < pics.size(); i++) {
            bannerItems.add(new BannerItem(pics.get(i), titles.get(i)));
        }
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },bannerItems)
                .setPageIndicator(new int[]{R.mipmap.abc_btn_radio_to_on_mtrl_000,
                        R.mipmap.abc_btn_radio_to_on_mtrl_015})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .startTurning(3500);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(int position) {

                switch (position) {
                    case 0:
                        in.putExtra("id",18);
                        startActivity(in);
//                        Toast.makeText(getActivity(), "你点击了"+position, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        in.putExtra("id",96);
                        startActivity(in);
//                        Toast.makeText(getActivity(), "你点击了"+position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        in.putExtra("id",90);
                        startActivity(in);
//                        Toast.makeText(getActivity(), "你点击了"+position, Toast.LENGTH_SHORT).show();
                        break;
                    default:

                        break;
                }
            }
        });
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contact.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONUtil util = retrofit.create(JSONUtil.class);

        Call<CarouselEntity> call = util.getCarousel();

        call.enqueue(new Callback<CarouselEntity>() {
            @Override
            public void onResponse(Call<CarouselEntity> call, Response<CarouselEntity> response) {
                List<CarouselEntity.DataBean> data = response.body().getData();

                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getPhoto() != null){
//                        Log.d("pring", "onResponse: "+data.get(i).getPhoto().getPhoto_url());
                        imglist.add(data.get(i).getPhoto().getPhoto_url());
                    }

                }
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getPhoto() != null){
//                        Log.d("pring", "onResponse: "+data.get(i).getPhoto());
                        titles.add(data.get(i).getTopic());
                    }

                }

                is = new String[imglist.size()];
                for (int i = 0; i < imglist.size(); i++) {
                    is[i] = imglist.get(i);
                }

                ts = new String[titles.size()];
                for (int i = 0; i < titles.size(); i++) {
                    ts[i] = titles.get(i);
                }

                for (int i = 0; i < imglist.size(); i ++) {
                    bannerItems.add(new BannerItem(is[i], ts[i]));
                }

                convenientBanner
                        .setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                            @Override
                            public NetworkImageHolderView createHolder() {
                                return new NetworkImageHolderView();
                            }
                        }, bannerItems)
                        .setPageIndicator(new int[]{R.mipmap.abc_btn_radio_to_on_mtrl_000,
                                R.mipmap.abc_btn_radio_to_on_mtrl_015})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                        .startTurning(2000);
            }

            @Override
            public void onFailure(Call<CarouselEntity> call, Throwable t) {

            }
        });
*/
    }

   /* //下载listview数据
    @Override
    public Object paresJson(String json) {
        if (json != null) {
            try {
                JSONArray jarray = new JSONObject(json).optJSONArray("data");
                TypeToken<List<DestinationEntity.DataBean>> tt = new TypeToken<List<DestinationEntity.DataBean>>(){};
                return new Gson().fromJson(jarray.toString(),tt.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null){
            List<DestinationEntity.DataBean> dataBeanList = (List<DestinationEntity.DataBean>) object;
            adapter.setDatas(dataBeanList);

        }
    }*/




}

package com.qf.contacts;

/**
 * Created by lenovo on 2016/9/30.
 */
public interface Contact {
    //头部图片
    String BASE_URL = "http://q.chanyouji.com/";
//    String PLAN_HEADIMG="api/v1/adverts.json?market=qq&first_launch=false";
    //攻略 目的地列表
    String PLAN_DESTINATIONS="http://q.chanyouji.com/api/v2/destinations.json";

    //攻略 目的地详情
    String PAGE_STRATEGY="http://q.chanyouji.com/api/v3/destinations/%d.json";

    //攻略 每一个X日游详情
    String PAGE_STRATEGY_DETAILS="http://q.chanyouji.com/api/v2/plans/%d.json";

    //概括与地图
    String SUMMARY_MAP = "http://q.chanyouji.com/api/v2/destinations/%d/groupings.json";//166

    //游记接口
    String TRAVEL_NOTE = "http://q.chanyouji.com/api/v1/timelines.json?page=%d";

    //游记
    String NOTE_WORLD_NOTE = "http://q.chanyouji.com/api/v1/users/%s/user_activities.json?parent_district_id=%d&page=%d";

    //更多district_id游记
    String MORE_NOTE = "http://q.chanyouji.com/api/v1/user_activities.json?district_id=%d&page=%d";
//    String NOTE_USER_ALLNOTE2 = "http://q.chanyouji.com/api/v1/users/2098/user_activities.json?parent_district_id=100048&page=1";

    //行程单
    String TRAVEL_ORDER = "http://q.chanyouji.com/api/v1/albums/37.json";

    //遇见世界
//    String MEET_WORLD = "api/v1/albums/90.json";
    String MEET_WORLD = "http://q.chanyouji.com/api/v1/albums/90.json";

    //附近目的地
    String NEARBY_DIS = "http://q.chanyouji.com/api/v2/destinations/nearby.json?lat=%f&lng=%f";
    //附近旅行灵感
    String NEARBY_INSPI_NOTE = "http://q.chanyouji.com/api/v2/destinations/nearby_inspiration_activities.json?lat=22.529226&lng=114.026752";
    //
    String AREA_DESTI = "http://q.chanyouji.com/api/v2/destinations/list.json?area=%s";

}

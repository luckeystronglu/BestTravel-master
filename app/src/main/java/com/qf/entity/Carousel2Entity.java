package com.qf.entity;

/**
 * Created by lenovo on 2016/11/7.
 */
public class Carousel2Entity {

    /**
     * width : 1280
     * height : 600
     * photo_url : http://inspiration.chanyouji.cn/Advert/135/3a5a8918bc0f1656ab0621e02d532b20.jpg
     */

    private PhotoBean photo;
    /**
     * photo : {"width":1280,"height":600,"photo_url":"http://inspiration.chanyouji.cn/Advert/135/3a5a8918bc0f1656ab0621e02d532b20.jpg"}
     * id : 135
     * ios_url :
     * android_url :
     * target_id : 95
     * advert_type : album
     * topic : 氢气球每周精选
     * market : all
     * open_in_browser : false
     */

    private int id;
    private String ios_url;
    private String android_url;
    private String target_id;
    private String advert_type;
    private String topic;
    private String market;
    private boolean open_in_browser;

    public PhotoBean getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoBean photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIos_url() {
        return ios_url;
    }

    public void setIos_url(String ios_url) {
        this.ios_url = ios_url;
    }

    public String getAndroid_url() {
        return android_url;
    }

    public void setAndroid_url(String android_url) {
        this.android_url = android_url;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getAdvert_type() {
        return advert_type;
    }

    public void setAdvert_type(String advert_type) {
        this.advert_type = advert_type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public boolean isOpen_in_browser() {
        return open_in_browser;
    }

    public void setOpen_in_browser(boolean open_in_browser) {
        this.open_in_browser = open_in_browser;
    }

    public static class PhotoBean {
        private int width;
        private int height;
        private String photo_url;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }
    }
}

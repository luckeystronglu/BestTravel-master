package com.qf.entity;

import java.util.List;

/**
 * Created by lenovo on 2016/11/17.
 */
public class UserNoteEntity {

    /**
     * id : 63066
     * made_at : 2016-11-14T03:00:05.000Z
     * likes_count : 5
     * comments_count : 0
     * topic : 静静漫游帕劳海底世界―硬珊瑚
     * contents_count : 9
     * district_id : 1445564
     * created_at : 2016-11-14T03:00:23.000Z
     * favorites_count : 1
     * parent_district_id : 1445564
     * parent_district_count : 8
     * description : 来帕劳所有潜水的人都一定是想看到美丽的珊瑚和各种鱼儿。
     硬珊瑚区就是这样两者兼具的景点，在这里除了可以看到各种不同形状的硬珊瑚，还可以看到数量惊人的热带鱼。 
     在这里有一个绝对不容错过的巨型玫瑰珊瑚，因为在世界其他地方开放浮潜的水域里，能看到这样巨型玫瑰珊瑚的机会并不多，所以你目前所见的，可是世界上绝无仅有的，再多看它两眼吧！硬珊瑚区的感觉这个就像是一个怪石林立的怪石头博物馆，有的枝枝蔓蔓，有的圆头圆脑，还有各种不规则的奇形怪状。
     帕劳硬珊瑚区，即这里的珊瑚不像软珊瑚会随着水流舞动，看来如同一丛丛的石林，不过，这里的鱼群还颇多，下水后可以拿出些许面包屑，就能引来美丽的热带鱼群。 
     若是运气好的话，还可以看到帕劳的国宝鱼－龙王雕（国内称苏眉），因为眼睛上方的两道短小黑色纹路和眉毛颇像而得名，而其成鱼的背部很像拿破仑的帽子，故又有拿破仑鱼之称。
     我觉得我们的运气实在是太好了。真的看见苏眉鱼啦。而且在这里还可以看到小丑鱼。
     Tip：提醒大家硬珊瑚区的珊瑚如同岩石般坚固，千万不要以卵击石，用自己的肉体去体验岩石的坚固哦！小心被刮伤。
     * current_user_liked : false
     * current_user_commented : false
     * current_user_favorited : false
     * contents : [{"id":388924,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388924/062f8c6c81c409071d7a06a5cb088a86.jpg","width":600,"height":400,"position":0},{"id":388925,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388925/3b255617715fd2f77a156c4cca6d7e78.jpg","width":1280,"height":960,"position":1},{"id":388926,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388926/d61fcfe8c1bd7e5b6e2b0a802a3d545c.jpg","width":934,"height":700,"position":2},{"id":388927,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388927/0d8f04042adb278e117da39a690f0d4f.jpg","width":1280,"height":960,"position":3},{"id":388928,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388928/c8566af8e868a633315466ef3e943a2d.jpg","width":613,"height":316,"position":4},{"id":388929,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388929/d9eaf11d90ddc0de207a54b41e3d27ae.jpg","width":614,"height":432,"position":5},{"id":388930,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388930/43a3cda71ac86fcfa359d6e74411a75e.jpg","width":600,"height":420,"position":6},{"id":388931,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388931/e3dd6a22bad62251d597766ab88e6354.jpg","width":1280,"height":960,"position":7},{"id":388932,"caption":null,"photo_url":"http://inspiration.chanyouji.cn/UserActivityContent/388932/072d3043a00ebb9f2ec252c3c813f7e5.jpg","width":1024,"height":768,"position":8}]
     * districts : [{"id":1445564,"name":"帕劳","name_en":"Palau","name_pinyin":"pa lao|pl","score":null,"level":2,"path":".120003.1445564.","published":false,"is_in_china":false,"user_activities_count":56,"lat":7.51498,"lng":134.583,"is_valid_destination":false,"destination_id":290}]
     * categories : [{"id":9,"name":"潜水","category_type":"official"}]
     * poi : null
     * user : {"id":1049574,"name":"月儿","gender":0,"level":1,"photo_url":"http://inspiration.chanyouji.cn/User/1049574/a2543ce0b7c988f6c332c7d49f53f194.jpg"}
     * inspiration_activity_id : 0
     * inspiration_activity : null
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String made_at;
        private int likes_count;
        private int comments_count;
        private String topic;
        private int contents_count;
        private int district_id;
        private String created_at;
        private int favorites_count;
        private int parent_district_id;
        private int parent_district_count;
        private String description;
        private boolean current_user_liked;
        private boolean current_user_commented;
        private boolean current_user_favorited;
        private Object poi;
        /**
         * id : 1049574
         * name : 月儿
         * gender : 0
         * level : 1
         * photo_url : http://inspiration.chanyouji.cn/User/1049574/a2543ce0b7c988f6c332c7d49f53f194.jpg
         */

        private UserBean user;
        private int inspiration_activity_id;
        private Object inspiration_activity;
        /**
         * id : 388924
         * caption : null
         * photo_url : http://inspiration.chanyouji.cn/UserActivityContent/388924/062f8c6c81c409071d7a06a5cb088a86.jpg
         * width : 600
         * height : 400
         * position : 0
         */

        private List<ContentsBean> contents;
        /**
         * id : 1445564
         * name : 帕劳
         * name_en : Palau
         * name_pinyin : pa lao|pl
         * score : null
         * level : 2
         * path : .120003.1445564.
         * published : false
         * is_in_china : false
         * user_activities_count : 56
         * lat : 7.51498
         * lng : 134.583
         * is_valid_destination : false
         * destination_id : 290
         */

        private List<DistrictsBean> districts;
        /**
         * id : 9
         * name : 潜水
         * category_type : official
         */

        private List<CategoriesBean> categories;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMade_at() {
            return made_at;
        }

        public void setMade_at(String made_at) {
            this.made_at = made_at;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public int getContents_count() {
            return contents_count;
        }

        public void setContents_count(int contents_count) {
            this.contents_count = contents_count;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getFavorites_count() {
            return favorites_count;
        }

        public void setFavorites_count(int favorites_count) {
            this.favorites_count = favorites_count;
        }

        public int getParent_district_id() {
            return parent_district_id;
        }

        public void setParent_district_id(int parent_district_id) {
            this.parent_district_id = parent_district_id;
        }

        public int getParent_district_count() {
            return parent_district_count;
        }

        public void setParent_district_count(int parent_district_count) {
            this.parent_district_count = parent_district_count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isCurrent_user_liked() {
            return current_user_liked;
        }

        public void setCurrent_user_liked(boolean current_user_liked) {
            this.current_user_liked = current_user_liked;
        }

        public boolean isCurrent_user_commented() {
            return current_user_commented;
        }

        public void setCurrent_user_commented(boolean current_user_commented) {
            this.current_user_commented = current_user_commented;
        }

        public boolean isCurrent_user_favorited() {
            return current_user_favorited;
        }

        public void setCurrent_user_favorited(boolean current_user_favorited) {
            this.current_user_favorited = current_user_favorited;
        }

        public Object getPoi() {
            return poi;
        }

        public void setPoi(Object poi) {
            this.poi = poi;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getInspiration_activity_id() {
            return inspiration_activity_id;
        }

        public void setInspiration_activity_id(int inspiration_activity_id) {
            this.inspiration_activity_id = inspiration_activity_id;
        }

        public Object getInspiration_activity() {
            return inspiration_activity;
        }

        public void setInspiration_activity(Object inspiration_activity) {
            this.inspiration_activity = inspiration_activity;
        }

        public List<ContentsBean> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBean> contents) {
            this.contents = contents;
        }

        public List<DistrictsBean> getDistricts() {
            return districts;
        }

        public void setDistricts(List<DistrictsBean> districts) {
            this.districts = districts;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class UserBean {
            private int id;
            private String name;
            private int gender;
            private int level;
            private String photo_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }
        }

        public static class ContentsBean {
            private int id;
            private Object caption;
            private String photo_url;
            private int width;
            private int height;
            private int position;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCaption() {
                return caption;
            }

            public void setCaption(Object caption) {
                this.caption = caption;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }

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

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }
        }

        public static class DistrictsBean {
            private int id;
            private String name;
            private String name_en;
            private String name_pinyin;
            private Object score;
            private int level;
            private String path;
            private boolean published;
            private boolean is_in_china;
            private int user_activities_count;
            private double lat;
            private double lng;
            private boolean is_valid_destination;
            private int destination_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName_pinyin() {
                return name_pinyin;
            }

            public void setName_pinyin(String name_pinyin) {
                this.name_pinyin = name_pinyin;
            }

            public Object getScore() {
                return score;
            }

            public void setScore(Object score) {
                this.score = score;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public boolean isPublished() {
                return published;
            }

            public void setPublished(boolean published) {
                this.published = published;
            }

            public boolean isIs_in_china() {
                return is_in_china;
            }

            public void setIs_in_china(boolean is_in_china) {
                this.is_in_china = is_in_china;
            }

            public int getUser_activities_count() {
                return user_activities_count;
            }

            public void setUser_activities_count(int user_activities_count) {
                this.user_activities_count = user_activities_count;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public boolean isIs_valid_destination() {
                return is_valid_destination;
            }

            public void setIs_valid_destination(boolean is_valid_destination) {
                this.is_valid_destination = is_valid_destination;
            }

            public int getDestination_id() {
                return destination_id;
            }

            public void setDestination_id(int destination_id) {
                this.destination_id = destination_id;
            }
        }

        public static class CategoriesBean {
            private int id;
            private String name;
            private String category_type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCategory_type() {
                return category_type;
            }

            public void setCategory_type(String category_type) {
                this.category_type = category_type;
            }
        }
    }
}

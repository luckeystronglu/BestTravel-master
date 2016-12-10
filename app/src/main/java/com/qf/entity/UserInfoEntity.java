package com.qf.entity;

/**
 * Created by lenovo on 2016/12/10.
 */
public class UserInfoEntity {

    /**
     * message : ok
     * status : 200
     * data : {"id":2180,"photo_url":"http://inspiration.chanyouji.cn/User/2180/68a2e9654b8c967669a7ade2e171299f.jpg","name":"sandyjin","gender":0,"level":3,"memo":"微信联系","email":"sandyjin7312@163.com","introduce":"","user_activities_count":115,"followings_count":14,"followers_count":118,"wishes_count":0,"favorites_count":14,"is_device_user":false,"unread_notifications_count":{"comments":0,"follows":0,"systems":0,"likes":0,"favorites":0},"header_photo":{"photo_url":"http://inspiration.chanyouji.cn/Photo/39a009a703f6e55719e0f068c7ec3990.jpg","width":1365,"height":768},"is_follow":false,"follow_each_other":false}
     */

    private String message;
    private int status;
    /**
     * id : 2180
     * photo_url : http://inspiration.chanyouji.cn/User/2180/68a2e9654b8c967669a7ade2e171299f.jpg
     * name : sandyjin
     * gender : 0
     * level : 3
     * memo : 微信联系
     * email : sandyjin7312@163.com
     * introduce :
     * user_activities_count : 115
     * followings_count : 14
     * followers_count : 118
     * wishes_count : 0
     * favorites_count : 14
     * is_device_user : false
     * unread_notifications_count : {"comments":0,"follows":0,"systems":0,"likes":0,"favorites":0}
     * header_photo : {"photo_url":"http://inspiration.chanyouji.cn/Photo/39a009a703f6e55719e0f068c7ec3990.jpg","width":1365,"height":768}
     * is_follow : false
     * follow_each_other : false
     */

    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String photo_url;
        private String name;
        private int gender;
        private int level;
        private String memo;
        private String email;
        private String introduce;
        private int user_activities_count;
        private int followings_count;
        private int followers_count;
        private int wishes_count;
        private int favorites_count;
        private boolean is_device_user;
        /**
         * comments : 0
         * follows : 0
         * systems : 0
         * likes : 0
         * favorites : 0
         */

        private UnreadNotificationsCountBean unread_notifications_count;
        /**
         * photo_url : http://inspiration.chanyouji.cn/Photo/39a009a703f6e55719e0f068c7ec3990.jpg
         * width : 1365
         * height : 768
         */

        private HeaderPhotoBean header_photo;
        private boolean is_follow;
        private boolean follow_each_other;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getUser_activities_count() {
            return user_activities_count;
        }

        public void setUser_activities_count(int user_activities_count) {
            this.user_activities_count = user_activities_count;
        }

        public int getFollowings_count() {
            return followings_count;
        }

        public void setFollowings_count(int followings_count) {
            this.followings_count = followings_count;
        }

        public int getFollowers_count() {
            return followers_count;
        }

        public void setFollowers_count(int followers_count) {
            this.followers_count = followers_count;
        }

        public int getWishes_count() {
            return wishes_count;
        }

        public void setWishes_count(int wishes_count) {
            this.wishes_count = wishes_count;
        }

        public int getFavorites_count() {
            return favorites_count;
        }

        public void setFavorites_count(int favorites_count) {
            this.favorites_count = favorites_count;
        }

        public boolean isIs_device_user() {
            return is_device_user;
        }

        public void setIs_device_user(boolean is_device_user) {
            this.is_device_user = is_device_user;
        }

        public UnreadNotificationsCountBean getUnread_notifications_count() {
            return unread_notifications_count;
        }

        public void setUnread_notifications_count(UnreadNotificationsCountBean unread_notifications_count) {
            this.unread_notifications_count = unread_notifications_count;
        }

        public HeaderPhotoBean getHeader_photo() {
            return header_photo;
        }

        public void setHeader_photo(HeaderPhotoBean header_photo) {
            this.header_photo = header_photo;
        }

        public boolean isIs_follow() {
            return is_follow;
        }

        public void setIs_follow(boolean is_follow) {
            this.is_follow = is_follow;
        }

        public boolean isFollow_each_other() {
            return follow_each_other;
        }

        public void setFollow_each_other(boolean follow_each_other) {
            this.follow_each_other = follow_each_other;
        }

        public static class UnreadNotificationsCountBean {
            private int comments;
            private int follows;
            private int systems;
            private int likes;
            private int favorites;

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public int getFollows() {
                return follows;
            }

            public void setFollows(int follows) {
                this.follows = follows;
            }

            public int getSystems() {
                return systems;
            }

            public void setSystems(int systems) {
                this.systems = systems;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public int getFavorites() {
                return favorites;
            }

            public void setFavorites(int favorites) {
                this.favorites = favorites;
            }
        }

        public static class HeaderPhotoBean {
            private String photo_url;
            private int width;
            private int height;

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
        }
    }
}

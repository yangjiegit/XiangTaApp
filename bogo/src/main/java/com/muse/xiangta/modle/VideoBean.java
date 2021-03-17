package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class VideoBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"uid":100283,"is_online":0,"city":"","img":"http://qiniu.muse12.com/video_cover_img/1577018427871_1577018413224.jpg","video_url":"http://qiniu.muse12.com/video/1577018429262_1577018390881.mp4","user_nickname":"生活没有假如","overlapping_sound":"Hello World","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100284,"is_online":0,"city":"","img":"http://qiniu.muse12.com/video_cover_img/1577004993770_1577004984637.jpg","video_url":"http://qiniu.muse12.com/video/1577004994361_VIDEO_20191219_003558.mp4","user_nickname":"1314","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100287,"is_online":0,"city":"郑州市","img":"http://qiniu.muse12.com/idImage/1577005178982.png","video_url":"http://qiniu.muse12.com/idIosVideo/1577005178564.mp4","user_nickname":"小师妹","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100296,"is_online":0,"city":"潍坊市","img":"http://qiniu.muse12.com/video_cover_img/1577082598058_1577082579990.jpg","video_url":"http://qiniu.muse12.com/video/1577082608540_1577082570518.mp4","user_nickname":"大湿兄😍","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100338,"is_online":0,"city":"","img":"http://qiniu.muse12.com/video_cover_img/1577084119010_1577084098294.jpg","video_url":"http://qiniu.muse12.com/video/1577084129401_1576842332067.mp4","user_nickname":"Pen泉","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100348,"is_online":0,"city":"西安市","img":"http://qiniu.muse12.com/video_cover_img/1577084542705_1577084494956.jpg","video_url":"http://qiniu.muse12.com/video/1577084543944_1569403356755.mp4","user_nickname":"欧美型小姐姐","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100350,"is_online":0,"city":"","img":"http://qiniu.muse12.com/idImage/1577083737656.png","video_url":"http://qiniu.muse12.com/idIosVideo/1577083737987.mp4","user_nickname":"粉嫩","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100379,"is_online":0,"city":"宜宾市","img":"http://qiniu.muse12.com/idImage/1577086016653.png","video_url":"http://qiniu.muse12.com/idIosVideo/1577086016542.mp4","user_nickname":"可可","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100386,"is_online":0,"city":"阜新市","img":"http://qiniu.muse12.com/video_cover_img/1577086274625_1577086263467.jpg","video_url":"http://qiniu.muse12.com/video/1577086275196_52182646112768640041575977376201.mp4","user_nickname":"乖宝宝听指挥","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0},{"uid":100391,"is_online":0,"city":"中山市","img":"http://qiniu.muse12.com/video_cover_img/1577097855501_1577097806272.jpg","video_url":"http://qiniu.muse12.com/video/1577097855703_1575325672228.mp4","user_nickname":"男科女医生","overlapping_sound":"","sex":2,"age":18,"focus":0,"video_cost":0}]
     */

    private int code;
    private String msg;
    /**
     * uid : 100283
     * is_online : 0
     * city :
     * img : http://qiniu.muse12.com/video_cover_img/1577018427871_1577018413224.jpg
     * video_url : http://qiniu.muse12.com/video/1577018429262_1577018390881.mp4
     * user_nickname : 生活没有假如
     * overlapping_sound : Hello World
     * sex : 2
     * age : 18
     * focus : 0
     * video_cost : 0
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int uid;
        private int is_online;
        private String city;
        private String img;
        private String video_url;
        private String user_nickname;
        private String overlapping_sound;
        private int sex;
        private int age;
        private int focus;
        private int video_cost;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getIs_online() {
            return is_online;
        }

        public void setIs_online(int is_online) {
            this.is_online = is_online;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getOverlapping_sound() {
            return overlapping_sound;
        }

        public void setOverlapping_sound(String overlapping_sound) {
            this.overlapping_sound = overlapping_sound;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getFocus() {
            return focus;
        }

        public void setFocus(int focus) {
            this.focus = focus;
        }

        public int getVideo_cost() {
            return video_cost;
        }

        public void setVideo_cost(int video_cost) {
            this.video_cost = video_cost;
        }
    }
}

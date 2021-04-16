package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class NoticeIndexBean implements Serializable {

    /**
     * code : 1
     * msg :
     * list : []
     * data : {"list":[{"id":112,"is_audio":0,"audio_file":"","publish_time":"1天前","msg_content":"不过现在还是有点期待你","uid":102728,"video_url":"http://xta.zzmzrj.com/idIosVideo/1618472740862.mp4","cover_url":"http://xta.zzmzrj.com/idImage/1618472740797.png","city":"郑州市","lat":"34.785731","lng":"113.686213","duration":"","is_love":0,"title":"恋爱了","love_status":0,"comment_count":0,"userInfo":{"id":102728,"avatar":"http://xta.zzmzrj.com/e210b202104081654297235.png","user_nickname":"晨晨","sex":2,"level":1,"coin":999859,"user_status":2,"is_auth":1,"is_online":1,"link_id":"0","signature":"","noble":"http://xta.zzmzrj.com/admin/20210323/4705d7cb8e9fc580231e35047439cee9.png"},"originalPicUrls":[],"thumbnailPicUrls":[],"is_like":"1","like_count":1},{"id":111,"is_audio":0,"audio_file":"","publish_time":"1天前","msg_content":"好多东西在看","uid":102728,"video_url":"","cover_url":"","city":"郑州市","lat":"34.785699","lng":"113.685983","duration":"","is_love":0,"title":"恋爱了","love_status":0,"comment_count":0,"userInfo":{"id":102728,"avatar":"http://xta.zzmzrj.com/e210b202104081654297235.png","user_nickname":"晨晨","sex":2,"level":1,"coin":999859,"user_status":2,"is_auth":1,"is_online":1,"link_id":"0","signature":"","noble":"http://xta.zzmzrj.com/admin/20210323/4705d7cb8e9fc580231e35047439cee9.png"},"originalPicUrls":["http://xta.zzmzrj.com/idImage/1618472228111.png"],"thumbnailPicUrls":["http://xta.zzmzrj.com/idImage/1618472228111.png"],"is_like":"0","like_count":0},{"id":108,"is_audio":0,"audio_file":"","publish_time":"1天前","msg_content":"好多朋友说了不要打电话我要来接你","uid":102728,"video_url":"http://xta.zzmzrj.com/idIosVideo/1618471567613.mp4","cover_url":"http://xta.zzmzrj.com/idImage/1618471567839.png","city":"郑州市","lat":"34.785966","lng":"113.686094","duration":"","is_love":0,"title":"恋爱了","love_status":0,"comment_count":1,"userInfo":{"id":102728,"avatar":"http://xta.zzmzrj.com/e210b202104081654297235.png","user_nickname":"晨晨","sex":2,"level":1,"coin":999859,"user_status":2,"is_auth":1,"is_online":1,"link_id":"0","signature":"","noble":"http://xta.zzmzrj.com/admin/20210323/4705d7cb8e9fc580231e35047439cee9.png"},"originalPicUrls":[],"thumbnailPicUrls":[],"is_like":"0","like_count":1},{"id":107,"is_audio":0,"audio_file":"","publish_time":"1天前","msg_content":"是否能够真正成为了我们生命之中不可或缺最重要就是一场一天了吧","uid":102728,"video_url":"http://xta.zzmzrj.com/idIosVideo/1618471353221.mp4","cover_url":"http://xta.zzmzrj.com/idImage/1618471353494.png","city":"","lat":"","lng":"","duration":"","is_love":0,"title":"恋爱了","love_status":0,"comment_count":0,"userInfo":{"id":102728,"avatar":"http://xta.zzmzrj.com/e210b202104081654297235.png","user_nickname":"晨晨","sex":2,"level":1,"coin":999859,"user_status":2,"is_auth":1,"is_online":1,"link_id":"0","signature":"","noble":"http://xta.zzmzrj.com/admin/20210323/4705d7cb8e9fc580231e35047439cee9.png"},"originalPicUrls":[],"thumbnailPicUrls":[],"is_like":"0","like_count":0}]}
     */

    private int code;
    private String msg;
    private DataBean data;
    private List<?> list;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 112
         * is_audio : 0
         * audio_file :
         * publish_time : 1天前
         * msg_content : 不过现在还是有点期待你
         * uid : 102728
         * video_url : http://xta.zzmzrj.com/idIosVideo/1618472740862.mp4
         * cover_url : http://xta.zzmzrj.com/idImage/1618472740797.png
         * city : 郑州市
         * lat : 34.785731
         * lng : 113.686213
         * duration :
         * is_love : 0
         * title : 恋爱了
         * love_status : 0
         * comment_count : 0
         * userInfo : {"id":102728,"avatar":"http://xta.zzmzrj.com/e210b202104081654297235.png","user_nickname":"晨晨","sex":2,"level":1,"coin":999859,"user_status":2,"is_auth":1,"is_online":1,"link_id":"0","signature":"","noble":"http://xta.zzmzrj.com/admin/20210323/4705d7cb8e9fc580231e35047439cee9.png"}
         * originalPicUrls : []
         * thumbnailPicUrls : []
         * is_like : 1
         * like_count : 1
         */

        private List<DynamicListModel> list;

        public List<DynamicListModel> getList() {
            return list;
        }

        public void setList(List<DynamicListModel> list) {
            this.list = list;
        }

    }
}

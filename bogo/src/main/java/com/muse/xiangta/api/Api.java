package com.muse.xiangta.api;

import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.PostRequest;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.manage.AppConfig;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.utils.StringUtils;

import java.io.File;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * api
 */

public class Api {

    //检查通话记录是否存在
    public static void doCheckVideoCallExits(String channel_id, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/check_video_call_exits")
                .params("channel_id", channel_id)
                .tag("doCheckVideoCallExits")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetGiftCabinetList(String id, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/request_get_gift_cabinet")
                .params("to_user_id", id)
                .tag("doRequestGetGiftCabinetList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    /**
     * 拨打视频电话
     *
     * @param uid   uid
     * @param token token
     * @param id    对方id
     */
    public static void getPushCallVideo(String uid, String token, String id, StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/Push/index")
                .params("uid", uid)
                .params("token", token)
                .params("id", id)
                .tag("getPushCallVideo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "拨打电话####" + "getPushCallVideo");
    }

    /**
     * 获取关注列表
     *
     * @param uid   uid
     * @param token token
     */
    public static void getAboutDataList(String uid, String token, int page, StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_api/get_follow_list")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .tag("getAboutDataList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取关注列表####" + "getAboutDataList");
    }

    /**
     * 获取粉丝列表
     *
     * @param uid   uid
     * @param token token
     */
    public static void getFansDataList(String uid, String token, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/get_fans_list")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .tag("getFansDataList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取粉丝列表####" + "getFansDataList");
    }

    /**
     * 批量获取用户魅力排行榜
     *
     * @param uid   uid
     * @param token token
     * @param type  日榜day 周榜week 不填是总榜
     * @param page  分页不传或0为第一页每页
     */
    public static void getCharmListData(String uid, String token, String type, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/rank_api/charm_rank_list")
                .params("uid", uid)
                .params("token", token)
                .params("type", type)
                .params("page", page)
                .tag("getCharmListData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "批量获取用户魅力排行榜####" + "getCharmListData");
    }

    /**
     * 批量获取用户财气排行榜
     *
     * @param uid   uid
     * @param token token
     * @param type  日榜day 周榜week 不填是总榜
     * @param page  分页不传或0为第一页每页
     */
    public static void getMoneyListData(String uid, String token, String type, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/rank_api/wealth_rank_list")
                .params("uid", uid)
                .params("token", token)
                .params("type", type)
                .params("page", page)
                .tag("getMoneyListData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "批量获取用户财气排行榜####" + "getMoneyListData");
    }

    /**
     * 批量获取新人页列表信息
     *
     * @param uid   uid
     * @param token token
     */
    public static void getNewPeoplePageList(String uid, String token, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_news_user_list")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .tag("getNewPeoplePageList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "批量获取新人页列表信息####" + "getNewPeoplePageList");
    }

    /**
     * 附近的人
     *
     * @param uid
     * @param token
     * @param page
     * @param callback
     */
    public static void getNearPeoplePageList(String uid, String token, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/nearby_user")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .tag("getNewPeoplePageList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "批量获取新人页列表信息####" + "getNewPeoplePageList");
    }

    /**
     * 获取轮播图
     *
     * @param uid       uid
     * @param token     token
     * @param shuffling 类型,1推荐页轮播*2消息页轮播
     */
    public static void getRollImage(String uid, String token, String shuffling, StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/recommended_api/shuffling")
                .params("uid", uid)
                .params("token", token)
                .params("shuffling", shuffling)
                .tag("getUserPhotoList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取轮播图####" + shuffling + "getUserPhotoList");
    }

    /**
     * 获取小视频列表
     *
     * @param uid   用户id
     * @param token token
     * @param type  类型##
     * @param lat   附近的视频 要传的用户维度参数
     * @param lng   附近的视频 要传的用户经度参数
     * @see ApiUtils.VideoType 推荐reference最新latest关注attention附近near
     */
    public static void getVideoPageList(String uid, String token, String type, int page, String lat, String lng, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/small_video_api/index")
                .params("uid", uid)
                .params("token", token)
                .params("type", type)
                .params("lat", lat)
                .params("lng", lng)
                .params("page", page)
                .tag("getVideoPageList")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .execute(callback);
    }

    public static void getVideoList(String uid, String token, String type, int page, StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/small_video_api/index")
                .params("uid", uid)
                .params("token", token)
                .params("type", type)
                .params("page", page)
                .tag("getVideoList")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .execute(callback);
    }

    /**
     * 获取推荐用户列表
     *
     * @param uid   用户uid
     * @param token token
     * @param page  分页数
     */
    public static void getRecommendUserList(String uid, String token, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/recommend_user")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .tag("getRecommendUserList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取推荐用户列表####" + "getRecommendUserList");
    }

    /**
     * 获取推荐用户列表
     *
     * @param uid   用户uid
     * @param token token
     * @param page  分页数
     */
    public static void recommendUser(String uid, String token, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/recommend_user_3x")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .tag("getRecommendUserList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取推荐用户列表####" + "getRecommendUserList");
    }

    /**
     * 执行监听是否在线(进行心跳操作)
     *
     * @param uid   用户id
     * @param token token
     */
    public static void doMonitorIsOnLine(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/app_api/interval")
                .params("uid", uid)
                .params("token", token)
                .tag("doMonitorIsOnLine")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
        Log.d("api", "执行监听是否在线(进行心跳操作)####" + "doMonitorIsOnLine");
    }

    /**
     * 获取配置信息
     *
     * @param uid   用户id
     * @param token token
     */
    public static void getConfigData(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/app_api/config")
                .params("uid", uid)
                .params("token", token)
                .tag("getConfigData")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
        Log.d("api", "获取配置信息####" + "getConfigData");
    }

    /**
     * 获取用户信息
     *
     * @param id    目标用户id
     * @param uid   用户id
     * @param token token
     */
    public static void getUserData(String id, String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/personal_api/get_user_page_info")
                .params("id", id)
                .params("uid", uid)
                .params("token", token)
                .tag("getUserData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取用户信息####" + "getUserData");
    }

    /**
     * 获取用户信息
     *
     * @param id    目标用户id
     * @param uid   用户id
     * @param token token
     */
    public static void getUserBaseData(String id, String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/personal_api/get_user_base_info")
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", id)
                .tag("getUserBaseData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取用户信息####" + "getUserData");
    }

    /**
     * 获取自身信息
     *
     * @param uid   用户id
     * @param token token
     */
    public static void getUserDataByMe(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/get_user_center_info")
                .params("uid", uid)
                .params("token", token)
                .tag("getUserDataByMe")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取自身信息####" + "getUserDataByMe");
    }

    /**
     * 关注用户
     *
     * @param id    目标人id
     * @param token token
     * @param uid   用户id+
     */
    public static void doLoveTheUser(String id, String uid, String token, StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/personal_api/click_attention")
                .params("id", id)
                .params("uid", uid)
                .params("token", token)
                .tag("doLoveTheUser")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "关注用户####" + "doLoveTheUser");
    }

    /**
     * 编辑用户资料保存
     *
     * @param uid           id
     * @param token         token
     * @param user_nickname 用户昵称
     * @param avatar        用户头像
     * @param sex           性别
     * @param img           轮播图文件集合
     */
    public static void saveUserDataAtCompile(
            String height, String weight, String constellation,
            String introduce, String image_label, String self_label,
            String uid, String token, String user_nickname, String avatar, int sex,
            String occupation, String education, String love_status, String overlapping_sound,
            String coverurl, String video_url,
            List<String> img, String sign, StringCallback callback) {
        PostRequest postRequest = OkGo.post(AppConfig.API_DOMAIN + "/user_api/update_user_info_190708");
        postRequest.params("uid", uid);
        postRequest.params("token", token);
        postRequest.params("sex", sex);
        postRequest.params("occupation", occupation);
        postRequest.params("education", education);
        postRequest.params("love_status", love_status);
        postRequest.params("overlapping_sound", overlapping_sound);
        postRequest.params("coverurl", coverurl);
        postRequest.params("video_url", video_url);
        postRequest.params("constellation", constellation);
        postRequest.params("user_nickname", user_nickname);
        postRequest.params("sign", sign);
        postRequest.params("height", height);
        postRequest.params("weight", weight);
        postRequest.params("introduce", introduce);
        postRequest.params("image_label", image_label);
        postRequest.params("self_label", self_label);


        if (avatar != null) {
            postRequest.params("avatar", avatar);
        }
        if (img.size() != 0) {
            for (int i = 0; i < img.size(); i++) {
                postRequest.params("img" + i, img.get(i));
                Log.d("api", "img--->" + i + "####" + img.get(i).toString());
            }
        }
        postRequest.tag("saveUserDataAtCompile");
        postRequest.cacheMode(CacheMode.NO_CACHE);
        postRequest.execute(callback);
        Log.d("api", "imgs---->" + img + "####" + img.toString());
        Log.d("api", "编辑用户资料保存####" + "saveUserDataAtCompile");
    }

    /**
     * 在编辑时获取用户数据
     *
     * @param uid   用户id
     * @param token 用户token
     */
    public static void getUserDataAtCompile(String uid, String token, StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_api/edit_user_info")
                .params("uid", uid)
                .params("token", token)
                .tag("getUserDataAtCompile")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
        Log.d("api", "在编辑时获取用户数据####" + "getUserDataAtCompile");
    }

    /**
     * 在编辑时删除用户图片
     *
     * @param uid   用户id
     * @param token 用户token
     */
    public static void delectUserImage(String uid, String token, String delId, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/del_image")
                .params("uid", uid)
                .params("token", token)
                .params("id", delId)
                .tag("delectUserImage")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
        Log.d("api", "在编辑时删除用户图片####" + "delectUserImage");
    }

    /**
     * 发送验证码
     *
     * @param mobile 手机号码
     */
    public static void sendCodeByRegister(String mobile, StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/login_api/code")
                .params("mobile", mobile)
                .tag("sendCodeByRegister")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
    }

    /**
     * 用户登录操作
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    public static void userLogin(String mobile, String code, String inviteCode, String agent, String uuid, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/login_api/do_login")
                .params("mobile", mobile)
                .params("code", code)
                .params("invite_code", inviteCode)
                .params("agent", agent)
                .params("uuid", uuid)
                .tag("userLogin")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
    }

    /**
     * 用户登录操作(自动登录)
     *
     * @param id    手机号
     * @param token 验证码
     */
    public static void userLogin(StringCallback callback, String id, String token) {
        OkGo.post(AppConfig.API_DOMAIN + "/login_api/do_login")
                .params("id", id)
                .params("token", token)
                .tag("userLogin")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
    }

    //拨打视频电话
    public static void doCallToUser(String userId, String userToken, String targetUserId, int type, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/video_call_1215")
                .params("uid", userId)
                .params("token", userToken)
                .params("id", targetUserId)
                .params("call_type", type)
                .tag("doCallToUser")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //回复电话
    public static void doReplyVideoCall(String userId, String userToken, String toUserId, String channel, String type, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/reply_video_call_0907")
                .params("uid", userId)
                .params("token", userToken)
                .params("to_user_id", toUserId)
                .params("type", type)
                .params("channel", channel)
                .tag("doReplyVideoCall")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //挂断电话
    public static void doHangUpVideoCall(String userId, String userToken, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/hang_up_video_call_0907")
                .params("uid", userId)
                .params("token", userToken)
                .tag("doHangUpVideoCall")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //结束通话
    public static void doEndVideoCall(String userId, String userToken, String toUserId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/end_video_call_0907")
                .params("uid", userId)
                .params("token", userToken)
                .params("to_user_id", toUserId)
                .tag("doEndVideoCall")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //计时扣费
    public static void doVideoCallTimeCharging(String userId, String userToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/video_call_time_charging")
                .params("uid", userId)
                .params("token", userToken)
                .tag("doVideoCallTimeCharging")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取娱乐场正在直播的视频列表
    public static void doGetVideoList(String uId, String userToken, int count, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/live_api/rand_video_live_list")
                .params("uid", uId)
                .params("token", userToken)
                .params("count", count)
                .tag("doGetVideoList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //直播心跳
    public static void doUpdateTabLiveHeart(String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/live_api/update_live")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doUpdateTabLiveHeart")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取用户会话信息
    public static void doRequestConversationUserInfo(StringBuilder ids, String type, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/get_conversation_user_info")
                .params("ids", ids.toString())
                .params("type", type)
                .tag("doRequestConversationUserInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取自己的私照
    public static void doRequestGetPrivatePhoto(String uId, String id, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/private_photo_api/pictures_list")
                .params("uid", uId)
                .params("id", id)
                .params("page", page)
                .tag("doRequestGetPrivatePhoto")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //上传私照
    public static void doUploadPrivatePhoto(String uId, String uToken, List<File> files, StringCallback callback) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", uId);
        httpParams.put("token", uToken);
        for (File file : files) {
            httpParams.put(file.getName(), file);
        }
        OkGo.post(AppConfig.API_DOMAIN + "/private_photo_api/private_photos_upload")
                .params(httpParams)
                .tag("doUploadPrivatePhoto")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetUploadSign(String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/get_upload_sign")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doRequestGetUploadSign")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doUploadShortVideo(String uId, String uToken, int status, String money, String title, String videoId, String videoPath, String coverPath, String lat, String lng, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/add_video")
                .params("uid", uId)
                .params("token", uToken)
                .params("status", status)
                .params("money", money)
                .params("title", title)
                .params("video_url", videoPath)
                .params("video_id", videoId)
                .params("cover_url", coverPath)
                .params("lat", lat)
                .params("lng", lng)
                .tag("doUploadShortVideo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    /**
     * @param uId 被点赞用户id
     * @dw 点赞用户
     */
    public static void doFabulousUser(String uId, String uToken, String channelId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/video_fabulous")
                .params("uid", uId)
                .params("token", uToken)
                .params("channel_id", channelId)
                .tag("doFabulousUser")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doGetVideo(String uId, String uToken, String videoId, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/get_video")
                .params("uid", uId)
                .params("token", uToken)
                .params("video_id", videoId)
                .tag("doGetVideo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestBuyVideo(String uId, String uToken, String videoId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/buy_video")
                .params("uid", uId)
                .params("token", uToken)
                .params("video_id", videoId)
                .tag("doRequestBuyVideo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestSelectPic(String uId, String uToken, String pid, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/private_photo_api/select_photo")
                .params("uid", uId)
                .params("token", uToken)
                .params("pid", pid)
                .tag("doRequestSelectPic")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);

    }

    //购买私照
    public static void doRequestBuyPhoto(String uId, String uToken, String pid, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/private_photo_api/pay_personal")
                .params("uid", uId)
                .params("token", uToken)
                .params("pid", pid)
                .tag("doRequestBuyPhoto")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取视频结束信息
    public static void doReuqestGetVideoEndInfo(String uId, String uToken, String channelId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/get_video_call_end_info")
                .params("uid", uId)
                .params("token", uToken)
                .params("channel_id", channelId)
                .tag("doReuqestGetVideoEndInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //提交认证视频
    public static void doSubmitAuthVideo(String uId, String uToken, String videoUrl, String coverUrl, String videoId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/video_auth")
                .params("uid", uId)
                .params("token", uToken)
                .params("video_url", videoUrl)
                .params("cover_url", coverUrl)
                .params("video_id", videoId)
                .tag("doSubmitAuthVideo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取短视频列表
    public static void doGetShortVideoList(String uId, String uToken, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/get_video_list")
                .params("uid", uId)
                .params("token", uToken)
                .params("page", page)
                .tag("doGetShortVideoList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //删除视频文件
    public static void doDeleteVideoFile(String uId, String uToken, String videoId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/del_video")
                .params("uid", uId)
                .params("token", uToken)
                .params("video_id", videoId)
                .tag("doDeleteVideoFile")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //充值规则
    public static void doRequestGetChargeRule(String uId, String uToken, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/recharge_api/get_recharge_page_data")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doRequestGetChargeRule")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //钱包信息
    public static void doRequestGetWealthPageInfo(String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_wealth_page_info")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doRequestGetWealthPageInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //私信信息
    public static void doPrivateChat(String uId, String uToken, String toUserId, StringCallback callback) {


        OkGo.get(AppConfig.API_DOMAIN + "/personal_api/private_chat")
                .params("uid", uId)
                .params("token", uToken)
                .params("to_user_id", toUserId)
                .tag("doPrivateChat")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //关注用户
    public static void doFollowVideo(String uId, String uToken, String videoId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/follow_video")
                .params("uid", uId)
                .params("token", uToken)
                .params("video_id", videoId)
                .tag("doFollowVideo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //拉黑用户
    public static void doRequestBlackUser(String uId, String uToken, String toUserId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/personal_api/black_user")
                .params("uid", uId)
                .params("token", uToken)
                .params("to_user_id", toUserId)
                .tag("doRequestBlackUser")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取礼物列表
    public static void doRequestGetGift(StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_gift_list")
                .tag("doRequestGetGift")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取礼物列表
    public static void doRequestGetMyGift(String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_bag_list")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doRequestGetGift")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //发送礼物
    public static void doSendGift(String uid, String token, String count, String toUserId, String giftId, String channelId, String url, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + url)
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", toUserId)
                .params("gid", giftId)
                .params("count", count)
                .params("channel", channelId)
                .tag("doSendGift")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取邀请页面信息
    public static void doRequestGetInviteData(String uId, String token, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/invite_api/get_my_invite_page")
                .params("uid", uId)
                .params("token", token)
                .params("page", page)
                .tag("doRequestGetInviteData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //检查是否提交邀请码
    public static void doCheckIsFullInviteCode(String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/invite_api/is_full_invite_code")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doCheckIsFullInviteCode")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //提交邀请码
    public static void doRequestSubmitInviteCode(String uId, String uToken, String inviteCode, int type, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/invite_api/full_invite_code")
                .params("uid", uId)
                .params("token", uToken)
                .params("invite_code", inviteCode)
                .params("type", type)
                .tag("doRequestSubmitInviteCode")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //提现
    public static void doRequestCash(String uId, String uToken, String name, String number, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/cash_income")
                .params("uid", uId)
                .params("token", uToken)
                .params("name", name)
                .params("number", number)
                .tag("doRequestCash")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //获取我的收益信息
    public static void doGetCashPageInfo(String uId, String uToken, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_user_income_page_info")
                .params("uid", uId)
                .params("token", uToken)
                .params("page", page)
                .tag("doGetCashPageInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //举报
    public static void doReportUser(String uId, String uToken, String toUserId, int type, String content, List<File> fileList, StringCallback callback) {

        PostRequest postRequest = OkGo.post(AppConfig.API_DOMAIN + "/user_api/do_report_user");
        postRequest.params("uid", uId);
        postRequest.params("token", uToken);
        postRequest.params("to_user_id", toUserId);
        postRequest.params("type", type);
        postRequest.params("content", content);

        if (fileList.size() != 0) {
            for (int i = 0; i < fileList.size(); i++) {
                postRequest.params("img" + i, fileList.get(i));
                Log.d("api", "img--->" + i + "####" + fileList.get(i).toString());
            }
        }
        postRequest.tag("doReportUser");
        postRequest.cacheMode(CacheMode.NO_CACHE);
        postRequest.execute(callback);
    }


    public static void doFeedBack(String content, String tel, String imgJson, StringCallback callback) {

        PostRequest postRequest = OkGo.post(AppConfig.API_DOMAIN + "/feedback_api/app_buy");
        postRequest.params("uid", SaveData.getInstance().getId());
        postRequest.params("token", SaveData.getInstance().getToken());
        postRequest.params("centent", content);
        postRequest.params("tel", tel);

        postRequest.params("img", imgJson);

        postRequest.tag("doReportUser");
        postRequest.cacheMode(CacheMode.NO_CACHE);
        postRequest.execute(callback);
    }


    //获取举报类型列表
    public static void doGetReportList(StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_report_type")
                .tag("doGetReportList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //删除私照
    public static void doDelPrivatePhoto(String uId, String uToken, String pid, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/private_photo_api/del_photo")
                .params("uid", uId)
                .params("token", uToken)
                .params("pid", pid)
                .tag("doDelPrivatePhoto")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetCity(StringCallback callback) {

        OkGo.get("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json")
                .tag("doRequestGetCity")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    //加盟合作
    public static void doJoinIn(String uId, String uToken, String type, String content, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/join_in")
                .params("uid", uId)
                .params("token", uToken)
                .params("type", type)
                .params("contact", content)
                .tag("doJoinIn")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetUserContributionRank(String uId, String uToken, String toUserId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/rank_api/user_contribution_rank")
                .params("uid", uId)
                .params("token", uToken)
                .params("to_user_id", toUserId)
                .tag("doRequestGetUserContributionRank")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //guardian_api/app_buy
    public static void doGuardian(String toUserId, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guardian_api/app_index")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("hostid", toUserId)
                .tag("doGuardian")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    public static void doGetBlackList(String uId, String uToken, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/black_list")
                .params("uid", uId)
                .params("token", uToken)
                .params("page", page)
                .tag("doGetBlackList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestPerfectInfo(String uid, String token, String userNickname, int sex, File avatar, StringCallback callback) {

        OkGo.post(AppConfig.API_DOMAIN + "/login_api/perfect_reg_info")
                .params("uid", uid)
                .params("token", token)
                .params("user_nickname", userNickname)
                .params("sex", sex)
                .params("avatar", avatar)
                .tag("doRequestPerfectInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doGetOnlineUser(String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_online_user")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doGetOnlineUser")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doCheckIsNeedCharging(String uId, String toUserId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/is_need_charging")
                .params("uid", uId)
                .params("to_user_id", toUserId)
                .tag("doCheckIsNeedCharging")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestCharge(String uId, String uToken, String rid, String pid, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/pay_api/pay")
                .params("uid", uId)
                .params("token", uToken)
                .params("pid", pid)
                .params("rid", rid)
                .tag("doRequestCharge")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doChangeVideoChargeCoin(String uId, String uToken, String coin, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/change_video_line_money")
                .params("uid", uId)
                .params("token", uToken)
                .params("coin", coin)
                .tag("doChangeVideoChargeCoin")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestSetDoNotDisturb(int type, String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/request_set_do_not_disturb")
                .params("uid", uId)
                .params("token", uToken)
                .params("type", type)
                .tag("doRequestSetDoNotDisturb")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRefreshCity(String uId, String city, String lat, String lng, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/refresh_city")
                .params("uid", uId)
                .params("city", city)
                .params("lat", lat)
                .params("lng", lng)
                .tag("doRefreshCity")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doPlatAuthLogin(String platId, String inviteCode, String agent, String uuid, int loginway, JsonCallback callback) {


        OkGo.get(AppConfig.API_DOMAIN + "/login_api/auth_login")
                .params("plat_id", platId)
                .params("invite_code", inviteCode)
                .params("agent", agent)
                .params("uuid", uuid)
                .params("login_way", loginway)
                .tag("doPlatAuthLogin")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetSystemMessageList(String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/system_message_api/get_system_message")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doRequestGetSystemMessageList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doGetInviteCode(String uId, String uToken, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/invite_api/get_invite_code")
                .params("uid", uId)
                .params("token", uToken)
                .tag("doGetInviteCode")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestChatPay(String uId, String uToken, String toUserId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/deal_api/request_private_chat_pay")
                .params("uid", uId)
                .params("token", uToken)
                .params("to_user_id", toUserId)
                .tag("doRequestChatPay")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doCheckVideoCoinRange(String money, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/video_coin_check")
                .params("money", money)
                .tag("doCheckVideoCoinRange")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetVideoChatPageData(StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/request_get_video_chat_page_data")
                .tag("doRequestGetVideoChatPageData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void getOnlineUserList(String uId, String uToken, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/request_get_index_online")
                .tag("getOnlineUserList")
                .params("uid", uId)
                .params("token", uToken)
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //一键约爱
    public static void doRequestOneKeyCall(String uid, String token, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/one_key_video_call_1901017")
                .tag("doRequestOneKeyCall")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetStarEmceeList(String levelId, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/request_get_star_emcee_list")
                .tag("doRequestGetStarEmceeList")
                .params("level_id", levelId)
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetAttentionList(String uid, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/request_get_follow_emcee_list")
                .tag("doRequestGetAttentionList")
                .params("uid", uid)
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetEvaluate(String uid, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/request_get_evaluate_list")
                .params("uid", uid)
                .tag("doRequestGetEvaluate")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestEvaluateEmcee(String uid, String token, String toUserId, String channelId, String labelListStr, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/request_submit_evaluate")
                .tag("doRequestEvaluateEmcee")
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", toUserId)
                .params("label_str", labelListStr)
                .params("channel_id", channelId)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestSearch(String uid, String keyWord, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/request_search")
                .tag("doRequestSearch")
                .params("uid", uid)
                .params("key_word", keyWord)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetEvaluate(String uid, int evaluatePage, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/personal_api/get_evaluate_list")
                .tag("doRequestGetEvaluate")
                .params("to_user_id", uid)
                .params("page", evaluatePage)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestSubmitAuthInfo(HttpParams params, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/app_request_submit_auth_info")
                .tag("doRequestSubmitAuthInfo")
                .params(params)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doGetOtherUserShortVideoList(String uid, String token, String toUserId, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/short_video_api/get_other_user_video_list")
                .tag("doGetOtherUserShortVideoList")
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", toUserId)
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void getUserHomePageInfo(String toUserId, String uid, String token, JsonCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/get_user_page_info")
                .tag("getUserHomePageInfo")
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", toUserId)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetUserPageInfo(String uid, String token, int page, String toUserId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/get_user_page_user_info")
                .tag("doRequestGetUserPageInfo")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("to_user_id", toUserId)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetOSSInfo(String uid, String token, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/app_api/get_qiniu_upload_token")
                .tag("doRequestGetOSSInfo")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetRewardCoinRule(StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_coin_reward_rule")
                .tag("doRequestGetRewardCoinRule")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetPayPalOrderStatus(String id, String r_id, String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/pay_api/check_pay_pal_order_status")
                .params("order_id", id)
                .params("r_id", r_id)
                .params("uid", uid)
                .params("token", token)
                .tag("doRequestGetPayPalOrderStatus")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRewardCoin(String uid, String token, String r_id, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/deal_api/coin_reward")
                .params("r_id", r_id)
                .params("uid", uid)
                .params("token", token)
                .tag("doRewardCoin")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetVideoCallList(String userId, String token, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/get_video_call_list")
                .params("uid", userId)
                .params("token", token)
                .tag("doRequestGetVideoCallList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doGetVideoCallInfo(String userId, String token, String id, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/get_video_call_info")
                .params("uid", userId)
                .params("token", token)
                .params("id", id)
                .tag("doGetVideoCallInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    //实时获取主播收益信息
    public static void doRequestGetVideoCallTimeInfo(String uid, String channelName, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/get_video_call_time_info")
                .params("uid", uid)
                .params("channel_id", channelName)
                .tag("doRequestGetVideoCallTimeInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    /**
     * 获取动态列表 推荐
     */

    public static void doRequestGetDynamicList(String uid, String token, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/get_list_3x")
                .tag("doRequestGetDynamicList")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("type", 2)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    /**
     * 获取动态列表 关注
     */

    public static void doRequestGetLoveDynamicList(String uid, String token, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/get_list_3x")
                .tag("doRequestGetDynamicList")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("action", "att")
                .params("type", 2)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    /**
     * 获取动态列表 我的
     */
    public static void doRequestGetMyDynamicList(String uid, String token, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/get_list_3x")
                .tag("doRequestGetDynamicList")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("to_user_id", uid)
                .params("type", 2)
                .params("action", "ou")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    /**
     * 获取动态列表 附近
     */
    public static void doRequestGeNearDynamicList(String uid, String token, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/get_list_3x")
                .tag("doRequestGetDynamicList")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("to_user_id", uid)
                .params("type", 2)
                .params("action", "near")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //点赞
    public static void doRequestDynamicLike(String uid, String token, String zoneId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/request_like")
                .tag("doRequestDynamicLike")
                .params("uid", uid)
                .params("token", token)
                .params("zone_id", zoneId)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetDynamicCommonList(String zoneId, String uid, String token, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/get_reply_list")
                .tag("doRequestGetDynamicCommonList")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("zone_id", zoneId)
                .params("type", 2)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestPublishCommon(String uid, String token, String msgContent, String zoneId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/add_dynamic_reply")
                .tag("doRequestPublishCommon")
                .params("uid", uid)
                .params("token", token)
                .params("body", msgContent)
                .params("zone_id", zoneId)
                .params("type", 2)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    public static void doRequestPushDynamic(String city, String uid, String token, String content, int isAudio, List<String> uploadImgUrlList,
                                            String uploadVideoUrl, String voiceFilePath, int fileType, String uploadVideoThmbUrl, int duration,
                                            String title, String is_love, StringCallback callback) {

        HttpParams params = new HttpParams();
        params.put("uid", uid);
        params.put("token", token);
        params.put("msg_content", content);
        params.put("is_audio", isAudio);
        params.put("duration", duration);
        //     type   0普通图片动态  1视频动态
        params.put("type", fileType);
        params.put("city", city);
        if (!StringUtils.isEmpty(title)) {
            params.put("title", title);
            params.put("is_love", is_love);
        }


        params.put("lat", CuckooApplication.getInstances().getLat());
        params.put("lng", CuckooApplication.getInstances().getLng());

        if (isAudio == 1) {
            params.put("audio_url", voiceFilePath);
        }
        //图片动态
        if (fileType == 0) {

            for (int i = 0; i < uploadImgUrlList.size(); i++) {
                params.put("file" + (i + 1), uploadImgUrlList.get(i));
            }

        } else {
            if (!TextUtils.isEmpty(uploadVideoUrl)) {
                params.put("cover_url", uploadVideoThmbUrl);
                params.put("video_url", uploadVideoUrl);
            }

        }
        Log.e("doRequestPushDynamic", params.toString());
        OkGo.post(AppConfig.API_DOMAIN + "/bzone_api/add_dynamic_new")
                .tag("doRequestPushDynamic")
                .params(params)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);


    }

    public static void doRequestDelDynamic(String uid, String token, String zoneId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/del_dynamic")
                .tag("doRequestDynamicLike")
                .params("uid", uid)
                .params("token", token)
                .params("zone_id", zoneId)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    /**
     * 获取推荐列表
     *
     * @param uid   用户id
     * @param token token
     */
    public static void getRecommedListData(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/recommended_api/recommend_list")
                .params("uid", uid)
                .params("token", token)
                .tag("getUserData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
        Log.d("api", "获取推荐列表####" + "getRecommedListData");
    }

    /**
     * 一键关注推荐用户
     */

    public static void getCommendRecommedListData(String dataList, String uid, String token, StringCallback callback) {


        OkGo.post(AppConfig.API_DOMAIN + "/recommended_api/follows")
                .params("uid", uid)
                .params("liveuid", dataList)
                .params("token", token)
                .tag("getCommendRecommedListData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetSubscribeList(String uid, String token, int action, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/get_my_subscribe_user_list")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("action", action)
                .tag("doRequestGetSubscribeList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestBackVideoCall(String uid, String token, String toUserId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/back_video_call")
                .params("uid", uid)
                .params("token", token)
                .params("id", toUserId)
                .tag("doRequestBackVideoCall")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    public static void cancelSubScribe(String uid, String token, String id, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/user_api/cancel_video_call")
                .params("uid", uid)
                .params("token", token)
                .params("id", id)
                .tag("cancelSubScribe")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    /**
     * 获取用户性质
     *
     * @param to_user_id
     * @param callback
     */
    public static void doGetAuthStatus(String to_user_id, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/get_user_auth_status")
                .params("to_user_id", to_user_id)
                .tag("doGetAuthStatus")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doSubscribe(String uid, String token, String toUserId, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/subscribe_user")
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", toUserId)
                .tag("doSubscribe")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetIsAuth(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/is_auth")
                .params("uid", uid)
                .params("token", token)
                .tag("doRequestGetIsAuth")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    /**
     * 个人中心 动态 我的
     */

    public static void doRequestGetMyDynamicList(String uid, String token, String toUserId, int page, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/bzone_api/get_list_3x")
                .tag("doRequestGetDynamicList")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("to_user_id", toUserId)
                .params("type", 2)
                .params("action", "ou")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    public static void getVipData(String id, String token, StringCallback callback) {

        OkGo.get(AppConfig.API_DOMAIN + "/vip_api/get_vip_page_info")
                .tag("getVipData")
                .params("uid", id)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void selectToPay(String id, String token, String rid, String pid, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/pay_api/pay_vip")
                .params("uid", id)
                .params("token", token)
                .params("pid", pid)
                .params("rid", rid)
                .tag("doRequestCharge")
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    public static void doRequestCheckImg(String uid, String token, File file, StringCallback callback) {

        OkGo.post(AppConfig.API_DOMAIN + "/tools_api/check_img_compliance")
                .params("uid", uid)
                .params("token", token)
                .params("image", file)
                .tag("doRequestCheckImg")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doCreateGuild(String uid, String token, String name, String introduce, String uploadLogoImgUrl, StringCallback callback) {

        OkGo.post(AppConfig.API_DOMAIN + "/guild_api/create_guild")
                .params("uid", uid)
                .params("token", token)
                .params("name", name)
                .params("introduce", introduce)
                .params("logo_img", uploadLogoImgUrl)
                .tag("doCreateGuild")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetGuildList(String uid, String token, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/guild_list")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .tag("doRequestGetGuildList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doJoinGuild(String uid, String token, int id, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/join_guild")
                .params("uid", uid)
                .params("token", token)
                .params("guild_id", id)
                .tag("doJoinGuild")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);

    }

    public static void doRequestGetGuildUser(String uid, String token, String guildId, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/guild_user_list")
                .params("uid", uid)
                .params("token", token)
                .params("guild_id", guildId)
                .params("page", page)
                .tag("doRequestGetGuildUser")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetGuildInfo(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/guild_info")
                .params("uid", uid)
                .params("token", token)
                .tag("doRequestGetGuildInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetGuildApplyUser(String uid, String token, String guildId, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/guild_apply_user_list")
                .params("uid", uid)
                .params("token", token)
                .params("guild_id", guildId)
                .params("page", page)
                .tag("doRequestGetGuildApplyUser")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestAudition(String uid, String token, String id, String agree, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/audition")
                .params("uid", uid)
                .params("token", token)
                .params("id", id)
                .params("action", agree)
                .tag("doRequestAudition")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetRatioInfo(String uid, String token, int userId, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/get_user_ratio")
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", userId)
                .tag("doRequestGetRatioInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doChangeUserRatio(String uid, String token, int userId, String videoLineRatio, String giftRatio, String photoRatio, String shortVideoRatio, String chatRatio, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/save_user_ratio")
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", userId)
                .params("host_one_video_proportion", videoLineRatio)
                .params("host_bay_gift_proportion", giftRatio)
                .params("host_bay_phone_proportion", photoRatio)
                .params("host_bay_video_proportion", shortVideoRatio)
                .params("host_direct_messages", chatRatio)
                .tag("doChangeUserRatio")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doSelectIncomeLog(String uid, String token, long startTime, long endTime, String toUserId, int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guild_api/select_income_log")
                .params("uid", uid)
                .params("token", token)
                .params("to_user_id", toUserId)
                .params("start_time", startTime)
                .params("end_time", endTime)
                .params("page", page)
                .tag("doSelectIncomeLog")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void getMsgPageInfo(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/system_message_api/unread_messages")
                .params("uid", uid)
                .params("token", token)
                .tag("getMsgPageInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    ///invitation_api/app_index 邀请好友信息
    public static void getU(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/app_index")
                .params("uid", uid)
                .params("token", token)
                .tag("getMsgPageInfo")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    ///invitation_api/app_index 我的等级
    public static void getLevel(StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/level_api/app_index")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //invitation_api/app_withdrawal 提现信息
    public static void getWithdrawal(StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/app_withdrawal")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    //invitation_api/app_withdrawal 提现
    public static void toDrawal(int rulesId, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/withdrawal_api/request_user_apply")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("id", rulesId)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    //invitation_api/app_invited_us 推广明细
    public static void getInvitedInfo(String type, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/app_invited_users")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("type", type)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //invitation_api/upd_binding_account 绑定账户
    public static void bindAccount(String type, String wx, String ali, String name, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/upd_binding_account")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("pay", ali)
                .params("wx", wx)
                .params("name", name)
                .params("type", type)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //invitation_api/upd_binding_account 绑定银行卡信息
    public static void bindBank(String bank_account, String bank_cardno, String bank_name, String bank_addr, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/upd_binding_bank")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("bank_account", bank_account)
                .params("bank_cardno", bank_cardno)
                .params("bank_name", bank_name)
                .params("bank_addr", bank_addr)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //invitation_api/get_binding_account 绑定信息
    public static void getAccountBind(StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/get_binding_account")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    ///Level_api/app_fee 收费设置
    public static void getFee(StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/Level_api/app_fee")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //Level_api/app_fee_add
    public static void setFee(String id, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/Level_api/app_fee_add")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("id", id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //novice_guide_api/app_about
    public static void getAboutUs(StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/novice_guide_api/app_about")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //novice_guide_api/app_about
    public static void getGuardList(String id, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guardian_api/app_buy")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("hostid", id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //page_data_api/about_love
    public static void getOneKeyCallInfo(StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/about_love")
                .params("uid", SaveData.getInstance().getId())
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    //detail_api/app_index
    public static void getIncome(boolean b, int page, String date, final StringCallback callback) {
        Log.i("明细", "getIncome: " + b + "," + page);
        OkGo.get(AppConfig.API_DOMAIN + "/detail_api/app_index")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("type", b ? "1" : "2")
                .params("page", page)
                .params("date", date)
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        callback.onSuccess(s, call, response);
                    }

                });
    }

    //detail_api/app_withdrawal
    public static void getDrawal(int page, final StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/detail_api/app_withdrawal")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //detail_api/app_recharge
    public static void getRecharge(int page, String date, final StringCallback callback) {
        String url = AppConfig.API_DOMAIN + "/detail_api/app_recharge" + "?uid=" + SaveData.getInstance().getId() + "&token=" + SaveData.getInstance().getToken() + "&page=" + page;
        Log.i("充值明细", "getRecharge: " + url);
        OkGo.get(AppConfig.API_DOMAIN + "/detail_api/app_recharge")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //guardian_api/app_buy_add
    public static void buyGuardian(String id, String hos, final StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/guardian_api/app_buy_add")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("id", id).params("hostid", hos)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //invitation_api/reward_subsidiary
    public static void getReward(String date, final StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/reward_subsidiary")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("date", date)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    //invitation_api/is_sex_reward_subsidiary
    public static void getRewardSex(boolean boy, String date, final StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/is_sex_reward_subsidiary")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("date", date)
                .params("type", boy ? "1" : "2")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //
    public static void getDrawingInfo(int page, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/sel_withdrawal")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("page", page + "")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    public static void getEditInfo(StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/app_edit_user_info")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    //guardian_api/app_buy_add
    public static void getRules(StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/guardian_api/app_privilege")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestSaveCustomMsgData(String uId, String token, String toString, StringCallback callback) {
        OkGo.post(AppConfig.API_DOMAIN + "/custom_say_func_plugs_api/add_custom_msg")
                .params("uid", uId)
                .params("token", token)
                .params("msg_str", toString)
                .tag("doRequestSaveCustomMsgData")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void doRequestGetCustomMsgList(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/custom_say_func_plugs_api/get_custom_msg_list")
                .params("uid", uid)
                .params("token", token)
                .tag("doRequestGetCustomMsgList")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    public static void getRulesData(String uid, String token, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/withdrawal_api/get_cash_rule")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void getSignInfo(String uId, String uToken, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/sign_in_api/is_sign_in")
                .params("uid", uId)
                .params("token", uToken)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }


    public static void toWinthDraw(String money, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/app_withdrawal_sub")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("money", money)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }


    public static void getSearchHistory(String id, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/search_log")
                .params("uid", id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    public static void getShareInfo(StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/invitation_api/app_picture")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    public static void getIsBindPhone(StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/is_binding_mobile")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //绑定手机号
    public static void bindMobile(String mobile, String code, StringCallback callback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/binging_mobile")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("mobile", mobile)
                .params("code", code)
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void sendShareInfo(String id, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/small_video_api/share_number")
                .params("id", id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /**
     * 获取形象标签
     *
     * @param stringCallback
     */
    public static void getLablesData(StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/image_labels")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /**
     * 修改形象标签
     *
     * @param lables
     * @param stringCallback
     */
    public static void updateLableInfo(String lables, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/user_api/upd_image_labels")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("image_label", lables)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /**
     * 保存主播联系方式收费价格
     *
     * @param wechatNum
     * @param wechatPrice
     * @param qqNum
     * @param qqPrice
     * @param phoneNum
     * @param phonePrice
     * @param stringCallback
     */
    public static void saveContactInfo(String wechatNum, String wechatPrice, String qqNum, String qqPrice, String phoneNum, String phonePrice, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/contact_buy_func_plugs_api/save_contact")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())

                .params("wx_number", wechatNum)
                .params("wx_price", Integer.parseInt(wechatPrice))

                .params("qq_number", qqNum)
                .params("qq_price", Integer.parseInt(qqPrice))

                .params("phone_number", phoneNum)
                .params("phone_price", Integer.parseInt(phonePrice))
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /**
     * 获取主播修改联系方式信息接口
     *
     * @param type
     * @param stringCallback
     */
    public static void getContactData(String type, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/contact_buy_func_plugs_api/bind_contact")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())

                .params("type", type)

                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /**
     * |
     * 查询是否购买过主播连续方式接口
     *
     * @param targetUserId
     * @param type
     * @param stringCallback
     */
    public static void getCanShowContactResult(String targetUserId, String type, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/contact_buy_func_plugs_api/select_contact")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("to_user_id", targetUserId)
                .params("type", type)

                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /**
     * 购买主播联系方式
     *
     * @param targetUserId
     * @param type
     * @param stringCallback
     */
    public static void toBuyContact(String targetUserId, String type, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/contact_buy_func_plugs_api/buy_contact")
                .params("uid", SaveData.getInstance().getId())
                .params("token", SaveData.getInstance().getToken())
                .params("to_user_id", targetUserId)
                .params("type", type)

                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /**
     * 获取觅友列表接口
     *
     * @param stringCallback
     */
    public static void getFriendsData(StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/page_data_api/find_friends")
                .params("uid", SaveData.getInstance().getId())
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /**
     * @dw 删除通话记录
     */
    public static void doDeleteVideoCallRecord(String uid, String token, String channel_id, StringCallback stringCallback) {
        OkGo.get(AppConfig.API_DOMAIN + "/video_call_api/del_video_call_record")
                .params("uid", uid)
                .params("token", token)
                .params("channel_id", channel_id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 上传文件
     * */
    public static void doUploadFile(String uid, String token, File file, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/app_api/local_upload")
                .params("uid", uid)
                .params("token", token)
                .params("img", file)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 等级信息
     * */
    public static void getLevel(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_api/get_level_info")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 贵族列表
     * */
    public static void getNobleList(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/shiyi_api/noblelist")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 贵族列表
     * */
    public static void getNoble(String uid, String token, String nid, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/shiyi_api/noble")
                .params("uid", uid)
                .params("nid", nid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 贵族列表
     * */
    public static void CarList(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/shiyi_api/carlist")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 好友/密友/关注/粉丝/我的守护/守护我的/列表
     * */
    public static void UserLists(String uid, String token, String page, String type, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_api/get_user_lists")
                .params("uid", uid)
                .params("token", token)
                .params("type", type)
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 爱情故事
     * */
    public static void LoveList(String uid, String token, String action, String page,
                                StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/bzone_api/get_list_3x")
                .params("uid", uid)
                .params("token", token)
                .params("action", action)
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 获取交友宣言文本
     * */
    public static void getDeclarationText(String uid, String token,
                                          StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_api/get_declaration_text")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 录制交友宣言
     * */
    public static void saveDeclaration(String uid, String token, String duration, String audio_url,
                                       StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_api/save_declaration")
                .params("uid", uid)
                .params("token", token)
                .params("duration", duration)
                .params("audio_url", audio_url)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 一键匹配用户
     * */
    public static void videoCall(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/video_call_api/one_key_video_call_1901017")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 视频约会列表
     * */
    public static void userVideo(String uid, String token, String page, String limit, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_video_api/lists")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .params("limit", limit)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 群聊列表
     * */
    public static void groupList(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/group_api/grouplist")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 增加群成员
     * */
    public static void addGroupMember(String uid, String token, String GroupId, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/group_api/add_group_member")
                .params("uid", uid)
                .params("GroupId", GroupId)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 获取家族列表
     * */
    public static void familyList(String uid, String token, String type, String page, String limit, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/family/lists")
                .params("uid", uid)
                .params("token", token)
                .params("type", type)
                .params("page", page)
                .params("limit", limit)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 创建家族
     * */
    public static void createFamily(String uid, String token, String family_cover, String family_name, String description, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/family/create")
                .params("uid", uid)
                .params("token", token)
                .params("family_cover", family_cover)
                .params("family_name", family_name)
                .params("description", description)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 家族列表
     * */
    public static void getTaskList(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/shiyi_api/tasklist")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 家族成员列表
     * */
    public static void getMemberList(String uid, String token, String family_id, String page, String limit, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/family/get_member_list")
                .params("uid", uid)
                .params("token", token)
                .params("family_id", family_id)
                .params("page", page)
                .params("limit", limit)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 加入家族
     * */
    public static void join(String uid, String token, String family_id, String group_id, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/family/join")
                .params("uid", uid)
                .params("token", token)
                .params("family_id", family_id)
                .params("group_id", group_id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 购买座驾
     * */
    public static void buyMount(String uid, String token, String user_id, String id, String month, String pid, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/shiyi_api/buyMount")
                .params("uid", uid)
                .params("token", token)
                .params("user_id", user_id)
                .params("id", id)
                .params("pid", pid)
                .params("month", month)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 购买贵族
     * */
    public static void buyNobility(String uid, String token, String user_id, String id, String month, String pid, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/shiyi_api/buyNobility")
                .params("uid", uid)
                .params("token", token)
                .params("user_id", user_id)
                .params("id", id)
                .params("pid", pid)
                .params("month", month)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 展示用户金币、钻石余额
     * */
    public static void showUserGoldcoin(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user/show_user_goldcoin")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 金币兑换记录
     * */
    public static void getGoldcoinLog(String uid, String token, String page, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user/get_goldcoin_log")
                .params("uid", uid)
                .params("token", token)
                .params("page", page)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 金币兑换-产品列表
     * */
    public static void getGoldcoinList(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user/get_goldcoin_list")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 金币兑换-操作
     * */
    public static void goldcoinExchange(String uid, String token, String id, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user/goldcoin_exchange")
                .params("uid", uid)
                .params("token", token)
                .params("id", id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 我的座驾
     * */
    public static void mycar(String uid, String token, String user_id, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/shiyi_api/mycar")
                .params("uid", uid)
                .params("token", token)
                .params("user_id", user_id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 骑座驾
     * */
    public static void qicar(String uid, String token, String id, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/shiyi_api/qicar")
                .params("uid", uid)
                .params("token", token)
                .params("id", id)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 消息首页文字描述
     * */
    public static void getMsgDescInfo(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/page_data_api/get_msg_desc_info")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 获取配置信息
     * */
    public static void getSetting(String uid, String token, String group, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user/get_diy_setting")
                .params("uid", uid)
                .params("token", token)
                .params("group", group)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     *
     * */
    public static void setDiySetting(String uid, String token, String group, String charge, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user/set_diy_setting")
                .params("uid", uid)
                .params("token", token)
                .params("group", group)
                .params("charge", charge)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 申请入驻页面
     * */
    public static void applyInfo(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_video_api/apply_info")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 申请入驻
     * */
    public static void apply(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_video_api/apply")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 一键登录
     * */
    public static void jgLogin(String token, String uuid, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/login_api/jg_login")
                .params("token", token)
                .params("uuid", uuid)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }

    /*
     * 注销
     * */
    public static void destroyAccount(String uid, String token, StringCallback stringCallback) {
        OkGo.post(AppConfig.API_DOMAIN + "/user_api/destroy_account")
                .params("uid", uid)
                .params("token", token)
                .cacheMode(CacheMode.DEFAULT)
                .execute(stringCallback);
    }


}

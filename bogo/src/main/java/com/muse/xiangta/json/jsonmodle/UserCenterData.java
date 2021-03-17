package com.muse.xiangta.json.jsonmodle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weipeng on 2018/2/10.
 */

public class UserCenterData implements Serializable {

    /**
     * sex : 2
     * user_nickname : Jiker
     * avatar : http://qiniu.muse12.com/e529e202103011656491701.jpeg
     * coin : 1000019
     * user_status : 2
     * level : 1
     * is_open_do_not_disturb : 0
     * is_open_auto_see_hi : 0
     * is_vip : 0
     * signature :
     * is_auth : 1
     * user_auth_status : 1
     * split : 0
     * attention_fans : 0
     * attention_all : 1
     * friends_all : 15
     * private_friends_all : 13
     * pay_coin : []
     * has_declaration : 0
     * declaration :
     * overlapping_sound :
     * is_president : 0
     */

    private int sex;
    private String user_nickname;
    private String avatar;
    private int coin;
    private int user_status;
    private int level;
    private int is_open_do_not_disturb;
    private int is_open_auto_see_hi;
    private int is_vip;
    private String signature;
    private int is_auth;
    private int user_auth_status;
    private int split;
    private int attention_fans;
    private int attention_all;
    private int friends_all;
    private int private_friends_all;
    private int has_declaration;
    private String declaration;
    private String overlapping_sound;
    private int is_president;
    private List<?> pay_coin;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIs_open_do_not_disturb() {
        return is_open_do_not_disturb;
    }

    public void setIs_open_do_not_disturb(int is_open_do_not_disturb) {
        this.is_open_do_not_disturb = is_open_do_not_disturb;
    }

    public int getIs_open_auto_see_hi() {
        return is_open_auto_see_hi;
    }

    public void setIs_open_auto_see_hi(int is_open_auto_see_hi) {
        this.is_open_auto_see_hi = is_open_auto_see_hi;
    }

    public int getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(int is_vip) {
        this.is_vip = is_vip;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getIs_auth() {
        return is_auth;
    }

    public void setIs_auth(int is_auth) {
        this.is_auth = is_auth;
    }

    public int getUser_auth_status() {
        return user_auth_status;
    }

    public void setUser_auth_status(int user_auth_status) {
        this.user_auth_status = user_auth_status;
    }

    public int getSplit() {
        return split;
    }

    public void setSplit(int split) {
        this.split = split;
    }

    public int getAttention_fans() {
        return attention_fans;
    }

    public void setAttention_fans(int attention_fans) {
        this.attention_fans = attention_fans;
    }

    public int getAttention_all() {
        return attention_all;
    }

    public void setAttention_all(int attention_all) {
        this.attention_all = attention_all;
    }

    public int getFriends_all() {
        return friends_all;
    }

    public void setFriends_all(int friends_all) {
        this.friends_all = friends_all;
    }

    public int getPrivate_friends_all() {
        return private_friends_all;
    }

    public void setPrivate_friends_all(int private_friends_all) {
        this.private_friends_all = private_friends_all;
    }

    public int getHas_declaration() {
        return has_declaration;
    }

    public void setHas_declaration(int has_declaration) {
        this.has_declaration = has_declaration;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getOverlapping_sound() {
        return overlapping_sound;
    }

    public void setOverlapping_sound(String overlapping_sound) {
        this.overlapping_sound = overlapping_sound;
    }

    public int getIs_president() {
        return is_president;
    }

    public void setIs_president(int is_president) {
        this.is_president = is_president;
    }

    public List<?> getPay_coin() {
        return pay_coin;
    }

    public void setPay_coin(List<?> pay_coin) {
        this.pay_coin = pay_coin;
    }
}

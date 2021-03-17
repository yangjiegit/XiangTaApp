package com.muse.xiangta.json;

import java.io.Serializable;
import java.util.List;

public class FamilyBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"family_id":2,"family_name":"ceshijiazu","family_cover":"http://xta.zzmzrj.com/img/1615540208636_1615540196287.jpg","family_level_id":1,"family_people":0,"family_activation":0,"family_description_id":0,"family_active":1,"is_delete":0,"owner_uid":102597,"create_time":"2021-03-12 17:10:13","is_hot":0,"group_id":"@TGS#2YIWJ7AHD","city":"","lat":"0.0","lng":"0.0","is_join":1,"level":{"level_id":1,"level_name":"1级","min_activation":0,"max_activation":100,"max_people":100},"description":{"description_id":2,"description":"1231231231231323","family_id":2},"owner":{"id":102597,"user_type":2,"sex":2,"birthday":0,"last_login_time":1615529318,"score":0,"coin_system":0,"coin":999969,"create_time":"2021-03-01 16:56:12","user_status":2,"user_login":"","user_pass":"","user_nickname":"Jiker","user_email":"","user_url":"","avatar":"http://xta.zzmzrj.com/avatar/1615529903103_1615529855860.jpg","signature":"还未设置个性签名","last_login_ip":"171.8.115.229","user_activation_key":"","mobile":"15137113302","more":null,"token":"005fd7ae441fa4724e8ec668dfbfb016","address":"","level":1,"noble_level":0,"status":1,"teacher":0,"income":1000026,"income_total":1000026,"last_remove_message_time":1615449480,"fabulous":0,"is_reg_perfect":1,"custom_video_charging_coin":0,"is_open_do_not_disturb":0,"login_type":1,"plat_id":"o14DKt1bRJ0dnfd7c5gplsi0cjOY","reference":0,"last_change_name_time":0,"is_use_free_time":0,"is_online":1,"vip_end_time":0,"link_id":"0","invitation_coin":"0.00","sort":0,"is_auth":1,"alipay_account":"","alipay_account_name":"","host_bay_video_proportion":"0","host_bay_phone_proportion":"0","host_bay_gift_proportion":"0","host_one_video_proportion":"0","host_direct_messages":"0","device_uuid":"2830b200bad4df46","host_guardian_proportion":"0","invite_buckle_probability":0,"invite_buckle_recharge_probability":0,"host_turntable_ratio":"0","province":"","city":"","is_open_auto_see_hi":0,"wx_number":"","qq_number":"","phone_number":"","wx_price":0,"qq_price":0,"phone_price":0,"orderno":0,"shielding_time":0,"login_way":3,"lat":"0.0","lng":"0.0","height":"0","weight":"","constellation":"","image_label":"","introduce":"","host_bay_contact_proportion":"","diamond":0,"diamond_system":0,"glamour":0,"glamour_level_id":1,"wealth":5,"wealth_level_id":5,"has_declaration":1,"declaration":"http://xta.zzmzrj.com/audio/1615348420556_ceca9683-6274-4ad1-8bd1-e9fe2c7d69831615348412980.aac","overlapping_sound":"","love_status":1,"declaration_length":5}}]
     */

    private int code;
    private String msg;
    /**
     * family_id : 2
     * family_name : ceshijiazu
     * family_cover : http://xta.zzmzrj.com/img/1615540208636_1615540196287.jpg
     * family_level_id : 1
     * family_people : 0
     * family_activation : 0
     * family_description_id : 0
     * family_active : 1
     * is_delete : 0
     * owner_uid : 102597
     * create_time : 2021-03-12 17:10:13
     * is_hot : 0
     * group_id : @TGS#2YIWJ7AHD
     * city :
     * lat : 0.0
     * lng : 0.0
     * is_join : 1
     * level : {"level_id":1,"level_name":"1级","min_activation":0,"max_activation":100,"max_people":100}
     * description : {"description_id":2,"description":"1231231231231323","family_id":2}
     * owner : {"id":102597,"user_type":2,"sex":2,"birthday":0,"last_login_time":1615529318,"score":0,"coin_system":0,"coin":999969,"create_time":"2021-03-01 16:56:12","user_status":2,"user_login":"","user_pass":"","user_nickname":"Jiker","user_email":"","user_url":"","avatar":"http://xta.zzmzrj.com/avatar/1615529903103_1615529855860.jpg","signature":"还未设置个性签名","last_login_ip":"171.8.115.229","user_activation_key":"","mobile":"15137113302","more":null,"token":"005fd7ae441fa4724e8ec668dfbfb016","address":"","level":1,"noble_level":0,"status":1,"teacher":0,"income":1000026,"income_total":1000026,"last_remove_message_time":1615449480,"fabulous":0,"is_reg_perfect":1,"custom_video_charging_coin":0,"is_open_do_not_disturb":0,"login_type":1,"plat_id":"o14DKt1bRJ0dnfd7c5gplsi0cjOY","reference":0,"last_change_name_time":0,"is_use_free_time":0,"is_online":1,"vip_end_time":0,"link_id":"0","invitation_coin":"0.00","sort":0,"is_auth":1,"alipay_account":"","alipay_account_name":"","host_bay_video_proportion":"0","host_bay_phone_proportion":"0","host_bay_gift_proportion":"0","host_one_video_proportion":"0","host_direct_messages":"0","device_uuid":"2830b200bad4df46","host_guardian_proportion":"0","invite_buckle_probability":0,"invite_buckle_recharge_probability":0,"host_turntable_ratio":"0","province":"","city":"","is_open_auto_see_hi":0,"wx_number":"","qq_number":"","phone_number":"","wx_price":0,"qq_price":0,"phone_price":0,"orderno":0,"shielding_time":0,"login_way":3,"lat":"0.0","lng":"0.0","height":"0","weight":"","constellation":"","image_label":"","introduce":"","host_bay_contact_proportion":"","diamond":0,"diamond_system":0,"glamour":0,"glamour_level_id":1,"wealth":5,"wealth_level_id":5,"has_declaration":1,"declaration":"http://xta.zzmzrj.com/audio/1615348420556_ceca9683-6274-4ad1-8bd1-e9fe2c7d69831615348412980.aac","overlapping_sound":"","love_status":1,"declaration_length":5}
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
        private int family_id;
        private String family_name;
        private String family_cover;
        private int family_level_id;
        private int family_people;
        private int family_activation;
        private int family_description_id;
        private int family_active;
        private int is_delete;
        private int owner_uid;
        private String create_time;
        private int is_hot;
        private String group_id;
        private String city;
        private String lat;
        private String lng;
        private int is_join;
        /**
         * level_id : 1
         * level_name : 1级
         * min_activation : 0
         * max_activation : 100
         * max_people : 100
         */

        private LevelBean level;
        /**
         * description_id : 2
         * description : 1231231231231323
         * family_id : 2
         */

        private DescriptionBean description;
        /**
         * id : 102597
         * user_type : 2
         * sex : 2
         * birthday : 0
         * last_login_time : 1615529318
         * score : 0
         * coin_system : 0
         * coin : 999969
         * create_time : 2021-03-01 16:56:12
         * user_status : 2
         * user_login :
         * user_pass :
         * user_nickname : Jiker
         * user_email :
         * user_url :
         * avatar : http://xta.zzmzrj.com/avatar/1615529903103_1615529855860.jpg
         * signature : 还未设置个性签名
         * last_login_ip : 171.8.115.229
         * user_activation_key :
         * mobile : 15137113302
         * more : null
         * token : 005fd7ae441fa4724e8ec668dfbfb016
         * address :
         * level : 1
         * noble_level : 0
         * status : 1
         * teacher : 0
         * income : 1000026
         * income_total : 1000026
         * last_remove_message_time : 1615449480
         * fabulous : 0
         * is_reg_perfect : 1
         * custom_video_charging_coin : 0
         * is_open_do_not_disturb : 0
         * login_type : 1
         * plat_id : o14DKt1bRJ0dnfd7c5gplsi0cjOY
         * reference : 0
         * last_change_name_time : 0
         * is_use_free_time : 0
         * is_online : 1
         * vip_end_time : 0
         * link_id : 0
         * invitation_coin : 0.00
         * sort : 0
         * is_auth : 1
         * alipay_account :
         * alipay_account_name :
         * host_bay_video_proportion : 0
         * host_bay_phone_proportion : 0
         * host_bay_gift_proportion : 0
         * host_one_video_proportion : 0
         * host_direct_messages : 0
         * device_uuid : 2830b200bad4df46
         * host_guardian_proportion : 0
         * invite_buckle_probability : 0
         * invite_buckle_recharge_probability : 0
         * host_turntable_ratio : 0
         * province :
         * city :
         * is_open_auto_see_hi : 0
         * wx_number :
         * qq_number :
         * phone_number :
         * wx_price : 0
         * qq_price : 0
         * phone_price : 0
         * orderno : 0
         * shielding_time : 0
         * login_way : 3
         * lat : 0.0
         * lng : 0.0
         * height : 0
         * weight :
         * constellation :
         * image_label :
         * introduce :
         * host_bay_contact_proportion :
         * diamond : 0
         * diamond_system : 0
         * glamour : 0
         * glamour_level_id : 1
         * wealth : 5
         * wealth_level_id : 5
         * has_declaration : 1
         * declaration : http://xta.zzmzrj.com/audio/1615348420556_ceca9683-6274-4ad1-8bd1-e9fe2c7d69831615348412980.aac
         * overlapping_sound :
         * love_status : 1
         * declaration_length : 5
         */

        private OwnerBean owner;

        public int getFamily_id() {
            return family_id;
        }

        public void setFamily_id(int family_id) {
            this.family_id = family_id;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }

        public String getFamily_cover() {
            return family_cover;
        }

        public void setFamily_cover(String family_cover) {
            this.family_cover = family_cover;
        }

        public int getFamily_level_id() {
            return family_level_id;
        }

        public void setFamily_level_id(int family_level_id) {
            this.family_level_id = family_level_id;
        }

        public int getFamily_people() {
            return family_people;
        }

        public void setFamily_people(int family_people) {
            this.family_people = family_people;
        }

        public int getFamily_activation() {
            return family_activation;
        }

        public void setFamily_activation(int family_activation) {
            this.family_activation = family_activation;
        }

        public int getFamily_description_id() {
            return family_description_id;
        }

        public void setFamily_description_id(int family_description_id) {
            this.family_description_id = family_description_id;
        }

        public int getFamily_active() {
            return family_active;
        }

        public void setFamily_active(int family_active) {
            this.family_active = family_active;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
        }

        public int getOwner_uid() {
            return owner_uid;
        }

        public void setOwner_uid(int owner_uid) {
            this.owner_uid = owner_uid;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getIs_join() {
            return is_join;
        }

        public void setIs_join(int is_join) {
            this.is_join = is_join;
        }

        public LevelBean getLevel() {
            return level;
        }

        public void setLevel(LevelBean level) {
            this.level = level;
        }

        public DescriptionBean getDescription() {
            return description;
        }

        public void setDescription(DescriptionBean description) {
            this.description = description;
        }

        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
            this.owner = owner;
        }

        public static class LevelBean implements Serializable{
            private int level_id;
            private String level_name;
            private int min_activation;
            private int max_activation;
            private int max_people;

            public int getLevel_id() {
                return level_id;
            }

            public void setLevel_id(int level_id) {
                this.level_id = level_id;
            }

            public String getLevel_name() {
                return level_name;
            }

            public void setLevel_name(String level_name) {
                this.level_name = level_name;
            }

            public int getMin_activation() {
                return min_activation;
            }

            public void setMin_activation(int min_activation) {
                this.min_activation = min_activation;
            }

            public int getMax_activation() {
                return max_activation;
            }

            public void setMax_activation(int max_activation) {
                this.max_activation = max_activation;
            }

            public int getMax_people() {
                return max_people;
            }

            public void setMax_people(int max_people) {
                this.max_people = max_people;
            }
        }

        public static class DescriptionBean implements Serializable{
            private int description_id;
            private String description;
            private int family_id;

            public int getDescription_id() {
                return description_id;
            }

            public void setDescription_id(int description_id) {
                this.description_id = description_id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getFamily_id() {
                return family_id;
            }

            public void setFamily_id(int family_id) {
                this.family_id = family_id;
            }
        }

        public static class OwnerBean implements Serializable{
            private int id;
            private int user_type;
            private int sex;
            private int birthday;
            private int last_login_time;
            private int score;
            private int coin_system;
            private int coin;
            private String create_time;
            private int user_status;
            private String user_login;
            private String user_pass;
            private String user_nickname;
            private String user_email;
            private String user_url;
            private String avatar;
            private String signature;
            private String last_login_ip;
            private String user_activation_key;
            private String mobile;
            private Object more;
            private String token;
            private String address;
            private int level;
            private int noble_level;
            private int status;
            private int teacher;
            private int income;
            private int income_total;
            private int last_remove_message_time;
            private int fabulous;
            private int is_reg_perfect;
            private int custom_video_charging_coin;
            private int is_open_do_not_disturb;
            private int login_type;
            private String plat_id;
            private int reference;
            private int last_change_name_time;
            private int is_use_free_time;
            private int is_online;
            private int vip_end_time;
            private String link_id;
            private String invitation_coin;
            private int sort;
            private int is_auth;
            private String alipay_account;
            private String alipay_account_name;
            private String host_bay_video_proportion;
            private String host_bay_phone_proportion;
            private String host_bay_gift_proportion;
            private String host_one_video_proportion;
            private String host_direct_messages;
            private String device_uuid;
            private String host_guardian_proportion;
            private int invite_buckle_probability;
            private int invite_buckle_recharge_probability;
            private String host_turntable_ratio;
            private String province;
            private String city;
            private int is_open_auto_see_hi;
            private String wx_number;
            private String qq_number;
            private String phone_number;
            private int wx_price;
            private int qq_price;
            private int phone_price;
            private int orderno;
            private int shielding_time;
            private int login_way;
            private String lat;
            private String lng;
            private String height;
            private String weight;
            private String constellation;
            private String image_label;
            private String introduce;
            private String host_bay_contact_proportion;
            private int diamond;
            private int diamond_system;
            private int glamour;
            private int glamour_level_id;
            private int wealth;
            private int wealth_level_id;
            private int has_declaration;
            private String declaration;
            private String overlapping_sound;
            private int love_status;
            private int declaration_length;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }

            public int getLast_login_time() {
                return last_login_time;
            }

            public void setLast_login_time(int last_login_time) {
                this.last_login_time = last_login_time;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getCoin_system() {
                return coin_system;
            }

            public void setCoin_system(int coin_system) {
                this.coin_system = coin_system;
            }

            public int getCoin() {
                return coin;
            }

            public void setCoin(int coin) {
                this.coin = coin;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getUser_status() {
                return user_status;
            }

            public void setUser_status(int user_status) {
                this.user_status = user_status;
            }

            public String getUser_login() {
                return user_login;
            }

            public void setUser_login(String user_login) {
                this.user_login = user_login;
            }

            public String getUser_pass() {
                return user_pass;
            }

            public void setUser_pass(String user_pass) {
                this.user_pass = user_pass;
            }

            public String getUser_nickname() {
                return user_nickname;
            }

            public void setUser_nickname(String user_nickname) {
                this.user_nickname = user_nickname;
            }

            public String getUser_email() {
                return user_email;
            }

            public void setUser_email(String user_email) {
                this.user_email = user_email;
            }

            public String getUser_url() {
                return user_url;
            }

            public void setUser_url(String user_url) {
                this.user_url = user_url;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getLast_login_ip() {
                return last_login_ip;
            }

            public void setLast_login_ip(String last_login_ip) {
                this.last_login_ip = last_login_ip;
            }

            public String getUser_activation_key() {
                return user_activation_key;
            }

            public void setUser_activation_key(String user_activation_key) {
                this.user_activation_key = user_activation_key;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getMore() {
                return more;
            }

            public void setMore(Object more) {
                this.more = more;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getNoble_level() {
                return noble_level;
            }

            public void setNoble_level(int noble_level) {
                this.noble_level = noble_level;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTeacher() {
                return teacher;
            }

            public void setTeacher(int teacher) {
                this.teacher = teacher;
            }

            public int getIncome() {
                return income;
            }

            public void setIncome(int income) {
                this.income = income;
            }

            public int getIncome_total() {
                return income_total;
            }

            public void setIncome_total(int income_total) {
                this.income_total = income_total;
            }

            public int getLast_remove_message_time() {
                return last_remove_message_time;
            }

            public void setLast_remove_message_time(int last_remove_message_time) {
                this.last_remove_message_time = last_remove_message_time;
            }

            public int getFabulous() {
                return fabulous;
            }

            public void setFabulous(int fabulous) {
                this.fabulous = fabulous;
            }

            public int getIs_reg_perfect() {
                return is_reg_perfect;
            }

            public void setIs_reg_perfect(int is_reg_perfect) {
                this.is_reg_perfect = is_reg_perfect;
            }

            public int getCustom_video_charging_coin() {
                return custom_video_charging_coin;
            }

            public void setCustom_video_charging_coin(int custom_video_charging_coin) {
                this.custom_video_charging_coin = custom_video_charging_coin;
            }

            public int getIs_open_do_not_disturb() {
                return is_open_do_not_disturb;
            }

            public void setIs_open_do_not_disturb(int is_open_do_not_disturb) {
                this.is_open_do_not_disturb = is_open_do_not_disturb;
            }

            public int getLogin_type() {
                return login_type;
            }

            public void setLogin_type(int login_type) {
                this.login_type = login_type;
            }

            public String getPlat_id() {
                return plat_id;
            }

            public void setPlat_id(String plat_id) {
                this.plat_id = plat_id;
            }

            public int getReference() {
                return reference;
            }

            public void setReference(int reference) {
                this.reference = reference;
            }

            public int getLast_change_name_time() {
                return last_change_name_time;
            }

            public void setLast_change_name_time(int last_change_name_time) {
                this.last_change_name_time = last_change_name_time;
            }

            public int getIs_use_free_time() {
                return is_use_free_time;
            }

            public void setIs_use_free_time(int is_use_free_time) {
                this.is_use_free_time = is_use_free_time;
            }

            public int getIs_online() {
                return is_online;
            }

            public void setIs_online(int is_online) {
                this.is_online = is_online;
            }

            public int getVip_end_time() {
                return vip_end_time;
            }

            public void setVip_end_time(int vip_end_time) {
                this.vip_end_time = vip_end_time;
            }

            public String getLink_id() {
                return link_id;
            }

            public void setLink_id(String link_id) {
                this.link_id = link_id;
            }

            public String getInvitation_coin() {
                return invitation_coin;
            }

            public void setInvitation_coin(String invitation_coin) {
                this.invitation_coin = invitation_coin;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getIs_auth() {
                return is_auth;
            }

            public void setIs_auth(int is_auth) {
                this.is_auth = is_auth;
            }

            public String getAlipay_account() {
                return alipay_account;
            }

            public void setAlipay_account(String alipay_account) {
                this.alipay_account = alipay_account;
            }

            public String getAlipay_account_name() {
                return alipay_account_name;
            }

            public void setAlipay_account_name(String alipay_account_name) {
                this.alipay_account_name = alipay_account_name;
            }

            public String getHost_bay_video_proportion() {
                return host_bay_video_proportion;
            }

            public void setHost_bay_video_proportion(String host_bay_video_proportion) {
                this.host_bay_video_proportion = host_bay_video_proportion;
            }

            public String getHost_bay_phone_proportion() {
                return host_bay_phone_proportion;
            }

            public void setHost_bay_phone_proportion(String host_bay_phone_proportion) {
                this.host_bay_phone_proportion = host_bay_phone_proportion;
            }

            public String getHost_bay_gift_proportion() {
                return host_bay_gift_proportion;
            }

            public void setHost_bay_gift_proportion(String host_bay_gift_proportion) {
                this.host_bay_gift_proportion = host_bay_gift_proportion;
            }

            public String getHost_one_video_proportion() {
                return host_one_video_proportion;
            }

            public void setHost_one_video_proportion(String host_one_video_proportion) {
                this.host_one_video_proportion = host_one_video_proportion;
            }

            public String getHost_direct_messages() {
                return host_direct_messages;
            }

            public void setHost_direct_messages(String host_direct_messages) {
                this.host_direct_messages = host_direct_messages;
            }

            public String getDevice_uuid() {
                return device_uuid;
            }

            public void setDevice_uuid(String device_uuid) {
                this.device_uuid = device_uuid;
            }

            public String getHost_guardian_proportion() {
                return host_guardian_proportion;
            }

            public void setHost_guardian_proportion(String host_guardian_proportion) {
                this.host_guardian_proportion = host_guardian_proportion;
            }

            public int getInvite_buckle_probability() {
                return invite_buckle_probability;
            }

            public void setInvite_buckle_probability(int invite_buckle_probability) {
                this.invite_buckle_probability = invite_buckle_probability;
            }

            public int getInvite_buckle_recharge_probability() {
                return invite_buckle_recharge_probability;
            }

            public void setInvite_buckle_recharge_probability(int invite_buckle_recharge_probability) {
                this.invite_buckle_recharge_probability = invite_buckle_recharge_probability;
            }

            public String getHost_turntable_ratio() {
                return host_turntable_ratio;
            }

            public void setHost_turntable_ratio(String host_turntable_ratio) {
                this.host_turntable_ratio = host_turntable_ratio;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getIs_open_auto_see_hi() {
                return is_open_auto_see_hi;
            }

            public void setIs_open_auto_see_hi(int is_open_auto_see_hi) {
                this.is_open_auto_see_hi = is_open_auto_see_hi;
            }

            public String getWx_number() {
                return wx_number;
            }

            public void setWx_number(String wx_number) {
                this.wx_number = wx_number;
            }

            public String getQq_number() {
                return qq_number;
            }

            public void setQq_number(String qq_number) {
                this.qq_number = qq_number;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public int getWx_price() {
                return wx_price;
            }

            public void setWx_price(int wx_price) {
                this.wx_price = wx_price;
            }

            public int getQq_price() {
                return qq_price;
            }

            public void setQq_price(int qq_price) {
                this.qq_price = qq_price;
            }

            public int getPhone_price() {
                return phone_price;
            }

            public void setPhone_price(int phone_price) {
                this.phone_price = phone_price;
            }

            public int getOrderno() {
                return orderno;
            }

            public void setOrderno(int orderno) {
                this.orderno = orderno;
            }

            public int getShielding_time() {
                return shielding_time;
            }

            public void setShielding_time(int shielding_time) {
                this.shielding_time = shielding_time;
            }

            public int getLogin_way() {
                return login_way;
            }

            public void setLogin_way(int login_way) {
                this.login_way = login_way;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getConstellation() {
                return constellation;
            }

            public void setConstellation(String constellation) {
                this.constellation = constellation;
            }

            public String getImage_label() {
                return image_label;
            }

            public void setImage_label(String image_label) {
                this.image_label = image_label;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getHost_bay_contact_proportion() {
                return host_bay_contact_proportion;
            }

            public void setHost_bay_contact_proportion(String host_bay_contact_proportion) {
                this.host_bay_contact_proportion = host_bay_contact_proportion;
            }

            public int getDiamond() {
                return diamond;
            }

            public void setDiamond(int diamond) {
                this.diamond = diamond;
            }

            public int getDiamond_system() {
                return diamond_system;
            }

            public void setDiamond_system(int diamond_system) {
                this.diamond_system = diamond_system;
            }

            public int getGlamour() {
                return glamour;
            }

            public void setGlamour(int glamour) {
                this.glamour = glamour;
            }

            public int getGlamour_level_id() {
                return glamour_level_id;
            }

            public void setGlamour_level_id(int glamour_level_id) {
                this.glamour_level_id = glamour_level_id;
            }

            public int getWealth() {
                return wealth;
            }

            public void setWealth(int wealth) {
                this.wealth = wealth;
            }

            public int getWealth_level_id() {
                return wealth_level_id;
            }

            public void setWealth_level_id(int wealth_level_id) {
                this.wealth_level_id = wealth_level_id;
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

            public int getLove_status() {
                return love_status;
            }

            public void setLove_status(int love_status) {
                this.love_status = love_status;
            }

            public int getDeclaration_length() {
                return declaration_length;
            }

            public void setDeclaration_length(int declaration_length) {
                this.declaration_length = declaration_length;
            }
        }
    }
}

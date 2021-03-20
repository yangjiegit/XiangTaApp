package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class MemberItemBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"id":10,"uid":102604,"family_id":5,"is_delete":0,"create_time":"2021-03-16 10:22:27","user":{"id":102604,"user_type":2,"sex":2,"birthday":0,"last_login_time":1615859467,"score":0,"coin_system":0,"coin":0,"create_time":"2021-03-08 16:29:47","user_status":2,"user_login":"","user_pass":"","user_nickname":"帅哥2","user_email":"","user_url":"","avatar":"http://xta.zzmzrj.com/idImage/1615360951953.png","signature":"","last_login_ip":"171.8.114.45","user_activation_key":"","mobile":"15137830553","more":null,"token":"c8869250fb43feee585d13c8a030c778","address":"郑州市","level":1,"noble_level":0,"status":1,"teacher":0,"income":0,"income_total":0,"last_remove_message_time":1615859560,"fabulous":0,"is_reg_perfect":1,"custom_video_charging_coin":15,"is_open_do_not_disturb":0,"login_type":0,"plat_id":"","reference":0,"last_change_name_time":0,"is_use_free_time":0,"is_online":0,"vip_end_time":0,"link_id":"0","invitation_coin":"0.00","alipay_account":"","sort":0,"is_auth":1,"alipay_account_name":"","host_bay_video_proportion":"0","host_bay_phone_proportion":"0","host_bay_gift_proportion":"0","host_one_video_proportion":"0","host_direct_messages":"0","device_uuid":"3C66D59A-58B4-41F8-A459-CC809B9EE020","host_guardian_proportion":"0","invite_buckle_probability":0,"invite_buckle_recharge_probability":0,"host_turntable_ratio":"0","province":"","city":"郑州市","is_open_auto_see_hi":0,"wx_number":"","qq_number":"","phone_number":"","wx_price":0,"qq_price":0,"phone_price":0,"orderno":0,"shielding_time":0,"login_way":1,"lat":"34.785970","lng":"113.685911","height":"0","weight":"","constellation":"","image_label":"","introduce":"","host_bay_contact_proportion":"","diamond":0,"diamond_system":0,"glamour":0,"glamour_level_id":5,"wealth":5,"wealth_level_id":1,"has_declaration":1,"declaration":"http://xta.zzmzrj.com/idIosAudio/1615360349342.wav","overlapping_sound":"","love_status":"1","declaration_length":0,"education":"","occupation":"","is_settled":1,"noble":"","age":18}}]
     */

    private int code;
    private String msg;
    /**
     * id : 10
     * uid : 102604
     * family_id : 5
     * is_delete : 0
     * create_time : 2021-03-16 10:22:27
     * user : {"id":102604,"user_type":2,"sex":2,"birthday":0,"last_login_time":1615859467,"score":0,"coin_system":0,"coin":0,"create_time":"2021-03-08 16:29:47","user_status":2,"user_login":"","user_pass":"","user_nickname":"帅哥2","user_email":"","user_url":"","avatar":"http://xta.zzmzrj.com/idImage/1615360951953.png","signature":"","last_login_ip":"171.8.114.45","user_activation_key":"","mobile":"15137830553","more":null,"token":"c8869250fb43feee585d13c8a030c778","address":"郑州市","level":1,"noble_level":0,"status":1,"teacher":0,"income":0,"income_total":0,"last_remove_message_time":1615859560,"fabulous":0,"is_reg_perfect":1,"custom_video_charging_coin":15,"is_open_do_not_disturb":0,"login_type":0,"plat_id":"","reference":0,"last_change_name_time":0,"is_use_free_time":0,"is_online":0,"vip_end_time":0,"link_id":"0","invitation_coin":"0.00","alipay_account":"","sort":0,"is_auth":1,"alipay_account_name":"","host_bay_video_proportion":"0","host_bay_phone_proportion":"0","host_bay_gift_proportion":"0","host_one_video_proportion":"0","host_direct_messages":"0","device_uuid":"3C66D59A-58B4-41F8-A459-CC809B9EE020","host_guardian_proportion":"0","invite_buckle_probability":0,"invite_buckle_recharge_probability":0,"host_turntable_ratio":"0","province":"","city":"郑州市","is_open_auto_see_hi":0,"wx_number":"","qq_number":"","phone_number":"","wx_price":0,"qq_price":0,"phone_price":0,"orderno":0,"shielding_time":0,"login_way":1,"lat":"34.785970","lng":"113.685911","height":"0","weight":"","constellation":"","image_label":"","introduce":"","host_bay_contact_proportion":"","diamond":0,"diamond_system":0,"glamour":0,"glamour_level_id":5,"wealth":5,"wealth_level_id":1,"has_declaration":1,"declaration":"http://xta.zzmzrj.com/idIosAudio/1615360349342.wav","overlapping_sound":"","love_status":"1","declaration_length":0,"education":"","occupation":"","is_settled":1,"noble":"","age":18}
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
        private int id;
        private int uid;
        private int family_id;
        private int is_delete;
        private String create_time;
        /**
         * id : 102604
         * user_type : 2
         * sex : 2
         * birthday : 0
         * last_login_time : 1615859467
         * score : 0
         * coin_system : 0
         * coin : 0
         * create_time : 2021-03-08 16:29:47
         * user_status : 2
         * user_login :
         * user_pass :
         * user_nickname : 帅哥2
         * user_email :
         * user_url :
         * avatar : http://xta.zzmzrj.com/idImage/1615360951953.png
         * signature :
         * last_login_ip : 171.8.114.45
         * user_activation_key :
         * mobile : 15137830553
         * more : null
         * token : c8869250fb43feee585d13c8a030c778
         * address : 郑州市
         * level : 1
         * noble_level : 0
         * status : 1
         * teacher : 0
         * income : 0
         * income_total : 0
         * last_remove_message_time : 1615859560
         * fabulous : 0
         * is_reg_perfect : 1
         * custom_video_charging_coin : 15
         * is_open_do_not_disturb : 0
         * login_type : 0
         * plat_id :
         * reference : 0
         * last_change_name_time : 0
         * is_use_free_time : 0
         * is_online : 0
         * vip_end_time : 0
         * link_id : 0
         * invitation_coin : 0.00
         * alipay_account :
         * sort : 0
         * is_auth : 1
         * alipay_account_name :
         * host_bay_video_proportion : 0
         * host_bay_phone_proportion : 0
         * host_bay_gift_proportion : 0
         * host_one_video_proportion : 0
         * host_direct_messages : 0
         * device_uuid : 3C66D59A-58B4-41F8-A459-CC809B9EE020
         * host_guardian_proportion : 0
         * invite_buckle_probability : 0
         * invite_buckle_recharge_probability : 0
         * host_turntable_ratio : 0
         * province :
         * city : 郑州市
         * is_open_auto_see_hi : 0
         * wx_number :
         * qq_number :
         * phone_number :
         * wx_price : 0
         * qq_price : 0
         * phone_price : 0
         * orderno : 0
         * shielding_time : 0
         * login_way : 1
         * lat : 34.785970
         * lng : 113.685911
         * height : 0
         * weight :
         * constellation :
         * image_label :
         * introduce :
         * host_bay_contact_proportion :
         * diamond : 0
         * diamond_system : 0
         * glamour : 0
         * glamour_level_id : 5
         * wealth : 5
         * wealth_level_id : 1
         * has_declaration : 1
         * declaration : http://xta.zzmzrj.com/idIosAudio/1615360349342.wav
         * overlapping_sound :
         * love_status : 1
         * declaration_length : 0
         * education :
         * occupation :
         * is_settled : 1
         * noble :
         * age : 18
         */

        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getFamily_id() {
            return family_id;
        }

        public void setFamily_id(int family_id) {
            this.family_id = family_id;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Serializable{
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
            private String alipay_account;
            private int sort;
            private int is_auth;
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
            private String love_status;
            private int declaration_length;
            private String education;
            private String occupation;
            private int is_settled;
            private String noble;
            private int age;

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

            public String getAlipay_account() {
                return alipay_account;
            }

            public void setAlipay_account(String alipay_account) {
                this.alipay_account = alipay_account;
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

            public String getLove_status() {
                return love_status;
            }

            public void setLove_status(String love_status) {
                this.love_status = love_status;
            }

            public int getDeclaration_length() {
                return declaration_length;
            }

            public void setDeclaration_length(int declaration_length) {
                this.declaration_length = declaration_length;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public int getIs_settled() {
                return is_settled;
            }

            public void setIs_settled(int is_settled) {
                this.is_settled = is_settled;
            }

            public String getNoble() {
                return noble;
            }

            public void setNoble(String noble) {
                this.noble = noble;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }
    }
}

package com.muse.chat.model;

import java.io.Serializable;
import java.util.List;

public class HelpIndexBean implements Serializable {


    /**
     * code : 1
     * msg : 获取成功
     * data : {"gongyue":{"title":"绿色公约","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/29.html"},"help":[{"title":"常见问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/19.html"},{"title":"真人认证","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/20.html"},{"title":"账号问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/21.html"},{"title":"金币问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/22.html"},{"title":"钻石问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/23.html"},{"title":"更多问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/24.html"}],"hot_help":[{"title":"1、什么是亲密度？","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/25.html"},{"title":"2、如何成为对方守护？","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/26.html"},{"title":"3、为什么没有人回复我","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/27.html"},{"title":"4、还有其他疑惑或者建议，怎么办？","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/28.html"}]}
     */

    private int code;
    private String msg;
    /**
     * gongyue : {"title":"绿色公约","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/29.html"}
     * help : [{"title":"常见问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/19.html"},{"title":"真人认证","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/20.html"},{"title":"账号问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/21.html"},{"title":"金币问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/22.html"},{"title":"钻石问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/23.html"},{"title":"更多问题","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/24.html"}]
     * hot_help : [{"title":"1、什么是亲密度？","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/25.html"},{"title":"2、如何成为对方守护？","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/26.html"},{"title":"3、为什么没有人回复我","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/27.html"},{"title":"4、还有其他疑惑或者建议，怎么办？","url":"http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/28.html"}]
     */

    private DataBean data;

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

    public static class DataBean implements Serializable {
        /**
         * title : 绿色公约
         * url : http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/29.html
         */

        private GongyueBean gongyue;
        /**
         * title : 常见问题
         * url : http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/19.html
         */

        private List<HelpBean> help;
        /**
         * title : 1、什么是亲密度？
         * url : http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/25.html
         */

        private List<HotHelpBean> hot_help;

        public GongyueBean getGongyue() {
            return gongyue;
        }

        public void setGongyue(GongyueBean gongyue) {
            this.gongyue = gongyue;
        }

        public List<HelpBean> getHelp() {
            return help;
        }

        public void setHelp(List<HelpBean> help) {
            this.help = help;
        }

        public List<HotHelpBean> getHot_help() {
            return hot_help;
        }

        public void setHot_help(List<HotHelpBean> hot_help) {
            this.hot_help = hot_help;
        }

        public static class GongyueBean implements Serializable {
            private String title;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class HelpBean implements Serializable {
            private String title;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class HotHelpBean implements Serializable {
            private String title;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}

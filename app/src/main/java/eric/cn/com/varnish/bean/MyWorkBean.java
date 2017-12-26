package eric.cn.com.varnish.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class MyWorkBean {

    /**
     * line1 : {"name":"1号线","bus_number":"辽A-00001","user_phone":"","user_name":"","site":[{"name":"中海寰宇","time":"05:00","type":"1"},{"name":"小白楼","time":"05:05","type":"0"},{"name":"共和","time":"05:10","type":"0"},{"name":"工厂","time":"07:00","type":"2"}],"end_site":"工厂","end_time":"07:00","begin_site":"小白楼","begin_time":"05:05","time":"1514217600","classes":"A勤","no":"1"}
     * line2 : {"name":"2号线","bus_number":"辽A-00004","user_phone":"","user_name":"","site":[{"name":"工厂","time":"","type":"2"},{"name":"望花街","time":"","type":"0"},{"name":"小白楼","time":"","type":"0"},{"name":"中海寰宇","time":"","type":"1"},{"name":"共和","time":"","type":"0"}],"end_site":"共和","end_time":"","begin_site":"小白楼","begin_time":"","time":"1514217600","classes":"A勤","no":"1"}
     * error : 0
     * returl :
     * msg :
     */

    private Line1Bean line1;
    private Line2Bean line2;
    private int error;
    private String returl;
    private String msg;

    public Line1Bean getLine1() {
        return line1;
    }

    public void setLine1(Line1Bean line1) {
        this.line1 = line1;
    }

    public Line2Bean getLine2() {
        return line2;
    }

    public void setLine2(Line2Bean line2) {
        this.line2 = line2;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getReturl() {
        return returl;
    }

    public void setReturl(String returl) {
        this.returl = returl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Line1Bean {
        /**
         * name : 1号线
         * bus_number : 辽A-00001
         * user_phone :
         * user_name :
         * site : [{"name":"中海寰宇","time":"05:00","type":"1"},{"name":"小白楼","time":"05:05","type":"0"},{"name":"共和","time":"05:10","type":"0"},{"name":"工厂","time":"07:00","type":"2"}]
         * end_site : 工厂
         * end_time : 07:00
         * begin_site : 小白楼
         * begin_time : 05:05
         * time : 1514217600
         * classes : A勤
         * no : 1
         */

        private String name;
        private String bus_number;
        private String user_phone;
        private String user_name;
        private String end_site;
        private String end_time;
        private String begin_site;
        private String begin_time;
        private String time;
        private String classes;
        private String no;
        private List<SiteBean> site;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBus_number() {
            return bus_number;
        }

        public void setBus_number(String bus_number) {
            this.bus_number = bus_number;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getEnd_site() {
            return end_site;
        }

        public void setEnd_site(String end_site) {
            this.end_site = end_site;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getBegin_site() {
            return begin_site;
        }

        public void setBegin_site(String begin_site) {
            this.begin_site = begin_site;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public List<SiteBean> getSite() {
            return site;
        }

        public void setSite(List<SiteBean> site) {
            this.site = site;
        }

        public static class SiteBean {
            /**
             * name : 中海寰宇
             * time : 05:00
             * type : 1
             */

            private String name;
            private String time;
            private String type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

    public static class Line2Bean {
        /**
         * name : 2号线
         * bus_number : 辽A-00004
         * user_phone :
         * user_name :
         * site : [{"name":"工厂","time":"","type":"2"},{"name":"望花街","time":"","type":"0"},{"name":"小白楼","time":"","type":"0"},{"name":"中海寰宇","time":"","type":"1"},{"name":"共和","time":"","type":"0"}]
         * end_site : 共和
         * end_time :
         * begin_site : 小白楼
         * begin_time :
         * time : 1514217600
         * classes : A勤
         * no : 1
         */

        private String name;
        private String bus_number;
        private String user_phone;
        private String user_name;
        private String end_site;
        private String end_time;
        private String begin_site;
        private String begin_time;
        private String time;
        private String classes;
        private String no;
        private List<SiteBeanX> site;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBus_number() {
            return bus_number;
        }

        public void setBus_number(String bus_number) {
            this.bus_number = bus_number;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getEnd_site() {
            return end_site;
        }

        public void setEnd_site(String end_site) {
            this.end_site = end_site;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getBegin_site() {
            return begin_site;
        }

        public void setBegin_site(String begin_site) {
            this.begin_site = begin_site;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public List<SiteBeanX> getSite() {
            return site;
        }

        public void setSite(List<SiteBeanX> site) {
            this.site = site;
        }

        public static class SiteBeanX {
            /**
             * name : 工厂
             * time :
             * type : 2
             */

            private String name;
            private String time;
            private String type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}

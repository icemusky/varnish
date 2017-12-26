package eric.cn.com.varnish.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/25.
 */

public class MyCatBean {

    /**
     * week1 : {"list":[{"name":"1号线","bus_number":"辽A-00001","user_phone":"","user_name":"","end_site":"工厂","end_time":"07:00","begin_site":"小白楼","begin_time":"05:05","time":"1514217600","classes":"A勤","no":"1"}],"open":"1","submited":1}
     * week2 : {"list":[],"open":1,"submited":0}
     * error : 0
     * returl :
     * msg :
     */

    private Week1Bean week1;
    private Week2Bean week2;
    private int error;
    private String returl;
    private String msg;

    public Week1Bean getWeek1() {
        return week1;
    }

    public void setWeek1(Week1Bean week1) {
        this.week1 = week1;
    }

    public Week2Bean getWeek2() {
        return week2;
    }

    public void setWeek2(Week2Bean week2) {
        this.week2 = week2;
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

    public static class Week1Bean {
        /**
         * list : [{"name":"1号线","bus_number":"辽A-00001","user_phone":"","user_name":"","end_site":"工厂","end_time":"07:00","begin_site":"小白楼","begin_time":"05:05","time":"1514217600","classes":"A勤","no":"1"}]
         * open : 1
         * submited : 1
         */

        private String open;
        private int submited;
        private List<ListBean> list;

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public int getSubmited() {
            return submited;
        }

        public void setSubmited(int submited) {
            this.submited = submited;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * name : 1号线
             * bus_number : 辽A-00001
             * user_phone :
             * user_name :
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
        }
    }

    public static class Week2Bean {
        /**
         * list : []
         * open : 1
         * submited : 0
         */

        private int open;
        private int submited;
        private List<ListBean> list;

        public int getOpen() {
            return open;
        }

        public void setOpen(int open) {
            this.open = open;
        }

        public int getSubmited() {
            return submited;
        }

        public void setSubmited(int submited) {
            this.submited = submited;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
        public static class ListBean {
            /**
             * name : 1号线
             * bus_number : 辽A-00001
             * user_phone :
             * user_name :
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
        }
    }
}

package eric.cn.com.varnish.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class VarnishApplyBean {

    /**
     * list : [{"time":1512748800,"classes_id":0},{"time":1512835200,"classes_id":0},{"time":1512921600,"classes_id":0},{"time":1513008000,"classes_id":0},{"time":1513094400,"classes_id":0},{"time":1513180800,"classes_id":0},{"time":1513267200,"classes_id":0}]
     * open : 0
     * error : 0
     * returl :
     * msg :
     */

    private String open;
    private int error;
    private String returl;
    private String msg;
    private List<ListBean> list;

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * time : 1512748800
         * classes_id : 0
         */

        private int time;
        private int classes_id;

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getClasses_id() {
            return classes_id;
        }

        public void setClasses_id(int classes_id) {
            this.classes_id = classes_id;
        }
    }
}

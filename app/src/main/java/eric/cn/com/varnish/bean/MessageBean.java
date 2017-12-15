package eric.cn.com.varnish.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class MessageBean {

    /**
     * list : [{"id":"3","title":"测试数据3"},{"id":"2","title":"测试数据2"},{"id":"1","title":"测试数据1"}]
     * error : 0
     * returl :
     * msg :
     */

    private int error;
    private String returl;
    private String msg;
    private List<ListBean> list;

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
         * id : 3
         * title : 测试数据3
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

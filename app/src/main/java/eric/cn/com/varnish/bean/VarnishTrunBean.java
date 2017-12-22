package eric.cn.com.varnish.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class VarnishTrunBean {

    /**
     * list : [{"id":"2","name":"A勤"},{"id":"3","name":"B勤"},{"id":"4","name":"C勤"},{"id":"5","name":"D勤"},{"id":"6","name":"E勤"}]
     * msg :
     * error : 0
     */

    private String msg;
    private int error;
    private List<ListBean> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 2
         * name : A勤
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

package eric.cn.com.varnish.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class VarnishUPDownBean {

    /**
     * list : [{"name":"于洪区","list":[{"id":"1","name":"第一粮库"},{"id":"8","name":"航空航天大学"}]},{"name":"沈北新区","list":[]},{"name":"浑南区","list":[{"id":"3","name":"小白楼"},{"id":"11","name":"北陵东门"},{"id":"12","name":"望花街"}]},{"name":"苏家屯区","list":[{"id":"4","name":"房产局"},{"id":"6","name":"三台子地铁口"},{"id":"9","name":"共和"}]},{"name":"铁西区","list":[{"id":"5","name":"碧潭公园"},{"id":"10","name":"怒江街（观音庙）"}]}]
     * msg :
     * error : 0
     */

    private String msg;
    private int error;
    private List<ListBeanX> list;

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

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * name : 于洪区
         * list : [{"id":"1","name":"第一粮库"},{"id":"8","name":"航空航天大学"}]
         */

        private String name;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * name : 第一粮库
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
}

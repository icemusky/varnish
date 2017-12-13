package eric.cn.com.varnish.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13.
 */

public class DepartmentBean {

    /**
     * list : [{"id":"12","name":"品质保证组"},{"id":"13","name":"IT组"},{"id":"14","name":"财务部"},{"id":"15","name":"人事总务部"}]
     * error : 0
     */

    private int error;
    private List<ListBean> list;

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
         * id : 12
         * name : 品质保证组
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

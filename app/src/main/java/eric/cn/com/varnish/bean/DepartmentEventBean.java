package eric.cn.com.varnish.bean;

/**
 * Created by Administrator on 2017/12/13.
 */

public class DepartmentEventBean {
    private String name;
    private String id;

    public DepartmentEventBean(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

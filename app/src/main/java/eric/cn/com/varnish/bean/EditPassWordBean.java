package eric.cn.com.varnish.bean;

/**
 * Created by Administrator on 2017/12/14.
 */

public class EditPassWordBean {

    /**
     * error : -2
     * returl :
     * msg : 登录已过期
     */

    private int error;
    private String returl;
    private String msg;

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
}

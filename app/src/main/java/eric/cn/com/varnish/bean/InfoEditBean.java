package eric.cn.com.varnish.bean;

/**
 * Created by Eric on 2017/12/16.
 */

public class InfoEditBean {

    /**
     * error : 0
     * returl :
     * msg :
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

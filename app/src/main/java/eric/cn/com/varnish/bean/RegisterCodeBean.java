package eric.cn.com.varnish.bean;

/**
 * Created by Administrator on 2017/12/13.
 */

public class RegisterCodeBean {

    /**
     * code : 722349
     * delay : 10
     * id : 17
     * error : 0
     * returl :
     */

    private String code;
    private String delay;
    private int id;
    private int error;
    private String returl;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

package eric.cn.com.varnish.bean;

/**
 * Created by Administrator on 2017/12/13.
 */

public class LgoinBean {

    /**
     * token : 1cc6594e4f9c82bd4202352fc5cf888691fc67a4b50c90f94c5477bd683b729f
     * expire : 1513162379
     * error : 0
     * returl :
     */

    private String token;
    private int expire;
    private int error;
    private String returl;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
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

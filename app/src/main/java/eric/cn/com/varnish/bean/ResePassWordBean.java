package eric.cn.com.varnish.bean;

/**
 * Created by Administrator on 2017/12/14.
 */

public class ResePassWordBean {

    /**
     * token : 66b9ec9614b654b3375a10fc8c81a8369be6e58bda969cf8212c07a81e7c9604
     * expire : 1513251389
     * error : 0
     * returl :
     * msg :
     */

    private String token;
    private int expire;
    private int error;
    private String returl;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package eric.cn.com.varnish.bean;

/**
 * Created by Administrator on 2017/12/13.
 */

public class RegisterBean {

    /**
     * token : 252d58b333a4dd3d7d4736e02440c350d357ea50bafeccaa505bc760311fcd6d
     * expire : 1513159842
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

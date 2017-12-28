package eric.cn.com.varnish.bean;

/**
 * Created by Administrator on 2017/12/28.
 */

public class UpDataBean {

    /**
     * version : 1.0
     * versionCode : 1
     * url : /www/upload/site/2017/1228/20171228084254871487.apk
     * msg :
     * error : 0
     */

    private String version;
    private String versionCode;
    private String url;
    private String msg;
    private int error;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
}

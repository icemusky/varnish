package eric.cn.com.varnish.http;

/**
 * Created by Administrator on 2017/12/12.
 */

public class RequestURL {
    //测试接口地址
    public static String URL = "https://tongqinche.lnanfang.com";
    //发送手机验证码
    public static String smscode = URL + "/api/app/smscode";
    //查看部门
    public static String department = URL + "/api/app/department";
    //用户注册
    public static String reg = URL + "/api/app/reg";
    //用户登录
    public static String login = URL + "/api/app/login";
    //密码找回
    public static String forget = URL + "/api/app/forget";
    //修改密码
    public static String password = URL + "/api/app/user/password";
}
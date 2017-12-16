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
    //获取用户消息列表
    public static String message = URL + "/api/app/user/message";
    //获取用户消息内容
    public static String message_detail = URL + "/api/app/user/message_detail";
    //获取用户可选的上下车站点列表
    public static String site = URL + "/api/app/site";
    //上下车地点变更申请
    public static String user_site = URL + "/api/app/user/site";
    //获取用户信息
    public static String info = URL + "/api/app/user/info";
    //修改个人信息
    public static String edit_info = URL + "/api/app/user/edit_info";
    //修改手机
    public static String phone = URL + "/api/app/user/phone";
}

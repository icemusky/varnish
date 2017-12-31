package eric.cn.com.varnish;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/11/29.
 */

public class MyApplication extends Application {
    public static String API = "AWeBhlUB7AW1HCIruRSlnWNnqgL6KeWQ";
    public static Context mContext;
    public static String TOKEN = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        x.Ext.init(this);
        x.Ext.setDebug(false);

        JPushInterface.setDebugMode(true);//极光推送
        JPushInterface.init(this);

    }
}
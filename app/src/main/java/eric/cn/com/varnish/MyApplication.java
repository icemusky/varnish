package eric.cn.com.varnish;

import android.app.Application;
import android.content.Context;

import org.xutils.x;



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

    }
}
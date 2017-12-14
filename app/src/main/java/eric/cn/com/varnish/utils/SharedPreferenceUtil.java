package eric.cn.com.varnish.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zgy
 * on 2016/11/20.
 * 轻量级存储
 */

public class SharedPreferenceUtil {

    /**
     * 保存int类型数据
     */
    public static void createPrefereceInt(String fileName, Context context, String key, int value) {

        SharedPreferences prefs = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    /**
     * 取int
     */
    public static int getPrefereceFileKeyValueInt(String fileName, Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        int value = prefs.getInt(key, 0);
        return value;
    }


    /**
     * 保存value到文件
     *
     * @param fileName 文件名字
     * @param context  上下文
     * @param key      保存key
     * @param value    对应value
     */
    public static void createPrefereceFile(String fileName, Context context, String key, String value) {

        SharedPreferences prefs = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(key, value);
        edit.commit();
    }

    /**
     * 获取文件中的value
     *
     * @param fileName 文件名
     * @param context  上下文
     * @param key      对应key
     * @return value
     */
    public static String getPrefereceFileKeyValue(String fileName, Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        String value = prefs.getString(key, "");
        return value;
    }

    public void clearData(String fileName, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        prefs.edit().clear().commit();
    }
}

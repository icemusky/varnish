package eric.cn.com.varnish.utils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/9/14.
 */

public class DateUtils {
    public static String getTodayDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }
    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat   sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }
    /**
     * 将时间戳转成日期字符串
     * @param timeStamp 时间戳的值,类型为：Long
     * @param pattern 转成字符串的格式
     * @return
     */
    public static String getDateStringByTimeSTamp(Long timeStamp,String pattern){
        String result = null;
        Date date = new Date(timeStamp*1000);
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        result = sd.format(date);
        return result;
    }
}

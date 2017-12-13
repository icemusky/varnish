package eric.cn.com.varnish.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**  网络请求加密格式
 * Created by Administrator on 2017/12/12.
 */

public class HttpPost {
    private static String str="";

    /**
     * 将字符串 转换为 sha256格式
     * @param strSrc
     * @return
     */
    public static String SHA256(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
    /**
     * 循环参数
     */

    public static String Parameter(List<String> list,HashMap<String, String>data){
        str="";
//        for (Map.Entry<String, String> entry : data.entrySet()) {
//            entry.getKey();
//            entry.getValue();
//            str=str+entry.getKey()+"="+entry.getValue()+"&";
//            Log.i("httpPost", "for:: "+str);
//        }
//        str= str.substring(0,str.length()-1);
//        Log.i("httpPost", "for完结:: "+str);
        for (int i=0;i<list.size();i++){
            str=str+list.get(i)+"="+data.get(list.get(i))+"&";
            Log.i("httpPost", "for:: "+str);
        }
        str= str.substring(0,str.length()-1);
        Log.i("httpPost", "for完结:: "+str);
        return str;
    }

    /**
     * 生成随机数
     */
    public static String Random(){
        String str="";
        Random random=new Random();
        for (int i = 0; i <10; i++) {
            System.out.println("random.nextInt()="+random.nextInt(20));
            str=str+random.nextInt(20);
        }
       return  str;
    }
}

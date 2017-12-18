package eric.cn.com.varnish.utils;

import org.xutils.image.ImageOptions;

import eric.cn.com.varnish.R;

/**
 * Created by Administrator on 2017/12/18.
 */

public class ImageOptionsUtils {
    public static ImageOptions ImageUtils() {
        ImageOptions options = new ImageOptions.Builder()
//设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.per_head)
//设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.per_head)
//设置使用缓存
                .setUseMemCache(true)
//设置显示圆形图片
                .setCircular(true)
//设置支持gif
                .setIgnoreGif(false)    //以及其他方法
                .build();
        return options;
    }
}

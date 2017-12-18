package eric.cn.com.varnish;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import eric.cn.com.varnish.activity.EditPassWordActvity;
import eric.cn.com.varnish.activity.InfoActivity;
import eric.cn.com.varnish.activity.MessageActivity;
import eric.cn.com.varnish.activity.MyCatActivity;
import eric.cn.com.varnish.activity.SettingActivity;
import eric.cn.com.varnish.activity.StationChangeActivity;
import eric.cn.com.varnish.bean.InfoBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.ImageOptionsUtils;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_info;
    private LinearLayout ll_setting;
    private LinearLayout ll_info;
    private LinearLayout ll_pass;
    private LinearLayout ll_message;
    private LinearLayout ll_cat;
    private LinearLayout ll_my_cat;
    private LinearLayout ll_my_sign;
    private TextView tv_num;
    private MyProgressDialog dialog;
    private static int CAMERA_REQUEST_CODE01 = 1;
    private static int GALLERY_REQUEST_CODE01 = 2;
    private static int CROP_REQUEST_CODE01 = 3;//裁剪返回的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getInfoNet();
    }

    private void initView() {
        iv_info = (ImageView) findViewById(R.id.iv_info);
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);
        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        ll_pass = (LinearLayout) findViewById(R.id.ll_pass);
        ll_message = (LinearLayout) findViewById(R.id.ll_message);
        ll_cat = (LinearLayout) findViewById(R.id.ll_cat);
        ll_my_cat= (LinearLayout) findViewById(R.id.ll_my_cat);
        ll_my_sign= (LinearLayout) findViewById(R.id.ll_my_sign);
        tv_num= (TextView) findViewById(R.id.tv_num);


        ll_cat.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
        ll_message.setOnClickListener(this);
        ll_pass.setOnClickListener(this);
        ll_info.setOnClickListener(this);
        iv_info.setOnClickListener(this);
        ll_my_cat.setOnClickListener(this);
        ll_my_sign.setOnClickListener(this);
    }

    private void getInfoNet() {

        RequestParams params = new RequestParams(RequestURL.info);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);

        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);

        x.http().post(params,new HttpCallBack<InfoBean>(new IAsyncHttpComplete<InfoBean>() {
            @Override
            public void onSuccess(InfoBean result) {
                if (result.getError()==0){
                    bindData(result);
                }else {
                    Toast.makeText(MainActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
                }

                Log.i("InfoActivity",new Gson().toJson(result.toString()));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        }));

    }

    private void bindData(InfoBean bean) {
        tv_num.setText(bean.getData().getNumber());
        x.image().bind(iv_info,bean.getData().getAvatar(), ImageOptionsUtils.ImageUtils());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_info:
                //头像设置
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_REQUEST_CODE01);
                break;
            case R.id.ll_info:
                //个人设置
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                break;
            case R.id.ll_pass:
                //密码修改
                startActivity(new Intent(MainActivity.this, EditPassWordActvity.class));
                break;
            case R.id.ll_message:
                //通知消息
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                break;
            case R.id.ll_cat:
                //车辆变更申请
                startActivity(new Intent(MainActivity.this, StationChangeActivity.class));
                break;
            case R.id.ll_setting:
                //设置
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.ll_my_cat:
                //我的通勤车
                startActivity(new Intent(MainActivity.this, MyCatActivity.class));
                break;
            case R.id.ll_my_sign:
                //签到
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   if (requestCode == GALLERY_REQUEST_CODE01) {
            //相册回调
            if (data == null) {
                return;
            }
            Uri uri;
            uri = data.getData();
            File fileUri = convertUri(uri);
            setPicture(fileUri);
        }

    }
    private File convertUri(Uri uri) {
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File saveBitmap(Bitmap bitmap) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/you");
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsolutePath() + "jian.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return img;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setPicture(File file) {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(MainActivity.this, "图片上传中！！！");
        RequestParams params = new RequestParams(RequestURL.info);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);

        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);
        params.addBodyParameter("avator_path", new File(String.valueOf(file)));
         x.http().post(params, new Callback.CommonCallback<String>() {
             @Override
             public void onSuccess(String result) {
                 Log.i("MainActivity",result.toString());
             }

             @Override
             public void onError(Throwable ex, boolean isOnCallback) {

             }

             @Override
             public void onCancelled(CancelledException cex) {

             }

             @Override
             public void onFinished() {
                 dialog.CloseDialog();
             }
         });
    }
}

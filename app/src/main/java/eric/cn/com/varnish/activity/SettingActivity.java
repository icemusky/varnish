package eric.cn.com.varnish.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import eric.cn.com.varnish.MainActivity;
import eric.cn.com.varnish.MyApplication;
import eric.cn.com.varnish.R;
import eric.cn.com.varnish.bean.UpDataBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.CommonUtils;
import eric.cn.com.varnish.utils.DownloadApk;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyDialog;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.UpdataService;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/8.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private LinearLayout ll_submit;
    private MyProgressDialog dialog;
    private RelativeLayout rl_gengxin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        rl_gengxin = (RelativeLayout) findViewById(R.id.rl_gengxin);

        tv_top_title.setText("设置");
        ll_top_balck.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
        rl_gengxin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_submit:
                //退出登录
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(SettingActivity.this);
                final android.app.AlertDialog mydialog = builder.create();

                View view = View.inflate(SettingActivity.this, R.layout.dailog_zhuche, null);
                mydialog.setView(view, 0, 0, 0, 0);
                LinearLayout ll_canle = (LinearLayout) view.findViewById(R.id.ll_canle);
                ll_canle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mydialog.dismiss();
                    }
                });
                LinearLayout ll_submit = (LinearLayout) view.findViewById(R.id.ll_submit);
                ll_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setlogout();
                    }
                });
                mydialog.show();
                break;
            case R.id.rl_gengxin:
                //更新
                getUpDate();
                break;
        }
    }

    private void getUpDate() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(SettingActivity.this, "检查版本中！！！");
        RequestParams params = new RequestParams(RequestURL.version);
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
        params.setMultipart(true);
        final String CurVirsion = CommonUtils.getInstance.getVersionInfo(this);
        x.http().post(params, new HttpCallBack<UpDataBean>(new IAsyncHttpComplete<UpDataBean>() {
            @Override
            public void onSuccess(final UpDataBean result) {
                if (result.getError() == 0) {
                    if (VersionComparison(result.getVersionCode(), CurVirsion) == 1) {
                        if (Build.VERSION.SDK_INT <= 23) {
                            new DownloadApk(SettingActivity.this, RequestURL.URL + result.getUrl(), "1");
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                            builder.setMessage("有新版本是否更新？");
                            builder.setTitle("提示");

                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    SettingActivity.this.finish();

                                }
                            });

                            builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i("SettingActivity", "下载地址::::::::" + RequestURL.URL + result.getUrl());
                                    if (RequestURL.URL + result.getUrl() != null) {
                                        Intent updataService = new Intent(SettingActivity.this, UpdataService.class);
                                        updataService.putExtra("url", RequestURL.URL + result.getUrl());
                                        startService(updataService);
                                    }
                                }
                            });
                            builder.create().show();
                        }
                    } else {
                        Toast.makeText(SettingActivity.this, "最新版本！无需更新", Toast.LENGTH_SHORT).show();
                    }
                }
                Log.i("SettingActivity", "版本更新" + result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {
                dialog.CloseDialog();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        }));
    }

    private void setlogout() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(SettingActivity.this, "退出中！！！");
        RequestParams params = new RequestParams(RequestURL.logout);
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
        params.setMultipart(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                Log.i("SettingActivity", result.toString());
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

    public static int VersionComparison(String versionServer, String versionLocal) {
        String version1 = versionServer;
        String version2 = versionLocal;
        if (version1 == null || version1.length() == 0 || version2 == null || version2.length() == 0)
            throw new IllegalArgumentException("Invalid parameter!");

        int index1 = 0;
        int index2 = 0;
        while (index1 < version1.length() && index2 < version2.length()) {
            int[] number1 = getValue(version1, index1);

            int[] number2 = getValue(version2, index2);


            if (number1[0] < number2[0]) {

                return -1;
            } else if (number1[0] > number2[0]) {

                return 1;
            } else {
                index1 = number1[1] + 1;
                index2 = number2[1] + 1;
            }
        }
        if (index1 == version1.length() && index2 == version2.length())
            return 0;
        if (index1 < version1.length())
            return 1;
        else
            return -1;
    }

    public static int[] getValue(String version, int index) {
        int[] value_index = new int[2];
        StringBuilder sb = new StringBuilder();
        while (index < version.length() && version.charAt(index) != '.') {
            sb.append(version.charAt(index));
            index++;
        }
        value_index[0] = Integer.parseInt(sb.toString());
        value_index[1] = index;

        return value_index;
    }
}

package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import eric.cn.com.varnish.MyApplication;
import eric.cn.com.varnish.R;
import eric.cn.com.varnish.bean.StationChangeBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/8.
 */

public class StationChangeActivity extends AppCompatActivity implements View.OnClickListener {
    private int REQUEST_REGION_PICK = 100;
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private LinearLayout ll_submit;
    private LinearLayout ll_cat;
    private EditText et_content;
    private TextView tv_name;
    private String CarStatID = "";//选择站点ID
    private String CarStatNAME = "";//选择站点名字
    private MyProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_change);
        initView();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        tv_top_title.setText("上下车站点变更申请");
        ll_cat = (LinearLayout) findViewById(R.id.ll_cat);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_name = (TextView) findViewById(R.id.tv_name);

        ll_top_balck.setOnClickListener(this);
        ll_cat.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_cat:
                //选择车站点
                Intent intent = new Intent();
                intent.setClass(StationChangeActivity.this, VarnishUpDownActivity.class);
                startActivityForResult(intent, REQUEST_REGION_PICK);
                break;
            case R.id.ll_submit:
                //提交申请
                if (!TextUtils.isEmpty(CarStatNAME) && !TextUtils.isEmpty(et_content.getText().toString())) {
                    getUserSiteNet();
                } else {
                    Toast.makeText(StationChangeActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REGION_PICK) {
            if (data != null) {
                CarStatID = data.getStringExtra("id");
                CarStatNAME = data.getStringExtra("name");
                tv_name.setText(CarStatNAME);
            }
        }
    }

    private void getUserSiteNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(StationChangeActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.user_site);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);
        hashMap.put("site_id", CarStatID);//上下车站点的ID
        hashMap.put("reason", et_content.getText().toString());//变更原因
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);
        params.addBodyParameter("site_id", CarStatID);
        params.addBodyParameter("reason", et_content.getText().toString());

        x.http().post(params, new HttpCallBack<StationChangeBean>(new IAsyncHttpComplete<StationChangeBean>() {
            @Override
            public void onSuccess(StationChangeBean result) {
                if (result.getError() == 0) {
                    Toast.makeText(StationChangeActivity.this, "变更成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(StationChangeActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                Log.i("StationChangeActivity",new Gson().toJson(result.toString()));
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
}

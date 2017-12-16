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
import eric.cn.com.varnish.bean.RegisterCodeBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.CountDownTimerUtils;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/7.
 */

public class EditNewPhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private LinearLayout ll_submit;
    private EditText tv_phone, et_num;
    private TextView tv_send;

    private String phoneCODE = "";//短信验证码
    private String phoneCODE_ID = "";//短信验证码ID
    private String NewphoneCODE = "";//短信验证码
    private String NewphoneCODE_ID = "";//短信验证码ID

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_newphone);
        initView();
        Intent intent = getIntent();
        phoneCODE = intent.getStringExtra("code");
        phoneCODE_ID = intent.getStringExtra("code_id");
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        tv_phone = (EditText) findViewById(R.id.tv_phone);
        et_num = (EditText) findViewById(R.id.et_num);
        tv_send = (TextView) findViewById(R.id.tv_send);

        tv_top_title.setText("绑定新手机号");
        ll_top_balck.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
        tv_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_submit:
                //修改新手机号
                if (!TextUtils.isEmpty(tv_phone.getText().toString()) && tv_phone.getText().toString().length() == 11 && !TextUtils.isEmpty(et_num.getText().toString())) {
                    if (et_num.getText().toString().equals(NewphoneCODE)) {
                        setPhoneNet();
                    }else {
                        Toast.makeText(EditNewPhoneActivity.this, "验证码不争取", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditNewPhoneActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_send:
                //发送验证码
                if (!TextUtils.isEmpty(tv_phone.getText().toString()) && tv_phone.getText().toString().length() == 11) {
                    sendCode();
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tv_send, 60000, 1000);
                    mCountDownTimerUtils.start();
                } else {
                    Toast.makeText(EditNewPhoneActivity.this, "请填写正确手机号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setPhoneNet() {
        RequestParams params = new RequestParams(RequestURL.phone);
        String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("phone", tv_phone.getText().toString());
        hashMap.put("code1_id", phoneCODE_ID);
        hashMap.put("code1", phoneCODE);
        hashMap.put("new_phone", tv_phone.getText().toString());
        hashMap.put("code2_id", NewphoneCODE_ID);
        hashMap.put("code2", NewphoneCODE);
        //对key键值按字典升序排序
        Collection<String> keyset= hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("phone", tv_phone.getText().toString());
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list,hashMap) + MyApplication.API));
        params.addBodyParameter("code1_id", phoneCODE_ID);
        params.addBodyParameter("code1", phoneCODE);
        params.addBodyParameter("new_phone", tv_phone.getText().toString());
        params.addBodyParameter("code2_id", NewphoneCODE_ID);
        params.addBodyParameter("code2", NewphoneCODE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("EditNewPhoneActivity",result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void sendCode() {
        RequestParams params = new RequestParams(RequestURL.smscode);
        String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("phone", tv_phone.getText().toString());
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("phone", tv_phone.getText().toString());
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        x.http().post(params, new HttpCallBack<RegisterCodeBean>(new IAsyncHttpComplete<RegisterCodeBean>() {
            @Override
            public void onSuccess(RegisterCodeBean result) {
                if (result.getError() == 0) {
                    NewphoneCODE = result.getCode();
                    NewphoneCODE_ID = result.getId() + "";
                    Log.i("EditNewPhoneActivity", "短信验证码:" + NewphoneCODE);
                }
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
}

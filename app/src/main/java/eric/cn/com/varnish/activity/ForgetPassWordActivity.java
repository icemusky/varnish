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
 * Created by Administrator on 2017/11/29.
 */

public class ForgetPassWordActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_submit;
    private LinearLayout ll_balck;
    private EditText et_phone;
    private EditText et_num;
    private TextView tv_send;
    private String phoneCODE = "";//短信验证码
    private String phoneCODE_ID = "";//短信验证码ID

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    private void initView() {
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        ll_balck = (LinearLayout) findViewById(R.id.ll_balck);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_num = (EditText) findViewById(R.id.et_num);
        tv_send = (TextView) findViewById(R.id.tv_send);

        ll_balck.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
        tv_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_submit:
                if (!TextUtils.isEmpty(et_phone.getText().toString()) && !TextUtils.isEmpty(et_num.getText().toString())) {
                    if (phoneCODE.equals(et_num.getText().toString())) {
                        Intent intent = new Intent();
                        intent.setClass(ForgetPassWordActivity.this, ResePassWordActivity.class);
                        intent.putExtra("code", phoneCODE);
                        intent.putExtra("code_id", phoneCODE_ID);
                        intent.putExtra("phone", et_phone.getText().toString());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ForgetPassWordActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgetPassWordActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_balck:
                finish();
                break;
            case R.id.tv_send:
                //发送验证码
                if (!TextUtils.isEmpty(et_phone.getText().toString()) && et_phone.getText().length() == 11) {
                    sendCode();
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tv_send, 60000, 1000);
                    mCountDownTimerUtils.start();
                } else {
                    Toast.makeText(ForgetPassWordActivity.this, "手机号错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void sendCode() {
        RequestParams params = new RequestParams(RequestURL.smscode);
        String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("phone", et_phone.getText().toString());
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("phone", et_phone.getText().toString());
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        x.http().post(params, new HttpCallBack<RegisterCodeBean>(new IAsyncHttpComplete<RegisterCodeBean>() {
            @Override
            public void onSuccess(RegisterCodeBean result) {
                if (result.getError() == 0) {
                    phoneCODE = result.getCode();
                    phoneCODE_ID = result.getId() + "";
                    Log.i("ForgetPassWordActivity", "短信验证码:" + phoneCODE);
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

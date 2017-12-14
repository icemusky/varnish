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
import eric.cn.com.varnish.bean.ResePassWordBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ResePassWordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_login;
    private String phoneCODE = "";//短信验证码
    private String phoneCODE_ID = "";//短信验证码ID
    private String phone = "";//手机号
    private LinearLayout ll_balck;
    private EditText et_phone;
    private EditText et_num;
    private LinearLayout ll_submit;
    private MyProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rese_password);
        Intent intent = getIntent();
        phoneCODE = intent.getStringExtra("code");
        phoneCODE_ID = intent.getStringExtra("code_id");
        phone = intent.getStringExtra("phone");
        initView();
    }

    private void initView() {
        tv_login = (TextView) findViewById(R.id.tv_login);
        ll_balck = (LinearLayout) findViewById(R.id.ll_balck);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_num = (EditText) findViewById(R.id.et_num);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);

        ll_submit.setOnClickListener(this);
        ll_balck.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                //返回登录页
                startActivity(new Intent(ResePassWordActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.ll_balck:
                finish();
                break;
            case R.id.ll_submit:
                //提交
                if (!TextUtils.isEmpty(et_num.getText().toString()) && !TextUtils.isEmpty(et_phone.getText().toString())) {
                    if (et_num.getText().toString().equals(et_phone.getText().toString())) {
                        getForgetNet();
                    } else {
                        Toast.makeText(ResePassWordActivity.this, "新旧密码不一致！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ResePassWordActivity.this, "请填写完整信息！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getForgetNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(ResePassWordActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.forget);
        String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("phone", phone);//手机号
        hashMap.put("code_id", phoneCODE_ID);//验证码的id
        hashMap.put("code", phoneCODE);//验证码
        hashMap.put("password", et_phone.getText().toString());//新密码
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("phone", phone);//手机号
        params.addBodyParameter("code_id", phoneCODE_ID);//验证码的id
        params.addBodyParameter("code", phoneCODE);//验证码
        params.addBodyParameter("password", et_phone.getText().toString());//新密码
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));

        x.http().post(params, new HttpCallBack<ResePassWordBean>(new IAsyncHttpComplete<ResePassWordBean>() {
            @Override
            public void onSuccess(ResePassWordBean result) {
                if (result.getError() == 0) {
                    startActivity(new Intent(ResePassWordActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(ResePassWordActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                Log.i("ResePassWordActivity", new Gson().toJson(result));
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

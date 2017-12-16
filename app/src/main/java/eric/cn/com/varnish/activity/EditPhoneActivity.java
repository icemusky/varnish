package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class EditPhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private LinearLayout ll_submit;
    private TextView tv_phone;
    private TextView tv_send;
    private EditText tv_num;

    private String phoneCODE = "";//短信验证码
    private String phoneCODE_ID = "";//短信验证码ID
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone);
        initView();
        Intent intent=getIntent();
        tv_phone.setText(intent.getStringExtra("phone"));
    }

    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        tv_phone= (TextView) findViewById(R.id.tv_phone);
        tv_send= (TextView) findViewById(R.id.tv_send);
        tv_num= (EditText) findViewById(R.id.tv_num);

        tv_top_title.setText("手机号修改");
        ll_top_balck.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
        tv_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_submit:
                //提交按钮
                if (phoneCODE.equals(tv_num.getText().toString())){
                    Intent intent=new Intent();
                    intent.setClass(EditPhoneActivity.this,EditNewPhoneActivity.class);
                    intent.putExtra("code",phoneCODE);
                    intent.putExtra("code_id",phoneCODE_ID);
                    startActivity(intent);
                }else {
                    Toast.makeText(EditPhoneActivity.this,"验证码不正确",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_send:
                //发送验证码
                sendCode();
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tv_send, 60000, 1000);
                mCountDownTimerUtils.start();
                break;
        }
    }
    private void sendCode() {
        RequestParams params = new RequestParams(RequestURL.smscode);
        String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("phone", tv_phone.getText().toString());
        //对key键值按字典升序排序
        Collection<String> keyset= hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("phone", tv_phone.getText().toString());
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list,hashMap) + MyApplication.API));
        x.http().post(params, new HttpCallBack<RegisterCodeBean>(new IAsyncHttpComplete<RegisterCodeBean>() {
            @Override
            public void onSuccess(RegisterCodeBean result) {
                if (result.getError() == 0) {
                    phoneCODE=result.getCode();
                    phoneCODE_ID=result.getId()+"";
                    Log.i("EditPhoneActivity","短信验证码:"+phoneCODE);
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

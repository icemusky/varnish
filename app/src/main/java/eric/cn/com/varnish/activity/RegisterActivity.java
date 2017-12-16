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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
import eric.cn.com.varnish.bean.DepartmentEventBean;
import eric.cn.com.varnish.bean.RegisterBean;
import eric.cn.com.varnish.bean.RegisterCodeBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.CountDownTimerUtils;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/11/29.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_balck;
    private TextView tv_send;
    private EditText et_phone;
    private TextView et_bumen;
    private EditText et_name;
    private LinearLayout ll_bumen;
    private EditText et_num;
    private EditText et_psd;
    private EditText et_pwd_submit;
    private EditText et_sheyuan;
    private LinearLayout ll_submit;
    private DepartmentEventBean departmentEventBean;
    private String phoneCODE = "";//短信验证码
    private String phoneCODE_ID = "";//短信验证码ID
    private MyProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void initView() {
        ll_balck = (LinearLayout) findViewById(R.id.ll_balck);
        tv_send = (TextView) findViewById(R.id.tv_send);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_name = (EditText) findViewById(R.id.et_name);
        ll_bumen = (LinearLayout) findViewById(R.id.ll_bumen);
        et_num = (EditText) findViewById(R.id.et_num);
        et_psd = (EditText) findViewById(R.id.et_psd);
        et_pwd_submit = (EditText) findViewById(R.id.et_pwd_submit);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        et_bumen = (TextView) findViewById(R.id.et_bumen);
        et_sheyuan= (EditText) findViewById(R.id.et_sheyuan);

        ll_balck.setOnClickListener(this);
        tv_send.setOnClickListener(this);
        ll_bumen.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                    Toast.makeText(RegisterActivity.this, "手机号错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_bumen:
                //选择部门
                startActivity(new Intent(RegisterActivity.this, DepartmentActivity.class));
                break;
            case R.id.ll_submit:
                //注册
               if (validation()){
                   getRegisterNet();
                   Log.i("RegisterActivity","验证成功:");
               }
                break;
        }
    }

    /**
     * 注册网络请求
     */
    private void getRegisterNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(RegisterActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.reg);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("name", et_name.getText().toString());
        hashMap.put("group_id", departmentEventBean.getId());
        hashMap.put("number",  et_sheyuan.getText().toString());
        hashMap.put("phone", et_phone.getText().toString());
        hashMap.put("code_id",  phoneCODE_ID);
        hashMap.put("code", phoneCODE);
        hashMap.put("password", et_psd.getText().toString());
        //对key键值按字典升序排序
        Collection<String> keyset= hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list,hashMap) + MyApplication.API));
        params.addBodyParameter("name", et_name.getText().toString());//用户姓名
        params.addBodyParameter("group_id", departmentEventBean.getId());//用户部门ID
        params.addBodyParameter("number", et_sheyuan.getText().toString());//社员编号
        params.addBodyParameter("phone", et_phone.getText().toString());//手机号
        params.addBodyParameter("code_id", phoneCODE_ID);//验证码的id
        params.addBodyParameter("code", phoneCODE);//验证码
        params.addBodyParameter("password", et_psd.getText().toString());//密码
        x.http().post(params,new HttpCallBack<RegisterBean>(new IAsyncHttpComplete<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean result) {
                if (result.getError()==0){
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                Log.i("RegisterActivity","注册:"+new Gson().toJson(result));
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

    //验证
    private boolean validation() {
        if (!TextUtils.isEmpty(et_name.getText().toString()) && !TextUtils.isEmpty(et_bumen.getText().toString()) && !TextUtils.isEmpty(et_sheyuan.getText().toString()) &&
                !TextUtils.isEmpty(et_phone.getText().toString()) && !TextUtils.isEmpty(et_num.getText().toString()) && !TextUtils.isEmpty(et_psd.getText().toString()) && !TextUtils.isEmpty(et_pwd_submit.getText().toString())) {
            if (!et_bumen.getText().toString().equals("请选择部门")) {
                if (et_psd.getText().toString().equals(et_pwd_submit.getText().toString())) {
                    if (et_num.getText().toString().equals(phoneCODE)){
                         return true;
                    }else {
                        Toast.makeText(RegisterActivity.this, "短信验证码不一致", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "请选择部门", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(RegisterActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void sendCode() {
        RequestParams params = new RequestParams(RequestURL.smscode);
        String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("phone", et_phone.getText().toString());
        //对key键值按字典升序排序
        Collection<String> keyset= hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("phone", et_phone.getText().toString());
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list,hashMap) + MyApplication.API));
        x.http().post(params, new HttpCallBack<RegisterCodeBean>(new IAsyncHttpComplete<RegisterCodeBean>() {
            @Override
            public void onSuccess(RegisterCodeBean result) {
                if (result.getError() == 0) {
                    phoneCODE=result.getCode();
                    phoneCODE_ID=result.getId()+"";
                    Log.i("RegisterActivity","短信验证码:"+phoneCODE);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCity(DepartmentEventBean bean) {
        //接收回传参数
        departmentEventBean = bean;
        et_bumen.setText(departmentEventBean.getName());
    }
}

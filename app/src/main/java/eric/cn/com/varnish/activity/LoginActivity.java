package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
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

import eric.cn.com.varnish.MainActivity;
import eric.cn.com.varnish.MyApplication;
import eric.cn.com.varnish.R;
import eric.cn.com.varnish.bean.LgoinBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyDialog;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.SharedPreferenceUtil;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/11/29.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_pwd;
    private CheckBox cb_checkbox;
    private TextView tv_pwd;
    private LinearLayout ll_submit;
    private TextView tv_register;

    private MyProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        readCheckBox();


    }


    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        cb_checkbox = (CheckBox) findViewById(R.id.cb_checkbox);
        tv_pwd = (TextView) findViewById(R.id.tv_pwd);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        tv_register = (TextView) findViewById(R.id.tv_register);
        ll_submit.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_pwd.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_submit:
                //登陆
                if (!TextUtils.isEmpty(et_name.getText().toString())&&!TextUtils.isEmpty(et_pwd.getText().toString())){
                    getLoginNet();
                    isCheckBox();
                }else {
                    Toast.makeText(LoginActivity.this, "请填写账号密码！！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register:
                //注册
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_pwd:
                //忘记密码
                startActivity(new Intent(LoginActivity.this, ForgetPassWordActivity.class));
                break;
        }
    }

    private void getLoginNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(LoginActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.login);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("number", et_name.getText().toString());
        hashMap.put("password", et_pwd.getText().toString());
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("number", et_name.getText().toString());//社员编号
        params.addBodyParameter("password", et_pwd.getText().toString());//密码
        x.http().post(params, new HttpCallBack<LgoinBean>(new IAsyncHttpComplete<LgoinBean>() {
            @Override
            public void onSuccess(LgoinBean result) {
                if (result.getError() == 0) {
                    //登陆成功
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    MyApplication.TOKEN=result.getToken();
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,result.getMsg(), Toast.LENGTH_SHORT).show();
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
                dialog.CloseDialog();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        }));
    }

    /**
     * 存储账号密码
     */
    private void isCheckBox() {
        if (cb_checkbox.isChecked()) {
            SharedPreferenceUtil.createPrefereceFile("logincheck", LoginActivity.this, "logincheck", "true");
            SharedPreferenceUtil.createPrefereceFile("user_name", LoginActivity.this, "user_name",et_name.getText().toString());
            SharedPreferenceUtil.createPrefereceFile("password", LoginActivity.this, "password", et_pwd.getText().toString());
        } else {
            SharedPreferenceUtil.createPrefereceFile("logincheck", LoginActivity.this, "logincheck", "false");
        }
    }

    /**
     * 判断checkBox 选择上回复账号密码
     */
    private void readCheckBox() {
        if (SharedPreferenceUtil.getPrefereceFileKeyValue("logincheck", this, "logincheck").equals("true")){
            et_name.setText(SharedPreferenceUtil.getPrefereceFileKeyValue("user_name", this, "user_name"));
            et_pwd.setText(SharedPreferenceUtil.getPrefereceFileKeyValue("password", this, "password"));
        }
    }

}

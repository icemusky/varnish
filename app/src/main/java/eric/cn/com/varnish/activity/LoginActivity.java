package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import eric.cn.com.varnish.MainActivity;
import eric.cn.com.varnish.R;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
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
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                break;
            case R.id.tv_register:
                //注册
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.tv_pwd:
                //忘记密码
                startActivity(new Intent(LoginActivity.this,ForgetPassWordActivity.class));
                break;
        }
    }

}

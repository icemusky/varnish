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
    private LinearLayout ll_login_left;
    private LinearLayout ll_login_right;
    private EditText et_name;
    private EditText et_pwd;
    private CheckBox cb_checkbox;
    private TextView tv_pwd;
    private LinearLayout ll_submit;
    private TextView tv_register;

    private boolean isLogin;
    private TextView tv_login_left;
    private TextView tv_login_right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        ll_login_left = (LinearLayout) findViewById(R.id.ll_login_left);
        ll_login_right = (LinearLayout) findViewById(R.id.ll_login_right);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        cb_checkbox = (CheckBox) findViewById(R.id.cb_checkbox);
        tv_pwd = (TextView) findViewById(R.id.tv_pwd);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_login_left = (TextView) findViewById(R.id.tv_login_left);
        tv_login_right = (TextView) findViewById(R.id.tv_login_right);

        ll_login_left.setOnClickListener(this);
        ll_login_right.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_pwd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_login_left:
                //普通会员切换
                isLogin = false;
                showStyle(isLogin);
                break;
            case R.id.ll_login_right:
                //车长切换
                isLogin = true;
                showStyle(isLogin);
                break;
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

    private void showStyle(boolean type) {
        if (type == false) {
            ll_login_left.setBackground(getResources().getDrawable(R.drawable.btn_left_blue_shape));
            ll_login_right.setBackground(getResources().getDrawable(R.drawable.btn_right_white_shape));
            tv_login_left.setTextColor(getResources().getColor(R.color.white));
            tv_login_right.setTextColor(getResources().getColor(R.color.grey_color1));
        } else if (type == true) {
            ll_login_left.setBackground(getResources().getDrawable(R.drawable.btn_left_white_shape));
            ll_login_right.setBackground(getResources().getDrawable(R.drawable.btn_right_blue_shape));
            tv_login_left.setTextColor(getResources().getColor(R.color.grey_color1));
            tv_login_right.setTextColor(getResources().getColor(R.color.white));
        }
    }
}

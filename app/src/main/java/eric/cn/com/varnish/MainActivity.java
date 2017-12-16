package eric.cn.com.varnish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import eric.cn.com.varnish.activity.EditPassWordActvity;
import eric.cn.com.varnish.activity.InfoActivity;
import eric.cn.com.varnish.activity.MessageActivity;
import eric.cn.com.varnish.activity.MyCatActivity;
import eric.cn.com.varnish.activity.SettingActivity;
import eric.cn.com.varnish.activity.StationChangeActivity;
import eric.cn.com.varnish.bean.InfoBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_info;
    private LinearLayout ll_setting;
    private LinearLayout ll_info;
    private LinearLayout ll_pass;
    private LinearLayout ll_message;
    private LinearLayout ll_cat;
    private LinearLayout ll_my_cat;
    private LinearLayout ll_my_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv_info = (ImageView) findViewById(R.id.iv_info);
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);
        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        ll_pass = (LinearLayout) findViewById(R.id.ll_pass);
        ll_message = (LinearLayout) findViewById(R.id.ll_message);
        ll_cat = (LinearLayout) findViewById(R.id.ll_cat);
        ll_my_cat= (LinearLayout) findViewById(R.id.ll_my_cat);
        ll_my_sign= (LinearLayout) findViewById(R.id.ll_my_sign);


        ll_cat.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
        ll_message.setOnClickListener(this);
        ll_pass.setOnClickListener(this);
        ll_info.setOnClickListener(this);
        iv_info.setOnClickListener(this);
        ll_my_cat.setOnClickListener(this);
        ll_my_sign.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_info:
                //头像设置

                break;
            case R.id.ll_info:
                //个人设置
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                break;
            case R.id.ll_pass:
                //密码修改
                startActivity(new Intent(MainActivity.this, EditPassWordActvity.class));
                break;
            case R.id.ll_message:
                //通知消息
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                break;
            case R.id.ll_cat:
                //车辆变更申请
                startActivity(new Intent(MainActivity.this, StationChangeActivity.class));
                break;
            case R.id.ll_setting:
                //设置
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.ll_my_cat:
                //我的通勤车
                startActivity(new Intent(MainActivity.this, MyCatActivity.class));
                break;
            case R.id.ll_my_sign:
                //签到
                break;
        }
    }


}

package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import eric.cn.com.varnish.bean.EditPassWordBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/7.
 */

public class EditPassWordActvity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private LinearLayout ll_submit;
    private EditText et_yuan_pwd;
    private CheckBox cb_checkbox;
    private EditText et_new_pwd;
    private EditText et_new_subpwd;
    private CheckBox cb_checkbox2;
    private MyProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        initView();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        et_yuan_pwd = (EditText) findViewById(R.id.et_yuan_pwd);
        cb_checkbox = (CheckBox) findViewById(R.id.cb_checkbox);
        et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
        et_new_subpwd = (EditText) findViewById(R.id.et_new_subpwd);
        cb_checkbox2 = (CheckBox) findViewById(R.id.cb_checkbox2);

        tv_top_title.setText("密码修改");
        ll_top_balck.setOnClickListener(this);
        ll_submit.setOnClickListener(this);

        cb_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    et_yuan_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    et_yuan_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        cb_checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    et_new_subpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    et_new_subpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_submit:
                //修改密码
                if (!TextUtils.isEmpty(et_yuan_pwd.getText().toString()) && !TextUtils.isEmpty(et_new_pwd.getText().toString()) && !TextUtils.isEmpty(et_new_subpwd.getText().toString())) {
                    if (et_new_pwd.getText().toString().equals(et_new_subpwd.getText().toString())) {
                        getPassWordNet();
                    } else {
                        Toast.makeText(EditPassWordActvity.this, "新旧密码不一致", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditPassWordActvity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getPassWordNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(EditPassWordActvity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.password);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);//用户的token
        hashMap.put("password", et_yuan_pwd.getText().toString());//原密码
        hashMap.put("new_password", et_new_subpwd.getText().toString());//新密码
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);//用户的token
        params.addBodyParameter("password", et_yuan_pwd.getText().toString());//原密码
        params.addBodyParameter("new_password", et_new_subpwd.getText().toString());//新密码
        x.http().post(params, new HttpCallBack<EditPassWordBean>(new IAsyncHttpComplete<EditPassWordBean>() {
            @Override
            public void onSuccess(EditPassWordBean result) {
                if (result.getError() == 0) {
                    startActivity(new Intent(EditPassWordActvity.this, LoginActivity.class));
                    finish();
                    Toast.makeText(EditPassWordActvity.this, "密码修改成功！请重新登陆", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditPassWordActvity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                Log.i("EditPassWordActvity", new Gson().toJson(result));
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

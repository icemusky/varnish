package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import eric.cn.com.varnish.MainActivity;
import eric.cn.com.varnish.MyApplication;
import eric.cn.com.varnish.R;
import eric.cn.com.varnish.adapter.DepartmentAdapter;
import eric.cn.com.varnish.bean.DepartmentEventBean;
import eric.cn.com.varnish.bean.InfoBean;
import eric.cn.com.varnish.bean.InfoEditBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/6.
 */

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ll_top_balck;
    private TextView tv_phone;
    private LinearLayout ll_submit;
    private TextView tv_top_title;
    private LinearLayout ll_mail;
    private LinearLayout ll_bumen;
    private MyProgressDialog dialog;
    private TextView tv_name,tv_name_type,tv_bumen,tv_num,tv_num_type,tv_email,tv_phone_type;

    private String DepartmentNAME="";//部门名字
    private String DepartmentID="";//部门ID
    private String Phone="";//电话
    private String Email="";//邮箱

   private int IntentEMail=101;//Intent 邮箱回调
    private int IntentPhone=102;//Intent 手机号回调
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
        getInfoNet();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        tv_top_title= (TextView) findViewById(R.id.tv_top_title);
        ll_mail= (LinearLayout) findViewById(R.id.ll_mail);
        ll_bumen= (LinearLayout) findViewById(R.id.ll_bumen);
        tv_name= (TextView) findViewById(R.id.tv_name);
        tv_name_type= (TextView) findViewById(R.id.tv_name_type);
        tv_bumen= (TextView) findViewById(R.id.tv_bumen);
        tv_num= (TextView) findViewById(R.id.tv_num);
        tv_num_type= (TextView) findViewById(R.id.tv_num_type);
        tv_email= (TextView) findViewById(R.id.tv_email);
        tv_phone_type= (TextView) findViewById(R.id.tv_phone_type);

        tv_top_title.setText("个人信息");

        ll_top_balck.setOnClickListener(this);
        tv_phone_type.setOnClickListener(this);
        ll_mail.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.tv_phone_type:
                //修改手机号
                Intent intent1=new Intent();
                intent1.setClass(InfoActivity.this,EditPhoneActivity.class);
                intent1.putExtra("phone",tv_phone.getText().toString());
                startActivity(intent1);
                break;
            case R.id.ll_mail:
                //修改邮箱号
                Intent intent=new Intent();
                intent.setClass(InfoActivity.this,EditMailActivity.class);
                startActivityForResult(intent,IntentEMail);
                break;
            case R.id.ll_bumen:
                //选择部门
                startActivity(new Intent(InfoActivity.this,DepartmentActivity.class));
                break;
            case R.id.ll_submit:
                //修改个人信息
                setInfoNet();
                break;
        }
    }

    /**
     * 修改用户信息
     */
    private void setInfoNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(InfoActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.edit_info);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);
        if (!DepartmentID.equals("")){
            hashMap.put("group_id", DepartmentID);//用户部门ID
        }
        if (!tv_email.getText().toString().equals("")){
            hashMap.put("email", tv_email.getText().toString());//邮箱
        }
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);
        if (!DepartmentID.equals("")) {
            params.addBodyParameter("group_id", DepartmentID);
        }
        if (!tv_email.getText().toString().equals("")) {
            params.addBodyParameter("email", tv_email.getText().toString());
        }
        x.http().post(params,new HttpCallBack<InfoEditBean>(new IAsyncHttpComplete<InfoEditBean>() {
            @Override
            public void onSuccess(InfoEditBean result) {
                if (result.getError()==0){
                    Toast.makeText(InfoActivity.this,"个人信息修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(InfoActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
                }
                Log.i("InfoActivity",new Gson().toJson(result.toString()));
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
     * 获取用户信息
     */

    private void getInfoNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(InfoActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.info);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);

        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);

        x.http().post(params,new HttpCallBack<InfoBean>(new IAsyncHttpComplete<InfoBean>() {
            @Override
            public void onSuccess(InfoBean result) {
                if (result.getError()==0){
                    bindData(result);
                }else {
                    Toast.makeText(InfoActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
                }

                Log.i("InfoActivity",new Gson().toJson(result.toString()));
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

    private void bindData(InfoBean bean) {
        tv_name.setText(bean.getData().getName());//姓名
        tv_bumen.setText(bean.getData().getGroup_id()); //错误  部门名字  现在是部门id
        tv_num.setText(bean.getData().getNumber());//社员编号
        tv_email.setText(bean.getData().getEmail());//邮箱
        tv_phone.setText(bean.getData().getPhone());//手机号
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCity(DepartmentEventBean bean) {
        //接收回传参数
        tv_bumen.setText(bean.getName());
        DepartmentNAME=bean.getName();
        DepartmentID=bean.getId();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentEMail) {
            if (data != null) {
                tv_email.setText(data.getStringExtra("email"));
            }
        }
    }
}

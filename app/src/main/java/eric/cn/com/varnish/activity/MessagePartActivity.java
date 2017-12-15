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
import eric.cn.com.varnish.bean.MessagePartBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.DateUtils;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/7.
 */

public class MessagePartActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private TextView tv_title;
    private TextView tv_data;
    private TextView tv_context;

    private String id;
    private MyProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_part);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        initView();
        getMessageNet();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_data = (TextView) findViewById(R.id.tv_data);
        tv_context = (TextView) findViewById(R.id.tv_context);

        tv_top_title.setText("详情介绍");
        ll_top_balck.setOnClickListener(this);


    }

    private void getMessageNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(MessagePartActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.message_detail);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);
        hashMap.put("id", id);
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);


        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);
        params.addBodyParameter("id", id);
        x.http().post(params, new HttpCallBack<MessagePartBean>(new IAsyncHttpComplete<MessagePartBean>() {
            @Override
            public void onSuccess(MessagePartBean result) {
                if (result.getError()==0){
                    bindData(result);
                }else {
                    Toast.makeText(MessagePartActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
                }
                Log.i("MessagePartActivity",new Gson().toJson(result.toString()));
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

    private void bindData(MessagePartBean bean) {
        tv_title.setText(bean.getData().getTitle());
        tv_data.setText(DateUtils.getDateToString(Long.getLong(bean.getData().getTime())));
        tv_context.setText(bean.getData().getContent());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
        }
    }
}

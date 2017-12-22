package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import eric.cn.com.varnish.adapter.VarnishTrunAdapter;
import eric.cn.com.varnish.adapter.VarnishUpDownAdapter;
import eric.cn.com.varnish.bean.VarnishApplyBean;
import eric.cn.com.varnish.bean.VarnishTrunBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/11.
 */

public class VarnishTrunActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private ListView lv;
    private VarnishTrunAdapter adapter;
    private List<VarnishTrunBean.ListBean> data;
    private int REQUEST_TONG_QING=101;
    private int Myposition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varnish_trun);
        Intent intent=getIntent();
        Myposition=intent.getIntExtra("position",0);
        initView();
        getClasses();
    }

    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        lv = (ListView) findViewById(R.id.lv);

        ll_top_balck.setOnClickListener(this);
        tv_top_title.setText("出勤");


    }

    /**查询勤次信息
     *
     *
     */
    private void getClasses() {

        RequestParams params = new RequestParams(RequestURL.cat_classes);
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

        x.http().post(params,new HttpCallBack<VarnishTrunBean>(new IAsyncHttpComplete<VarnishTrunBean>() {
            @Override
            public void onSuccess(VarnishTrunBean result) {
                if (result.getError()==0){
                    binData(result);
                }
                Log.i("VarnishTrunActivity",new Gson().toJson(result.toString()));
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

    private void binData(VarnishTrunBean bean) {

        data=new ArrayList<>();
        VarnishTrunBean.ListBean listbean=new VarnishTrunBean.ListBean();
        listbean.setId("0");
        listbean.setName("不出勤");
        data.add(listbean);
        data.addAll(bean.getList());
        adapter=new VarnishTrunAdapter(VarnishTrunActivity.this,data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("position", Myposition);
                intent.putExtra("id",data.get(position).getId());
                intent.putExtra("name",data.get(position).getName());
                setResult(REQUEST_TONG_QING, intent);
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_top_balck:
                finish();
                break;
        }
    }
}

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
import eric.cn.com.varnish.adapter.VarnishApplyAdapter;
import eric.cn.com.varnish.bean.VarnishApplyBean;
import eric.cn.com.varnish.bean.VarnishTrunBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/11.
 */

public class VarnishApplyActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private ListView lv;
    private VarnishApplyAdapter applyAdapter;
    private List<VarnishApplyBean.ListBean> data;
    private LinearLayout ll_cat;
    private TextView tv_name;

    private int REQUEST_REGION_PICK = 100;
    private String CarStatID = "";//选择站点ID
    private String CarStatNAME = "";//选择站点名字
    private VarnishTrunBean varnishTrunBean;
    private List<String> tongqin_id_list;
    private List<String> tongqin_name_list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varnish_apply);
        initView();
        getClasses();
    }

    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        lv = (ListView) findViewById(R.id.lv_id);
        ll_cat= (LinearLayout) findViewById(R.id.ll_cat);
        tv_name= (TextView) findViewById(R.id.tv_name);

        tv_top_title.setText("通勤车申请");
        ll_top_balck.setOnClickListener(this);
        ll_cat.setOnClickListener(this);


    }

    /**查询勤次日期
     * 0表示上一周，1表示本周，2表示下一周
     * @param week
     */
    private void getClasses(String week) {

        RequestParams params = new RequestParams(RequestURL.classes);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);
        hashMap.put("week", week);

        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);
        params.addBodyParameter("week", week);

        x.http().post(params,new HttpCallBack<VarnishApplyBean>(new IAsyncHttpComplete<VarnishApplyBean>() {
            @Override
            public void onSuccess(VarnishApplyBean result) {
                if(result.getError()==0){
                    bindData(result);
                }
                Log.i("VarnishApplyActivity","查询勤次信息:"+new Gson().toJson(result.toString()));
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
    /**查询勤次ID
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

        x.http().post(params, new HttpCallBack<VarnishTrunBean>(new IAsyncHttpComplete<VarnishTrunBean>() {
            @Override
            public void onSuccess(VarnishTrunBean result) {
                if (result.getError() == 0) {
                    varnishTrunBean = result;
                    tongqin_id_list=new ArrayList<String>();
                    tongqin_name_list=new ArrayList<String>();
                    for (int i = 0; i <varnishTrunBean.getList().size() ; i++) {
                        if (i==0){
                            tongqin_id_list.add("0");
                            tongqin_name_list.add("不出勤");
                        }
                        tongqin_id_list.add(varnishTrunBean.getList().get(i).getId());
                        tongqin_name_list.add(varnishTrunBean.getList().get(i).getName());
                    }
                    getClasses("0");
                }
                Log.i("VarnishTrunAdapter", new Gson().toJson(result.toString()));
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
    private void bindData(VarnishApplyBean bean) {
        data=new ArrayList<>();
        data.addAll(bean.getList());
        applyAdapter=new VarnishApplyAdapter(VarnishApplyActivity.this,data,tongqin_id_list,tongqin_name_list);
        lv.setAdapter(applyAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(VarnishApplyActivity.this,VarnishTrunActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_cat:
                Intent intent = new Intent();
                intent.setClass(VarnishApplyActivity.this, VarnishUpDownActivity.class);
                startActivityForResult(intent, REQUEST_REGION_PICK);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REGION_PICK) {
            if (data != null) {
                CarStatID = data.getStringExtra("id");
                CarStatNAME = data.getStringExtra("name");
                tv_name.setText(CarStatNAME);
            }
        }
    }
}

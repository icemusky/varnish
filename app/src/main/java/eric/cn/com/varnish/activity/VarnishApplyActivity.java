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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eric.cn.com.varnish.MyApplication;
import eric.cn.com.varnish.R;
import eric.cn.com.varnish.adapter.VarnishApplyAdapter;
import eric.cn.com.varnish.bean.VarnishApplyBean;
import eric.cn.com.varnish.bean.VarnishTrunBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.DateUtils;
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
    private List<VarnishApplyBean.ListBean> data_list;
    private LinearLayout ll_cat;
    private TextView tv_name;
    private LinearLayout ll_submit;

    private int REQUEST_REGION_PICK = 100;
    private int REQUEST_TONG_QING = 101;
    private String CarStatID = "";//选择站点ID
    private String CarStatNAME = "";//选择站点名字
    private VarnishTrunBean varnishTrunBean;
    private List<String> tongqin_id_list;
    private List<String> tongqin_name_list;
    private Map<String, String> map_data;

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
        ll_cat = (LinearLayout) findViewById(R.id.ll_cat);
        tv_name = (TextView) findViewById(R.id.tv_name);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);

        tv_top_title.setText("通勤车申请");
        ll_top_balck.setOnClickListener(this);
        ll_cat.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
    }

    /**
     * 查询勤次日期
     * 0表示上一周，1表示本周，2表示下一周
     *
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

        x.http().post(params, new HttpCallBack<VarnishApplyBean>(new IAsyncHttpComplete<VarnishApplyBean>() {
            @Override
            public void onSuccess(VarnishApplyBean result) {
                if (result.getError() == 0) {
                    bindData(result);
                }
                Log.i("VarnishApplyActivity", "查询勤次信息:" + new Gson().toJson(result.toString()));
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

    /**
     * 查询勤次ID
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
                    map_data = new HashMap<String, String>();

                    for (int i = 0; i < varnishTrunBean.getList().size(); i++) {
                        if (i == 0) {
                            map_data.put("0", "不出勤");
                        }
                        map_data.put(varnishTrunBean.getList().get(i).getId(), varnishTrunBean.getList().get(i).getName());
                    }

                    getClasses("2");
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
        data_list = new ArrayList<>();
        data_list.addAll(bean.getList());
        tongqin_name_list = new ArrayList<>();
        Set set = map_data.entrySet();
        for (int i = 0; i < bean.getList().size(); i++) {

            for (Iterator iter = set.iterator(); iter.hasNext(); ) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                System.out.println(key + " :" + value);
                if (bean.getList().get(i).getClasses_id() == Integer.parseInt(key)) {
                    tongqin_name_list.add(value);
                }
            }
        }


        applyAdapter = new VarnishApplyAdapter(VarnishApplyActivity.this, data_list, tongqin_name_list);
        lv.setAdapter(applyAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(VarnishApplyActivity.this, VarnishTrunActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_TONG_QING);
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
            case R.id.ll_submit:
                //提交
                setSubmitNet("2");
                break;
        }
    }

    /**
     * 提交通勤车申请
     */
    private void setSubmitNet(String week) {
        RequestParams params = new RequestParams(RequestURL.submit);
        final String random = HttpPost.Random() + "";
        long time = System.currentTimeMillis() / 1000;
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);
        hashMap.put("week", week);
        hashMap.put("site_id", CarStatID);
        hashMap.put("time", time + "");

        hashMap.put("classes1", data_list.get(0).getClasses_id() + "");
        hashMap.put("classes2", data_list.get(1).getClasses_id() + "");
        hashMap.put("classes3", data_list.get(2).getClasses_id() + "");
        hashMap.put("classes4", data_list.get(3).getClasses_id() + "");
        hashMap.put("classes5", data_list.get(4).getClasses_id() + "");
        hashMap.put("classes6", data_list.get(5).getClasses_id() + "");
        hashMap.put("classes7", data_list.get(6).getClasses_id() + "");

        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);
        params.addBodyParameter("week", week);//1本周，2下周
        params.addBodyParameter("site_id", CarStatID);//上下车站点的ID
        params.addBodyParameter("time", time+"");//当前时间
        params.addBodyParameter("classes1", data_list.get(0).getClasses_id() + "");
        params.addBodyParameter("classes2", data_list.get(1).getClasses_id() + "");
        params.addBodyParameter("classes3", data_list.get(2).getClasses_id() + "");
        params.addBodyParameter("classes4", data_list.get(3).getClasses_id() + "");
        params.addBodyParameter("classes5", data_list.get(4).getClasses_id() + "");
        params.addBodyParameter("classes6", data_list.get(5).getClasses_id() + "");
        params.addBodyParameter("classes7", data_list.get(6).getClasses_id() + "");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("VarnishApplyActivity", "提交申请：" + result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
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
        if (requestCode == REQUEST_TONG_QING) {
            if (data != null) {
                int position = data.getIntExtra("position", 0);
                String id = data.getStringExtra("id");
                String name = data.getStringExtra("name");
                Log.i("VarnishApplyActivity", "选项：" + position + "id:" + id + "名字：" + name);


                data_list.get(position).setClasses_id(Integer.parseInt(id));
                tongqin_name_list.set(position, name);
                applyAdapter = new VarnishApplyAdapter(VarnishApplyActivity.this, data_list, tongqin_name_list);
                lv.setAdapter(applyAdapter);
                applyAdapter.notifyDataSetChanged();
            }
        }

    }

}

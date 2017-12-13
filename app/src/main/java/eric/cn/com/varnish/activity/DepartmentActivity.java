package eric.cn.com.varnish.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
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
import eric.cn.com.varnish.adapter.DepartmentAdapter;
import eric.cn.com.varnish.bean.DepartmentBean;
import eric.cn.com.varnish.bean.DepartmentEventBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/7.
 */

public class DepartmentActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private ListView lv;
    private DepartmentAdapter adapter;
    private List<DepartmentBean.ListBean> data;
    private DepartmentBean.ListBean bean;

    private MyProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        initView();
        getDepartmentNet();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        lv = (ListView) findViewById(R.id.lv);

        tv_top_title.setText("部门");
        ll_top_balck.setOnClickListener(this);


    }

    /**
     * 网络请求 查看部门
     */
    private void getDepartmentNet(){
        dialog = new MyProgressDialog();
        dialog.ShowDialog(DepartmentActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.department);
        String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        //对key键值按字典升序排序
        Collection<String> keyset= hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list,hashMap) + MyApplication.API));
        x.http().post(params,new HttpCallBack<DepartmentBean>(new IAsyncHttpComplete<DepartmentBean>() {
            @Override
            public void onSuccess(DepartmentBean result) {
                if (result.getError()==0){
                    bindData(result);
                }else {
                    Toast.makeText(DepartmentActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(DepartmentActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
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

    private void bindData(DepartmentBean bean) {
        data=new ArrayList<>();
        data.addAll(bean.getList());
        adapter=new DepartmentAdapter(DepartmentActivity.this,data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new DepartmentEventBean(data.get(position).getName(),data.get(position).getId()));
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

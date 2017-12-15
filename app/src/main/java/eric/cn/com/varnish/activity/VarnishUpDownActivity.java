package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import eric.cn.com.varnish.MyApplication;
import eric.cn.com.varnish.R;
import eric.cn.com.varnish.adapter.VarnishUpDownAdapter;
import eric.cn.com.varnish.bean.VarnishUPDownBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/11.
 */

public class VarnishUpDownActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private ListView lv;
    private VarnishUpDownAdapter adapter;
    private List<VarnishUPDownBean.ListBeanX> data;
    private MyProgressDialog dialog;
    private OptionsPickerView areaTypePtins;
    private String CarStatID = "";//选择站点ID
    Timer timer = new Timer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varnish_up_down);
        initView();
        getSiteNet();
    }

    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        lv = (ListView) findViewById(R.id.lv);

        ll_top_balck.setOnClickListener(this);
        tv_top_title.setText("上下车站点");


    }

    private void getSiteNet() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(VarnishUpDownActivity.this, "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.site);
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

        x.http().post(params, new HttpCallBack<VarnishUPDownBean>(new IAsyncHttpComplete<VarnishUPDownBean>() {
            @Override
            public void onSuccess(VarnishUPDownBean result) {
                if (result.getError() == 0) {
                    bindData(result);
                } else {
                    Toast.makeText(VarnishUpDownActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                Log.i("VarnishUpDownActivity", new Gson().toJson(result.toString()));
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

    private void bindData(final VarnishUPDownBean bean) {
        data = new ArrayList<>();
        data.addAll(bean.getList());
        adapter = new VarnishUpDownAdapter(VarnishUpDownActivity.this, data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initAreaPicker(bean, position);
                if (areaTypePtins != null) {
                    areaTypePtins.show();
                }
            }
        });
    }

    /**
     * 初始化 城市选择器
     */
    private void initAreaPicker(final VarnishUPDownBean bean, final int position) {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */
        areaTypePtins = new OptionsPickerView.Builder(VarnishUpDownActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = bean.getList().get(position).getList().get(options1).getName();
                CarStatID = bean.getList().get(position).getList().get(options1).getId();
                Toast.makeText(VarnishUpDownActivity.this, "站点：" + tx, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("name", tx);
                intent.putExtra("id", CarStatID);
                setResult(100, intent);
                finish();
            }
        })
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.DKGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("省", "市", "区")
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .build();
        List<String> name = new ArrayList<>();
        for (int i = 0; i < bean.getList().get(position).getList().size(); i++) {
            name.add(bean.getList().get(position).getList().get(i).getName());
        }
        areaTypePtins.setPicker(name);//二级选择器
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

package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.adapter.VarnishApplyAdapter;

/**
 * Created by Administrator on 2017/12/11.
 */

public class VarnishApplyActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private ListView lv;
    private VarnishApplyAdapter applyAdapter;
    private List<String> data;
    private LinearLayout ll_cat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varnish_apply);
        initView();
    }

    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        lv = (ListView) findViewById(R.id.lv_id);
        ll_cat= (LinearLayout) findViewById(R.id.ll_cat);

        tv_top_title.setText("通勤车申请");
        ll_top_balck.setOnClickListener(this);
        ll_cat.setOnClickListener(this);

        //假数据
        data=new ArrayList<>();
        data.add("1");     data.add("1");     data.add("1");
        applyAdapter=new VarnishApplyAdapter(VarnishApplyActivity.this,data);
        lv.setAdapter(applyAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(VarnishApplyActivity.this,VarnishTrunActivity.class));
            }
        });
//        lv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(VarnishApplyActivity.this,VarnishUpDownActivity.class));
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_cat:
                startActivity(new Intent(VarnishApplyActivity.this,VarnishUpDownActivity.class));
                break;
        }
    }
}

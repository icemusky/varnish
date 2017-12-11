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
import eric.cn.com.varnish.adapter.VarnishUpDownAdapter;

/**
 * Created by Administrator on 2017/12/11.
 */

public class VarnishUpDownActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private ListView lv;
    private VarnishUpDownAdapter adapter;
    private List<String> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varnish_up_down);
        initView();
    }

    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        lv = (ListView) findViewById(R.id.lv);

        ll_top_balck.setOnClickListener(this);
        tv_top_title.setText("上下车站点");

        //假数据
        data=new ArrayList<>();
        data.add("1");        data.add("1");        data.add("1");
        adapter=new VarnishUpDownAdapter(VarnishUpDownActivity.this,data);
        lv.setAdapter(adapter);

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

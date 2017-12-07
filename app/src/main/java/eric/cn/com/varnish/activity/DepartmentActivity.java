package eric.cn.com.varnish.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.adapter.DepartmentAdapter;

/**
 * Created by Administrator on 2017/12/7.
 */

public class DepartmentActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private ListView lv;
    private DepartmentAdapter adapter;
    private List<String> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        initView();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        lv = (ListView) findViewById(R.id.lv);

        tv_top_title.setText("部门");
        ll_top_balck.setOnClickListener(this);

        //测试数据
        data=new ArrayList<>();
        data.add("111");     data.add("111");     data.add("111");
        adapter=new DepartmentAdapter(DepartmentActivity.this,data);
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

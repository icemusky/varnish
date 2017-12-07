package eric.cn.com.varnish.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import eric.cn.com.varnish.R;

/**
 * Created by Administrator on 2017/12/7.
 */

public class MessagePartActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private TextView tv_title;
    private TextView tv_data;
    private TextView tv_context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_part);
        initView();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
        }
    }
}

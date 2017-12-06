package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import eric.cn.com.varnish.R;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ForgetPassWordActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    private void initView() {
        ll_submit= (LinearLayout) findViewById(R.id.ll_submit);
        ll_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_submit:
                startActivity(new Intent(ForgetPassWordActivity.this,ResePassWordActivity.class));
                finish();
                break;
        }
    }
}

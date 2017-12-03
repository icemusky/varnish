package eric.cn.com.varnish.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import eric.cn.com.varnish.R;

/**
 * Created by Administrator on 2017/11/29.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_balck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        ll_balck = (LinearLayout) findViewById(R.id.ll_balck);

        ll_balck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_balck:
                finish();
                break;
        }
    }
}

package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import eric.cn.com.varnish.R;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ResePassWordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rese_password);
        initView();
    }

    private void initView() {
        tv_login = (TextView) findViewById(R.id.tv_login);

        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(ResePassWordActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
}

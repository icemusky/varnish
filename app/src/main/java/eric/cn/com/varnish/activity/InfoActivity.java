package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.adapter.DepartmentAdapter;

/**
 * Created by Administrator on 2017/12/6.
 */

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ll_top_balck;
    private TextView tv_phone;
    private LinearLayout ll_submit;
    private TextView tv_top_title;
    private LinearLayout ll_mail;
    private LinearLayout ll_bumen;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
    }

    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        tv_top_title= (TextView) findViewById(R.id.tv_top_title);
        ll_mail= (LinearLayout) findViewById(R.id.ll_mail);
        ll_bumen= (LinearLayout) findViewById(R.id.ll_bumen);

        tv_top_title.setText("个人信息");

        ll_top_balck.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        ll_mail.setOnClickListener(this);
        ll_bumen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.tv_phone:
                //修改手机号
                 startActivity(new Intent(InfoActivity.this,EditPhoneActivity.class));
                break;
            case R.id.ll_mail:
                //修改邮箱号
                startActivity(new Intent(InfoActivity.this,EditMailActivity.class));
                break;
            case R.id.ll_bumen:
                //选择部门
                startActivity(new Intent(InfoActivity.this,DepartmentActivity.class));
                break;
        }
    }
}

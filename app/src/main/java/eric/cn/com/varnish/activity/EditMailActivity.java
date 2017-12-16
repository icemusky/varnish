package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import eric.cn.com.varnish.R;

/**
 * Created by Administrator on 2017/12/7.
 */

public class EditMailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;
    private LinearLayout ll_submit;
    private EditText et_email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mail);
        initView();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);
        et_email= (EditText) findViewById(R.id.et_email);

        tv_top_title.setText("邮箱修改");
        ll_top_balck.setOnClickListener(this);
        ll_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_submit:
                //修改邮箱
                Intent intent = new Intent();
                intent.putExtra("email", et_email.getText().toString());
                setResult(101, intent);
                finish();
                break;
        }
    }


}

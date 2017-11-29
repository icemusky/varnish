package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import eric.cn.com.varnish.R;


public class GuideActivity extends AppCompatActivity implements View.OnClickListener{

    protected Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载启动图片
        setContentView(R.layout.activity_guide);
//        Integer time = 2500;    //设置等待时间，单位为毫秒
//        Handler handler = new Handler();
//        //当计时结束时，跳转至主界面
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(Guide.this, LoginActivity.class));
//                finish();
//            }
//        }, time);
         initView();
    }

    private void initView() {
        btn_submit= (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
}

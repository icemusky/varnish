package eric.cn.com.varnish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import eric.cn.com.varnish.MainActivity;
import eric.cn.com.varnish.MyApplication;
import eric.cn.com.varnish.R;
import eric.cn.com.varnish.fragment.MyNextWeekFragment;
import eric.cn.com.varnish.fragment.MyThieWeekFragment;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpPost;

/**
 * Created by Administrator on 2017/12/8.
 */

public class MyCatActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;

    private TabLayout OrderTab;
    private ViewPager OrderVp;
    private MyThieWeekFragment myThieWeekFragment;
    private MyNextWeekFragment myNextWeekFragment;
    private List<String> mTitleList;
    private List<Fragment> mFragmentList;
    private MyOrderAdapter adapter;

    private LinearLayout ll_submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycat);
        initView();
        getBus();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        OrderTab = (TabLayout) findViewById(R.id.OrderTab);
        OrderVp = (ViewPager) findViewById(R.id.OrderVp);
        ll_submit = (LinearLayout) findViewById(R.id.ll_submit);

        ll_top_balck.setOnClickListener(this);
        ll_submit.setOnClickListener(this);

        tv_top_title.setText("我的通勤车");
        myNextWeekFragment =new MyNextWeekFragment();
        myThieWeekFragment =new MyThieWeekFragment();
        mTitleList = new ArrayList<>();//页卡标题集合
        mFragmentList = new ArrayList<>();//fragment集合
        mTitleList.add("本周");
        mTitleList.add("下周");

        OrderTab.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        OrderTab.addTab(OrderTab.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        OrderTab.addTab(OrderTab.newTab().setText(mTitleList.get(1)));

        mFragmentList.add(myNextWeekFragment);
        mFragmentList.add(myThieWeekFragment);

        adapter = new MyOrderAdapter(getSupportFragmentManager(),mFragmentList);
        OrderVp.setAdapter(adapter);
        OrderTab.setupWithViewPager(OrderVp);
        OrderVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private class MyOrderAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragmentList;
        public MyOrderAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
            super(fm);
            this.mFragmentList = mFragmentList;
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mTitleList.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {

            return mTitleList.get(position % mTitleList.size());
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_balck:
                finish();
                break;
            case R.id.ll_submit:
                //立即申请
                startActivity(new Intent(MyCatActivity.this,VarnishApplyActivity.class));
                break;
        }
    }
    /**
     * 获取通勤车数据
     */

    private void getBus() {

        RequestParams params = new RequestParams(RequestURL.bus);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);

        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.i("MyCatActivity","通勤车信息:"+result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}

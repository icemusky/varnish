package eric.cn.com.varnish.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.fragment.MyDownWorkFragment;
import eric.cn.com.varnish.fragment.MyNextWeekFragment;
import eric.cn.com.varnish.fragment.MyThieWeekFragment;
import eric.cn.com.varnish.fragment.MyUpWorkFragment;

/**
 * Created by Administrator on 2017/12/8.
 */

public class MyWorkActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_top_balck;
    private TextView tv_top_title;

    private TabLayout OrderTab;
    private ViewPager OrderVp;
    private MyUpWorkFragment myUpWorkFragment;
    private MyDownWorkFragment myDownWorkFragment;
    private List<String> mTitleList;
    private List<Fragment> mFragmentList;
    private MyOrderAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_work);
        initView();
    }


    private void initView() {
        ll_top_balck = (LinearLayout) findViewById(R.id.ll_top_balck);
        tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        OrderTab = (TabLayout) findViewById(R.id.OrderTab);
        OrderVp = (ViewPager) findViewById(R.id.OrderVp);

        ll_top_balck.setOnClickListener(this);

        tv_top_title.setText("当前日期");
        myUpWorkFragment =new MyUpWorkFragment();
        myDownWorkFragment =new MyDownWorkFragment();
        mTitleList = new ArrayList<>();//页卡标题集合
        mFragmentList = new ArrayList<>();//fragment集合
        mTitleList.add("上班");
        mTitleList.add("下班");

        OrderTab.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        OrderTab.addTab(OrderTab.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        OrderTab.addTab(OrderTab.newTab().setText(mTitleList.get(1)));

        mFragmentList.add(myUpWorkFragment);
        mFragmentList.add(myDownWorkFragment);

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
        }
    }

}

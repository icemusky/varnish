package eric.cn.com.varnish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.activity.MyCatActivity;
import eric.cn.com.varnish.activity.MyWorkActivity;
import eric.cn.com.varnish.adapter.MyWeekAdapter;

/**
 * Created by Administrator on 2017/12/8.
 */

public class MyThieWeekFragment extends Fragment {
    private View view;
    private ListView lv;
    private MyWeekAdapter adapter;
    private List<String> data;
    private LinearLayout ll_bg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_up, container, false);
        initView();
        return view;

    }

    private void initView() {
        lv= (ListView) view.findViewById(R.id.lv);
        ll_bg= (LinearLayout) view.findViewById(R.id.ll_bg);
        //判断有没有数据
//        lv.setVisibility(View.GONE);
//        ll_bg.setVisibility(View.VISIBLE);
        //假数据
        data=new ArrayList<>();
        data.add("1");       data.add("1");       data.add("1");
        adapter=new MyWeekAdapter(getActivity(),data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),MyWorkActivity.class));
            }
        });
    }
}

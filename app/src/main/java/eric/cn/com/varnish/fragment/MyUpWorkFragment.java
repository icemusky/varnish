package eric.cn.com.varnish.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.adapter.MyWeekAdapter;
import eric.cn.com.varnish.adapter.MyWeekItemAdapter;
import eric.cn.com.varnish.utils.view.MyListView;

/**
 * Created by Administrator on 2017/12/8.
 */

public class MyUpWorkFragment extends Fragment {
    private View view;
    private List<String> data;
    private LinearLayout ll_bg;
    private MyListView my_lv;
    private MyWeekItemAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_my_work_data, container, false);
        initView();
        return view;

    }

    private void initView() {
        my_lv= (MyListView) view.findViewById(R.id.my_lv);
        //假数据
        data=new ArrayList<>();
        data.add("1");       data.add("1");       data.add("1");
        adapter=new MyWeekItemAdapter(getActivity(),data);
        my_lv.setAdapter(adapter);
    }
}

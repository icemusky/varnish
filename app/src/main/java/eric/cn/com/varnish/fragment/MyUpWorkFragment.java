package eric.cn.com.varnish.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import eric.cn.com.varnish.MyApplication;
import eric.cn.com.varnish.R;
import eric.cn.com.varnish.activity.MyWorkActivity;
import eric.cn.com.varnish.adapter.MyWeekItemAdapter;
import eric.cn.com.varnish.bean.MyWorkBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;
import eric.cn.com.varnish.utils.view.MyListView;

/**
 * Created by Administrator on 2017/12/8.
 */

public class MyUpWorkFragment extends Fragment {
    private View view;
    private List<MyWorkBean.Line1Bean.SiteBean> data;
    private LinearLayout ll_bg;
    private MyListView my_lv;
    private MyWeekItemAdapter adapter;
    private TextView tv_chepai;
    private TextView tv_qin01;
    private TextView tv_tv_qin02;
    private TextView tv_tv_qin03;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_qi;
    private TextView tv_zhong;
    private TextView tv_start_time;
    private TextView tv_end_time,tv_zhan;
    private LinearLayout ll;
    private MyProgressDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_my_work_data, container, false);
        initView();
        getBus();
        return view;

    }

    private void initView() {
        my_lv = (MyListView) view.findViewById(R.id.my_lv);

        tv_chepai = (TextView) view.findViewById(R.id.tv_chepai);
        tv_qin01 = (TextView) view.findViewById(R.id.tv_qin01);
        tv_tv_qin02 = (TextView) view.findViewById(R.id.tv_tv_qin02);
        tv_tv_qin03 = (TextView) view.findViewById(R.id.tv_tv_qin03);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_qi = (TextView) view.findViewById(R.id.tv_qi);
        tv_zhong = (TextView) view.findViewById(R.id.tv_zhong);
        tv_start_time = (TextView) view.findViewById(R.id.tv_start_time);
        tv_end_time = (TextView) view.findViewById(R.id.tv_end_time);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        tv_zhan= (TextView) view.findViewById(R.id.tv_zhan);
    }

    /**
     * 获取通勤车数据
     */

    private void getBus() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(getActivity(), "网络请求中！！！");
        RequestParams params = new RequestParams(RequestURL.bus_detail);
        final String random = HttpPost.Random() + "";
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("nonce", random);
        hashMap.put("token", MyApplication.TOKEN);
        hashMap.put("time", MyWorkActivity.time);
        //对key键值按字典升序排序
        Collection<String> keyset = hashMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        params.addBodyParameter("nonce", random);
        params.addBodyParameter("sign", HttpPost.SHA256(HttpPost.Parameter(list, hashMap) + MyApplication.API));
        params.addBodyParameter("token", MyApplication.TOKEN);
        params.addBodyParameter("time", MyWorkActivity.time);


        x.http().post(params, new HttpCallBack<MyWorkBean>(new IAsyncHttpComplete<MyWorkBean>() {
            @Override
            public void onSuccess(MyWorkBean result) {
                if (result.getError() == 0) {
                    bindData(result);
                } else {
                    Toast.makeText(getContext(), result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                Log.i("MyUpWorkFragment", new Gson().toJson(result.toString()));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {
                dialog.CloseDialog();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        }));

    }

    private void bindData(MyWorkBean bean) {
        //绑定数据
        tv_chepai.setText(bean.getLine1().getBus_number());//车牌
        tv_qin01.setText(bean.getLine1().getClasses());//A勤
        tv_tv_qin02.setText(bean.getLine1().getName());//A勤 后面
        tv_tv_qin03.setText(bean.getLine1().getNo()+"座");//A勤 后面 座位
        tv_name.setText(bean.getLine1().getUser_name());//司机名字
        tv_phone.setText(bean.getLine1().getUser_phone());//司机手机
        tv_qi.setText(bean.getLine1().getBegin_site());//起始站点
        tv_zhong.setText(bean.getLine1().getEnd_site());//结束站点
        tv_start_time.setText("发车时间:"+bean.getLine1().getBegin_time());//起始时间
        tv_end_time.setText("到达时间:"+bean.getLine1().getEnd_time());//结束时间
        tv_zhan.setText("共"+bean.getLine1().getSite().size()+"站");

        data = new ArrayList<>();
        data.addAll(bean.getLine1().getSite());
        adapter = new MyWeekItemAdapter(getActivity(), data);
        my_lv.setAdapter(adapter);
    }
}

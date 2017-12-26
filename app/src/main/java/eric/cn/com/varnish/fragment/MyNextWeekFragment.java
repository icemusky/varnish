package eric.cn.com.varnish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import eric.cn.com.varnish.activity.LoginActivity;
import eric.cn.com.varnish.activity.MyWorkActivity;
import eric.cn.com.varnish.activity.VarnishApplyActivity;
import eric.cn.com.varnish.adapter.MyNextWeekAdapter;
import eric.cn.com.varnish.adapter.MyWeekAdapter;
import eric.cn.com.varnish.bean.MyCatBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.MyProgressDialog;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**
 * Created by Administrator on 2017/12/8.
 */

public class MyNextWeekFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ListView lv;
    private MyNextWeekAdapter adapter;
    private List<MyCatBean.Week2Bean.ListBean> data;
    private LinearLayout ll_bg,ll_bg01;
    private MyProgressDialog dialog;
    private LinearLayout ll_submit;
    private  String open="";// 1可以提交 0 不可以提交
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_down, container, false);
        initView();
        getBus();
        return view;
    }
    private void initView() {
        lv= (ListView) view.findViewById(R.id.lv);
        ll_submit= (LinearLayout) view.findViewById(R.id.ll_submit);
        ll_bg= (LinearLayout) view.findViewById(R.id.ll_bg);
        ll_bg01= (LinearLayout) view.findViewById(R.id.ll_bg01);
        //判断有没有数据
//        lv.setVisibility(View.GONE);
//        ll_bg.setVisibility(View.VISIBLE);
        ll_submit.setOnClickListener(this);
    }
    /**
     * 获取通勤车数据
     */

    private void getBus() {
        dialog = new MyProgressDialog();
        dialog.ShowDialog(getActivity(), "网络请求中！！！");
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

        x.http().post(params,new HttpCallBack<MyCatBean>(new IAsyncHttpComplete<MyCatBean>() {
            @Override
            public void onSuccess(MyCatBean result) {
                if (result.getError()==0){
                    bindData(result);
                }else {
                    Toast.makeText(getContext(),result.getMsg(),Toast.LENGTH_SHORT).show();
                }
                Log.i("MyNextWeekFragment", "通勤车信息:" +new Gson().toJson(result.toString()));
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

    private void bindData(MyCatBean bean) {
        open=bean.getWeek1().getOpen()+"";
        data=new ArrayList<>();
        data.addAll(bean.getWeek2().getList());
        adapter=new MyNextWeekAdapter(getActivity(),data);
        lv.setAdapter(adapter);
        //如果没事数据隐藏
        if (data.size()>0){
            ll_bg01.setVisibility(View.VISIBLE);
            ll_bg.setVisibility(View.INVISIBLE);
        }else {
            ll_bg01.setVisibility(View.INVISIBLE);
            ll_bg.setVisibility(View.VISIBLE);
        }
        //按钮显示状态
        if (open.equals("1")){
            ll_submit.setBackgroundResource(R.drawable.btn_green_shape);
        }else {
            ll_submit.setBackgroundResource(R.drawable.btn_gray_shape);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_submit:
                //立即申请
                if (open.equals("1")){
                    Intent intent=new Intent();
                    intent.setClass(getContext(),VarnishApplyActivity.class);
                    intent.putExtra("type","2");
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(),"不可以提交审核！",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}

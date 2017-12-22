package eric.cn.com.varnish.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
import eric.cn.com.varnish.bean.VarnishTrunBean;
import eric.cn.com.varnish.http.RequestURL;
import eric.cn.com.varnish.utils.HttpCallBack;
import eric.cn.com.varnish.utils.HttpPost;
import eric.cn.com.varnish.utils.interfaces.IAsyncHttpComplete;

/**  我的通勤车 选择出勤
 * Created by Administrator on 2017/12/7.
 */

public class VarnishTrunAdapter extends BaseAdapter {
    private Context mContext;
    private List<VarnishTrunBean.ListBean> data;
    private LayoutInflater mflater;
    public VarnishTrunAdapter(Context context, List<VarnishTrunBean.ListBean> data) {
        this.mflater = LayoutInflater.from(context);
        this.mContext = context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolde mViewHolde = null;
        if (convertView == null) {
            mViewHolde = new ViewHolde();
            convertView = mflater.inflate(R.layout.item_varnish_trun, null);
            mViewHolde.item_tv_title = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(mViewHolde);
        } else {
            mViewHolde = (ViewHolde) convertView.getTag();
        }

        mViewHolde.item_tv_title.setText(data.get(position).getName());
        return convertView;
    }

    private class ViewHolde {
        private TextView item_tv_title;
    }


}

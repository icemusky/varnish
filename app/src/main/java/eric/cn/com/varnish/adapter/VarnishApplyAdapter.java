package eric.cn.com.varnish.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.bean.VarnishApplyBean;
import eric.cn.com.varnish.bean.VarnishTrunBean;
import eric.cn.com.varnish.utils.DateUtils;

/**
 * 我的通勤车 上周下周
 * Created by Administrator on 2017/12/7.
 */

public class VarnishApplyAdapter extends BaseAdapter {
    private Context mContext;
    private List<VarnishApplyBean.ListBean> data;
    private LayoutInflater mflater;
    private VarnishTrunBean bean;
    private List<String> tongqin_id_list;
    private List<String> tongqin_name_list;

    public VarnishApplyAdapter(Context context, List<VarnishApplyBean.ListBean> data, List<String> tongqin_id_list, List<String> tongqin_name_list) {
        this.mflater = LayoutInflater.from(context);
        this.mContext = context;
        this.data = data;
        this.tongqin_id_list = tongqin_id_list;
        this.tongqin_name_list = tongqin_name_list;
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
            convertView = mflater.inflate(R.layout.item_varnish_apply, null);
            mViewHolde.tv_hao = (TextView) convertView.findViewById(R.id.tv_hao);
            mViewHolde.tv_tongqin = (TextView) convertView.findViewById(R.id.tv_tongqin);
            convertView.setTag(mViewHolde);
        } else {
            mViewHolde = (ViewHolde) convertView.getTag();
        }
        Log.i("VarnishApplyAdapter", data.get(position).getTime() + "");
//        Log.i("VarnishApplyAdapter",DateUtils.getDateString(data.get(position).getTime()));
        mViewHolde.tv_hao.setText(DateUtils.getDateStringByTimeSTamp((long) data.get(position).getTime(), "dd日"));
        String id=data.get(position).getClasses_id()+"";
//        if (id.equals(tongqin_id_list.get(position))){
//            mViewHolde.tv_tongqin.setText(tongqin_name_list.get(position));
//        }

        return convertView;
    }

    private class ViewHolde {
        private TextView tv_hao, tv_tongqin;
    }
}

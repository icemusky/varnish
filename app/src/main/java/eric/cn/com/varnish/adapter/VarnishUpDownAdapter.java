package eric.cn.com.varnish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.bean.VarnishUPDownBean;

/**  我的通勤车 上周下周
 * Created by Administrator on 2017/12/7.
 */

public class VarnishUpDownAdapter extends BaseAdapter {
    private Context mContext;
    private List<VarnishUPDownBean.ListBeanX> data;
    private LayoutInflater mflater;
    public VarnishUpDownAdapter(Context context, List<VarnishUPDownBean.ListBeanX> data) {
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
            convertView = mflater.inflate(R.layout.item_varnish_up_down, null);
            mViewHolde.item_tv_title = (TextView) convertView.findViewById(R.id.item_tv_title);
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

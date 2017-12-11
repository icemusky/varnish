package eric.cn.com.varnish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import eric.cn.com.varnish.R;

/**  我的通勤车 上周下周
 * Created by Administrator on 2017/12/7.
 */

public class VarnishApplyAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> data;
    private LayoutInflater mflater;
    public VarnishApplyAdapter(Context context, List<String> data) {
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
            convertView = mflater.inflate(R.layout.item_varnish_apply, null);
//            mViewHolde.item_tv_title = (TextView) convertView.findViewById(R.id.item_tv_title);
            convertView.setTag(mViewHolde);
        } else {
            mViewHolde = (ViewHolde) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolde {
//        private TextView item_tv_title;
    }
}

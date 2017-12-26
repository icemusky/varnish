package eric.cn.com.varnish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.bean.MyWorkBean;

/**   我的通勤车 上班 下班 时间表
 * Created by Administrator on 2017/12/7.
 */

public class MyWeekDownItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<MyWorkBean.Line2Bean.SiteBeanX> data;
    private LayoutInflater mflater;

    public MyWeekDownItemAdapter(Context context, List<MyWorkBean.Line2Bean.SiteBeanX> data) {
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
            convertView = mflater.inflate(R.layout.item_my_work_item, null);
            mViewHolde.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolde.tv_zhan = (TextView) convertView.findViewById(R.id.tv_zhan);
            mViewHolde.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(mViewHolde);
        } else {
            mViewHolde = (ViewHolde) convertView.getTag();
        }
        mViewHolde.tv_name.setText(data.get(position).getName());
        mViewHolde.tv_time.setText(data.get(position).getTime());
        if (data.get(position).getType().equals("1")){
            mViewHolde.tv_zhan.setVisibility(View.VISIBLE);
        }else {
            mViewHolde.tv_zhan.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private class ViewHolde {
        private TextView tv_name,tv_zhan,tv_time;
    }
}

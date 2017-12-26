package eric.cn.com.varnish.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import eric.cn.com.varnish.R;
import eric.cn.com.varnish.activity.MyWorkActivity;
import eric.cn.com.varnish.bean.MyCatBean;
import eric.cn.com.varnish.utils.DateUtils;

/**
 * 我的通勤车 上周下周
 * Created by Administrator on 2017/12/7.
 */

public class MyNextWeekAdapter extends BaseAdapter {
    private Context mContext;
    private List<MyCatBean.Week2Bean.ListBean> data;
    private LayoutInflater mflater;

    public MyNextWeekAdapter(Context context, List<MyCatBean.Week2Bean.ListBean> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolde mViewHolde = null;
        if (convertView == null) {
            mViewHolde = new ViewHolde();
            convertView = mflater.inflate(R.layout.item_my_work, null);
            mViewHolde.tv_chepai = (TextView) convertView.findViewById(R.id.tv_chepai);
            mViewHolde.tv_qin01 = (TextView) convertView.findViewById(R.id.tv_qin01);
            mViewHolde.tv_tv_qin02 = (TextView) convertView.findViewById(R.id.tv_tv_qin02);
            mViewHolde.tv_tv_qin03 = (TextView) convertView.findViewById(R.id.tv_tv_qin03);
            mViewHolde.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            mViewHolde.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            mViewHolde.tv_qi = (TextView) convertView.findViewById(R.id.tv_qi);
            mViewHolde.tv_zhong = (TextView) convertView.findViewById(R.id.tv_zhong);
            mViewHolde.tv_start_time = (TextView) convertView.findViewById(R.id.tv_start_time);
            mViewHolde.tv_end_time = (TextView) convertView.findViewById(R.id.tv_end_time);
            mViewHolde.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
            mViewHolde.ll= (LinearLayout) convertView.findViewById(R.id.ll);
            convertView.setTag(mViewHolde);
        } else {
            mViewHolde = (ViewHolde) convertView.getTag();
        }
        mViewHolde.tv_chepai.setText(data.get(position).getBus_number());//车牌
        mViewHolde.tv_qin01.setText(data.get(position).getClasses());// 1勤
        mViewHolde.tv_tv_qin02.setText(data.get(position).getName());// 1勤 后面
        mViewHolde.tv_tv_qin03.setText(data.get(position).getNo()+"座"); //  1勤 后面 座位
        mViewHolde.tv_name.setText(data.get(position).getUser_name());//车长
        mViewHolde.tv_phone.setText(data.get(position).getUser_phone());//车长电话
        mViewHolde.tv_qi.setText(data.get(position).getBegin_site());//开始站点
        mViewHolde.tv_zhong.setText(data.get(position).getEnd_site());//结束站点
        mViewHolde.tv_start_time.setText("发车时间:"+data.get(position).getBegin_time());//开始时间
        mViewHolde.tv_end_time.setText("到达时间:"+data.get(position).getEnd_time());//结束时间
        mViewHolde.tv_data.setText(DateUtils.getDateToString(Long.parseLong(data.get(position).getTime())));//乘做时间

        mViewHolde.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext, MyWorkActivity.class);
                intent.putExtra("time",data.get(position).getTime());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolde {
        private TextView tv_chepai, tv_qin01, tv_tv_qin02, tv_tv_qin03, tv_name, tv_phone, tv_qi, tv_zhong, tv_start_time, tv_end_time, tv_data;
        private LinearLayout ll;
    }


}

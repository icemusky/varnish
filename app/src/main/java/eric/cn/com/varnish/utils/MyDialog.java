package eric.cn.com.varnish.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import eric.cn.com.varnish.R;

/**
 * Created by Eric on 2018/1/7.
 */

public class MyDialog {
    public  AlertDialog dialog;
    boolean type=false;
    public  void  MyViewDialog(Context context, @DrawableRes int res){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
         dialog = builder.create();

         View view = View.inflate(context, R.layout.dailog_tishi, null);
        dialog.setView(view, 0, 0, 0, 0);
        ImageView iv_img= (ImageView) view.findViewById(R.id.iv_img);
        iv_img.setImageResource(res);
    }
    public void MyShow(){
        dialog.show();

    }
    public void MyDismiss(){
        dialog.dismiss();
    }

    public  boolean  MyZhuCheDialog(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialog = builder.create();

        View view = View.inflate(context, R.layout.dailog_zhuche, null);
        dialog.setView(view, 0, 0, 0, 0);
        LinearLayout ll_canle= (LinearLayout) view.findViewById(R.id.ll_canle);
        ll_canle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              type=false;
            }
        });
        LinearLayout ll_submit= (LinearLayout) view.findViewById(R.id.ll_submit);
        ll_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=true;
            }
        });
        return type;
    }
}

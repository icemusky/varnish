package eric.cn.com.varnish.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public enum CommonUtils {
	getInstance;
	/**
     * Returns whether the network is available
     */
	private static String TAG = "CommonUtils";
	public static boolean isNetworkAvailable1(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.w(TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0, length = info.length; i < length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns whether the network is roaming
	 */
	public static boolean isNetworkRoaming(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			// Log.w(Constants.TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null
					&& info.getType() == ConnectivityManager.TYPE_MOBILE) {
			} else {
			}
		}
		return false;
	}
	//检查是否有网络
	public boolean NetState(Context context) {
		boolean flag = false;
		ConnectivityManager connectManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectManager.getActiveNetworkInfo() != null) {
			return flag;
		} else {
			return false;
		}
	}
	
	public boolean isNetworkAvailable(Context ctx) {
		try {
			ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			return (info != null && info.isConnected());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean strNullOrEmpty(String str) {
		boolean isNullOrEmpty = false;
		str = str.trim();
		isNullOrEmpty = str == null || "".equals(str);
		return isNullOrEmpty;
	}
	
	public void hideKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		View view = ((Activity) context).getCurrentFocus();
		if (view != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);// 隐藏软键盘
			imm.isActive();
		}
	}

	public boolean keyboardStatus(Context context) {
		InputMethodManager imm2 = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//		View view = ((Activity) context).getCurrentFocus();
		return imm2.isActive();
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param view
	 */
	public void hideSoftKeyBoard(View view) {
		if (view == null) {
			return;
		}
		try {
			Context context = view.getContext();
			view.requestFocus();
			InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);//隐藏输入法
		} catch (Exception e) {}
	}
	

	
	public String getVersionInfo(Context context) {
		PackageManager manager;
		PackageInfo info = null;
		manager = context.getPackageManager();
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//int code = info.versionCode;
		String versionName = info.versionName;
		System.out.println("APP VERSION::" + versionName);
		return versionName;
	}
	
	public int getVersionCode(Context context) {
		PackageManager manager;
		PackageInfo info = null;
		manager = context.getPackageManager();
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int code = info.versionCode;
		System.out.println("APP CODE::" + code);
		return code;
	}
	/** 获取屏幕的宽度 */
	public final static int getWindowsWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	/** 获取屏幕的高度 */
	public final static int getWindowsHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}

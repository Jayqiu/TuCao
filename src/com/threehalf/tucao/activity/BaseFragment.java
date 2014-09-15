package com.threehalf.tucao.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.threehalf.tucao.R;
import com.threehalf.tucao.view.FlippingLoadingDialog;

public abstract class BaseFragment extends SherlockFragment {
	/**
	 * 屏幕的宽度�?高度、密�?
	 */
	protected View mView;
	protected int mScreenWidth;
	protected int mScreenHeight;
	protected float mDensity;
	protected Activity mActivity;
	protected Context mContext;
	protected FlippingLoadingDialog mLoadingDialog;
	protected boolean isinit = false;

	public BaseFragment() {
		super();
	}

	public BaseFragment(Activity activity, Context context) {
		mActivity = activity;
		mContext = context;
		mLoadingDialog = new FlippingLoadingDialog(context, "请求提交");
		/**
		 * 获取屏幕宽度、高度�?密度
		 */
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
		mDensity = metric.density;
		isinit = true;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initViews();
		initEvents();

		// if (isinit) {
		// initData();
		// isinit = false;
		// }

		// isinit=true;
		return mView;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initData();
	}

	protected abstract void initViews();

	protected abstract void initEvents();

	protected abstract void initData();

	public View findViewById(int id) {
		return mView.findViewById(id);
	}

	protected void showLoadingDialog(String text) {
		if (text != null) {
			mLoadingDialog.setText(text);
		}
		mLoadingDialog.show();
	}

	protected void dismissLoadingDialog() {
		if (mLoadingDialog.isShowing()) {
			mLoadingDialog.dismiss();
		}
	}

	/** 显示自定义Toast提示(来自String) **/
	protected void showCustomToast(String text) {
		View toastRoot = LayoutInflater.from(mContext).inflate(
				R.layout.common_toast, null);
		((TextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
		Toast toast = new Toast(mContext);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastRoot);
		toast.show();
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(mContext, cls);
		startActivity(intent);
	}

	// @Override
	// public void setUserVisibleHint(boolean isVisibleToUser) {
	// // TODO Auto-generated method stub
	// super.setUserVisibleHint(isVisibleToUser);
	// if (isVisibleToUser && isinit) {
	// // fragment可见时加载数据
	// isinit = false;
	// initData();
	// } else {
	// // 不可见时不执行操作
	// }
	//
	// }
}

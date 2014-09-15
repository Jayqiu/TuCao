package com.threehalf.tucao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.threehalf.tucao.R;
import com.threehalf.tucao.application.BaseApplication;
import com.threehalf.tucao.view.FlippingLoadingDialog;

public abstract class BaseFragmentActivity extends SherlockFragmentActivity {
	/**
	 * 屏幕的宽度、高度、密度
	 */
	protected BaseApplication mApplication;
	protected int mScreenWidth;
	protected int mScreenHeight;
	protected float mDensity;
	protected FlippingLoadingDialog mLoadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApplication = (BaseApplication) getApplication();
		mLoadingDialog = new FlippingLoadingDialog(this, "请求提交");
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
		mDensity = metric.density;
		
	}

	/** 初始化视�? **/
	protected abstract void initViews();

	/** 初始化事�? **/
	protected abstract void initEvents();

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

	/** 短暂显示Toast提示(来自res) **/
	protected void showShortToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
	}

	/** 短暂显示Toast提示(来自String) **/
	protected void showShortToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/** 长时间显示Toast提示(来自res) **/
	protected void showLongToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
	}

	/** 长时间显示Toast提示(来自String) **/
	protected void showLongToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	/** 显示自定义Toast提示(来自res) **/
	protected void showCustomToast(int resId) {
		View toastRoot = LayoutInflater.from(BaseFragmentActivity.this)
				.inflate(R.layout.common_toast, null);
		((TextView) toastRoot.findViewById(R.id.toast_text))
				.setText(getString(resId));
		Toast toast = new Toast(BaseFragmentActivity.this);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastRoot);
		toast.show();
	}

	/** 显示自定义Toast提示(来自String) **/
	protected void showCustomToast(String text) {
		View toastRoot = LayoutInflater.from(BaseFragmentActivity.this)
				.inflate(R.layout.common_toast, null);
		((TextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
		Toast toast = new Toast(BaseFragmentActivity.this);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastRoot);
		toast.show();
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** 默认退出 **/
	protected void defaultFinish() {
		super.finish();
	}

}

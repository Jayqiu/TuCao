package com.threehalf.tucao.activity.setting;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.threehalf.tucao.R;
import com.threehalf.tucao.activity.BaseFragment;

/**
 * z�?��
 * 
 * @author jayqiu
 * 
 */
@SuppressLint("ValidFragment")
public class SettingFragment extends BaseFragment {

	private static SettingFragment foundFragment;

	public SettingFragment(Activity activity, Context context) {
		super(activity, context);
	}

	public static SettingFragment newInstance(Activity activity, Context context) {
		SettingFragment foundFragment = new SettingFragment(activity, context);
		return foundFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_hottest, null);
		Log.i("HottestFragment", "onCreateView");
		return v;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.i("HottestFragment", "onDestroyView");
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}
}

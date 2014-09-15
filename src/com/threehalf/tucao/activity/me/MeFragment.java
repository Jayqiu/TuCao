package com.threehalf.tucao.activity.me;

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
public class MeFragment extends BaseFragment {

	private static MeFragment foundFragment;

	public MeFragment(Activity activity, Context context) {
		super(activity, context);
	}

	public static MeFragment newInstance(Activity activity, Context context) {
		MeFragment foundFragment = new MeFragment(activity, context);
		return foundFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_me, null);
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

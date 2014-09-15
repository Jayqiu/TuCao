package com.threehalf.tucao.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.threehalf.tucao.activity.hottest.HottestFragment;
import com.threehalf.tucao.activity.me.MeFragment;
import com.threehalf.tucao.activity.newes.NewestFragment;
import com.threehalf.tucao.activity.setting.SettingFragment;

public class MainFragmentAdapter extends FragmentStatePagerAdapter {
	private Activity activity;
	private Context mContext;

	public MainFragmentAdapter(FragmentManager fm, Activity activity,
			Context context) {
		super(fm);
		this.activity = activity;
		this.mContext = context;
		// TODO Auto-generated constructor stub
	}

	private String[] titleStr = { "新槽", "热槽", "我", "设置" };

	@Override
	public android.support.v4.app.Fragment getItem(int position) {
		switch (position) {
		case 0:

			return NewestFragment.newInstance(activity, mContext);
		case 1:
			return HottestFragment.newInstance(activity, mContext);
		case 2:
			return MeFragment.newInstance(activity, mContext);
		case 3:
			return SettingFragment.newInstance(activity, mContext);
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return titleStr[position];
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return titleStr.length;
	}

}

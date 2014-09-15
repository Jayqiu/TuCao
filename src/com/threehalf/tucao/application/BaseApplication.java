package com.threehalf.tucao.application;

import android.app.Application;

public class BaseApplication extends Application {
	private static BaseApplication mInstance;

	public static BaseApplication getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this;
	}
}

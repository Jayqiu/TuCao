package com.threehalf.tucao.util;

import com.threehalf.tucao.entity.UserEntity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppCache {
	private static AppCache uniqueInstance = null;
	private Context mContext;
	private SharedPreferences sharedPreferences;

	public AppCache(Context context) {
		this.mContext = context;
		sharedPreferences = mContext.getSharedPreferences(
				ConstantUtil.APPSTORE_XML, Context.MODE_PRIVATE);
	}

	public static AppCache getInstance(Context context) {

		if (uniqueInstance == null) {
			uniqueInstance = new AppCache(context);
		}
		return uniqueInstance;
	}

	public void saveLoginUserInfo(UserEntity userEntity) {

		Editor editor = sharedPreferences.edit();

		editor.commit();
	}

	public UserEntity getLoginInfo() {
		UserEntity userEntity = new UserEntity();

		return userEntity;
	}

	public void saveImageCode(int imageCode) {
		Editor editor = sharedPreferences.edit();
		editor.commit();
		editor.clear();
	}

	public int getImageCode() {
		return 0;
	}

}

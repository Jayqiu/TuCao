package com.threehalf.tucao.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

import com.threehalf.tucao.util.FileToolUtil;
import com.threehalf.tucao.util.PathUtilManager;

public class AppFileCache {
	private Context mContext;
	private static AppFileCache appFileCache;

	public AppFileCache(Context context) {
		this.mContext = context;
	}

	public static AppFileCache getInstance(Context context) {
		if (appFileCache == null) {
			appFileCache = new AppFileCache(context);
		}
		return appFileCache;
	}

	public String getNewsToCao() {
		File file = new File(PathUtilManager.getSdCardRootPath()
				+ PathUtilManager.FILE_CACHE_DIR
				+ PathUtilManager.NEWS_TOCAO_CACHE_FILE_NAME);
		FileToolUtil fileToolUtil = new FileToolUtil(file, mContext);
		try {
			return fileToolUtil.readFiles();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.i("FileNotFoundException",
					PathUtilManager.NEWS_TOCAO_CACHE_FILE_NAME
							+ "path not find");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			Log.i("IOException", PathUtilManager.NEWS_TOCAO_CACHE_FILE_NAME
					+ "read IOException");
			return null;
		}

	}

	public boolean saveNewsTuCao(String content) {
		File file = new File(PathUtilManager.getSdCardRootPath()
				+ PathUtilManager.FILE_CACHE_DIR
				+ PathUtilManager.NEWS_TOCAO_CACHE_FILE_NAME);
		FileToolUtil fileToolUtil = new FileToolUtil(file, mContext);
		try {
			fileToolUtil.writeFiles(content);
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Exception", PathUtilManager.NEWS_TOCAO_CACHE_FILE_NAME
					+ "write IOException");
			return false;
		}
	}

	public String getHottesToCao() {
		File file = new File(PathUtilManager.getSdCardRootPath()
				+ PathUtilManager.FILE_CACHE_DIR
				+ PathUtilManager.HOTTEST_TOCAO_CACHE_FILE_NAME);
		FileToolUtil fileToolUtil = new FileToolUtil(file, mContext);
		try {
			return fileToolUtil.readFiles();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.i("FileNotFoundException",
					PathUtilManager.HOTTEST_TOCAO_CACHE_FILE_NAME
							+ "path not find");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			Log.i("IOException", PathUtilManager.HOTTEST_TOCAO_CACHE_FILE_NAME
					+ "read IOException");
			return null;
		}

	}

	public boolean saveHottesTuCao(String content) {
		File file = new File(PathUtilManager.getSdCardRootPath()
				+ PathUtilManager.FILE_CACHE_DIR
				+ PathUtilManager.HOTTEST_TOCAO_CACHE_FILE_NAME);
		FileToolUtil fileToolUtil = new FileToolUtil(file, mContext);
		try {
			fileToolUtil.writeFiles(content);
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Exception", PathUtilManager.HOTTEST_TOCAO_CACHE_FILE_NAME
					+ "write IOException");
			return false;
		}
	}

}

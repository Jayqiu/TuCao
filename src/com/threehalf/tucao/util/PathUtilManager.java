package com.threehalf.tucao.util;

import android.os.Environment;

public class PathUtilManager {
	public static final String APP_ROOT_DIR = "/data/data/com.threehalf.tucao/cache";
	public static final String UPLOAD_FILES_DIR = getSdCardRootPath()
			+ "uploadfile/";
	public static final String SPEECH_FILES_DIR = getSdCardRootPath()
			+ "speech/";
	public static final String DOWNLOAD_FILES_DIR = getSdCardRootPath()
			+ "picture/";
	public static final String IMAGES_CACHE_DIR = getSdCardRootPath();
	public static final String FILE_CACHE_DIR = "filecache/";
	public static final String NEWS_TOCAO_CACHE_FILE_NAME = "news_tucao.wen";

	public static final String HOTTEST_TOCAO_CACHE_FILE_NAME = "hottest_tucao.wen";

	/**
	 * 获取缓存图片目录路径
	 * 
	 * @return
	 */
	public static String getSdCardRootPath() {
		if (isSdCardExit()) {
			return Environment.getExternalStorageDirectory().getPath()
					+ "/tucao/";
		} else {
			return APP_ROOT_DIR + "/tucao/";
		}
	}

	public static boolean isSdCardExit() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

}

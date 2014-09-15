package com.threehalf.tucao.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class FileToolUtil {
	private File file;
	private Context mContext;

	public FileToolUtil(File file, Context context) {
		try {
			createFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.file = file;
		this.mContext = context;
	}

	/**
	 * 保存文件内容
	 * 
	 * @param c
	 * @param fileName
	 *            文件名称
	 * @param content
	 *            内容
	 */
	public void writeFiles(String content) throws Exception {
		// 打开文件获取输出流，文件不存在则自动创建
		FileOutputStream fos = new FileOutputStream(file);
		// mContext.openFileOutput(file.getName(), mode);
		fos.write(content.getBytes());
		fos.close();
	}

	/**
	 * 读取文件内容
	 * 
	 * @param c
	 * @param fileName
	 *            文件名称
	 * @return 返回文件内容
	 */
	public String readFiles() throws IOException, FileNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream fis;
		fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		String content = baos.toString();
		fis.close();
		baos.close();
		return content;
	}

	private boolean createFile(File file) throws IOException {
		if (!file.exists()) {
			makeDir(file.getParentFile());
		}
		return file.createNewFile();
	}

	/**
	 * Enhancement of java.io.File#mkdir() Create the given directory . If the
	 * parent folders don't exists, we will create them all.
	 * 
	 * @see java.io.File#mkdir()
	 * @param dir
	 *            the directory to be created
	 */
	private void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

}

package com.lc.zy.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Files {

	/**
	 * 把输入流写入文件中.
	 * 
	 * @param file
	 * @param ins
	 */
	public static void write(File file, InputStream ins) {
		OutputStream out = null;
		try {
			File dir = file.getParentFile();
			if (dir != null && !dir.exists()) {
				dir.mkdirs();
			}
			
			out = new FileOutputStream(file);
			write(out, ins);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					new RuntimeException(e);
				}
			}
			
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					new RuntimeException(e);
				}
			}
		}
	}
	
	
	/**
	 * @param path
	 * @param ins
	 */
	public static void write(String path, InputStream ins) { 
		write(new File(path), ins);
	}
	
	
	/**
	 * @return 随机文件名.
	 */
	public static String newFilename() {
		String uuid = UUID.get();
		return uuid;
	}
	
	/**
	 * @param filename
	 * @return 文件扩展名.
	 */
	public static String getExtension(String filename) {
		int idx = filename.lastIndexOf('.');
		if (idx > 0) {
			return filename.substring(idx, filename.length());
		} else {
			return "";
		}
	}
	
	
	/**
	 * 读写输入输出流.
	 * 注意，调用此方法不自动关闭传递进来的输入输出流.
	 * @param out
	 * @param ins
	 */
	public static void write(OutputStream out, InputStream ins) { 
		try {
			byte[] buf = new byte[4096];
			
			for (int len = ins.read(buf); len > 0; len = ins.read(buf)) {
				out.write(buf, 0, len);
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 把文件以2进制流方式写入输出流.
	 * @param out
	 * @param file
	 */
	public static void write(OutputStream out, File file) { 
		InputStream ins = null;
		try {
			ins = new FileInputStream(file);
			write(out, ins);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}

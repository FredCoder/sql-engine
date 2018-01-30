package com.k.engine.cache.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

import com.k.engine.cache.bean.CacheLocalFile;


/**
 * Cache IO操作工具类
 * 
 * @author Fren
 *
 */
public class CacheIOUtils {

	private CacheIOUtils() {
	}

	/**
	 * 序列化对象为本地临时文件
	 * 
	 * @param value
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean createObjectFile(Object value, File file) throws IOException {
		if (value == null || file == null) {
			return false;
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(value);
			file.deleteOnExit();
			oos.close();
		} catch (IOException ex) {
			throw new IOException(ex);
		} 
		return true;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static CacheLocalFile remove(File file) throws Exception {
		if (file == null || false == file.exists()) {
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			CacheLocalFile clf = (CacheLocalFile) ois.readObject();
			ois.close();
			del(file);
			return clf;
		} catch (IOException e) {
			throw new IOException(e);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
	}

	/**
	 * 删除文件或者文件夹 注意：删除文件夹时不会判断文件夹是否为空，如果不空则递归删除子文件或文件夹<br>
	 * 某个文件删除失败会终止删除操作
	 * 
	 * @param file
	 *            文件对象
	 * @return 成功与否
	 * @throws IOException
	 *             IO异常
	 */
	public static boolean del(File file) throws IOException {
		if (file == null || false == file.exists()) {
			return false;
		}

		if (file.isDirectory()) {
			clean(file);
		}
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			throw new IOException(e);
		}
		return true;
	}

	/**
	 * 清空文件夹<br>
	 * 注意：清空文件夹时不会判断文件夹是否为空，如果不空则递归删除子文件或文件夹<br>
	 * 某个文件删除失败会终止删除操作
	 * 
	 * @param directory
	 *            文件夹
	 * @return 成功与否
	 * @throws IOException
	 *             IO异常
	 */
	public static boolean clean(File directory) throws IOException {
		if (directory == null || directory.exists() == false || false == directory.isDirectory()) {
			return true;
		}

		final File[] files = directory.listFiles();
		for (File childFile : files) {
			boolean isOk = del(childFile);
			if (isOk == false) {
				// 删除一个出错则本次删除任务失败
				return false;
			}
		}
		return true;
	}

}

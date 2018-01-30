package com.k.engine.cache;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.k.engine.cache.bean.CacheLocalFile;
import com.k.engine.cache.bean.KeyValue;
import com.k.engine.cache.utils.CacheIOUtils;

/**
 * Cache文件缓存补偿机制
 * 
 * @author Fren
 *
 */
public class DefaultCacheIoPlus<K, V> {

	private static final String PATH = "temp/";

	/**
	 * 回收临时文件，将穿透Cache的数据返回给高速缓存
	 * @param <T>
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public KeyValue<K, V> recycleTemp2Cache(K k) {
		if (k == null) {
			return null;
		}
		KeyValue<K, V> kmap = null;
		try {
			CacheLocalFile clf = CacheIOUtils.remove(new File(PATH + k));
			kmap = new KeyValue<K, V>(k, (V) clf.getClazz().cast(clf.getObject()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kmap;
	}

	/**
	 * 将高速缓存中的废弃数据，移动到本地临时文件中
	 * 
	 * @param k
	 * @param v
	 * @return boolean
	 */
	public boolean recycleCache2Temp(K k, V v) {
		boolean flag = true;
		if (k == null || v == null) {
			return false;
		}
		try {
			CacheIOUtils.createObjectFile(new CacheLocalFile(v.getClass(), v), new File(PATH + k));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
}

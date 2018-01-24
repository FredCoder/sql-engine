package com.k.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.k.engine.cache.DefaultCache;
import com.k.engine.cache.base.Cache;

public class CacheTest {
	static Cache cache = DefaultCache.getInstance("cache");
	
	static List<String> keyData = new ArrayList<String>();
	static List<String> selectArr = new ArrayList<String>();

	private final static int MAX_SIZE = 500000;
	
	private final static int MAX_SELECT = 100000;

	static {
		for (int i = 0; i < MAX_SIZE; i++) {
			String key = UUID.randomUUID().toString().replaceAll("-", "");
			String value = "";
			for (int j = 0; j < 3; ++j) {
				value += UUID.randomUUID().toString().replaceAll("-", "");
			}
			keyData.add(key);
			cache.put(key, value);
		}
		Random r = new Random(System.currentTimeMillis());
		for (int i = 0; i < MAX_SELECT; ++i) {
			String key = keyData.get(r.nextInt(keyData.size()));
			selectArr.add(key);
		}
	}
	
	/**
	 * @author Kevin.luo
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		RandomSelectByNumber(false);

		long endTime = System.currentTimeMillis();
		System.out.print("耗时:" + (endTime - startTime) + ";");
		System.out.print("[hash size]:" + cache.size() + ";");
		System.out.println("[selectArr size]:" + selectArr.size() + ";");
	}

	public static void RandomSelectByNumber(boolean printSW) {
		for (String key : selectArr) {
			if (printSW){
				System.out.println(cache.get(key));
			}
		}
	}

}

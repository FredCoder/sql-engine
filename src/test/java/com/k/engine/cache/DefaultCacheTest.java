package com.k.engine.cache;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.k.engine.cache.base.Cache;

public class DefaultCacheTest {
	static Cache<String, String> cache = null;
	
	static List<String> keyData = null;
	
	private final static int MAX_SIZE = 3000;
	
	private final static int MAX_SELECT = 100000;

	/**
	 * 1
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cache = DefaultCache.createCache("cache");
		keyData = new ArrayList<String>();
	}

	/**
	 * 4
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 2
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < MAX_SIZE; i++) {
			String key = UUID.randomUUID().toString().replaceAll("-", "");
			String value = "";
			for (int j = 0; j < 3; ++j) {
				value += UUID.randomUUID().toString().replaceAll("-", "");
			}
			keyData.add(key);
			cache.put(key, value);
		}
	}

	/**
	 * 3
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
		int missCount = 0;
		int hitCount = 0;
		long startTime = System.currentTimeMillis();
		for (String key : keyData) {
			cache.get(key);
		}
		long endTime = System.currentTimeMillis();
		System.out.print("耗时:" + (endTime - startTime) + ";");
		System.out.println("[hash size]:" + cache.size() + ";");
		System.out.println("[keyData size]:" + keyData.size() + ";");
		System.out.println("hit:" + cache.getCacheHits());
		System.out.println("miss:" + cache.getCacheMisses());
	}

}

package com.k.engine.cache;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.k.engine.cache.base.Cache;

import junit.framework.TestCase;

public class DefaultCacheTest extends TestCase {
	static List<String> keyData = new ArrayList<String>();
	static List<String> selectArr = new ArrayList<String>();
	private final static int MAX_SIZE = 500000;
	
	private final static int MAX_SELECT = 100000;

	@Test
	public void test() {
		//fail("Not yet implemented");
		Cache cache = DefaultCache.getInstance("test");
		System.out.println(cache.getMaxCacheSize());
		System.out.println(cache.getMaxLifetime());
		
		
	}

}

package com.k.engine.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DesTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String key = "14365e86793b4f1cb3af5523b03498de";
		Des app = new Des(key);
		System.out.println(app.decryptStr("67A0A110A694E3072E7AFEA6B07C5B86EB9724507052F62B"));
	}

}

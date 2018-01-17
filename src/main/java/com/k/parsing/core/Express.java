package com.k.parsing.core;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * 
 *
 */
public class Express {
	private static Logger logger = Logger.getLogger(Express.class);

	private static ExpressRunner runner;

	static {
		runner = new ExpressRunner();
	}

	private static ScriptEngineManager factory;

	static {
		factory = new ScriptEngineManager();
	}

	public static Object MathValue(String exp) throws ScriptException {
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		if (exp.equals(""))
			return "";
		Object o = engine.eval(exp);
		return o;
	}

	public static Object execute(String express) throws Exception {
		Object r;
		try {
			r = runner.execute(express, null, null, false, false);
			return r;
		} catch (Exception e) {
			logger.error("执行if指令表达式出现异常，表达式为[" + express + "]" + e.getMessage());
			return false;
		}
	}

	public static Object execute(String express, Map<String, Object> context) throws Exception {
		DefaultContext<String, Object> map = new DefaultContext<String, Object>();
		map.putAll(context);

		Object r = runner.execute(express, map, null, false, false);
		System.out.println(r);
		return r;
	}

	public static void main(String[] args) throws Exception {
		String dd = "\"('3','44','99')\"!=null";
		dd = "null!=null && null!=null";
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("dd", "2001-11-11");
		context.put("xx", 7);
		System.out.println(Express.execute(dd));
		String userName = "1' or '1'='1";
		String password = "123456";

		password = StringEscapeUtils.escapeSql(password);
		String sql = "SELECT COUNT(userId) FROM t_user WHERE userName='" + userName + "' AND password ='" + password
				+ "'";
		System.out.println(sql);
	}
}

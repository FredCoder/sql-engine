package com.k.parsing.result;

import com.k.parsing.command.DBCommand;

/**
 * 
 *
 */
public class AnalyResults {

	/**
	 * 解析出来的sql语句（带占位符）
	 */
	private String sql;

	/***
	 * 执行sql所需要的参数
	 */
	private Object parameter;

	private DBCommand db;

	public AnalyResults(String sql, Object parameter, DBCommand db) {
		super();
		this.sql = sql;
		this.parameter = parameter;
		this.db = db;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	public DBCommand getDb() {
		return db;
	}

	public void setDb(DBCommand db) {
		this.db = db;
	}
}

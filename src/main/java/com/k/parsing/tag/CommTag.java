/**
 * 
 */
package com.k.parsing.tag;

import com.k.parsing.command.DBCommand;
import com.k.parsing.command.TrimCommand;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * TODO
 * 
 * @author Kevin.luo
 * @date 2018年1月11日 上午9:57:37
 * 
 */
public class CommTag {
	@XStreamAsAttribute
	private String key;
	@XStreamAsAttribute
	private String parameterType;
	@XStreamAsAttribute
	private String author;
	@XStreamAsAttribute
	private String des;
	private DBCommand db;
	public String sql;
	private TrimCommand trim;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public TrimCommand getTrim() {
		return trim;
	}

	public void setTrim(TrimCommand trim) {
		this.trim = trim;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public DBCommand getDb() {
		return db;
	}

	public void setDb(DBCommand db) {
		this.db = db;
	}
	
	
}

package com.k.parsing.command;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * <p>Title:IfCommand</p>
 * <p>Description:  if 指令    </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: XXXX</p>
 * <p>Date:2015年1月3日</p>
 * @author CHEN_JIAN
 * @version 1.0
 */
@XStreamAlias(value="if")
public class IfCommand {
	@XStreamAsAttribute
	private String test;
	@XStreamAsAttribute
	private String prefix; //and | or
	@XStreamAsAttribute
	private String des;
	private String exp;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getPrefix() {
		if(StringUtils.isEmpty(prefix)){
			prefix="";
		}
		
		return prefix.trim();
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}

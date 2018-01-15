package com.k.parsing.command;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * 
 * <p>Title:GroupCommand</p>
 * <p>Description: 分组指令  </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: XXXX</p>
 * <p>Date:2015年1月28日</p>
 * @author CHEN_JIAN
 * @version 1.0
 */

@XStreamAlias(value="group")
public class GroupCommand {
	private String exp;
	
	private String having;

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getHaving() {
		return having;
	}

	public void setHaving(String having) {
		this.having = having;
	}
	
	

}

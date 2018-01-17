package com.k.parsing.command;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 *
 */
@XStreamAlias(value = "group")
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

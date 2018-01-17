package com.k.parsing.command;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * 
 */
@XStreamAlias(value = "order")
public class OrderCommand {

	@XStreamAsAttribute
	private String value;

	private String exp;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}
}

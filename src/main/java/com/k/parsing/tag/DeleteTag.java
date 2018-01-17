package com.k.parsing.tag;

import com.k.parsing.command.WhereCommand;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 *
 */
@XStreamAlias(value = "delete")
public class DeleteTag extends CommTag {
	private WhereCommand where;

	public WhereCommand getWhere() {
		return where;
	}

	public void setWhere(WhereCommand where) {
		this.where = where;
	}
}

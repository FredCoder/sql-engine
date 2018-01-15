/**
 * 
 */
package com.k.parsing.tag;

import com.k.parsing.command.WhereCommand;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * TODO
 * 
 * @author Kevin.luo
 * @date 2018年1月11日 上午9:56:43
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

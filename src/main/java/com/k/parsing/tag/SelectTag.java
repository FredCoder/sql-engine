package com.k.parsing.tag;

import com.k.parsing.command.GroupCommand;
import com.k.parsing.command.OrderCommand;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 *
 */
@XStreamAlias(value = "select")
public class SelectTag extends CommTag {
	@XStreamAsAttribute
	private String resultType;

	private OrderCommand order;

	private GroupCommand group;

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public OrderCommand getOrder() {
		return order;
	}

	public void setOrder(OrderCommand order) {
		this.order = order;
	}

	public GroupCommand getGroup() {
		return group;
	}

	public void setGroup(GroupCommand group) {
		this.group = group;
	}

}

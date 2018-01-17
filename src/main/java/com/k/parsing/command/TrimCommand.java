package com.k.parsing.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 
 *
 */
@XStreamAlias("trim")
public class TrimCommand {
	// 前缀
	@XStreamAsAttribute
	private String prefix;

	@XStreamImplicit
	private List<IfCommand> ifCommand;

	public String getPrefix() {
		if (StringUtils.isEmpty(prefix))
			this.prefix = "";
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<IfCommand> getIfCommand() {
		return ifCommand;
	}

	public void setIfCommand(List<IfCommand> ifCommand) {
		this.ifCommand = ifCommand;
	}

	public void addIfCommand(IfCommand command) {
		if (ifCommand == null) {
			ifCommand = new ArrayList<IfCommand>(0);
		}
		ifCommand.add(command);
	}
}

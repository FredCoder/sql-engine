package com.k.parsing.command;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 
 *
 */
@XStreamAlias(value = "where")
public class WhereCommand {

	@XStreamImplicit
	private List<IfCommand> ifCommand;

	public void addIfCommand(IfCommand command) {
		if (ifCommand == null) {
			ifCommand = new ArrayList<IfCommand>(0);
		}
		ifCommand.add(command);
	}

	public List<IfCommand> getIfCommand() {
		return ifCommand;
	}

	public void setIfCommand(List<IfCommand> ifCommand) {
		this.ifCommand = ifCommand;
	}
}

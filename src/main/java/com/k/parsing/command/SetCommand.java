package com.k.parsing.command;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 
 * <p>Title:SetCommand</p>
 * <p>Description:  set 指令  </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: XXXX</p>
 * <p>Date:2015年1月12日</p>
 * @author CHEN_JIAN
 * @version 1.0
 */
@XStreamAlias(value="set")
public class SetCommand {
	@XStreamImplicit
	private List<IfCommand>ifCommand;

	public List<IfCommand> getIfCommand() {
		return ifCommand;
	}

	public void setIfCommand(List<IfCommand> ifCommand) {
		this.ifCommand = ifCommand;
	}

	public void addIfCommand(IfCommand command){
		if(ifCommand==null){
			ifCommand=new ArrayList<IfCommand>(0);
		}
			ifCommand.add(command);
		
	}

}

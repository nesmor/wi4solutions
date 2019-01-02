package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.ManagerResponse;

public class RestartCommand extends AbstractAsteriskAction{
	
	Integer code = null;
	String command = "core restart now";
	ManagerResponse response;
	@Override
	public void execute() throws Exception {
		CommandAction action = new CommandAction();
		action.setCommand(this.command);
		this.response = this.managerConnection.sendAction(action);
		this.code = 0;
	}
	@Override
	public Integer getCode() {
		return this.code;
	}
	
	
	
	
}

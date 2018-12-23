package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.ManagerResponse;

public class RestartCommand extends AbstractAsteriskAction{
	
	String command = "restart now";
	ManagerResponse response;
	@Override
	public void execute() throws Exception {
		CommandAction action = new CommandAction();
		action.setCommand(this.command);
		this.response = this.managerConnection.sendAction(action);
	}
	
	
	
	
}

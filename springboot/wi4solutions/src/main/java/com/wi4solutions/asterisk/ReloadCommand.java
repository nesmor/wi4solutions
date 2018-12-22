package com.wi4solutions.asterisk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.ManagerResponse;

public class ReloadCommand extends AbstractAsteriskAction{

	String command = "relad";
	ManagerResponse response;
	@Override
	public void execute() throws Exception {
		CommandAction action = new CommandAction();
		action.setCommand(this.command);
		this.response = this.managerConnection.sendAction(action);
	}
	

}

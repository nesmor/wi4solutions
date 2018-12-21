package com.wi4solutions.asterisk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.action.CoreShowChannelsAction;
import org.asteriskjava.manager.response.ManagerResponse;

public class ActiveCallsComand extends AbstractAsteriskAction{
	

	@Override
	public void execute() throws Exception {
		//CoreShowChannelsAction action = new CoreShowChannelsAction();
		CommandAction action = new CommandAction();
		action.setCommand("core show channels");
		ManagerResponse response = this.managerConnection.sendAction(action);
		System.out.println(response.toString());
	}

}

package com.wi4solutions.asterisk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;

import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.action.CoreShowChannelsAction;
import org.asteriskjava.manager.response.ManagerResponse;

public class ActiveCallsComand extends AbstractAsteriskAction<ActiveCallsReponse>{
	
	ManagerResponse response;

	@Override
	public void execute() throws Exception {
		//CoreShowChannelsAction action = new CoreShowChannelsAction();
		CommandAction action = new CommandAction();
		action.setCommand("core show channels");
		this.response = this.managerConnection.sendAction(action);
		System.out.println(response.toString());
	}

	@Override
	public ActiveCallsReponse getResponse() {
		Set<String> keys = this.response.getAttributes().keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println("****************************************");
			System.out.println("***********Value set : " + key );
			System.out.println("***********Key set : " + key );
			System.out.println("***********Value set : " + this.response.getAttribute(key));
			System.out.println("****************************************");
		}
		
		return null;
	}
	
	public String getResponseString() {
		return (String) this.response.getAttribute("_result_");
	}

	@Override
	public Integer getCode() {
		// TODO Auto-generated method stub
		return null;
	}

}

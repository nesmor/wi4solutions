package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.CoreShowChannelsAction;

public class AsteriskServer {
	private String host;
	
	private String username;
	
	private String password;
	
	private ManagerConnection managerConnection;
	
	public AsteriskServer() {
		ManagerConnectionFactory factory = new ManagerConnectionFactory(this.host, this.username, this.password);
		this.managerConnection = factory.createManagerConnection();
	}

}

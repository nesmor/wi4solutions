package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.CoreShowChannelsAction;
import org.springframework.stereotype.Service;

@Service
public class AsteriskInvoker<T>{
	
	private String host = "192.168.210.13";
	
	private String username = "wixtel";
	
	private String password = "w1xt3l!mgr";
	
	private ManagerConnection managerConnection;
	
    public AsteriskServer asteriskServer;
	
	public AsteriskInvoker() {
		
	}
	
	public void connect() {
		ManagerConnectionFactory factory = new ManagerConnectionFactory(this.host, this.username, this.password);
		this.managerConnection = factory.createManagerConnection();
	}
	
	
	Action<T> action;
	
	public void setAction(Action<T> action) {
		this.action = action;
	}
	
    public  void invoke(Action<T> action) throws Exception 
    {	
    	this.action = action;
    	this.action.setManagerConnection(managerConnection);
    	this.action.execute();
    }

    public T getResponse(){
    	return this.action.getResponse();
    };
    
    public int getCode() {
	   return this.action.getCode();
    }
    
    public String getMessage() {
 	   return this.action.getMessage();
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Action<T> getAction() {
		return action;
	} 

    
}

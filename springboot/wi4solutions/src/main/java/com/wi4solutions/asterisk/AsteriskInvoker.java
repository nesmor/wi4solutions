package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.CoreShowChannelsAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.wi4solutions.config.ApplicationProperties;

@Service
public class AsteriskInvoker<T>{
	
	ApplicationProperties applicationProperties;
	
	
	public AsteriskInvoker(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}
	
	Action<T> action;
	
	public void setAction(Action<T> action) {
		this.action = action;
	}
	
    public  void invoke(Action<T> action) throws Exception 
    {	
    	this.action = action;
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

	public Action<T> getAction() {
		return action;
	} 

	
    
}

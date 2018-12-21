package com.wi4solutions.asterisk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.asteriskjava.manager.ManagerConnection;

public class ReloadCommand extends AbstractAction{

	StringBuilder response = new StringBuilder();
	
	String message ;
	
	Integer code;
	
	public ReloadCommand() {
		command = new String[]{"watch," ,"asterisk", "-vvvvvrx", "reload"};
	}

	@Override
	public void setManagerConnection(ManagerConnection managerConnection) {
		// TODO Auto-generated method stub
		
	}

}

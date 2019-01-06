package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;

public class LogoutCommand extends AbstractAsteriskAction{
	
	ManagerConnection managerConnection ;

	@Override
	public void setManagerConnection(ManagerConnection managerConnection) {
		this.managerConnection = managerConnection;
	}

	@Override
	public void execute() throws Exception {
		managerConnection.logoff();
	}

	@Override
	public Integer getCode() {
		// TODO Auto-generated method stub
		return null;
	}

}

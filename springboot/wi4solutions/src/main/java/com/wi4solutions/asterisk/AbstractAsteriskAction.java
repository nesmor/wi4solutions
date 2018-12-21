package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;

public abstract class AbstractAsteriskAction implements Action<String>{

	ManagerConnection managerConnection;

	@Override
	public String getResponse() {
		return null;
	}

	@Override
	public void handError() {
		
	}

	@Override
	public Integer getCode() {
		return null;
	}

	@Override
	public String getMessage() {
		return null;
	}

	@Override
	public void setManagerConnection(ManagerConnection managerConnection) {
		this.managerConnection = managerConnection;
	}

}

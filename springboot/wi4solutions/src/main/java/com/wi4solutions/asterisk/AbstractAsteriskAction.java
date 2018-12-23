package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;

public abstract class AbstractAsteriskAction<T> implements Action<T>{

	ManagerConnection managerConnection;

	@Override
	public T getResponse() {
		return null;
	};

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

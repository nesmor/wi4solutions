package com.wi4solutions.asterisk;

import org.asteriskjava.manager.ManagerConnection;

public interface Action<T> {
	static Integer SUCCESS_CODE = 0;
	static Integer ERROR_CODE = 1;
	
	public void execute() throws Exception;
	
	public T getResponse();
	
	public void handError();
	
	public Integer getCode();
	
	public String getMessage();
	
	public void setManagerConnection(ManagerConnection managerConnection);
}

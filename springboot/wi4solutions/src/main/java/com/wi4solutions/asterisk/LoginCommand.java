package com.wi4solutions.asterisk;

import java.io.IOException;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.TimeoutException;

public class LoginCommand extends AbstractAsteriskAction{

	@Override
	public void execute() throws IllegalStateException, IOException, AuthenticationFailedException, TimeoutException {
		if(managerConnection.getState() != managerConnection.getState().CONNECTED)
			managerConnection.login();
	}

	@Override
	public Integer getCode() {
		// TODO Auto-generated method stub
		return null;
	}

}

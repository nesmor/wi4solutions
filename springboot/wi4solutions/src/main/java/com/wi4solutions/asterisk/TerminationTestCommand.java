package com.wi4solutions.asterisk;

import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.wi4solutions.config.ApplicationProperties;

public class TerminationTestCommand extends AbstractAsteriskAction {
	
	private String number;
	
	Integer code = null;
	
	OriginateAction originateAction = null;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	public TerminationTestCommand(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}
	

	@Override
	public void execute() throws Exception {
		originateAction = new OriginateAction();
//        originateAction.setChannel(applicationProperties.getAsterisk().getChannel() + this.number + "@GOIP");
		originateAction.setChannel(this.number);
        originateAction.setContext(applicationProperties.getAsterisk().getContext());
       // originateAction.setCallerId(applicationProperties.getAsterisk().getCallerId());
        originateAction.setExten(applicationProperties.getAsterisk().getExtend());
        originateAction.setPriority(new Integer(applicationProperties.getAsterisk().getPriority()));
        ManagerResponse managerResponse  = this.managerConnection.sendAction(originateAction);
        if(managerResponse.getUniqueId() != null) {
        	this.code = 0;
        }
	}

	@Override
	public Integer getCode() {
		return this.code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
}

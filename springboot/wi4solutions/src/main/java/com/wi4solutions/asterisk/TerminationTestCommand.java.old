package com.wi4solutions.asterisk;

import java.security.Key;

import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wi4solutions.config.ApplicationProperties;
import com.wi4solutions.web.rest.SipPeerResource;

public class TerminationTestCommand extends AbstractAsteriskAction {
	
	private final Logger log = LoggerFactory.getLogger(TerminationTestCommand.class);
	
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
        log.debug("*******************Call sended***********************");
        log.debug("*****Message:" + managerResponse.getMessage());
        log.debug("*****UniqueId:" + managerResponse.getUniqueId());
        log.debug("*****Output:" + managerResponse.getOutput());
        log.debug("*****Response:" + managerResponse.getResponse());
        log.debug("*****----------Attributes--------*****");
        managerResponse.getAttributes().forEach((key, value) -> {
        	log.debug("*****key:" + key + "-- Value:" + value);
        });
        log.debug("*****-------End Attributes--------*****");
        log.debug("**********End Call****************************");
        
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

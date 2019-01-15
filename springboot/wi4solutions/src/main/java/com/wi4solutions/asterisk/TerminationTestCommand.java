package com.wi4solutions.asterisk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminationTestCommand extends AbstractAction{
	
	private final Logger log = LoggerFactory.getLogger(TerminationTestCommand.class);
	
	private String number;
	
	
	public TerminationTestCommand() {
		
		this.command = new String[] {"asterisk", "-rx",""};
	}
	
	
	@Override
	public void handError() {
		this.code = Action.ERROR_CODE;
		this.message = "wi4SolutionsApp.asterisk.message.call-error";
	}


	@Override
	public void handSuccess() {
		this.code = Action.SUCCESS_CODE;
		this.message = "wi4SolutionsApp.asterisk.message.call-sended";
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
		this.command[2] = " channel originate " + this.number + " extension 123@test-calls ";
		log.info("channel originate " + this.number + " extension 123@test-calls ");
	}
	
	

}

package com.wi4solutions.asterisk;

public class ActiveCallsCommand extends AbstractAction{
	
	public ActiveCallsCommand() {
		this.command = new String[] {"asterisk", "-rx", "core show channels" };
	}
	
	@Override
	public void handError() {
		this.code = Action.ERROR_CODE;
		this.message = "wi4SolutionsApp.asterisk.message.error";
	}


	@Override
	public void handSuccess() {
		this.code = Action.SUCCESS_CODE;
		this.message = "wi4SolutionsApp.asterisk.message.success";
	}
	
	public String getResponseString() {return this.getResponse();}

}

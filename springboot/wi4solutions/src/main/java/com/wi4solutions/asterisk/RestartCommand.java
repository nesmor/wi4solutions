package com.wi4solutions.asterisk;

public class RestartCommand extends AbstractAction{
	
	public RestartCommand() {
		this.command = new String[] {"asterisk", "-rx", "core restart now" };
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


}

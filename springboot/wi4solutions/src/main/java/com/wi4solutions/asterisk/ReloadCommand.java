package com.wi4solutions.asterisk;


public class ReloadCommand extends AbstractAction {

	
	public ReloadCommand() {
		this.command = new String[] {"asterisk", "-rx", "core reload" };
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

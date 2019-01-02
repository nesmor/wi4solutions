package com.wi4solutions.asterisk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class AbstractAction implements Action<String>{
	
	String[] command ;
	
	StringBuilder response = new StringBuilder();
	
	String message ;
	
	Integer code;
	
	@Override
	public void execute() throws Exception {
	try {
	        Process pb = Runtime.getRuntime().exec(command);
	        String line;
	        BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
	        while ((line = input.readLine()) != null) {
	            response.append(line);
	        }
	        input.close();
		}catch (Exception e) {
			this.handError();
		}
		this.code = SUCCESS_CODE;
		this.message = "command excetuted successfully";
	}
		

	@Override
	public String getResponse() {
		return response.toString();
	}

	@Override
	public void handError() {
		
	}

	@Override
	public Integer getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}

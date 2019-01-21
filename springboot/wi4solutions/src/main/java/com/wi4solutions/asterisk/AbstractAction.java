package com.wi4solutions.asterisk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAction implements Action<String>{

	private final Logger log = LoggerFactory.getLogger(AbstractAction.class);

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
							response.append("*");
	        }
	        input.close();
		}catch (Exception e) {
			this.handError();
		}
			log.info(response.toString());
		this.handSuccess();
	}


	@Override
	public String getResponse() {
		String commandResponse = response.toString();
		response.setLength(0);
		return commandResponse;

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

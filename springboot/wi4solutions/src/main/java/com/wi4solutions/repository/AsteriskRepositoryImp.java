package com.wi4solutions.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.wi4solutions.asterisk.Action;
import com.wi4solutions.asterisk.ActiveCallsComand;
import com.wi4solutions.asterisk.AsteriskInvoker;
import com.wi4solutions.asterisk.CommandFailedException;
import com.wi4solutions.asterisk.CommandNotFoundException;
import com.wi4solutions.domain.ActiveCall;
import com.wi4solutions.service.util.RandomUtil;

@Repository
public class AsteriskRepositoryImp  implements AsteriskRepository{

	@Autowired
	AsteriskInvoker<String> asteriskInvoker ;
//	@Override
//	public List<ActiveCall> findAll() {
//		List<ActiveCall> activeCalls = new ArrayList();
//		ActiveCall a = new ActiveCall();
//		for(int i = 0; i < 10 ; i++) {
//			a = new ActiveCall();
//			a.setNumber(RandomUtil.generatePassword());
//			a.setOriginator(RandomUtil.generatePassword());
//			a.setAni(RandomUtil.generatePassword());
//			a.setDni(RandomUtil.generatePassword());
//			a.setDuration(RandomUtil.generatePassword());
//			a.setGateway(RandomUtil.generatePassword());
//			a.setId(RandomUtil.generatePassword());
//			a.setDuration(RandomUtil.generatePassword());
//			a.setStatus(RandomUtil.generatePassword());
//			activeCalls.add(a);
//		}
//		return activeCalls;
//	}
	
	
	@Override
	public List<ActiveCall> findAll(){
		List<ActiveCall> activeCalls ;
		ActiveCallsComand activeCallsCommand = new ActiveCallsComand();
		try {
			asteriskInvoker.invoke(activeCallsCommand);
		} catch (Exception e) {
			throw new CommandFailedException();
		}
		int code = asteriskInvoker.getCode();
		if(code == 1)
			throw new CommandFailedException();
		String response = asteriskInvoker.getResponse();
		List<String> lines = Arrays.asList(response.split("\n"));
		activeCalls = lines.stream().map(((line) -> this.getActiveCall(line))).collect(Collectors.toList());
		return activeCalls;
	}

	@Override
	public List<ActiveCall> findAll(Sort sort) {
		return null;
	}

	private ActiveCall getActiveCall(String line) {
		line.split("/");
		ActiveCall a = new ActiveCall();
		return a;
		
	}
}

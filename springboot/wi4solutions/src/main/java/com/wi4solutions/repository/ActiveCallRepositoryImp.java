package com.wi4solutions.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.wi4solutions.domain.ActiveCall;
import com.wi4solutions.service.util.RandomUtil;

@Repository
public class ActiveCallRepositoryImp  implements ActiveCallRepository{

	@Override
	public List<ActiveCall> findAll() {
		List<ActiveCall> activeCalls = new ArrayList();
		ActiveCall a = new ActiveCall();
		for(int i = 0; i < 10 ; i++) {
			a = new ActiveCall();
			a.setNumber(RandomUtil.generatePassword());
			a.setOriginator(RandomUtil.generatePassword());
			a.setAni(RandomUtil.generatePassword());
			a.setDni(RandomUtil.generatePassword());
			a.setDuration(RandomUtil.generatePassword());
			a.setGateway(RandomUtil.generatePassword());
			a.setId(RandomUtil.generatePassword());
			a.setDuration(RandomUtil.generatePassword());
			a.setStatus(RandomUtil.generatePassword());
			activeCalls.add(a);
		}
		return activeCalls;
	}

	@Override
	public List<ActiveCall> findAll(Sort sort) {
		return null;
	}

}

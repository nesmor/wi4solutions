package com.wi4solutions.asterisk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wi4solutions.Wi4SolutionsApp;
import com.wi4solutions.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Wi4SolutionsApp.class)
@Transactional
public class AsteriskInvokerTest {
	
	@Autowired
	AsteriskInvoker asteriskInvoker ;
	
	@Before
    public void init() {
		
    }

    @Test
    @Transactional
    public void testCoreShowChannels() {
    	try {
    		//this.asteriskInvoker.setHost("192.31.50.204");
    		this.asteriskInvoker.setHost("192.168.210.13");
    		this.asteriskInvoker.setPassword("w1xt3l!mgr");
    		this.asteriskInvoker.setUsername("wixtel");
    		this.asteriskInvoker.connect();
	    	LoginCommand login = new LoginCommand();
	    	ActiveCallsComand activeCalls = new ActiveCallsComand();
	    	LogoutCommand logout = new LogoutCommand();
	    	this.asteriskInvoker.invoke(login);
	    	this.asteriskInvoker.invoke(activeCalls);
	     	this.asteriskInvoker.getResponse();
	    	this.asteriskInvoker.invoke(logout);
    	}catch (Exception e) {
    		e.printStackTrace();
    		
    	}
    }

	
}

package com.wi4solutions.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wi4solutions.Wi4SolutionsApp;
import com.wi4solutions.domain.CallReport;
import com.wi4solutions.domain.User;
import com.wi4solutions.repository.CallDetailRecordRepository;
import com.wi4solutions.repository.CallReportRepositoryOld;
import com.wi4solutions.repository.CallReportRepositoryImp;
import com.wi4solutions.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Wi4SolutionsApp.class)
public class CallReportServiceIntTest {
	
	
	@Autowired
	private CallReportRepositoryOld callReportRepository;
	
	@Autowired 
	private CallReportRepositoryImp CallReportRepositoryImp;
	
	@Autowired
	private CallReportServiceOld callReportService;
	
	private Date fromDate, toDate;
	
    @Before
    public void init() {
    }
    

    @Test
    @Transactional
    public void testGetCallReport() {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        	fromDate = new Date(sdf.parse("31-03-2018").getTime());
        	toDate = new Date(sdf.parse("1-4-2018").getTime());
        	CallReport report = this.CallReportRepositoryImp.findByFromDateAndToDate(this.fromDate, this.toDate);
            assertThat(report).isNotNull();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        
    }

    @Test
    @Transactional
    public void testFindByHour() {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        	fromDate = new Date(sdf.parse("31-03-2018").getTime());
        	toDate = new Date(sdf.parse("1-4-2018").getTime());
        	Object report = this.CallReportRepositoryImp.findByHour(this.fromDate);
            assertThat(report).isNotNull();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        
    }
    
    @Test
    @Transactional
    public void testFindByDate() {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        	fromDate = new Date(sdf.parse("31-03-2018").getTime());
        	toDate = new Date(sdf.parse("1-4-2018").getTime());
        	Object report = this.CallReportRepositoryImp.findByDate(this.fromDate, toDate);
            assertThat(report).isNotNull();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        
    }


    
	

}

package com.wi4solutions.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wi4solutions.domain.Authority;
import com.wi4solutions.domain.CallReport;
import com.wi4solutions.repository.AuthorityRepository;
import com.wi4solutions.repository.CallDetailRecordRepository;
import com.wi4solutions.repository.CallReportRepositoryOld;
import com.wi4solutions.repository.UserRepository;

@Service
@Transactional
public class CallReportServiceOld {
	
	private final Logger log = LoggerFactory.getLogger(UserService.class);
	
	private final CallReportRepositoryOld callReportRepository; 
	
    private final CacheManager cacheManager;
    
    public CallReportServiceOld(CallReportRepositoryOld callReportRepository, CacheManager cacheManager) {
        this.callReportRepository = callReportRepository;
        this.cacheManager = cacheManager;
    }
    
    public Object getCallReport(Date fromDate, Date toDate) {
        return callReportRepository.getCallReport(fromDate, toDate);
    }
    
}

package com.wi4solutions.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.wi4solutions.domain.CallReport;

public interface CallReportRepositoryCustom {
	
	public List<CallReport> findAll(Date fromDate, Date toDate, String type);
	
	public CallReport findByFromDateAndToDate(Date fromDate, Date toDate);
	
	public List<CallReport> findByHour(Date date);
	
	public List<CallReport> findByDate(Date fromDate, Date toDate);
	
}

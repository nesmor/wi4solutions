package com.wi4solutions.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import com.wi4solutions.domain.CallReport;



public interface CallReportRepositoryOld extends CrudRepository<CallReport, Long>{
	  
	  @Procedure
	  CallReport getCallReport(Date fromDate, Date toDate);
	  
	  @Procedure
	  String getCallReport2(Date fromDate, Date toDate);
}

	package com.wi4solutions.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.wi4solutions.domain.Authority;
import com.wi4solutions.domain.CallReport;
import com.wi4solutions.service.dto.UserDTO;

@Repository
public class CallReportRepositoryImp implements CallReportRepositoryCustom{

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<CallReport> findAll(Date fromDate, Date toDate, String type) {
		// TODO Auto-generated method stub
		List<Object[]> reports = null;
		String sql = null;
		Query q = null;
		switch(type) {
			case "week":
				 q = em.createQuery(
						"SELECT YEAR(c.calldate), WEEK(c.calldate) AS H , COUNT(c.id) AS CONNECTED_CALLS, " + 
						"SUM(c.duration) AS DURATION, (SELECT COUNT(cd.id) FROM Cdr cd  " + 
						"WHERE cd.disposition != 'ANSWERED' AND  WEEK(cd.calldate) = WEEK(c.calldate)) AS NOT_CONNECTED " + 
						"FROM Cdr c  WHERE c.disposition = 'ANSWERED' " + 
						"AND c.duration > 0 AND " + 
						"DATE(c.calldate) BETWEEN :fromDate AND :toDate  GROUP BY WEEK(c.calldate) ORDER BY YEAR(c.calldate) DESC, MONTH(c.calldate) ASC ");
			break;
			case "monthy":
				 q = em.createQuery(
						"SELECT YEAR(c.calldate), MONTH(c.calldate) AS H , COUNT(c.id) AS CONNECTED_CALLS, " + 
						"SUM(c.duration) AS DURATION, (SELECT COUNT(cd.id) FROM Cdr cd  " + 
						"WHERE cd.disposition != 'ANSWERED' AND  MONTH(cd.calldate) = MONTH(c.calldate)) AS NOT_CONNECTED " + 
						"FROM Cdr c  WHERE c.disposition = 'ANSWERED' " + 
						"AND c.duration > 0 AND " + 
						"DATE(c.calldate) BETWEEN :fromDate AND :toDate  GROUP BY MONTH(c.calldate) ORDER BY YEAR(c.calldate) DESC, WEEK(c.calldate) ASC ");
		   break;
		
		
		}
		
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		reports = q.getResultList();
		return (List<CallReport>) reports.stream().map(e -> {return this.mapCallReportType(e);}).collect(Collectors.toList());
	}

	@Override
	public CallReport findByFromDateAndToDate(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		CallReport report = new CallReport();
		StoredProcedureQuery findAll = em.createNamedStoredProcedureQuery("getCallReport2");
		findAll.setParameter("from_date", fromDate);
		findAll.setParameter("to_date", toDate);
		findAll.execute();
		report.setAcd(((Long)findAll.getOutputParameterValue(CallReport.PARAM_OUT_ACD)).floatValue());
		report.setAsr((Float)findAll.getOutputParameterValue(CallReport.PARAM_OUT_ASR));
		report.setTotalCalls((Long)findAll.getOutputParameterValue(CallReport.PARAM_OUT_CONNECTED_TOTAL_CALLS));
		report.setTotalDuration((Long)findAll.getOutputParameterValue(CallReport.PARAM_OUT_TOTAL_DURATION));
		report.setFailedCalls((Long)findAll.getOutputParameterValue(CallReport.PARAM_OUT_FAILED_CALLS));
		report.setConnectedCalls((Long)findAll.getOutputParameterValue(CallReport.PARAM_OUT_CONNECTED_CALLS));
		return report;
	}
	
	
	@Override
	public List<CallReport> findByHour(Date date) {
		// TODO Auto-generated method stub
		List<Object[]> reports = null;
		Query q = em.createQuery(
				"SELECT AVG(HOUR(c.calldate)) AS H , COUNT(c.id) AS CONNECTED_CALLS, " + 
				"SUM(c.duration) AS DURATION, (SELECT COUNT(cd.id) FROM Cdr cd  " + 
				"WHERE cd.disposition != 'ANSWERED' AND  HOUR(cd.calldate) = HOUR(c.calldate)) AS NOT_CONNECTED " + 
				"FROM Cdr c  WHERE c.disposition = 'ANSWERED' " + 
				"AND c.duration > 0 AND " + 
				"DATE(c.calldate) = :date GROUP BY HOUR(c.calldate)");
		q.setParameter("date", date);
		reports = q.getResultList();
		return (List<CallReport>)  reports.stream().map(e -> {return this.mapCallReport(e);}).collect(Collectors.toList());
	}
	
	
	@Override
	public List<CallReport> findByDate(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		List<Object[]> reports = null;
		Query q = em.createQuery(
				"SELECT DATE(c.calldate) AS H , COUNT(c.id) AS CONNECTED_CALLS, " + 
				"SUM(c.duration) AS DURATION, (SELECT COUNT(cd.id) FROM Cdr cd  " + 
				"WHERE cd.disposition != 'ANSWERED' AND  DATE(cd.calldate) = DATE(c.calldate)) AS NOT_CONNECTED " + 
				"FROM Cdr c  WHERE c.disposition = 'ANSWERED' " + 
				"AND c.duration > 0 AND " + 
				"DATE(c.calldate) BETWEEN :fromDate AND :toDate  GROUP BY DATE(c.calldate)");
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		reports = q.getResultList();
		return (List<CallReport>) reports.stream().map(e -> {return this.mapCallReport(e);}).collect(Collectors.toList());
	}
	
	
	
	
	
	private CallReport mapCallReport(Object[] data) {
		CallReport callReport = new CallReport();
		if(data[0] instanceof Date){
			callReport.setDate((Date)data[0]);
		}else {
			callReport.setHour((Double)data[0]);
		}
		callReport.setConnectedCalls((Long) data[1]);
		callReport.setTotalDuration((Long) data[2]);
		callReport.setFailedCalls((Long)data[3]);
		callReport.setTotalCalls(callReport.getConnectedCalls() + callReport.getFailedCalls());
		callReport.setAcd(callReport.getTotalDuration().floatValue() / callReport.getConnectedCalls().floatValue());
		callReport.setAsr(callReport.getConnectedCalls().floatValue() / callReport.getTotalCalls().floatValue());
		return callReport;
	}
	
	private CallReport mapCallReportType(Object[] data) {
		CallReport callReport = new CallReport();
		if(data[0] instanceof Double){
			callReport.setYear(((Double)data[0]).intValue());
		}else if(data[0] instanceof Integer){
			callReport.setYear((Integer)data[0]);
		}
		
		if(data[1] instanceof Double){
			callReport.setWeek(((Double)data[1]).intValue());
		}else if(data[1] instanceof Integer){
			callReport.setWeek((Integer)data[1]);
		}
	
		if(data[2] instanceof Long){
			callReport.setConnectedCalls((Long) data[2]);
		}else if(data[2] instanceof Integer){
			callReport.setConnectedCalls(((Integer)data[2]).longValue());
		}
		
		if(data[3] instanceof Long){
			callReport.setTotalDuration((Long) data[3]);
		}else if(data[3] instanceof Integer){
			callReport.setTotalDuration(((Integer)data[3]).longValue());
		}
		
		if(data[4] instanceof Long){
			callReport.setFailedCalls((Long) data[4]);
		}else if(data[4] instanceof Integer){
			callReport.setFailedCalls(((Integer)data[4]).longValue());
		}
		
		callReport.setTotalCalls(callReport.getConnectedCalls() + callReport.getFailedCalls());
		callReport.setAcd(callReport.getTotalDuration().floatValue() / callReport.getConnectedCalls().floatValue());
		callReport.setAsr(callReport.getConnectedCalls().floatValue() / callReport.getTotalCalls().floatValue());
		return callReport;
	}
	
	 public List<CallReport> getResume(Date fromDate, Date toDate){
    	 List<CallReport> reports = new ArrayList();
         CallReport report = new CallReport();
         report.setConnectedCalls(0l);
         report.setFailedCalls(0l);
         report.setTotalCalls(0l);
         report.setTotalDuration(0l);
         
         this.findByDate(fromDate, toDate).stream().forEach(r ->{
         		report.setFailedCalls(report.getFailedCalls() + r.getFailedCalls());
         		report.setConnectedCalls(report.getConnectedCalls() + r.getConnectedCalls());
                report.setTotalCalls(report.getFailedCalls() + report.getConnectedCalls());
                report.setTotalDuration(report.getTotalDuration() + r.getTotalDuration());
         });  
         report.setAcd(report.getTotalDuration().floatValue() / report.getConnectedCalls().floatValue());
         report.setAsr(report.getConnectedCalls().floatValue() / report.getTotalCalls().floatValue());
         reports.add(report);
         return reports;
    	
    }

}

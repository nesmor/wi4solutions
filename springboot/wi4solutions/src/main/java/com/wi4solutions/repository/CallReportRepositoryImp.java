package com.wi4solutions.repository;

import java.sql.Date;
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
						"WHERE cd.disposition != 'ANSWERED' AND  WEEK(cd.calldate) = WEEK(c.calldate),1) AS NOT_CONNECTED " + 
						"FROM Cdr c  WHERE c.disposition = 'ANSWERED' " + 
						"AND c.duration > 0 AND " + 
						"DATE(c.calldate) BETWEEN :fromDate AND :toDate  GROUP BY WEEK(c.calldate) ORDER BY YEAR(c.calldate) DESC, MONTH(c.calldate) ASC ");
			break;
			case "monthy":
				 q = em.createQuery(
						"SELECT YEAR(c.calldate), MONTH(c.calldate) AS H , COUNT(c.id) AS CONNECTED_CALLS, " + 
						"SUM(c.duration) AS DURATION, (SELECT COUNT(cd.id) FROM Cdr cd  " + 
						"WHERE cd.disposition != 'ANSWERED' AND  MONTH(cd.calldate) = MONTH(c.calldate),1) AS NOT_CONNECTED " + 
						"FROM Cdr c  WHERE c.disposition = 'ANSWERED' " + 
						"AND c.duration > 0 AND " + 
						"DATE(c.calldate) BETWEEN :fromDate AND :toDate  GROUP BY MONTH(c.calldate) ORDER BY YEAR(c.calldate) DESC, MONTH(c.calldate) ASC ");
		   break;
		
		
		}
		
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		reports = q.getResultList();
		return (List<CallReport>) reports.stream().map(e -> {return this.mapCallReport(e);}).collect(Collectors.toList());
	}

	@Override
	public CallReport findByFromDateAndToDate(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		CallReport report = new CallReport();
		StoredProcedureQuery findAll = em.createNamedStoredProcedureQuery("getCallReport2");
		findAll.setParameter("from_date", fromDate);
		findAll.setParameter("to_date", toDate);
		findAll.execute();
		report.setAcd((Long)findAll.getOutputParameterValue(CallReport.PARAM_OUT_ACD));
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
		callReport.setAcd(callReport.getTotalDuration() / callReport.getTotalCalls());
		callReport.setAcd(callReport.getTotalDuration() / callReport.getConnectedCalls());
		callReport.setAsr(callReport.getConnectedCalls() / callReport.getTotalCalls());
		return callReport;
	}
	
	

}

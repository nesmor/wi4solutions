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
						 "SELECT YEAR(c.calldate), WEEK(c.calldate) AS H ," +
						 "(SELECT CONCAT(COUNT(cdr.id),'|',SUM(cdr.billsec)) FROM  CallDetailRecord   cdr WHERE cdr.disposition = 'ANSWERED' AND WEEK(cdr.calldate) = WEEK(c.calldate)) AS CONNECTED_CALLS, " + 
						 "(SELECT COUNT(cd.id) FROM CallDetailRecord cd WHERE cd.disposition != 'ANSWERED' AND  WEEK(cd.calldate) = WEEK(c.calldate)) AS NOT_CONNECTED " +
						 "FROM  CallDetailRecord   c " +
						 "WHERE DATE(c.calldate) BETWEEN :fromDate AND :toDate   GROUP BY WEEK(c.calldate) ORDER BY YEAR(c.calldate) DESC, MONTH(c.calldate) ASC " 
						);
				 
			break;
			case "monthy":
				 q = em.createQuery(
						 "SELECT YEAR(c.calldate), MONTH(c.calldate) AS H ," + 
						 "(SELECT CONCAT(COUNT(cdr.id),'|',SUM(cdr.billsec)) FROM  CallDetailRecord   cdr WHERE cdr.disposition = 'ANSWERED' AND MONTH(cdr.calldate) = MONTH(c.calldate))" + 
						 "AS CONNECTED_CALLS,"  +   
						 "(SELECT COUNT(cd.id) FROM CallDetailRecord cd " +   
						 "WHERE cd.disposition != 'ANSWERED' AND  MONTH(cd.calldate) = MONTH(c.calldate)) AS NOT_CONNECTED " +  
						 "FROM CallDetailRecord c  WHERE " +
						 "DATE(c.calldate) BETWEEN :fromDate AND :toDate  GROUP BY MONTH(c.calldate) ORDER BY YEAR(c.calldate) DESC, WEEK(c.calldate) ASC"
						);
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
		report.setTotalCalls((Integer)findAll.getOutputParameterValue(CallReport.PARAM_OUT_CONNECTED_TOTAL_CALLS));
		report.setTotalDuration((Integer)findAll.getOutputParameterValue(CallReport.PARAM_OUT_TOTAL_DURATION));
		report.setFailedCalls((Integer)findAll.getOutputParameterValue(CallReport.PARAM_OUT_FAILED_CALLS));
		report.setConnectedCalls((Integer)findAll.getOutputParameterValue(CallReport.PARAM_OUT_CONNECTED_CALLS));
		return report;
	}
	
	
//	@Override
//	public List<CallReport> findByHour(Date date) {
//		// TODO Auto-generated method stub
//		List<Object[]> reports = null;
//		Query q = em.createQuery(
//				"SELECT AVG(HOUR(c.calldate)) AS H , COUNT(c.id) AS CONNECTED_CALLS, " + 
//				"SUM(c.billsec) AS DURATION, (SELECT COUNT(cd.id) FROM CallDetailRecord cd  " + 
//				"WHERE cd.disposition != 'ANSWERED' AND  HOUR(cd.calldate) = HOUR(c.calldate)) AS NOT_CONNECTED " + 
//				"FROM CallDetailRecord c  WHERE c.disposition = 'ANSWERED' " + 
//				"AND c.billsec > 0 AND " + 
//				"DATE(c.calldate) = :date GROUP BY HOUR(c.calldate)");//si answered = true y billsec > 0 hay registros en no answer porque los busca,
//		// debido a que la condicion se cumple, sino no busca los registros es decir answer = false
//		q.setParameter("date", date);
//		reports = q.getResultList();
//		return (List<CallReport>)  reports.stream().map(e -> {return this.mapCallReport(e);}).collect(Collectors.toList());
//	}
	
	@Override
	public List<CallReport> findByHour(Date date) {
		// TODO Auto-generated method stub
		List<Object[]> reports = null;
		Query q = em.createQuery(
				"SELECT HOUR(c.calldate) AS H , " + 
				"(SELECT CONCAT(COUNT(cdr.id),'|',SUM(cdr.billsec)) FROM CallDetailRecord cdr WHERE cdr.disposition = 'ANSWERED' AND HOUR(cdr.calldate) = HOUR(c.calldate)) " + 
				"AS CONNECTED_CALLS, " + 
				"(SELECT COUNT(cd.id) FROM CallDetailRecord cd WHERE cd.disposition != 'ANSWERED' AND HOUR(cd.calldate) = HOUR(c.calldate))" + 
				"AS NOT_CONNECTED " + 
				"FROM CallDetailRecord c WHERE DATE(c.calldate) =:date GROUP BY HOUR(c.calldate)" 
				);
		q.setParameter("date", date);
		reports = q.getResultList();
		return (List<CallReport>)  reports.stream().map(e -> {return this.mapCallReport(e);}).collect(Collectors.toList());
	}
	
	
	@Override
	public List<CallReport> findByDate(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		List<Object[]> reports = null;
		Query q = em.createQuery(
				"SELECT DATE(c.calldate) AS H , " + 
				"(SELECT CONCAT(COUNT(cdr.id),'|',SUM(cdr.billsec)) FROM CallDetailRecord cdr WHERE cdr.disposition = 'ANSWERED' AND DATE(cdr.calldate) = DATE(c.calldate)) " + 
				"AS CONNECTED_CALLS, " + 
				"(SELECT COUNT(cd.id) FROM CallDetailRecord cd WHERE cd.disposition != 'ANSWERED' AND DATE(cd.calldate) = DATE(c.calldate))" + 
				"AS NOT_CONNECTED " + 
				"FROM CallDetailRecord c WHERE DATE(c.calldate) BETWEEN :fromDate AND :toDate  GROUP BY DATE(c.calldate)");
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		reports = q.getResultList();
		return (List<CallReport>) reports.stream().map(e -> {return this.mapCallReport(e);}).collect(Collectors.toList());
	}
	
	
	
	
	
	private CallReport mapCallReport(Object[] data) {
		CallReport callReport = new CallReport();
		if(data[0] instanceof Date){
			callReport.setDate((Date)data[0]);
		}else if(data[0] != null){
			callReport.setHour(new Integer(data[0].toString()));
		}
		if(data[1] != null && data[1].toString().contains("|")) {
			this.processCallsConnected(data[1].toString(), callReport);
		} else {
			callReport.setConnectedCalls(0);
			callReport.setTotalDuration(0);
		}
		
		callReport.setFailedCalls(new Integer(data[2].toString()));
		callReport.setTotalCalls(callReport.getConnectedCalls() + callReport.getFailedCalls());
		callReport.setAsr(0f);
		callReport.setAcd(0f);
		if(callReport.getConnectedCalls().floatValue() != 0) {
			callReport.setAcd(callReport.getTotalDuration().floatValue() / callReport.getConnectedCalls().floatValue());
        }
        if(callReport.getTotalCalls().floatValue() != 0) {
        	callReport.setAsr(callReport.getConnectedCalls().floatValue() / callReport.getTotalCalls().floatValue());
        }
		return callReport;
	}
	
	private CallReport mapCallReportType(Object[] data) {
		CallReport callReport = new CallReport();
		callReport.setYear(new Integer(data[0].toString()));
		callReport.setWeek(new Integer(data[1].toString()));
		if(data[2] != null && data[2].toString().contains("|")) {
			this.processCallsConnected(data[2].toString(), callReport);
		} else {
			callReport.setConnectedCalls(0);
			callReport.setTotalDuration(0);
		}
		callReport.setFailedCalls(new Integer(data[3].toString()));
		callReport.setAsr(0f);
		callReport.setAcd(0f);
		callReport.setTotalCalls(callReport.getConnectedCalls() + callReport.getFailedCalls());
		if(callReport.getConnectedCalls().floatValue() != 0) {
			callReport.setAcd(callReport.getTotalDuration().floatValue() / callReport.getConnectedCalls().floatValue());
        }
        if(callReport.getTotalCalls().floatValue() != 0) {
        	callReport.setAsr(callReport.getConnectedCalls().floatValue() / callReport.getTotalCalls().floatValue());
        }
		return callReport;
	}
	
	 public List<CallReport> getResume(Date fromDate, Date toDate){
    	 List<CallReport> reports = new ArrayList();
         CallReport report = new CallReport();
         report.setConnectedCalls(0);
         report.setFailedCalls(0);
         report.setTotalCalls(0);
         report.setTotalDuration(0);
         report.setAsr(0f);
         report.setAcd(0f);
         
         this.findByDate(fromDate, toDate).stream().forEach(r ->{
         		report.setFailedCalls(report.getFailedCalls() + r.getFailedCalls());
         		report.setConnectedCalls(report.getConnectedCalls() + r.getConnectedCalls());
                report.setTotalCalls(report.getFailedCalls() + report.getConnectedCalls());
                report.setTotalDuration(report.getTotalDuration() + r.getTotalDuration());
         });  
         if(report.getConnectedCalls().floatValue() != 0) {
        	 report.setAcd(report.getTotalDuration().floatValue() / report.getConnectedCalls().floatValue());
         }
         if(report.getTotalCalls().floatValue() != 0) {
        	 report.setAsr(report.getConnectedCalls().floatValue() / report.getTotalCalls().floatValue());
         }
         reports.add(report);
         return reports;
    	
    }
	 
	 private void processCallsConnected(String data, CallReport callReport) {
		 if( data != null && (String)data != "" && ((String) data).contains("|")) {
			String[] answeredCalls  = data.split("\\|");
			try {
				callReport.setConnectedCalls(new Integer(answeredCalls[0]));
			}catch (Exception e) {
				callReport.setConnectedCalls(0);
			}
			try {
				callReport.setTotalDuration(new Integer(answeredCalls[1]));
			}catch (Exception e) {
				callReport.setTotalDuration(0);
			}
		}
		 
	 }

}

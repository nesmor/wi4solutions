package com.wi4solutions.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@Entity
@NamedStoredProcedureQueries({
	  @NamedStoredProcedureQuery(
	    name = "getCallReport", 
	    procedureName = "get_call_report", 
	    resultClasses = { CallReport.class }, 
	    parameters = { 
	        @StoredProcedureParameter(
	          name = "from_date", 
	          type = Date.class, 
	          mode = ParameterMode.IN),
	        @StoredProcedureParameter(
	          name = "to_date", 
	          type = Date.class, 
	          mode = ParameterMode.IN) 
	    }),
	  @NamedStoredProcedureQuery(
			    name = "getCallReport2", 
			    procedureName = "get_call_report_2", 
			    parameters = { 
			        @StoredProcedureParameter(
			          name = CallReport.PARAM_IN_FROM_DATE, 
			          type = Date.class, 
			          mode = ParameterMode.IN),
			        @StoredProcedureParameter(
			          name = CallReport.PARAM_IN_TO_DATE, 
			          type = Date.class, 
			          mode = ParameterMode.IN),
			        @StoredProcedureParameter(
			          name = CallReport.PARAM_OUT_FAILED_CALLS, 
			          type = Long.class, 
			          mode = ParameterMode.OUT),
			        @StoredProcedureParameter(
			          name = CallReport.PARAM_OUT_TOTAL_DURATION, 
			          type = Long.class, 
			          mode = ParameterMode.OUT),
			        @StoredProcedureParameter(
			          name = CallReport.PARAM_OUT_CONNECTED_TOTAL_CALLS, 
			          type = Long.class, 
			          mode = ParameterMode.OUT),
			        @StoredProcedureParameter(
			          name = CallReport.PARAM_OUT_CONNECTED_CALLS, 
			          type = Long.class, 
			          mode = ParameterMode.OUT),
			        @StoredProcedureParameter(
			          name = CallReport.PARAM_OUT_ACD, 
			          type = Long.class, 
			          mode = ParameterMode.OUT),
			        @StoredProcedureParameter(
			          name = CallReport.PARAM_OUT_ASR, 
			          type = Float.class, 
			          mode = ParameterMode.OUT)
			    })
	})
public class CallReport {
	
	public static final String PARAM_IN_FROM_DATE = "from_date";
	
	public static final String PARAM_IN_TO_DATE = "to_date";
	
	public static final String PARAM_OUT_ACD = "ACD";

	public static final String PARAM_OUT_ASR = "ASR";
	
	public static final String PARAM_OUT_FAILED_CALLS = "FAILED_CALLS";
	
	public static final String PARAM_OUT_TOTAL_DURATION = "TOTAL_DURATION";
	
	public static final String PARAM_OUT_CONNECTED_CALLS = "CONNECTED_CALLS";
	
	public static final String PARAM_OUT_CONNECTED_TOTAL_CALLS = "TOTAL_CALLS";
	
	public static final String REPORT_BY_HOUR = "REPORT_BY_HOUR";

	public static final String REPORT_BY_DATE = "REPORT_BY_DATE";
	
	private Date fromDate;
	
	private Date toDate;
	
	private Integer year;
	
	private Integer week;
	
	@Id
	private Long failedCalls;
	
	private Long totalCalls;
	
	private Long totalDuration;
	
	private Float asr;
	
	private Long acd;
	
	private Long minutes;
	
	private Long connectedCalls;
	
	private String reportType;
	
	private Double hour;
	
	private Date date;
	

	public Long getFailedCalls() {
		return failedCalls;
	}

	public void setFailedCalls(Long failedCalls) {
		this.failedCalls = failedCalls;
	}

	public Long getTotalCalls() {
		this.totalCalls = + this.connectedCalls + this.failedCalls;
		return totalCalls;
	}

	public void setTotalCalls(Long totalCalls) {
		this.totalCalls = totalCalls;
	}

	public Long getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Long totalDuration) {
		this.totalDuration = totalDuration;
	}

	public Float getAsr() {
		return asr;
	}

	public void setAsr(Float asr) {
		this.asr = asr;
	}
	
	public void setAsr(Long asr) {
		if(asr != null) {
			this.asr = new Float(asr/100);
		}
	}

	public Long getAcd() {
		return acd;
	}

	public void setAcd(Long acd) {
		this.acd = acd;
	}

	public Long getMinutes() {
		return minutes;
	}

	public void setMinutes(Long minutes) {
		this.minutes = minutes;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getConnectedCalls() {
		return connectedCalls;
	}

	public void setConnectedCalls(Long connectedCalls) {
		this.connectedCalls = connectedCalls;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	

	public Double getHour() {
		return hour;
	}

	public void setHour(Double hour) {
		this.hour = hour;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	@Override
	public String toString() {
		return "CallReport [fromDate=" + fromDate + ", toDate=" + toDate + ", failedCalls=" + failedCalls
				+ ", totalCalls=" + totalCalls + ", totalDuration=" + totalDuration + ", asr=" + asr + ", acd=" + acd
				+ ", minutes=" + minutes + ", connectedCalls=" + connectedCalls + "]";
	}
	
	
	
	
	
}

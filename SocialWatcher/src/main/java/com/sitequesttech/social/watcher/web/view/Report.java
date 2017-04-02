package com.sitequesttech.social.watcher.web.view;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.io.Serializable;

public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String[] queries;
	
	private String[] reportFields;
	
	private String dateRange;

	public String[] getQueries() {
		return queries;
	}

	public void setQueries(String[] queries) {
		this.queries = queries;
	}

	public String[] getReportFields() {
		return reportFields;
	}

	public void setReportFields(String[] reportFields) {
		this.reportFields = reportFields;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}	
	
	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return reflectionToString(this);
	}


}

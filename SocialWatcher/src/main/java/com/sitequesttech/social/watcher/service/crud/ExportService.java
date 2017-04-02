package com.sitequesttech.social.watcher.service.crud;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.service.export.Exporter;

@Service("ExportService")
public class ExportService {

	
	private static final Logger logger = Logger
			.getLogger(ExportService.class);
	
	@Autowired
	private Exporter exporter;
	
	@Autowired
	private QueryResultService queryResultService;
	
	public ByteArrayOutputStream doExportToCsv(Query query) {
		List<QueryResult> list = queryResultService.getPositiveReviewedQueryResultsByQuery(query);
		ByteArrayOutputStream result = null;
		try {
			result = exporter.exportToCsv(list);
			logger.debug(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ByteArrayOutputStream doExportToCsv(Collection<Query> query, String fromDate, String endDate, String[] reportFields) {
		List<QueryResult> list = queryResultService.getPositiveReviewedQueryResultsByQueryInDateRange(query, fromDate, endDate, reportFields);
		ByteArrayOutputStream result = null;
		try {
			result = exporter.exportToCsv(list, reportFields);
			logger.debug(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public HSSFWorkbook doExportToExcel(Query query) {
		List<QueryResult> list = queryResultService.getPositiveReviewedQueryResultsByQuery(query);
		HSSFWorkbook workbook = null;
		try {
			workbook = exporter.exportToExcel(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	public HSSFWorkbook doExportToExcel(Collection<Query> queries, String fromDate, String endDate, String[] reportFields) {
		List<QueryResult> list = queryResultService.getPositiveReviewedQueryResultsByQueryInDateRange(queries, fromDate, endDate, reportFields);
		HSSFWorkbook workbook = null;
		try {
			workbook = exporter.exportToExcel(list, reportFields);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	public ByteArrayOutputStream doExportToPdf(Query query) {
		List<QueryResult> list = queryResultService.getPositiveReviewedQueryResultsByQuery(query);
		ByteArrayOutputStream document = null;
		try {
			document = exporter.exportToPdf(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public ByteArrayOutputStream doExportToPdf(Collection<Query> queries, String fromDate, String endDate, String[] reportFields) {
		List<QueryResult> list = queryResultService.getPositiveReviewedQueryResultsByQueryInDateRange(queries, fromDate, endDate, reportFields);
		ByteArrayOutputStream document = null;
		try {
			document = exporter.exportToPdf(list, reportFields);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
}

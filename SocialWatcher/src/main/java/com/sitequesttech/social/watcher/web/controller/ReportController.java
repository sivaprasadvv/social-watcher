package com.sitequesttech.social.watcher.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;
import com.sitequesttech.social.watcher.service.crud.ExportService;
import com.sitequesttech.social.watcher.service.crud.QueryService;
import com.sitequesttech.social.watcher.service.crud.ReportService;
import com.sitequesttech.social.watcher.web.view.Report;
import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;

@Controller
@RequestMapping("/report/")
public class ReportController  extends AbstractCrudController<Report> {

	private static final Logger logger = Logger
			.getLogger(ReportController.class);
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
    private Validator validator;
	
	@Autowired
	private ExportService exportService;	
	
	@Autowired
	private QueryService queryService;

	@Override
	protected String getListPageName() {
		return "listReportPage";
	}
	
	@RequestMapping(value = "export", method = RequestMethod.GET)
    public String getExportPage() {
		return "exportPage";
    }

	@Override
	protected String getCreatePageName() {
		return "createReportPage";
	}

	@Override
	protected String getUpdatePageName() {
		return null;
	}

	@Override
	protected AbstractCrudService<Report> getService() {
		return null;
	}

	@Override
	protected EntityHelper<Report> getHelper() {
		return null;
	}

	@Override
	protected Validator getValidator() {
		return this.validator;
	}
	
	@RequestMapping(value = "/", method = POST, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestBody Report entity,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("create() - ");
		
		if (logger.isDebugEnabled())
			logger.debug("Creating entity: " + entity.toString());
		
		request.getSession().setAttribute("currentReportQuery", entity);
		
		return null;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ReadOperationResults list(HttpServletRequest request, ReadOperationParams params) {
		logger.info("list() - ");
		return reportService.processRead(request, params);
	}
	
	@ModelAttribute("queries")
    public Collection<Collection<String>> populateQueries(HttpServletRequest request) {
		logger.info("populateQueries() - ");		
		return reportService.fetchQueries(request);
    }
	
	@RequestMapping(value = "csv", method = POST)
	public String exportToCsv(@RequestBody Report entity, HttpServletRequest request, HttpServletResponse response) {
		logger.info("report entity :" +entity);
		Report report = (Report)request.getSession().getAttribute("currentReportQuery");
		report.setReportFields(entity.getReportFields());
		request.getSession().setAttribute("currentReportQuery", report);	
		
        return "redirect:/report/csv";
	}	
	
	@RequestMapping(value = "/csv", method = GET)
	public void exportToCsv(HttpServletRequest request, HttpServletResponse response) {
		Report report = (Report)request.getSession().getAttribute("currentReportQuery");
		Collection<String> selectedQueries = Arrays.asList(report.getQueries());
		Collection<Query> queries = queryService.getQueriesByQueryTextIn(selectedQueries);
		if (logger.isDebugEnabled())
			logger.debug("#############:" +queries.size());
		String dateRange = report.getDateRange();
		String[] dates = dateRange.split("-");
		ByteArrayOutputStream baos = exportService.doExportToCsv(queries, dates[0], dates[1], report.getReportFields());
		response.setContentType("application/vnd.ms-excel");
        response.setContentLength(baos.size());
        response.setHeader("Content-Disposition","attachment; filename=socialwatcher.csv");
 
        ServletOutputStream out;
		try {
			out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return;
	}	
	
	@RequestMapping(value = "excel", method = POST)
	public String exportToExcel(@RequestBody Report entity, HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("report entity :" +entity);
		Report report = (Report)request.getSession().getAttribute("currentReportQuery");
		report.setReportFields(entity.getReportFields());
		request.getSession().setAttribute("currentReportQuery", report);	
		
        return "redirect:/report/excel";
	}	
	
	@RequestMapping(value = "excel", method = GET)
	public void exportToExcel(HttpServletRequest request, HttpServletResponse response) {
		
		Report report = (Report)request.getSession().getAttribute("currentReportQuery");
		Collection<String> selectedQueries = Arrays.asList(report.getQueries());
		Collection<Query> queries = queryService.getQueriesByQueryTextIn(selectedQueries);
		String dateRange = report.getDateRange();
		String[] dates = dateRange.split("-");
		HSSFWorkbook workbook = exportService.doExportToExcel(queries, dates[0], dates[1], report.getReportFields());
		response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment; filename=socialwatcher.xls");
 
        ServletOutputStream out;
		try {
			out = response.getOutputStream();
			workbook.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
        return;
	}	
	
	@RequestMapping(value = "pdf", method = POST)
	public String exportToPdf(@RequestBody Report entity, HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("report entity :" +entity);
		Report report = (Report)request.getSession().getAttribute("currentReportQuery");
		report.setReportFields(entity.getReportFields());
		request.getSession().setAttribute("currentReportQuery", report);	
		
        return "redirect:/report/pdf";
	}	
	
	@RequestMapping(value = "pdf", method = GET)
	public void exportToPdf(HttpServletRequest request, HttpServletResponse response) {
		
		Report report = (Report)request.getSession().getAttribute("currentReportQuery");
		Collection<String> selectedQueries = Arrays.asList(report.getQueries());
		Collection<Query> queries = queryService.getQueriesByQueryTextIn(selectedQueries);
		String dateRange = report.getDateRange();
		String[] dates = dateRange.split("-");
		ByteArrayOutputStream baos = exportService.doExportToPdf(queries, dates[0], dates[1], report.getReportFields());
		response.setContentType("application/octet-stream");
		response.setContentLength(baos.size());
        response.setHeader("Content-Disposition","attachment; filename=socialwatcher.pdf");
 
        ServletOutputStream out;
		try {
			out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return;
	}	
}

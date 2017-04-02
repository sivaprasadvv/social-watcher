package com.sitequesttech.social.watcher.service.export;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;

import au.com.bytecode.opencsv.CSVWriter;

@Component
public class Exporter {
	
	private static final Logger logger = Logger
			.getLogger(Exporter.class);
	
	/**
	 * This method is used to write content to csv file directly
	 * @param items
	 * @return
	 * @throws Exception
	 */
	public String exportToCsvFile(List<QueryResult> items) throws Exception {
		
		CSVWriter writer = new CSVWriter(new FileWriter(ExportConstants.CONST_CSV_FILE_NAME), '\n');		
		
		String header = ExportConstants.CONST_NAME.toUpperCase()+ExportConstants.CONST_COMMA_SEPERATOR
				+ExportConstants.CONST_DESCRIPTION.toUpperCase()+ExportConstants.CONST_COMMA_SEPERATOR
				+ExportConstants.CONST_URL.toUpperCase();
		
		String[] entries = null;
		
		if (null != items && items.size() > 0) {
		
			int size = items.size();
			logger.debug("Total query results:" +size);
			entries = new String[size+1];
			entries[0] = header;
			
			int i = 1;
			for (QueryResult queryResult : items) {
				
			   entries[i++] = queryResult.getTitle()+ExportConstants.CONST_COMMA_SEPERATOR
					   		+ queryResult.getDescription()+ExportConstants.CONST_COMMA_SEPERATOR
					   		+queryResult.getUrl();
			}
			} else {
				entries = new String[1];
				entries[0] = header;
			}
		
			writer.writeNext(entries);
			writer.close();
			
			return getExecutionPath()+File.separator+ExportConstants.CONST_CSV_FILE_NAME;
	}
	
	/**
	 * This method is used to write content to excel file directly
	 * @param items
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String exportToExcelFile(List<QueryResult> items) throws Exception {
		
		String[] header = new String[]{
				ExportConstants.CONST_NAME.toUpperCase(), 
				ExportConstants.CONST_DESCRIPTION.toUpperCase(), 
				ExportConstants.CONST_URL.toUpperCase()
        };
		
		int rownum = 0;
		HSSFWorkbook workbook = new HSSFWorkbook();
	    HSSFSheet firstSheet = workbook.createSheet("Social Watcher");
        HSSFRow row = firstSheet.createRow(rownum);
        row.setHeightInPoints(40);
        FileOutputStream fos = null;
	    
		
		List<String> headerRow = Arrays.asList(header);
		List dataRow[] = null;
		
		if (null != items && items.size() > 0) {
			
			dataRow = new ArrayList<?>[items.size()];
			System.out.println(dataRow.length);
		
			for (int i = 0; i < items.size(); i++) {
			   QueryResult object = (QueryResult)items.get(i); 
			   dataRow[i] = new ArrayList();
			   dataRow[i].add(object.getTitle());
			   dataRow[i].add(object.getDescription());
			   dataRow[i].add(object.getUrl());
			}
			}
		
		List<List> recordToAdd = new ArrayList<List>();
        recordToAdd.add(headerRow);
        if (null != dataRow && dataRow.length > 0) {
	        for(int i=0;i<dataRow.length;i++)
	        	recordToAdd.add(dataRow[i]);
        }
        
        for (int j = 0; j < recordToAdd.size(); j++) {
            HSSFRow hrow = firstSheet.createRow(rownum);
            List<String> list= recordToAdd.get(j);
            for(int k=0; k<list.size(); k++)
            {
                HSSFCell cell = hrow.createCell(k);
                cell.setCellValue(new HSSFRichTextString(list.get(k)));
            }
            rownum++;
        }
        
        fos=new FileOutputStream(new File(ExportConstants.CONST_EXCEL_FILE_NAME));
        HSSFCellStyle hsfstyle = workbook.createCellStyle();
        hsfstyle.setBorderBottom((short) 1);
        hsfstyle.setFillBackgroundColor((short)245);
        workbook.write(fos);
		
		return getExecutionPath()+File.separator+ExportConstants.CONST_EXCEL_FILE_NAME;
	}
	
	/**
	 * This method is used to write content to pdf file directly
	 * @param items
	 * @return
	 * @throws Exception
	 */
	public String exportToPdfFile(List<QueryResult> items) throws Exception {
		
		String[] header = new String[]{
				ExportConstants.CONST_NAME.toUpperCase(), 
				ExportConstants.CONST_DESCRIPTION.toUpperCase(), 
				ExportConstants.CONST_URL.toUpperCase(),
				ExportConstants.CONST_PROFILE_IMAGE.toUpperCase(),
				ExportConstants.CONST_SOURCE.toUpperCase(),
				ExportConstants.CONST_CREATED_AT.toUpperCase()
        };
		
		Document  document = new Document(PageSize.A4.rotate());
		 
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new Date().getTime()+"_"+ExportConstants.CONST_PDF_FILE_NAME));
			document.addTitle("Social Watcher");
			document.open();
			
			PdfPTable table = new PdfPTable(header.length);
			
			for (int i = 0; i < header.length; i++) {
	            String lheader = header[i];
	            PdfPCell cell = new PdfPCell();
	            cell.setGrayFill(0.9f);
	            cell.setPhrase(new Phrase(lheader, new Font(Font.HELVETICA, 10,Font.BOLD)));
	            table.addCell(cell);
	        }
	        table.completeRow();
	        
	        String[][] data = null;
			
			if (null != items && items.size() > 0) {
			
				int size = items.size();
				data = new String[size][];
			
				for (int i = 0; i < size; i++) {
					QueryResult object = (QueryResult) items.get(i); 
				   
				   data[i] = new String[]{(String)object.getTitle()
						   		,(String)object.getDescription()
						   		,(String)object.getUrl()
						   		,(String)object.getProfileImageUrl()
						   		,(String)object.getSocialMediaName()
						   		,(String)object.getSourceCreatedAt()
				   };
				   		
				}
			}
			
			  for (int i = 0; i < data.length; i++) {
	              for (int j = 0; j < data[i].length; j++) {
	                  String datum = data[i][j];
	                  PdfPCell cell = null;
	                  if (SocialWatcherUtil.isImage(datum)) {
	                	  Image image = Image.getInstance(datum);
	                	  cell = new PdfPCell(image,false);
	                  } else {
	                	  cell = new PdfPCell();
	                  cell.setPhrase(new Phrase(datum,
	                          new Font(Font.HELVETICA, 10,Font.NORMAL)));
	                  }
	                  table.addCell(cell);
	              }
	              table.completeRow();
	          }
	        
	        document.add(table);
		} catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
        	try {
            document.close();
        	} catch(Exception ex) {
        		
        	}
        }
		
		return getExecutionPath()+File.separator+ExportConstants.CONST_PDF_FILE_NAME;
	}
	
	/**
	 * This method is used current working user directory
	 * @return
	 */
	public String getExecutionPath() {
		String executionPath = null;
		try {
			executionPath = System.getProperty("user.dir");
			System.out.println("Execution Path :" +executionPath);
		} catch (Exception e){
		      System.out.println("Exception caught ="+e.getMessage());
	    }
		return executionPath;
	}
	
	/**
	 * This method is used to export data while user click Csv button in browser
	 * @param items
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream exportToCsv(List<QueryResult> items) throws Exception {
		
		int OUTPUT_BYTE_ARRAY_INITIAL_SIZE = 4096;
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
		
		CSVWriter writer = new CSVWriter(new PrintWriter(outputStream) , '\n');		
		
		String header = ExportConstants.CONST_NAME.toUpperCase()+ExportConstants.CONST_COMMA_SEPERATOR
				+ExportConstants.CONST_DESCRIPTION.toUpperCase()+ExportConstants.CONST_COMMA_SEPERATOR
				+ExportConstants.CONST_URL.toUpperCase();
		
		String[] entries = null;
		
		if (null != items && items.size() > 0) {
		
			int size = items.size();
			logger.debug("Total query results:" +size);
			entries = new String[size+1];
			entries[0] = header;
			
			int i = 1;
			for (QueryResult queryResult : items) {
				
			   entries[i++] = queryResult.getTitle()+ExportConstants.CONST_COMMA_SEPERATOR
					   		+ queryResult.getDescription()+ExportConstants.CONST_COMMA_SEPERATOR
					   		+queryResult.getUrl();
			}
			} else {
				entries = new String[1];
				entries[0] = header;
			}
		
			writer.writeNext(entries);
			writer.close();
			
			return outputStream;
	}
	
	
	public ByteArrayOutputStream exportToCsv(List<QueryResult> items, String[] reportFields) throws Exception {
		
		int OUTPUT_BYTE_ARRAY_INITIAL_SIZE = 4096;
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
		
		CSVWriter writer = new CSVWriter(new PrintWriter(outputStream) , '\n');		
		
		String[] entries = null;
		
		String header = "";
		Collection<String> collectionHeader = getHeader(reportFields);
		String[] headerArr = new String[collectionHeader.size()];
		headerArr = collectionHeader.toArray(headerArr);
		
		for (int h=0;h<headerArr.length;h++) {
			if (h == headerArr.length-1)
				header += headerArr[h];
			else
				header += headerArr[h]+ExportConstants.CONST_COMMA_SEPERATOR;
		}
		
		if (null != items && items.size() > 0) {
		
			int size = items.size();
			logger.debug("Total query results:" +size);
			entries = new String[size+1];
			entries[0] = header;
			
			
			int i = 1;
			for (QueryResult queryResult : items) {
				String data = "";
				Collection<String> collectionData = getData(queryResult, reportFields);
				String[] dataArr = new String[collectionData.size()];
				dataArr = collectionData.toArray(dataArr);
				for (int d=0;d<dataArr.length;d++) {
					if (d == dataArr.length-1)
						data += dataArr[d];
					else
						data += dataArr[d]+ExportConstants.CONST_COMMA_SEPERATOR;
				}
				
			   entries[i++] = data;
			}
			} else {
				entries = new String[1];
				entries[0] = header;
			}
		
			writer.writeNext(entries);
			writer.close();
			
			return outputStream;
	}
	
	/**
	 * This method is used to export data while user click Excel button in browser
	 * @param items
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HSSFWorkbook exportToExcel(List<QueryResult> items) throws Exception {
		
		String[] header = new String[]{
				ExportConstants.CONST_NAME.toUpperCase(), 
				ExportConstants.CONST_DESCRIPTION.toUpperCase(), 
				ExportConstants.CONST_URL.toUpperCase()
        };
		
		int rownum = 0;
		HSSFWorkbook workbook = new HSSFWorkbook();
	    HSSFSheet firstSheet = workbook.createSheet("Social Watcher");
        HSSFRow row = firstSheet.createRow(rownum);
        row.setHeightInPoints(40);
		
		List<String> headerRow = Arrays.asList(header);
		List dataRow[] = null;
		
		if (null != items && items.size() > 0) {
			
			dataRow = new ArrayList<?>[items.size()];
			System.out.println(dataRow.length);
		
			for (int i = 0; i < items.size(); i++) {
			   QueryResult object = (QueryResult)items.get(i); 
			   dataRow[i] = new ArrayList();
			   dataRow[i].add(object.getTitle());
			   dataRow[i].add(object.getDescription());
			   dataRow[i].add(object.getUrl());
			}
			}
		
		List<List> recordToAdd = new ArrayList<List>();
        recordToAdd.add(headerRow);
        if (null != dataRow && dataRow.length > 0) {
	        for(int i=0;i<dataRow.length;i++)
	        	recordToAdd.add(dataRow[i]);
        }
        
        for (int j = 0; j < recordToAdd.size(); j++) {
            HSSFRow hrow = firstSheet.createRow(rownum);
            List<String> list= recordToAdd.get(j);
            for(int k=0; k<list.size(); k++)
            {
                HSSFCell cell = hrow.createCell(k);
                cell.setCellValue(new HSSFRichTextString(list.get(k)));
            }
            rownum++;
        }
        
        HSSFCellStyle hsfstyle = workbook.createCellStyle();
        hsfstyle.setBorderBottom((short) 1);
        hsfstyle.setFillBackgroundColor((short)245);
		
		return workbook;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HSSFWorkbook exportToExcel(List<QueryResult> items, String[] reportFields) throws Exception {
		
		Collection<String> collectionHeader = getHeader(reportFields);
		String[] header = new String[collectionHeader.size()];
		header = collectionHeader.toArray(header);
		
		int rownum = 0;
		HSSFWorkbook workbook = new HSSFWorkbook();
	    HSSFSheet firstSheet = workbook.createSheet("Social Watcher");
        HSSFRow row = firstSheet.createRow(rownum);
        row.setHeightInPoints(40);
		
		List<String> headerRow = Arrays.asList(header);
		List dataRow[] = null;
		
		if (null != items && items.size() > 0) {
			
			dataRow = new ArrayList<?>[items.size()];
			System.out.println(dataRow.length);
		
			for (int i = 0; i < items.size(); i++) {
			   QueryResult object = (QueryResult)items.get(i); 
			   dataRow[i] = (List) getData(object, reportFields);
			}
			}
		
		List<List> recordToAdd = new ArrayList<List>();
        recordToAdd.add(headerRow);
        if (null != dataRow && dataRow.length > 0) {
	        for(int i=0;i<dataRow.length;i++)
	        	recordToAdd.add(dataRow[i]);
        }
        
        for (int j = 0; j < recordToAdd.size(); j++) {
            HSSFRow hrow = firstSheet.createRow(rownum);
            List<String> list= recordToAdd.get(j);
            for(int k=0; k<list.size(); k++)
            {
                HSSFCell cell = hrow.createCell(k);
                cell.setCellValue(new HSSFRichTextString(list.get(k)));
            }
            rownum++;
        }
        
        HSSFCellStyle hsfstyle = workbook.createCellStyle();
        hsfstyle.setBorderBottom((short) 1);
        hsfstyle.setFillBackgroundColor((short)245);
		
		return workbook;
	}
	
	/**
	 * This method is used to export data while user click Pdf button in browser
	 * @param items
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream exportToPdf(List<QueryResult> items) throws Exception {
		
		int OUTPUT_BYTE_ARRAY_INITIAL_SIZE = 4096;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
		
		String[] header = new String[]{
				ExportConstants.CONST_NAME.toUpperCase(), 
				ExportConstants.CONST_DESCRIPTION.toUpperCase(), 
				ExportConstants.CONST_URL.toUpperCase(),
				ExportConstants.CONST_PROFILE_IMAGE.toUpperCase(),
				ExportConstants.CONST_SEARCH_ENGINE_IMAGE.toUpperCase(),
				ExportConstants.CONST_CREATED_AT.toUpperCase()
        };
		
		Document  document = new Document(PageSize.A4.rotate());
		 
		try {
			PdfWriter.getInstance(document, baos);
			document.addTitle("Social Watcher");
			document.open();
			
			PdfPTable table = new PdfPTable(header.length);
			
			for (int i = 0; i < header.length; i++) {
	            String lheader = header[i];
	            PdfPCell cell = new PdfPCell();
	            cell.setGrayFill(0.9f);
	            cell.setPhrase(new Phrase(lheader, new Font(Font.HELVETICA, 10,Font.BOLD)));
	            table.addCell(cell);
	        }
	        table.completeRow();
	        
	        String[][] data = null;
			
			if (null != items && items.size() > 0) {
			
				int size = items.size();
				data = new String[size][];
			
				for (int i = 0; i < size; i++) {
					QueryResult object = (QueryResult) items.get(i); 
				   
				   data[i] = new String[]{(String)object.getTitle()
						   		,(String)object.getDescription()
						   		,(String)object.getUrl()
						   		,(String)object.getProfileImageUrl()
						   		,(String)object.getSocialMediaName()
						   		,(String)object.getSourceCreatedAt()
				   };
				   		
				}
			}
			
			  for (int i = 0; i < data.length; i++) {
	              for (int j = 0; j < data[i].length; j++) {
	            	  String datum = data[i][j];
	                  PdfPCell cell = null;
	                  if (SocialWatcherUtil.isImage(datum)) {
	                	  Image image = Image.getInstance(datum);
	                	  cell = new PdfPCell(image,false);
	                  } else {
	                	  cell = new PdfPCell();
	                  cell.setPhrase(new Phrase(datum,
	                          new Font(Font.HELVETICA, 10,Font.NORMAL)));
	                  }
	                  table.addCell(cell);
	              }
	              table.completeRow();
	          }
	        
	        document.add(table);
		} catch (DocumentException e) {
            e.printStackTrace();
        } finally {
        	try {
                document.close();
            	} catch(Exception ex) {
            		
            	}
            }
		
		return baos;
	}
	
	/**
	 * 
	 * @param items
	 * @param reportFields
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream exportToPdf(List<QueryResult> items, String[] reportFields) throws Exception {
		
		int OUTPUT_BYTE_ARRAY_INITIAL_SIZE = 4096;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
		
		Collection<String> headerCollection = getHeader(reportFields);
		String[] header = new String[headerCollection.size()];
		header = headerCollection.toArray(header);
		
		Document  document = new Document(PageSize.A4.rotate());
		 
		try {
			PdfWriter.getInstance(document, baos);
			document.addTitle("Social Watcher");
			document.open();
			
			PdfPTable table = new PdfPTable(header.length);
			
			for (int i = 0; i < header.length; i++) {
	            String lheader = header[i];
	            PdfPCell cell = new PdfPCell();
	            cell.setGrayFill(0.9f);
	            cell.setPhrase(new Phrase(lheader, new Font(Font.HELVETICA, 10,Font.BOLD)));
	            table.addCell(cell);
	        }
	        table.completeRow();
	        
	        String[][] data = null;
			
			if (null != items && items.size() > 0) {
			
				int size = items.size();
				data = new String[size][];
			
				for (int i = 0; i < size; i++) {
					QueryResult object = (QueryResult) items.get(i); 
				   
				    Collection<String> collectionData =  getData(object, reportFields);
					data[i] = new String[collectionData.size()];
					data[i] = collectionData.toArray(data[i]);	
				}
			}
			
			  for (int i = 0; i < data.length; i++) {
	              for (int j = 0; j < data[i].length; j++) {
	            	  String datum = data[i][j];
	                  PdfPCell cell = null;
	                  if (SocialWatcherUtil.isImage(datum)) {
	                	  Image image = Image.getInstance(datum);
	                	  cell = new PdfPCell(image,false);
	                  } else {
	                	  cell = new PdfPCell();
	                  cell.setPhrase(new Phrase(datum,
	                          new Font(Font.HELVETICA, 10,Font.NORMAL)));
	                  }
	                  table.addCell(cell);
	              }
	              table.completeRow();
	          }
	        
	        document.add(table);
		} catch (DocumentException e) {
            e.printStackTrace();
        } finally {
        	try {
                document.close();
            	} catch(Exception ex) {
            		
            	}
            }
		
		return baos;
	}
	
/*	private String[] getHeader(String[] reportFields) {
		
		Collection<String> headerList = new ArrayList<String>();		
		String[] header = null;
		boolean isMultiSelectPresent = false;
		
		if (null != reportFields && reportFields.length > 0) {
			
			for(int i=0;i<reportFields.length;i++) {
				if ("multiselect-all".equals(reportFields[i])) {
					isMultiSelectPresent = true;
					break;
				}
			}
			
			if (isMultiSelectPresent)
				header = new String[reportFields.length-1];
			else
				header = new String[reportFields.length];
			
			for(String reportField : reportFields) {
				if ("Name".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Description".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Url".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Source".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("ProfileImage".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("IsReviewed".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("CreatedBy".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("CreatedDate".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Comment".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("SourceId".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("SourceCreatedDate".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("IsDeleted".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Place".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				}
			}
		}
		
		header = headerList.toArray(header);
		
		return header;
	}*/
	
	private Collection<String> getHeader(String[] reportFields) {

		Collection<String> headerList = new ArrayList<String>();
		if (null != reportFields && reportFields.length > 0) {
			for (String reportField : reportFields) {
				if ("Name".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Description".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Url".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Source".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("ProfileImage".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("IsReviewed".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("CreatedBy".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("CreatedDate".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Comment".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("SourceId".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("SourceCreatedDate".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("IsDeleted".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				} else if ("Place".equalsIgnoreCase(reportField)) {
					headerList.add(reportField.toUpperCase());
				}
			}
		}

		return headerList;
	}
	
	/*public String[] getData(QueryResult queryResult, String[] reportFields) {
		
		Collection<String> dataList = new ArrayList<String>();
		String[] data = null;
		boolean isMultiSelectPresent = false;
		
		if (null != reportFields && reportFields.length > 0) {
			
			for(int i=0;i<reportFields.length;i++) {
				if ("multiselect-all".equals(reportFields[i])) {
					isMultiSelectPresent = true;
					break;
				}
			}
			
			if (isMultiSelectPresent)
				data = new String[reportFields.length-1];
			else
				data = new String[reportFields.length];
			
			for(String reportField : reportFields) {
				if ("Name".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getTitle());
				} else if ("Description".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getDescription());
				} else if ("Url".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getUrl());
				} else if ("Source".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getSearchApiName());
				} else if ("ProfileImage".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getProfileImageUrl());
				} else if ("IsReviewed".equalsIgnoreCase(reportField)) {
					dataList.add(String.valueOf(queryResult.getIsReviewed()));
				} else if ("CreatedBy".equalsIgnoreCase(reportField)) {
					dataList.add( queryResult.getCreatedBy());
				} else if ("CreatedDate".equalsIgnoreCase(reportField)) {
					dataList.add(SocialWatcherUtil.convertDateToString(queryResult.getCreatedDate()));
				} else if ("Comment".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getComment());
				} else if ("SourceId".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getSourceId());
				} else if ("SourceCreatedDate".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getSourceCreatedAt());
				} else if ("IsDeleted".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getIsDeleted());
				} else if ("Place".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getPlace());
				}
			}
			
			data = dataList.toArray(data);
		}
			
		
		return data;
	}*/
	
public Collection<String> getData(QueryResult queryResult, String[] reportFields) {
		
		Collection<String> dataList = new ArrayList<String>();
		
		if (null != reportFields && reportFields.length > 0) {
			
			for(String reportField : reportFields) {
				if ("Name".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getTitle());
				} else if ("Description".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getDescription());
				} else if ("Url".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getUrl());
				} else if ("Source".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getSocialMediaName());
				} else if ("ProfileImage".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getProfileImageUrl());
				} else if ("IsReviewed".equalsIgnoreCase(reportField)) {
					dataList.add(String.valueOf(queryResult.getIsReviewed()));
				} else if ("CreatedBy".equalsIgnoreCase(reportField)) {
					dataList.add( String.valueOf(queryResult.getCreatedBy()));
				} else if ("CreatedDate".equalsIgnoreCase(reportField)) {
					dataList.add(SocialWatcherUtil.convertDateToString(queryResult.getCreatedDate()));
				} else if ("Comment".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getComment());
				} else if ("SourceId".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getSourceId());
				} else if ("SourceCreatedDate".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getSourceCreatedAt());
				} else if ("IsDeleted".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getIsDeleted());
				} else if ("Place".equalsIgnoreCase(reportField)) {
					dataList.add(queryResult.getPlace());
				}
			}
			
		}
			
		
		return dataList;
	}
	
}

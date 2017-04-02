package com.sitequesttech.social.watcher.common.support;

import java.beans.PropertyDescriptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import twitter4j.QueryResult;

@Component
public class SocialWatcherUtil {
	
	private static final Logger logger = Logger
			.getLogger(SocialWatcherUtil.class);
	
	public static String extractUrlFromText(String text) {
		
		String urlString = "";
		
		if ( null != text && !"".equals(text)) {
			Pattern urlPattern = Pattern.compile(SocialWatcherConstants.URL_EXTRACT_FROM_TEXT_PATTERN,
			        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			 Matcher matcher = urlPattern.matcher(text);
			 while (matcher.find()) {
			     int matchStart = matcher.start(1);
			     int matchEnd = matcher.end();
			     urlString = text.substring(matchStart, matchEnd);
			     logger.debug(urlString);
			     break;
			 }
		}
		 
		 return urlString;
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmptyOrNullString(String string) {
		
		return string == null || "".equals(string) || "null".equalsIgnoreCase(string);
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNotEmptyAndNotNullString(String string) {
		
		return string != null && !"".equals(string) && !"null".equalsIgnoreCase(string);
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullObject(Object obj) {
		
		return obj == null || obj.equals("null") || obj.equals("");
		
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNullObject(Object obj) {
		
		return obj != null && !obj.equals("null") && !obj.equals("");
		
	}
	
	/**
	 * 
	 * @param queryText
	 * @param resultText
	 * @return
	 */
	public static String highlightQueryTextInResultText(String queryText, String resultText) {
		
		String originalStringInResultText = "";
		if (isNotEmptyAndNotNullString(resultText))  
		{
			if (resultText.contains(queryText)) {
				originalStringInResultText = queryText;
			} else if (resultText.contains(queryText.toLowerCase())) {
				originalStringInResultText = queryText.toLowerCase();
			} else if (resultText.contains(queryText.toUpperCase())) {
				originalStringInResultText = queryText.toUpperCase();
			}
			resultText = resultText.replaceAll(originalStringInResultText, SocialWatcherConstants.HIGHLIGHT_QUERYTEXT_FORMAT.replace("<querytext>", originalStringInResultText));
		}
		
		return resultText;
		
	}
	
	public static boolean isQueryTextInResultText(String queryText, String resultText) {
		
		if (isNotEmptyAndNotNullString(resultText))  
		{
			return (resultText.contains(queryText) 
					|| resultText.contains(queryText.toLowerCase())
					|| resultText.contains(queryText.toUpperCase()));
		}
		
		return false;
		
	}
	
	public static String convertDateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	public static Date convertStringToDate(String strDate) {
		//07/01/2013 - 07/09/2013
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 public static Set<String> getQueryStrings(String query) {
		 
		 	String[] queryWords = getWords(query);
	    	Set<String> searchStringsSet = new HashSet<String>();
	    	
	    	if (queryWords.length > 1) {
		    	for (int i = 0; i < queryWords.length; i++) {
		    		for (int j = i+1; j < queryWords.length; j++) {
		    			searchStringsSet.add(queryWords[i]+" "+queryWords[j]);
		    		}
				}
		    	for (int i = queryWords.length-1; i > -1; i--) {
		    		for (int j = i-1; j > -1; j--) {
		    			searchStringsSet.add(queryWords[i]+" "+queryWords[j]);
		    		}
				}
	    	} else {
	    		searchStringsSet.add(queryWords[0]);
	    	}
	    	
	    	return searchStringsSet;
	    }
	 
	 public static String[] getWords(String query) {
	    	
	    	String[] results = query.split("[(\\+)(\\s)]");//(\\+)(\\s)
	    	return results;
	    	
	    }
	 
	 /**
	   * Validate image with regular expression
	   * @param image image for validation
	   * @return true valid image, false invalid image
	   */
	public static boolean isImage(String image) {
		final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";
		Pattern pattern = Pattern.compile(IMAGE_PATTERN);
		Matcher matcher = null;
		if (isNotEmptyAndNotNullString(image)) {
			matcher = pattern.matcher(image);
			return matcher.matches();
		}
		return false;
	}
	
	public static String getProfileUrl(String currentProfileUrl) {
		
		String profileUrl = currentProfileUrl;
		
		if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
			profileUrl = SocialWatcherConstants.NO_IMAGE_URL;
		
		return profileUrl;
		
	}
	
	public static String getSearchEngineLogo(String searchEngineName) {
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(searchEngineName)) {
        	if (SocialWatcherConstants.FACEBOOK_SEARCH_ENGINE.trim().equals(searchEngineName)) {
        		return SocialWatcherConstants.FACEBOOK_IMAGE_URL;
        	}
        	if (SocialWatcherConstants.TWITTER_SEARCH_ENGINE.trim().equals(searchEngineName)) {
        		return SocialWatcherConstants.TWITTER_IMAGE_URL;
        	}
        }
		
		return "";
		
	}
	
	public static void getPropertyNames(Class<?> cls) {
		
		PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(cls);
		for (PropertyDescriptor propertyDescriptor : properties) {
			logger.debug(propertyDescriptor.getName());
		}
	}
	
	public static boolean checkBooleanIsTrue(String value) {
		
		if (isNotEmptyAndNotNullString(value)) {
			if (value.equalsIgnoreCase("true") || 
					value.equalsIgnoreCase("yes") || 
					value.equalsIgnoreCase("y") ||
					value.equals("1"))
				return true;
		}
		
		return false;
	}
	
	public static boolean checkBooleanIsFalse(String value) {
		
		if (isNotEmptyAndNotNullString(value)) {
			if (value.equalsIgnoreCase("false") || 
					value.equalsIgnoreCase("no") || 
					value.equalsIgnoreCase("n") ||
					value.equals("0"))
				return true;
		}
		
		return false;
	}

	public static String isUser(String roleName) {
		
		if (roleName.equals("admin"))
			return "admin";
		if (roleName.equals("partner"))
			return "partner";
		if (roleName.equals("client"))
			return "client";
		
		return "user";
	}

}

/**
 * 
 */
package com.sitequesttech.social.watcher.common.exception;

/**
 * @author sivaprasad.vindula@icentris.com
 *
 */
public class SocialWatcherException extends BaseException{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor
	 */
	public SocialWatcherException(){
		
	}

	public SocialWatcherException(String message, Throwable cause) {
		super(message, cause);
	}

	public SocialWatcherException(String message) {
		super(message);
	}

	public SocialWatcherException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 
	 * @param errorId
	 * @param errorIdDesc
	 * @param correlationId
	 */
	public SocialWatcherException(int errorId, String errorIdDesc,
			String correlationId) {
		super(errorId, errorIdDesc, correlationId);
	}

	/**
	 * 
	 * @param errorId
	 * @param errorIdDesc
	 * @param e
	 */
	public SocialWatcherException(int errorId, String errorIdDesc, Throwable e) {
		super(errorId, errorIdDesc, e);
	}

	public SocialWatcherException(int errorId, String errorIdDesc, Throwable e,
			String correlationId) {
		super(errorId, errorIdDesc, e, correlationId);
	}
}

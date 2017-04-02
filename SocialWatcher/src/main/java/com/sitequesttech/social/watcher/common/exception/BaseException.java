/**
 * 
 */
package com.sitequesttech.social.watcher.common.exception;


/**
 * @author sivaprasad.vindula@icentris.com
 *
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int errorId;
    private String errorIdDesc;
    private String correlationId;

    public BaseException() {}

    public BaseException(final BaseException e) {
    	this( e.getErrorId(),
    		  e.getErrorIdDesc(),
    		  e,
    		  e.getCorrelationId() );
    }

    public BaseException( final int errorId,
    						final String errorIdDesc,
    						final String correlationId ) {
        this(errorId, errorIdDesc, null, correlationId);
    }

    public BaseException( final int errorId,
    						final String errorIdDesc,
    						final Throwable e ) {
        this(errorId, errorIdDesc, e, null);
    }

    public BaseException( final int errorId,
    						final String errorIdDesc,
    						final Throwable e,
    						final String correlationId )
    {
        super(e);

        this.errorId = errorId;
        this.errorIdDesc = errorIdDesc;
        this.correlationId = correlationId;
    }

    public BaseException(String message, Throwable cause) {
    	super(message,cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public String getCorrelationId() {
        return this.correlationId;
    }

    public int getErrorId() {
        return this.errorId;
    }

    public Throwable getOriginalException() {
        return getCause();
    }

    public String getErrorIdDesc() {
        return this.errorIdDesc;
    }

    public String getMessage() {
        return this.errorId + " " + this.errorIdDesc;
    }
}

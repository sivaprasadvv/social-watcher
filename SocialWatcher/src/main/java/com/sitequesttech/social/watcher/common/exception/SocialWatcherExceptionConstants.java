package com.sitequesttech.social.watcher.common.exception;

public interface SocialWatcherExceptionConstants {
    
    static final int BASE_ERRORID = 10000;
    
    static final int RANGE = 1000;
    
    static final int SOCIAL_WATCHER_DB_ERRORID_BASE = BASE_ERRORID + RANGE;
    
    public static final int SOCIAL_WATCHER_GENERAL_ERROR = BASE_ERRORID + 0;
    
    public static final String SOCIAL_WATCHER_GENERAL_ERROR_DESC = "General Application Error";
    
    public static final int SOCIAL_WATCHER_INVALID_PATH_ERROR = BASE_ERRORID + 1;
    
    public static final String SOCIAL_WATCHER_INVALID_PATH_ERROR_DESC = "Not a valid Path";
    
    public static final int SOCIAL_WATCHER_INVALID_RECORD_FORMAT_ERROR = BASE_ERRORID + 2;
    
    public static final String SOCIAL_WATCHER_INVALID_RECORD_FORMAT_ERROR_DESC = "Not a valid Record Format";
    
    public static final int SOCIAL_WATCHER_FILE_NOT_FOUND_ERROR = BASE_ERRORID + 3;
    
    public static final String SOCIAL_WATCHER_FILE_NOT_FOUND_ERROR_DESC = "File not Found";
    
    public static final int SOCIAL_WATCHER_FILE_READ_ERROR = BASE_ERRORID + 4;
    
    public static final String SOCIAL_WATCHER_FILE_READ_ERROR_DESC = "Error while file reading";
    
    public static final int SOCIAL_WATCHER_FILE_WRITE_ERROR = BASE_ERRORID + 5;
    
    public static final String SOCIAL_WATCHER_FILE_WRITE_ERROR_DESC = "Error while file writing";
    
    public static final int SOCIAL_WATCHER_INVALID_BUNDLE_ID = BASE_ERRORID + 6;
    
    public static final String SOCIAL_WATCHER_INVALID_BUNDLE_ID_DESC = "Invalid Bundle Id";
    
    public static final int SOCIAL_WATCHER_INVALID_DATA_ERROR = BASE_ERRORID + 7;
    
    public static final String SOCIAL_WATCHER_INVALID_DATA_ERROR_DESC = "Invalid data recevied in the input record";
    
    // Start: DB related errors
    public static final int SOCIAL_WATCHER_DB_CONNECTION_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 1;
    
    public static final String SOCIAL_WATCHER_DB_CONNECTION_ERROR_DESC = "Failed in getting DB Connection";
    
    public static final int SOCIAL_WATCHER_HIBERNATE_SESSION_OPEN_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 2;
    
    public static final String SOCIAL_WATCHER_HIBERNATE_SESSION_ERROR_OPEN_DESC = "Failed to get session";
    
    public static final int SOCIAL_WATCHER_HIBERNATE_SESSION_CLOSE_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 3;
    
    public static final String SOCIAL_WATCHER_HIBERNATE_SESSION_ERROR_CLOSE_DESC = "Failed to close session";
    
    public static final int SOCIAL_WATCHER_HIBERNATE_BEGIN_TRANSACTION_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 4;
    
    public static final String SOCIAL_WATCHER_HIBERNATE_BEGIN_TRANSACTION_ERROR_DESC = "Failed to begin transaction";
    
    public static final int SOCIAL_WATCHER_HIBERNATE_COMMIT_TRANSACTION_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 5;
    
    public static final String SOCIAL_WATCHER_HIBERNATE_COMMIT_TRANSACTION_ERROR_DESC = "Failed to commit transaction";
    
    public static final int SOCIAL_WATCHER_HIBERNATE_ROLLBACK_TRANSACTION_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 6;
    
    public static final String SOCIAL_WATCHER_HIBERNATE_ROLLBACK_TRANSACTION_ERROR_DESC = "Failed to rollback transaction";
    
    public static final int SOCIAL_WATCHER_UNABLE_TO_STORE_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 7;
    
    public static final String SOCIAL_WATCHER_UNABLE_TO_STORE_ERROR_DESC = "Unable to store";
    
    public static final int SOCIAL_WATCHER_UNABLE_TO_DELETE_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 8;
    
    public static final String SOCIAL_WATCHER_UNABLE_TO_DELETE_ERROR_DESC = "Unable to delete";
    
    public static final int SOCIAL_WATCHER_UNABLE_TO_RETRIEVE_ERROR = SOCIAL_WATCHER_DB_ERRORID_BASE + 9;
    
    public static final String SOCIAL_WATCHER_UNABLE_TO_RETRIEVE_ERROR_DESC = "Unable to fectch";
    
    // End: DB related errors
    
    // Start: Social Watcher File Log constants
    public static final String SOCIAL_WATCHER_FILE_LOG_LOAD_IN_PROCESS = "LOAD_INPROCESS";
    
    public static final String SOCIAL_WATCHER_FILE_LOG_LOAD_SUCCESS = "LOAD_SUCCESS";
    
    public static final String SOCIAL_WATCHER_FILE_LOG_LOAD_FAILURE = "LOAD_FAILURE";
    
    public static final String SOCIAL_WATCHER_FILE_LOG_PROCESSED = "PROCESSED";
    
    // End: Social Watcher File Log constants
    
}

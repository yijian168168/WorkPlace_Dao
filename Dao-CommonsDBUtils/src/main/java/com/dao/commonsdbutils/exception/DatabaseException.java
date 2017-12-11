package com.dao.commonsdbutils.exception;

/**
 * Created by zhangqingrong on 2016/5/7.
 */
public class DatabaseException extends Exception {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

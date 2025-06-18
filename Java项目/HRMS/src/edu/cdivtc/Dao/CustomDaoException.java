package edu.cdivtc.Dao;

/**
 * 自定义数据库异常类，用于封装 DAO 层的异常
 */
public class CustomDaoException extends Exception {

    public CustomDaoException(String message) {
        super(message);
    }

    public CustomDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

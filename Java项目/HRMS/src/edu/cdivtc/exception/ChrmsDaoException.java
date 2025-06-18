// 文件路径: src/edu/cdivtc/exception/ChrmsDaoException.java
package edu.cdivtc.exception;

/**
 * 自定义数据库异常类
 */
public class ChrmsDaoException extends Exception {
    public ChrmsDaoException(String message) {
        super(message);
    }

    public ChrmsDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
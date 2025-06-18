package edu.cdivtc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 数据库连接工具类
 */
public class JdbcUtils {
    private static final Logger LOGGER = Logger.getLogger(JdbcUtils.class.getName());
    private static String driver, url, username, password;

    static {
        try {
            // 加载db.properties配置
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            if (is == null) {
                throw new RuntimeException("配置文件 db.properties 未找到");
            }
            Properties prop = new Properties();
            prop.load(is);
            
            driver = prop.getProperty("jdbc.driver");
            url = prop.getProperty("jdbc.url");
            username = prop.getProperty("jdbc.username");
            password = prop.getProperty("jdbc.password");
            
            Class.forName(driver);  // 注册驱动
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "加载配置文件时发生错误", e);
            throw new RuntimeException("加载配置文件时发生错误", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "指定的驱动类未找到", e);
            throw new RuntimeException("指定的驱动类未找到", e);
        }
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "获取数据库连接失败", e);
            throw e;
        }
    }

    // 释放资源（关闭连接等）
    public static void release(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "关闭ResultSet时发生错误", e);
        }
        
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "关闭Statement时发生错误", e);
        }
        
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "关闭Connection时发生错误", e);
        }
    }
    
    // 开启事务
    public static void beginTransaction(Connection conn) throws SQLException {
        if (conn != null) {
            conn.setAutoCommit(false);
        }
    }
    
    // 提交事务
    public static void commitTransaction(Connection conn) throws SQLException {
        if (conn != null) {
            conn.commit();
            conn.setAutoCommit(true);
        }
    }
    
    // 回滚事务
    public static void rollbackTransaction(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "回滚事务时发生错误", e);
            }
        }
    }
}
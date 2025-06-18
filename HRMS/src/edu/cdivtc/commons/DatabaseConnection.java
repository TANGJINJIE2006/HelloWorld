package edu.cdivtc.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        // 示例代码（请根据实际情况修改数据库连接逻辑）
        String url = "jdbc:mysql://localhost:3306/chrms";
        String user = "root";
        String password = "123456";
        try {
            // 返回数据库连接
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // 输出错误信息到控制台
            System.err.println("数据库连接失败: " + e.getMessage());
            // 重新抛出异常以便上层处理
            throw e;
        }
    }
}

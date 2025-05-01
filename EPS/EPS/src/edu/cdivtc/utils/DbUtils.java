package edu.cdivtc.utils;

import javax.swing.*;
import java.sql.*;

public class DbUtils {
 //静态代码块加载数据库驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动a
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // 若报错,则提示报错解决方案
            JOptionPane.showMessageDialog(null,"数据库驱动加载失败，请将驱动包加载到项目下");
        }
    }
//获取数据库连接
    public static Connection getConn() {
        try{
            Connection conn = null;
            String url = "jdbc:mysql://127.0.0.1:3306/db_express_app";//项目对应的数据库URL
            String username = "root";//数据库用户名
            String password = "123456";//密码
            conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "数据库连接失败，请确认数据库用户名和密码是否正确。");
            return null;
        }
    }
// 测试
    public static void main(String[] args) {
        System.out.println(getConn());
    }
}

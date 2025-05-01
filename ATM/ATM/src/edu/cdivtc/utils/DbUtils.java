package edu.cdivtc.utils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
    //静态代码块加载数据库驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //若报错，则提示解决方案
            JOptionPane.showMessageDialog(null, "数据库驱动加载失败，请将驱动包加载到项目下");
        }
    }
    //获取数据库连接
    public static java.sql.Connection getConn() {
        try {
            Connection conn = null; //定义数据库连接
            String url = "jdbc:mysql://127.0.0.1:3306/db_atm_app"; //数据库地址URL
            String username = "root"; //数据库的用户名
            String password = "123456"; //密码
            conn = DriverManager.getConnection(url, username, password); //建立数据库连接
            return conn; //返回数据库连接
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "数据库连接失败，请确认数据用户名和密码是否正确。");
            return null; //若连接失败，则返回null
        }
    }
    //测试
    public static void main(String[] args) {
        System.out.println(getConn());
    }
}

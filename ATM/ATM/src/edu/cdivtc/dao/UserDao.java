package edu.cdivtc.dao;

import edu.cdivtc.entity.User;
import edu.cdivtc.utils.DbUtils;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    /**
     * 用户登录
     * @param accountNumber 卡号
     * @param password      密码
     * @return
     */
    public static boolean userLogin(String accountNumber, String password) {
        //1.获取数据库连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2.数据库语句
            ps = conn.prepareStatement("SELECT a.accountNumber,a.password,a.money,u.username" +
                    " from db_user u,db_account a " +
                    " WHERE a.accountNumber = ? AND a.password = ? AND a.cid = u.fk_cid");
            //3.处理数据库语句的参数
            ps.setString(1, accountNumber);
            ps.setString(2, password);
            //4.执行SQL语句
            ResultSet rs = ps.executeQuery();
            //5.处理结果集
            if (rs.next() && rs.getRow() > 0) {
                String resAccountNumber = rs.getString(1);//查询后的账号
                String resPassword = rs.getString(2);//查询后的密码
                String money = rs.getString(3);//查询后的余额
                String resUsername = rs.getString(4);//查询后的用户名
                //缓存登录成功后的用户信息
                UserSaveTool.setCurrentLoginAccountNumber(resAccountNumber);
                UserSaveTool.setCurrentLoginAccountPassword(resPassword);
                UserSaveTool.setCurrentLoginMoney(money);
                UserSaveTool.setCurrentLoginUsername(resUsername);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "账号密码错误");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库异常!");
            return false;
        } finally {
            try {
                //6.关闭数据库连接
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 开卡/注册
     * @param username 姓名
     * @param uid 身份证号码
     * @param gender 性别
     * @return
     */
    public static String addUser(String username, String uid, String gender) {
        //1.生成新卡，并获取到新卡卡号--操作卡号信息表
        String accountNumber = AccountDao.addAccount();
        //2.获取到新卡在数据库表中的主键id
        String cid = AccountDao.selectAccountByAccountNumber(accountNumber);
        Connection conn = DbUtils.getConn(); //获取数据库连接
        PreparedStatement ps = null;
        try {
            //3.数据库语句--- 在用户表中建立新卡与该用户的关系--- 操作用户表
            ps = conn.prepareStatement("INSERT INTO db_user(username,identityCard,gender,fk_cid) VALUES(?,?,?,?)");
            //数据库语句的参数
            ps.setString(1, username);
            ps.setString(2, uid);
            ps.setString(3, gender);
            ps.setString(4, cid);
            //4.执行SQL语句
            int i = ps.executeUpdate();
            //5.处理结果
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "开卡成功!");
                return accountNumber;
            } else {
                JOptionPane.showMessageDialog(null, "开卡失败!");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库异常!");
            return "";
        } finally {
            try {
                //6.关闭数据库连接
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
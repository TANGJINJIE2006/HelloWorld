package edu.cdivtc.dao;

import edu.cdivtc.utils.DbUtils;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    /**
     * 存款功能
     *
     * @param depositMoney 存入金额
     * @return
     */
    public static boolean deposit(Double depositMoney) {
        //1. 获取数据库连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2. 定义SQL语句
            ps = conn.prepareStatement("update db_account SET money = money + ? WHERE accountNumber = ?");
            //3.处理SQL语句的参数
            ps.setString(1, depositMoney.toString()); //存入的金额
            ps.setString(2, UserSaveTool.getCurrentLoginAccountNumber()); //登录用户的银行卡号密码
            //4.执行SQL语句
            int i = ps.executeUpdate();
            //5.判断结果
            if (i > 0) {
                //将登录缓存的银行卡余额进行更新：原有的+存入的
                double newMoney = Double.parseDouble(UserSaveTool.getCurrentLoginMoney()) + depositMoney;
                UserSaveTool.setCurrentLoginMoney(String.valueOf(newMoney));
                JOptionPane.showMessageDialog(null, "存入成功！");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "存入失败！");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库出错！");
            e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取款功能
     *
     * @param withdrawmoney 取款金额
     * @return
     */
    public static boolean withdraw(Double withdrawmoney) {
        //1.获取数据库连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2.编写SQL语句
            ps = conn.prepareStatement("update db_account SET money = money - ? WHERE accountNumber = ?");
            //3.处理SQL语句的参数
            ps.setString(1, withdrawmoney.toString()); //取出的金额
            ps.setString(2, UserSaveTool.getCurrentLoginAccountNumber()); //登录用户的银行卡号密码
            //4.执行SQL语句
            int i = ps.executeUpdate();
            //5.判断结果
            if (i > 0) {
                //将登录缓存的银行卡余额进行更新：原有的-取出的
                double newMoney = Double.parseDouble(UserSaveTool.getCurrentLoginMoney()) - withdrawmoney;
                UserSaveTool.setCurrentLoginMoney(String.valueOf(newMoney));
                JOptionPane.showMessageDialog(null, "取款成功！");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "取款失败！");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库出错！");
            e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //生成新卡
    public static String addAccount(){
        //随机生成卡号
        int resRandom = (int) (Math.random() * 9000)+1000; //后4位取值范围【1000，9999】
        String accontNumber = "62170038000"+String.valueOf(resRandom); //前面部分固定为62170038000
        //1.获取连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2.编写SQL语句
            ps = conn.prepareStatement("insert into db_account(accountNumber) values(?)");
            //3.处理SQL语句的参数
            ps.setString(1, accontNumber); //为SQL中的参数赋值
            //4.执行SQL语句
            int i = ps.executeUpdate();
            //5.判断结果
            if (i > 0) {
                return accontNumber;
            } else {
                JOptionPane.showMessageDialog(null, "开卡失败！");
                return "";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错!");
            e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 查询新卡id
     * @param accountNumber 新卡卡号
     * @return
     */
    public static String selectAccountByAccountNumber(String accountNumber){
        //1.获取连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2.编写SQL语句
            ps = conn.prepareStatement("select cid from db_account where accountNumber = ?");
            //3.处理SQL语句的参数
            ps.setString(1, accountNumber); //为SQL中的参数赋值
            //4.执行SQL语句
            ResultSet rs = ps.executeQuery();
            //5.判断结果
            if (rs.next() && rs.getRow() > 0) {
                String cid = rs.getString(1); //查询后的账号
                return cid;
            } else {
                JOptionPane.showMessageDialog(null, "开卡失败！");
                return "";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错!");
            e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

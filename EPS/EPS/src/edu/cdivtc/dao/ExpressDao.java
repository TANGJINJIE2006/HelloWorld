package edu.cdivtc.dao;

import edu.cdivtc.entity.Express;
import edu.cdivtc.utils.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpressDao {
    // 添加快递功能
    // @param express 整个快递的信息
    // @return boolean类型 true:添加成功;false:添加失败
    public static boolean addExpress(Express express) {
        // 1. 获取连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2. 编写SQL语句
            ps = conn.prepareStatement(
                    "INSERT INTO db_express(sendName, sendPhone, sendCompany, sendAddress, sendPostcode," +
                            "receiveName, receivePhone, receiveCompany, receiveAddress, receivePostcode)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // 3. 处理SQL语句参数
            ps.setString(1, express.getSendName());
            ps.setString(2, express.getSendPhone());
            ps.setString(3, express.getSendCompany());
            ps.setString(4, express.getSendAddress());
            ps.setString(5, express.getSendPostcode());
            ps.setString(6, express.getReceiveName());
            ps.setString(7, express.getReceivePhone());
            ps.setString(8, express.getReceiveCompany());
            ps.setString(9, express.getReceiveAddress());
            ps.setString(10, express.getReceivePostcode());

            // 4. 执行SQL语句
            int i = ps.executeUpdate();
            // 5. 判断是否执行成功
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "添加成功！");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "添加失败！");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错!");
            e.printStackTrace();
            return false;
        } finally {
            try {
                // 6. 关闭连接
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

    //    根据快递ID查询快递信息
    //    @param id 快递ID
    //    @return Express查询到的快递信息
    public static Express selectExpressById(String ID) {
        //1. 获取连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2. 编写SQL语句
            ps = conn.prepareStatement("SELECT * FROM db_express WHERE id = ?");
            //3. 处理SQL语句参数
            ps.setString(1, ID);
            //4. 执行SQL语句
            ResultSet rs = ps.executeQuery();
            //5. 判断结果
            if (rs.next() && rs.getRow() > 0) {
                Express express = new Express(Integer.parseInt(rs.getString(1)),
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11));
                return express;
            } else {
                JOptionPane.showMessageDialog(null, "还没有快递单");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错!");
            e.printStackTrace();
            return null;
        } finally {
            try {
                //6. 关闭连接
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

    //查询出快递单的总数
    //@return Integer 总数
    public static Integer selectExpressCount() {
        //1.获取数据库连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2.编写SQL语句
            ps = conn.prepareStatement("select count(ID) from db_express");
            //3.处理SQL语句参数
            //4.执行SQL语句
            ResultSet rs = ps.executeQuery();
            //5.获取结果集
            if (rs.next() && rs.getRow() > 0) {
                Integer count = Integer.parseInt(rs.getString(1));
                return count;
            } else {
                JOptionPane.showMessageDialog(null, "还没有快递单");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错");
            e.printStackTrace();
            return null;
        } finally {
            try {
                //6.关闭资源
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

    //修改当前显示的快递单信息
    //@param express 要修改的快递单信息
    //@return boolean true:修改成功;false:修改失败
    public static boolean updateExpressById(Express express) {
        //1. 获取数据库连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try {
            //2. 编写SQL语句
            ps = conn.prepareStatement("UPDATE db_express SET sendName = ?, sendPhone = ?," +
                    "sendCompany = ?, sendAddress = ?, sendPostcode = ?, receiveName = ?, receivePhone = ?," +
                    " receiveCompany = ?, receiveAddress = ?, receivePostcode = ? WHERE id = ?");
            //3. 处理SQL语句参数
            ps.setString(1, express.getSendName());//为SQl中的参数赋值
            ps.setString(2, express.getSendPhone());
            ps.setString(3, express.getSendCompany());
            ps.setString(4, express.getSendAddress());
            ps.setString(5, express.getSendPostcode());
            ps.setString(6, express.getReceiveName());
            ps.setString(7, express.getReceivePhone());
            ps.setString(8, express.getReceiveCompany());
            ps.setString(9, express.getReceiveAddress());
            ps.setString(10, express.getReceivePostcode());
            ps.setString(11, String.valueOf(express.getId()));
            //4. 执行SQL语句
            int i = ps.executeUpdate();
            //5. 判断是否执行成功
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "修改成功！");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "修改失败！");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库出错");
            e.printStackTrace();
            return false;
        } finally {
            try {
                //6. 关闭资源
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



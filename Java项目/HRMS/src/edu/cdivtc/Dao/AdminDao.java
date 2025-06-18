package edu.cdivtc.Dao;

import edu.cdivtc.entity.Admin;
import edu.cdivtc.commons.Room;
import edu.cdivtc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 管理员数据访问对象
 */
public class AdminDao {
    private static final Logger LOGGER = Logger.getLogger(AdminDao.class.getName());

    // 管理员登录验证
    public Admin login(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ? AND status = 1";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setEmail(rs.getString("email"));
                admin.setRealName(rs.getString("real_name"));
                admin.setPhone(rs.getString("phone"));
                admin.setLastLogin(rs.getTimestamp("last_login"));
                admin.setStatus(rs.getInt("status"));
                admin.setCreatedAt(rs.getTimestamp("created_at"));
                admin.setUpdatedAt(rs.getTimestamp("updated_at"));

                // 更新最后登录时间
                updateLastLogin(conn, admin.getAdminId());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "管理员登录验证失败", e);
            throw e;
        } finally {
            JdbcUtils.release(conn, pstmt, rs);
        }

        return admin;
    }

    // 更新最后登录时间
    private void updateLastLogin(Connection conn, int adminId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            String sql = "UPDATE admin SET last_login = CURRENT_TIMESTAMP WHERE admin_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, adminId);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    // 添加管理员
    public boolean addAdmin(Admin admin) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "INSERT INTO admin (username, password, email, real_name, phone, status) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getEmail());
            pstmt.setString(4, admin.getRealName());
            pstmt.setString(5, admin.getPhone());
            pstmt.setInt(6, admin.getStatus());

            int affectedRows = pstmt.executeUpdate();
            success = affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "添加管理员失败", e);
            throw e;
        } finally {
            JdbcUtils.release(conn, pstmt, null);
        }

        return success;
    }

    // 根据ID获取管理员
    public Admin getAdminById(int adminId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM admin WHERE admin_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, adminId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setEmail(rs.getString("email"));
                admin.setRealName(rs.getString("real_name"));
                admin.setPhone(rs.getString("phone"));
                admin.setLastLogin(rs.getTimestamp("last_login"));
                admin.setStatus(rs.getInt("status"));
                admin.setCreatedAt(rs.getTimestamp("created_at"));
                admin.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "获取管理员信息失败", e);
            throw e;
        } finally {
            JdbcUtils.release(conn, pstmt, rs);
        }

        return admin;
    }

    // 更新管理员信息
    public boolean updateAdmin(Admin admin) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "UPDATE admin SET email = ?, real_name = ?, phone = ?, status = ? " +
                         "WHERE admin_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin.getEmail());
            pstmt.setString(2, admin.getRealName());
            pstmt.setString(3, admin.getPhone());
            pstmt.setInt(4, admin.getStatus());
            pstmt.setInt(5, admin.getAdminId());

            int affectedRows = pstmt.executeUpdate();
            success = affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "更新管理员信息失败", e);
            throw e;
        } finally {
            JdbcUtils.release(conn, pstmt, null);
        }

        return success;
    }

    // 删除管理员
    public boolean deleteAdmin(int adminId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "DELETE FROM admin WHERE admin_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, adminId);

            int affectedRows = pstmt.executeUpdate();
            success = affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "删除管理员失败", e);
            throw e;
        } finally {
            JdbcUtils.release(conn, pstmt, null);
        }

        return success;
    }
    //客房管理
    public boolean addRoom(Room room) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "INSERT INTO room (room_no, room_type, floor, status, price, bed_type, max_guest,facilities) " +
                         "VALUES (?, ?，?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, room.getRoomNo());
            pstmt.setString(2, room.getRoomType());
            pstmt.setDouble(3, room.getFloor());
            pstmt.setInt(4, room.getStatus());
            pstmt.setString(5, room.getPrice());
            pstmt.setString(6, room.getBedType());
            pstmt.setString(7, room.getMaxGuest());
            pstmt.setString(8, room.getFacilities());

            int affectedRows = pstmt.executeUpdate();
            success = affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "添加房间失败", e);
            throw e;
        } finally {
            JdbcUtils.release(conn, pstmt, null);
        }

        return success;
    }


}
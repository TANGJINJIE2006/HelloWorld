package edu.cdivtc.entity;


import edu.cdivtc.Dao.AdminDao;
import edu.cdivtc.exception.ChrmsDaoException;

import java.sql.SQLException;

/**
 * 管理员服务类
 */
public class AdminService {
    private AdminDao adminDao = new AdminDao();

    public Admin login(String username, String password) throws ChrmsDaoException {
        try {
            // 在实际应用中，应该先对密码进行加密处理
            // String encryptedPassword = PasswordUtils.encrypt(password);
            return adminDao.login(username, password);
        } catch (SQLException e) {
            throw new ChrmsDaoException("登录验证失败", e);
        }
    }

    // 其他业务方法...
}
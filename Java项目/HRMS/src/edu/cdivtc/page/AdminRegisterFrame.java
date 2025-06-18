package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.theme.ThemeColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 管理员注册界面
 */
public class AdminRegisterFrame extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton saveAndLoginBtn, backBtn;

    public AdminRegisterFrame() {
        setTitle("管理员注册");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(ThemeColors.CONTENT_BACKGROUND);
        JPanel panel = (JPanel) getContentPane();
        JLabel title = new JLabel("管理员注册");
        ComponentStyle.setFormStyle(title, 24, ThemeColors.CONTENT_TITLE, 30, 20, 300, 40, panel);
        JLabel usernameLabel = new JLabel("用户名:");
        ComponentStyle.setFormStyle(usernameLabel, 18, Color.BLACK, 50, 100, 100, 30, panel);
        usernameField = new JTextField();
        usernameField.setBounds(150, 100, 250, 30);
        panel.add(usernameField);
        JLabel emailLabel = new JLabel("邮箱:");
        ComponentStyle.setFormStyle(emailLabel, 18, Color.BLACK, 50, 150, 100, 30, panel);
        emailField = new JTextField();
        emailField.setBounds(150, 150, 250, 30);
        panel.add(emailField);
        JLabel passwordLabel = new JLabel("密码:");
        ComponentStyle.setFormStyle(passwordLabel, 18, Color.BLACK, 50, 200, 100, 30, panel);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 200, 250, 30);
        panel.add(passwordField);
        // 左侧返回按钮
        backBtn = new JButton("返回登录");
        ComponentStyle.setButtonStyle(backBtn, ThemeColors.PRIMARY_BUTTON, 50, 270, 150, 40, panel);
        // 右侧保存并登录按钮
        saveAndLoginBtn = new JButton("保存并登录");
        ComponentStyle.setButtonStyle(saveAndLoginBtn, ThemeColors.SUCCESS_BUTTON, 250, 270, 180, 40, panel);
        // 点击“返回登录”
        backBtn.addActionListener((ActionEvent e) -> {
            this.dispose();
            new LoginFrame();
        });
        // 点击“登录并保存”
        saveAndLoginBtn.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写完整信息", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("admin_accounts.txt", true))) {
                writer.write("用户名: " + username + ", 邮箱: " + email + ", 密码: " + password);
                writer.newLine();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "保存失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, "注册成功，正在进入系统...", "提示", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();  // 关闭注册页面
            new MainFrame(username);  // 进入主界面，并传入用户名
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminRegisterFrame::new);
    }
}

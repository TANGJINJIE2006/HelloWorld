package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private JPanel loginPanel;
    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton, loginButton;

    public LoginFrame() {
        // 设置窗体样式
        ComponentStyle.setFrame("蓉城酒店预订管理系统-登录界面", 1500, 900, "src/images/login_bg.png", this);

        // 初始化登录面板
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        this.add(loginPanel);

        // 标题
        titleLabel = new JLabel("欢迎登录蓉城酒店预订管理系统", JLabel.CENTER);
        ComponentStyle.setFormStyle(titleLabel, 66, Color.white, 270, 60, 980, 100, loginPanel);
        // 添加左侧Logo图片
        ImageIcon logoIcon = new ImageIcon("src/images/cd.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(20, 150,  700, 700); // 可根据UI需要调整
        loginPanel.add(logoLabel, Integer.valueOf(1));
        // 用户名标签与输入框
        usernameLabel = new JLabel("用户名：", JLabel.RIGHT);
        ComponentStyle.setFormStyle(usernameLabel, 24, new Color(70, 130, 180), 750, 380, 150, 40, loginPanel);

        usernameField = new JTextField();
        ComponentStyle.setFormStyle(usernameField, 20, Color.BLACK, 910, 380, 300, 40, loginPanel);

        // 密码标签与输入框
        passwordLabel = new JLabel("密　码：", JLabel.RIGHT);
        ComponentStyle.setFormStyle(passwordLabel, 24, new Color(70, 130, 180), 750, 450, 150, 40, loginPanel);

        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        ComponentStyle.setFormStyle(passwordField, 20, Color.BLACK, 910, 450, 300, 40, loginPanel);

        // 注册按钮
        registerButton = new JButton("管理员注册");
        ComponentStyle.setButtonStyle(registerButton, new Color(255, 165, 0), 850, 550, 140, 45, loginPanel);
        registerButton.setForeground(Color.BLACK);

        // 登录按钮
        loginButton = new JButton("立即登录");
        ComponentStyle.setButtonStyle(loginButton, new Color(0, 200, 0), 1030, 550, 140, 45, loginPanel);
        loginButton.setForeground(Color.BLACK);

        // 设置背景遮罩层（可选，增强视觉效果）
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(740, 360, 500, 270);
        backgroundPanel.setBackground(new Color(173, 216, 230, 180)); // 半透明浅蓝
        backgroundPanel.setOpaque(true);
        loginPanel.add(backgroundPanel, Integer.valueOf(0)); // 添加到底层

        // 添加事件监听
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);

        // 设置背景图
        ComponentStyle.setPanelBackgroundImg("src/images/login_bg.png", 0, 0, 1500, 900, loginPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            this.setVisible(false);
            new AdminRegisterFrame();
        } else if (e.getSource() == loginButton) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请输入用户名和密码！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 此处可以添加数据库验证逻辑（示例省略）
            // 假设验证成功，打开主界面并传递用户名
            this.setVisible(false);
            new MainFrame(username);
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new LoginFrame());
//    }
public static void main(String[] args) {
    new LoginFrame();
}
}


package edu.cdivtc.page;
import edu.cdivtc.dao.UserDao;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame implements ActionListener {

    //项目logo
    private ImageIcon logoIcon;
    //登录面板
    private JPanel loginPanel;
    //标题文字，用户名文字，密码文字
    private JLabel tittleLabel, usernameLabel, passwordLabel;
    //用户名输入框
    private JTextField usernameField;
    //密码输入框
    private JPasswordField passwordField;
    //登录按钮，重置按钮
    private JButton loginButton, resetButton;
    //背景图片
    private JLabel bgImageLabel;

    //无参构造
    public LoginFrame() {
        setTitle("快递打印系统");//设置窗体标题
        setSize(1000, 600);//设置窗体大小
        setLocationRelativeTo(null);//设置窗体在电脑屏幕中间显示
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭属性
        setResizable(false);//设置窗体不可通过拖动改变大小
        //设置窗体图标logo
        logoIcon = new ImageIcon("src/images/logo.png");//实例化logo图片
        this.setIconImage(logoIcon.getImage());//将logo图片添加到登录窗体LoginFrame中
        //1.登录面板
        loginPanel = new JPanel();//实例化登录面板
        loginPanel.setLayout(null);//绝对布局
        this.add(loginPanel);//将登录面板添加到登录窗体LoginFrame中
        //2.大标题--快递打印系统
        tittleLabel = new JLabel("快递打印系统", JLabel.CENTER);//实例化大标题
        tittleLabel.setFont(new Font("楷体", Font.BOLD, 40));//设置字体
        tittleLabel.setBounds(480, 120, 550, 50);//设置位置和宽高
        loginPanel.add(tittleLabel);//将大标题添加到登录面板LoginFrame中
        //3.用户名--文字+输入框
        usernameLabel = new JLabel("用户名：", JLabel.CENTER);//实例化用户名文字
        usernameLabel.setFont(new Font("楷体", Font.BOLD, 25));//设置字体
        usernameLabel.setBounds(450, 200, 200, 50);//设置位置和宽高
        loginPanel.add(usernameLabel);//将用户名文字添加到登录面板LoginFrame中
        usernameField = new JTextField();//实例化用户名输入框
        usernameField.setFont(new Font("楷体", Font.BOLD, 25));//设置字体
        usernameField.setBounds(620, 200, 280, 50);//设置位置和宽高
        loginPanel.add(usernameField);//将用户名文字添加到登录面板LoginFrame中
        //4.密码--文字+输入框
        passwordLabel = new JLabel("密码", JLabel.CENTER);//实例化密码文字
        passwordLabel.setFont(new Font("楷体", Font.BOLD, 25));//设置字体
        passwordLabel.setBounds(450, 280, 200, 50);//设置位置和宽高
        loginPanel.add(passwordLabel);//将密码文字添加到登录面板LoginFrame中
        passwordField = new JPasswordField();//实例化密码输入框
        passwordField.setFont(new Font("楷体", Font.BOLD, 25));//设置字体
        passwordField.setBounds(620, 280, 280, 50);//设置位置和宽高
        passwordField.setEchoChar('*');//设置密码输入显示字符为*
        loginPanel.add(passwordField);//将密码输入框添加到登录面板LoginFrame中
        //5.按钮--重置+登录
        resetButton = new JButton("重置");//实例化按钮
        resetButton.setBackground(Color.orange);//背景色
        resetButton.setFont(new Font("楷体", Font.BOLD, 25));//设置字体
        resetButton.setBounds(620, 360, 120, 50);//设置位置和宽高
        loginPanel.add(resetButton);//将按钮添加到登录面板LoginFrame中
        loginButton = new JButton("登录");//实例化按钮
        loginButton.setBackground(Color.green);//背景色
        loginButton.setFont(new Font("楷体", Font.BOLD, 25));//设置字体
        loginButton.setBounds(780, 360, 120, 50);//设置位置和宽高
        loginPanel.add(loginButton);//将按钮添加到登录面板LoginFrame中
        // 设置回车键触发登录按钮
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(loginPanel);
        if (topFrame != null) {
            topFrame.getRootPane().setDefaultButton(loginButton);
        }
        //6.登录页面背景图片
        bgImageLabel = new JLabel(new ImageIcon("src/images/login.png"));//实例化图片
        bgImageLabel.setBounds(0, 0, 1000, 600);//设置位置和宽高
        loginPanel.add(bgImageLabel);//将图片添加到登录面板LoginFrame中
        //为重置按钮和登录按钮添加事件监听
        resetButton.addActionListener(this);
        loginButton.addActionListener(this);
        setVisible(true);//设置窗体可见
    }

    //主方法
    public static void main(String[] args) {
        new LoginFrame();//实例化登录窗体
    }

    //点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {     //重置按钮
            //清空登录表单
            usernameField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == loginButton) {  //登录按钮
            //1.获取用户输入的用户名和密码
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            //2.验证输入是否合法
            if (username == null || username.equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
                return;
            }
            if (password == null || password.equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
                return;
            }
            //3.实现逻辑登录
            if (UserDao.userLogin(username,password)) {
                this.setVisible(false);  //关闭当前窗口
                //保存登录用户信息
                UserSaveTool.getCurrentLoginUsername(username);
                UserSaveTool.setCurrentLoginUserPass(password);
                new MainFrame();  // 跳转到主页
            }
        }
    }
}
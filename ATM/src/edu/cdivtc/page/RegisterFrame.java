package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.commons.LoginFrame;
import edu.cdivtc.dao.UserDao;
import edu.cdivtc.utils.ColorUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    //注册面板
    private JPanel registerPanel;
    //文字：大标题 姓名 身份证号码 性别 卡号 初始密码
    private JLabel titleLable, usernameLabel, uidLabel, genderLabel, cidLabel, passwordLabel;
    //输入框：姓名 身份证号码 性别 卡号 初始密码
    private JTextField usernameField, uidField, cidField, passwordField;
    //性别按钮组
    private ButtonGroup gender;
    //男女单选按钮
    private JRadioButton boy,girl;
    //按钮：登录 确认
    private JButton loginButton, confirmButton;
    //用户选择的性别
    private String selectedGender="";
    public RegisterFrame() {
        //1.开户窗体样式设置
        ComponentStyle.setFrame("ATM自动存取款系统",600,650,"src/images/login.png",this);
        //2.开卡面板样式设置
        registerPanel = new JPanel();
        registerPanel.setLayout(null);
        registerPanel.setBackground(ColorUtils.PANEL_BACKGROUND_COLOR);
        this.add(registerPanel);
        //3.大标题
        titleLable = new JLabel("开卡申请");
        ComponentStyle.setFormStyle(titleLable, 40,Color.black,210,30,200,50,registerPanel);
        //4.姓名表单
        usernameLabel = new JLabel("姓      名：",JLabel.CENTER);
        ComponentStyle.setFormStyle(usernameLabel, 20,Color.black,50,110,200,50,registerPanel);
        usernameField = new JTextField();
        ComponentStyle.setFormStyle(usernameField, 20,Color.black,250,110,280,50,registerPanel);
        //5.身份证号码表单
        uidLabel = new JLabel("身份证号码：",JLabel.CENTER);
        ComponentStyle.setFormStyle(uidLabel, 20,Color.black,50,190,200,50,registerPanel);
        uidField = new JTextField();
        ComponentStyle.setFormStyle(uidField, 20,Color.black,250,190,280,50,registerPanel);
        //6.性别表单
        genderLabel = new JLabel("性      别：",JLabel.CENTER);
        ComponentStyle.setFormStyle(genderLabel, 20,Color.black,50,280,200,50,registerPanel);
        //单选按钮-男
        boy = new JRadioButton("男");
        boy.setBounds(300,280,100,50);
        boy.setFont(new Font("楷体",Font.BOLD,25));
        boy.setOpaque(false);
        //单选按钮-女
        girl = new JRadioButton("女");
        girl.setBounds(400,280,100,50);
        girl.setFont(new Font("楷体",Font.BOLD,25));
        girl.setOpaque(false);
        //按钮组-只能选一个
        gender = new ButtonGroup();
        gender.add(boy);
        gender.add(girl);
        registerPanel.add(boy);
        registerPanel.add(girl);
        //获取用户选择的性别
        ActionListener sliceActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton aButton = (AbstractButton) e.getSource();
                selectedGender = aButton.getText();
                System.out.println(selectedGender);
            }
        };
        boy.addActionListener(sliceActionListener);
        girl.addActionListener(sliceActionListener);
        //7.卡号表单
        cidLabel = new JLabel("卡      号：",JLabel.CENTER);
        ComponentStyle.setFormStyle(cidLabel, 25,Color.black,50,360,200,50,registerPanel);
        cidField = new JTextField();
        ComponentStyle.setFormStyle(cidField, 25,Color.black,250,360,280,50,registerPanel);
        //8.初始密码表单
        passwordLabel = new JLabel("初始密码：",JLabel.CENTER);
        ComponentStyle.setFormStyle(passwordLabel, 25,Color.black,50,440,200,50,registerPanel);
        passwordField = new JTextField();
        ComponentStyle.setFormStyle(passwordField, 25,Color.black,250,440,280,50,registerPanel);
        //9.按钮
        loginButton = new JButton("登录");
        ComponentStyle.setButtonStyle(loginButton,Color.orange,60,520,220,50,registerPanel);
        confirmButton = new JButton("确认");
        ComponentStyle.setButtonStyle(confirmButton,Color.green,310,520,220,50,registerPanel);
        //为按钮添加事件监听
        loginButton.addActionListener(this);
        confirmButton.addActionListener(this);
        //设置可见
        this.setVisible(true);
    }
    //主方法实例化注册窗体
//        public static void main(String[] args) {
//            new RegisterFrame();
//        }
     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) { //登录按钮
            //切换到登录页面
            this.setVisible(false);
            new LoginFrame();
        }else if(e.getSource() == confirmButton) { //确认按钮
            //1.获取输入
            String username = usernameField.getText().trim(); //姓名
            String uid = uidField.getText().trim(); //身份证号码
            //2.开卡
            String accountNumber = UserDao.addUser(username, uid, selectedGender); //得到新的卡号
            if (!accountNumber.equals("") || accountNumber!= null) {
                //将卡号和密码显示在界面上
                cidField.setText(accountNumber);
                passwordField.setText("123456");
            }
        }
    }
}

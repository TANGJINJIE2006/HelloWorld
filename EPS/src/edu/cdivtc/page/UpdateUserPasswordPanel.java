package edu.cdivtc.page;
import edu.cdivtc.dao.UserDao;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserPasswordPanel extends JPanel implements ActionListener {
    //文字：大标题 原密码 新密码 确认密码
    private  JLabel tittleLabel,passwordLabel1,passwordLabel2,passwordLabel3;
    private JPasswordField password1,password2,password3;
    private JButton btn_clear;
    private JButton btn_save;
    //构造方法
    public UpdateUserPasswordPanel(){
        //面板宽高 背景颜色 绝对布局
        setSize(1160,850);
        setBackground(Color.white);
        setLayout(null);
    //1.大标题
    tittleLabel = new JLabel("修改个人密码",JLabel.CENTER);
    tittleLabel.setFont(new Font("楷体",Font.BOLD,40));
    tittleLabel.setBounds(250,120,550,50);
    this.add(tittleLabel);
    //2.原密码
    passwordLabel1 = new JLabel("原密码",JLabel.CENTER);
    passwordLabel1.setFont(new Font("楷体",Font.BOLD,25));
    passwordLabel1.setBounds(220,200,200,50);
    this.add(passwordLabel1);
    password1 = new JPasswordField();
    password1.setFont(new Font("楷体",Font.BOLD,25));
    password1.setBounds(400,200,280,50);
    password1.setEchoChar('*');
    this.add(password1);
    //3.新密码
    passwordLabel2 = new JLabel("新密码:",JLabel.CENTER);
    passwordLabel2.setFont(new Font("楷体",Font.BOLD,25));
    passwordLabel2.setBounds(220,280,200,50);
    this.add(passwordLabel2);
    password2 = new JPasswordField();
    password2.setFont(new Font("楷体",Font.BOLD,25));
    password2.setBounds(400,280,280,50);
    password2.setEchoChar('*');
    this.add(password2);
    //4.确认密码
    passwordLabel3 = new JLabel("确认密码:",JLabel.CENTER);
    passwordLabel3.setFont(new Font("楷体",Font.BOLD,25));
    passwordLabel3.setBounds(220,360,200,50);
    this.add(passwordLabel3);
    password3 = new JPasswordField();
    password3.setFont(new Font("楷体",Font.BOLD,25));
    password3.setBounds(400,360,280,50);
    password3.setEchoChar('*');
    this.add(password3);
    //5.按钮
    btn_clear = new JButton("清空");
    btn_clear.setBackground(Color.orange);//背景色
    btn_clear.setFont(new Font("楷体",Font.BOLD,25));
    btn_clear.setBounds(400,440,120,50);
    this.add(btn_clear);
    btn_save = new JButton("提交");
    btn_save.setBackground(Color.green);
    btn_save.setFont(new Font("楷体",Font.BOLD,25));
    btn_save.setBounds(560,440,120,50);
    this.add(btn_save);
    //  为清空按钮和提交按钮添加事件监听
        btn_clear.addActionListener(this);
        btn_save.addActionListener(this);
    }
    //重写抽象方法
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_clear) { //清空按钮
            clearUpdatePassInput(); //清空输入框
        } else if (e.getSource() == btn_save) { //按钮提交
            //1.获取用户输入
            String pass1 = new String(password1.getPassword()).trim();
            String pass2 = new String(password2.getPassword()).trim();
            String pass3 = new String(password3.getPassword()).trim();
            //2.验证用户输入
            if (pass1 == null || pass1.equals("")) {
                JOptionPane.showMessageDialog(null,"原密码不能为空");
                return;
            }else if (!pass1.equals(UserSaveTool.getCurrentLoginUserPass())) {
                JOptionPane.showMessageDialog(null,"原密码错误");
                return;
            }else if (pass2 == null || pass2.equals("")) {
                JOptionPane.showMessageDialog(null,"新密码不能为空");
                return;
            }else if (!pass2.equals(pass3)) {
                JOptionPane.showMessageDialog(null,"新密码与确认密码输入不一致");
                return;
            }
            //3.执行修改个人密码
            if (UserDao.upadteUserPass(pass2)) {
            //4.修改用户工具类中保存的已登录的用户密码信息
            UserSaveTool.setCurrentLoginUserPass(pass2);
            //5.清空输入框
            clearUpdatePassInput();
            }
        }
    }
    //清空输入框
    private void clearUpdatePassInput() {
        password1.setText("");
        password2.setText("");
        password3.setText("");
    }
}
package edu.cdivtc.page;
import javax.swing.*;
import java.awt.*;
public class AddUserPanel extends JPanel {
    private JLabel tittleLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel1;
    private JPasswordField password1;
    private JLabel passwordLabel2;
    private JPasswordField password2;
    private JButton btn_clear;
    private JButton btn_save;
//构造方法
    public AddUserPanel(){
        //面板宽高 背景颜色 绝对布局
        setSize(1160,850);
        setBackground(Color.white);
        setLayout(null);
//1.大标题
    tittleLabel = new JLabel("添加用户",JLabel.CENTER);
    tittleLabel.setFont(new Font("楷体",Font.BOLD,40));
    tittleLabel.setBounds(250,120,550,50);
    this.add(tittleLabel);
//2.用户名
      usernameLabel = new JLabel("用户名",JLabel.CENTER);
      usernameLabel.setFont(new Font("楷体",Font.BOLD,25));
      usernameLabel.setBounds(220,200,200,50);
      this.add(usernameLabel);
      usernameField = new JTextField();
      usernameField.setFont(new Font("楷体",Font.BOLD,25));
      usernameField.setBounds(400,200,280,50);
      this.add(usernameField);
//3.密码
    passwordLabel1 = new JLabel("密码",JLabel.CENTER);
    passwordLabel1.setFont(new Font("楷体",Font.BOLD,25));
    passwordLabel1.setBounds(220,280,200,50);
    this.add(passwordLabel1);
    password1 = new JPasswordField();
    password1.setFont(new Font("楷体",Font.BOLD,25));
    password1.setBounds(400,280,280,50);
    password1.setEchoChar('*');//密码显示字符
    this.add(password1);
//4.确认密码
    passwordLabel2 = new JLabel("确认密码:",JLabel.CENTER);
    passwordLabel2.setFont(new Font("楷体",Font.BOLD,25));
    passwordLabel2.setBounds(210,360,200,50);
    this.add(passwordLabel2);
    password2 = new JPasswordField();
    password2.setFont(new Font("楷体",Font.BOLD,25));
    password2.setBounds(400,360,280,50);
    password2.setEchoChar('*');
    this.add(password2);
//5.按钮
    btn_clear = new JButton("清空");
    btn_clear.setBackground(Color.orange);
    btn_clear.setFont(new Font("楷体",Font.BOLD,25));
    btn_clear.setBounds(400,440,120,50);
    this.add(btn_clear);
    btn_save = new JButton("提交");
    btn_save.setBackground(Color.green);
    btn_save.setFont(new Font("楷体",Font.BOLD,25));
    btn_save.setBounds(560,440,120,50);
    this.add(btn_save);
    }

}

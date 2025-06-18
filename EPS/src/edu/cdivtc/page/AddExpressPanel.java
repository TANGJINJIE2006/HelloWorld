package edu.cdivtc.page;

import edu.cdivtc.dao.ExpressDao;
import edu.cdivtc.entity.Express;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpressPanel extends JPanel implements ActionListener {
    // 快递背景图片
    private Image expressImg;
    // 按钮：重置+保存
    private JButton btn_clear;
    private JButton btn_save;
    //输入框--寄件人信息：寄件人姓名，手机号码，单位，地址，邮编
    private JTextField sendName;
    private JTextField sendPhone;
    private JTextField sendCompany;
    private JTextArea sendAdress;
    private JTextField sendPostcode;
    //输入框--收件人信息：收件人姓名，手机号码，单位，地址，邮编
    private JTextField receiveName;
    private JTextField receivePhone;
    private JTextField receiveCompany;
    private JTextArea receiveAdress;
    private JTextField receivePostcode;

    // 无参构造
    public AddExpressPanel() {
        setSize(1100, 800);
        setLayout(null);//绝对定位
        //1.快递单背景
        expressImg = new ImageIcon(getClass().getResource("/images/express.jpg")).getImage();
        //2.按钮
        btn_clear = new JButton("清空");
        btn_clear.setBackground(Color.orange);
        btn_clear.setBounds(720, 70, 100, 50);
        btn_clear.setFont(new Font("楷体", Font.BOLD, 25));
        this.add(btn_clear);
        btn_save = new JButton("保存");
        btn_save.setBackground(Color.green);
        btn_save.setBounds(850, 70, 100, 50);
        btn_save.setFont(new Font("楷体", Font.BOLD, 25));
        this.add(btn_save);
        //3.寄件人信息输入框
        sendName = new JTextField();
        sendName.setBounds(165, 178, 140, 38);
        sendName.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendName);
        sendPhone = new JTextField();
        sendPhone.setBounds(380, 178, 140, 38);
        sendPhone.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendPhone);
        sendCompany = new JTextField();
        sendCompany.setBounds(165, 220, 355, 38);
        sendCompany.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendCompany);
        sendAdress = new JTextArea(3, 1);
        sendAdress.setBorder(BorderFactory.createEtchedBorder());
        sendAdress.setLineWrap(true);
        sendAdress.setBounds(140, 300, 355, 100);
        sendAdress.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendAdress);
        sendPostcode = new JTextField();
        sendPostcode.setBounds(410, 405, 110, 38);
        sendPostcode.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(sendPostcode);
        //4.收件人信息输入框
        receiveName = new JTextField();
        receiveName.setBounds(165, 490, 140, 38);
        receiveName.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receiveName);
        receivePhone = new JTextField();
        receivePhone.setBounds(380, 490, 140, 38);
        receivePhone.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receivePhone);
        receiveCompany = new JTextField();
        receiveCompany.setBounds(165, 532, 355, 38);
        receiveCompany.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receiveCompany);
        receiveAdress = new JTextArea(3, 1);
        receiveAdress.setBorder(BorderFactory.createEtchedBorder());
        receiveAdress.setLineWrap(true);
        receiveAdress.setBounds(140, 612, 355, 100);
        receiveAdress.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receiveAdress);
        receivePostcode = new JTextField();
        receivePostcode.setBounds(410, 715, 110, 38);
        receivePostcode.setFont(new Font("楷体", Font.BOLD, 18));
        this.add(receivePostcode);
        // 为清空按钮和保存按钮添加事件监听
        btn_clear.addActionListener(this);
        btn_save.addActionListener(this);
    }

    //根据图片和面板的尺寸自动绘制背景图片
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制背景图     参数：图片，绘制位置x，y，尺寸宽高，在哪个组件（添加快递面板）绘制
        g.drawImage(expressImg, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_clear) { //清空按钮
            //清空输入框内容
            clearExpressAddInput();
        } else if (e.getSource() == btn_save) { //保存按钮
            //1.获取输入信息
            String send_name = sendName.getText().trim();
            String send_phone = sendPhone.getText().trim();
            String send_company = sendCompany.getText().trim();
            String send_adress = sendAdress.getText().trim();
            String send_postcode = sendPostcode.getText().trim();
            String receive_name = receiveName.getText().trim();
            String receive_phone = receivePhone.getText().trim();
            String receive_company = receiveCompany.getText().trim();
            String receive_adress = receiveAdress.getText().trim();
            String receive_postcode = receivePostcode.getText().trim();
            //2.验证输入信息
            if (send_name == null || send_name.equals("")) {
                JOptionPane.showMessageDialog(null, "寄件人名称必须填写");
                return;
            } else if (send_phone == null || send_phone.equals("")) {
                JOptionPane.showMessageDialog(null, "寄件人手机号码必须填写");
                return;
            } else if (!send_phone.matches("^1[3-9]\\d{9}$")) {
                JOptionPane.showMessageDialog(null, "寄件人手机号码必须以1[3-9]开头的11位数字");
                return;
            } else if (send_company == null || send_company.equals("")) {
                JOptionPane.showMessageDialog(null, "寄件人单位必须填写");
                return;
            } else if (send_adress == null || send_adress.equals("")) {
                JOptionPane.showMessageDialog(null, "寄件人地址必须填写");
                return;
            } else if (send_postcode == null || send_postcode.equals("")) {
                JOptionPane.showMessageDialog(null, "寄件人邮编必须填写");
                return;
            } else if (!send_postcode.matches("^\\d{6}$")) {
                JOptionPane.showMessageDialog(null, "寄件人邮编必须是6位数字");
                return;
            } else if (receive_name == null || receive_name.equals("")) {
                JOptionPane.showMessageDialog(null, "收件人名称必须填写");
                return;
            } else if (receive_phone == null || receive_phone.equals("")) {
                JOptionPane.showMessageDialog(null, "收件人手机号码必须填写");
                return;
            } else if (!receive_phone.matches("^1[3-9]\\d{9}$")) {
                JOptionPane.showMessageDialog(null, "收件人手机号码必须以1[3-9]开头的11位数字");
                return;
            } else if (receive_company == null || receive_company.equals("")) {
                JOptionPane.showMessageDialog(null, "收件人单位必须填写");
                return;
            } else if (receive_adress == null || receive_adress.equals("")) {
                JOptionPane.showMessageDialog(null, "收件人地址必须填写");
                return;
            } else if (receive_postcode == null || receive_postcode.equals("")) {
                JOptionPane.showMessageDialog(null, "收件人邮编必须填写");
                return;
            } else if (!receive_postcode.matches("^\\d{6}$")) {
                JOptionPane.showMessageDialog(null, "收件人邮编必须是6位数字");
                return;
            }
            //3.实现添加快递逻辑
            //3.1用户输入的快递信息，-1代表使用数据库自动递增id，不动手动输入的id，-1只是占位符
            Express express = new Express(-1, send_name, send_phone, send_company, send_adress, send_postcode,
                    receive_name, receive_phone, receive_company, receive_adress, receive_postcode);
            //3.2添加快递
            if (ExpressDao.addExpress(express)) {
                //4.清空输入框
                clearExpressAddInput();
            }
        }
    }
    //清空输入框
    private void clearExpressAddInput() {
        sendName.setText("");
        sendPhone.setText("");
        sendCompany.setText("");
        sendAdress.setText("");
        sendPostcode.setText("");
        receiveName.setText("");
        receivePhone.setText("");
        receiveCompany.setText("");
        receiveAdress.setText("");
        receivePostcode.setText("");
    }
}


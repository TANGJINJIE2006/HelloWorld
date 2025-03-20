package edu.cdivtc.page;
import javax.swing.*;
import java.awt.*;
    public class AddExpressPanel extends JPanel {
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
        setSize(1100,800);
        setLayout(null);//绝对定位
        //1.快递单背景
        expressImg = new ImageIcon(getClass().getResource("/images/express.jpg")).getImage();
        //2.按钮
        btn_clear = new JButton("清空");
        btn_clear.setBackground(Color.orange);
        btn_clear.setBounds(720,70,100,50);
        btn_clear.setFont(new Font("楷体",Font.BOLD,25));
        this.add(btn_clear);
        btn_save = new JButton("保存");
        btn_save.setBackground(Color.green);
        btn_save.setBounds(850,70,100,50);
        btn_save.setFont(new Font("楷体",Font.BOLD,25));
        this.add(btn_save);
        //3.寄件人信息输入框
        sendName = new JTextField();
        sendName.setBounds(165,178,140,38);
        sendName.setFont(new Font("楷体",Font.BOLD,18));
        this.add(sendName);
        sendPhone = new JTextField();
        sendPhone.setBounds(380,178,140,38);
        sendPhone.setFont(new Font("楷体",Font.BOLD,18));
        this.add(sendPhone);
        sendCompany = new JTextField();
        sendCompany.setBounds(165,220,355,38);
        sendCompany.setFont(new Font("楷体",Font.BOLD,18));
        this.add(sendCompany);
        sendAdress = new JTextArea(3,1);
        sendAdress.setBorder(BorderFactory.createEtchedBorder());
        sendAdress.setLineWrap(true);
        sendAdress.setBounds(140,300,355,100);
        sendAdress.setFont(new Font("楷体",Font.BOLD,18));
        this.add(sendAdress);
        sendPostcode = new JTextField();
        sendPostcode.setBounds(410,405,110,38);
        sendPostcode.setFont(new Font("楷体",Font.BOLD,18));
        this.add(sendPostcode);
        //4.收件人信息输入框
        receiveName = new JTextField();
        receiveName.setBounds(165,490,140,38);
        receiveName.setFont(new Font("楷体",Font.BOLD,18));
        this.add(receiveName);
        receivePhone = new JTextField();
        receivePhone.setBounds(380,490,140,38);
        receivePhone.setFont(new Font("楷体",Font.BOLD,18));
        this.add(receivePhone);
        receiveCompany = new JTextField();
        receiveCompany.setBounds(165,532,355,38);
        receiveCompany.setFont(new Font("楷体",Font.BOLD,18));
        this.add(receiveCompany);
        receiveAdress = new JTextArea(3,1);
        receiveAdress.setBorder(BorderFactory.createEtchedBorder());
        receiveAdress.setLineWrap(true);
        receiveAdress.setBounds(140,612,355,100);
        receiveAdress.setFont(new Font("楷体",Font.BOLD,18));
        this.add(receiveAdress);
        receivePostcode = new JTextField();
        receivePostcode.setBounds(410,715,110,38);
        receivePostcode.setFont(new Font("楷体",Font.BOLD,18));
        this.add(receivePostcode);
    }
    //根据图片和面板的尺寸自动绘制背景图片
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    //绘制背景图     参数：图片，绘制位置x，y，尺寸宽高，在哪个组件（添加快递面板）绘制
        g.drawImage(expressImg,0,0,this.getWidth(),this.getHeight(),this);
    }
}

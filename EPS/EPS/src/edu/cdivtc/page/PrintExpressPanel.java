package edu.cdivtc.page;

import edu.cdivtc.dao.ExpressDao;
import edu.cdivtc.entity.Express;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

// 打印快递单的面板类，继承自JPanel并实现ActionListener接口
public class PrintExpressPanel extends JPanel implements ActionListener {
    //当前显示的快递单ID
    private Integer currentId = 0;
    //快递单总数
    private Integer expressCount = 0;
    //快递背景图片
    private Image expressImg;
    //private JButton btn_print;
    private JButton btn_print;
    private JButton btn_last;
    private JButton btn_next;
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

    // 无参构造函数，初始化面板内容和事件监听器
    public PrintExpressPanel() {
        setSize(1100, 800);
        setLayout(null);//绝对定位
        //1.快递单背景
        expressImg = new ImageIcon(getClass().getResource("/images/express.jpg")).getImage();
        //2.按钮
//        btn_clear = new JButton("清空");
//        btn_clear.setBackground(Color.orange);
//        btn_clear.setBounds(720,70,100,50);
//        btn_clear.setFont(new Font("楷体",Font.BOLD,25));
//        this.add(btn_clear);
//        btn_save = new JButton("保存");
//        btn_save.setBackground(Color.green);
//        btn_save.setBounds(850,70,100,50);
//        btn_save.setFont(new Font("楷体",Font.BOLD,25));
//        this.add(btn_save);
        btn_print = new JButton("打印");
        btn_print.setBackground(Color.green);
        btn_print.setBounds(850, 70, 100, 50);
        btn_print.setFont(new Font("楷体", Font.BOLD, 25));
        this.add(btn_print);
        btn_last = new JButton("上一条");
        btn_last.setBackground(new Color(221, 233, 255));
        btn_last.setBounds(380, 70, 150, 50);
        btn_last.setFont(new Font("楷体", Font.BOLD, 25));
        this.add(btn_last);
        btn_next = new JButton("下一条");
        btn_next.setBackground(new Color(221, 233, 255));
        btn_next.setBounds(545, 70, 150, 50);
        btn_next.setFont(new Font("楷体", Font.BOLD, 25));
        this.add(btn_next);
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
        //5.将所有输入框设置为不可编辑状态
        sendName.setEditable(false);
        sendPhone.setEditable(false);
        sendCompany.setEditable(false);
        sendAdress.setEditable(false);
        sendPostcode.setEditable(false);
        receiveName.setEditable(false);
        receivePhone.setEditable(false);
        receiveCompany.setEditable(false);
        receiveAdress.setEditable(false);
        receivePostcode.setEditable(false);
        // 默认显示第一条快递单信息
        // 默认进入打印快递单界面时，显示ID为1的快递单信息
        Express defaultExpress = ExpressDao.selectExpressById("1");
        if (defaultExpress != null) {
            showCurrentExpress(defaultExpress);//将查询结果显示在界面上
            currentId = 1; //当前显示的快递单ID为1
        }
        //为按钮添加监听
        btn_last.addActionListener(this);
        btn_next.addActionListener(this);
        btn_print.addActionListener(this);
        //由于没有删除快递单的功能，且添加快递单非本界面，每次显示本界面都会调用构造方法，因此将查询快递单总数的功能写在构造方法内
        expressCount = ExpressDao.selectExpressCount();
    }

    //根据图片和面板的尺寸自动绘制背景图片
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制背景图     参数：图片，绘制位置x，y，尺寸宽高，在哪个组件（添加快递面板）绘制
        g.drawImage(expressImg, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    //显示当前界面的快递信息
    public void showCurrentExpress(Express curentExpress) {
        sendName.setText(curentExpress.getSendName());
        sendPhone.setText(curentExpress.getSendPhone());
        sendCompany.setText(curentExpress.getSendCompany());
        sendAdress.setText(curentExpress.getSendAddress());
        sendPostcode.setText(curentExpress.getSendPostcode());
        receiveName.setText(curentExpress.getReceiveName());
        receivePhone.setText(curentExpress.getReceivePhone());
        receiveCompany.setText(curentExpress.getReceiveCompany());
        receiveAdress.setText(curentExpress.getReceiveAddress());
        receivePostcode.setText(curentExpress.getReceivePostcode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_last) { //上一条按钮
            //若当前快递单已经是第一条，则提示“已经是第一个快递单了”
            if (currentId == 1) {
                JOptionPane.showMessageDialog(null, "已经是第一个快递单了");
            } else {
                //若不是第一条，则查询ID-1的快递单信息，即显示上一条快递单信息
                currentId = currentId - 1;
                Express express = ExpressDao.selectExpressById(currentId.toString());
                //若查询出上一条快递单信息，则显示在界面上
                if (express != null) {
                    showCurrentExpress(express);
                }
            }
        } else if (e.getSource() == btn_next) { //下一条按钮
            //若 当前快递单已经是最后一条，则提示“已经是最后一个快递单了”
            if (currentId == expressCount) {
                JOptionPane.showMessageDialog(null, "已经是最后一个快递单了");
            } else { //若不是最后一条，则查询ID+1的快递单信息，即显示下一条快递单信息
                currentId = currentId + 1;
                Express express = ExpressDao.selectExpressById(currentId.toString());
                //若查询出下一条快递单信息，则显示在界面上
                if (express != null) {
                    showCurrentExpress(express);
                }
            }
        } else if (e.getSource() == btn_print) { //打印按钮
            //打印
            printExpress(this);

        }
    }

    //打印功能
    //@param  component 要打印的组件--打印快递单，则传递打印快递单面板
    private void printExpress(Component component) {
        //新建打印任务
        PrinterJob Pj = PrinterJob.getPrinterJob();
        Pj.setJobName("快递单打印");
        //设置打印内容
        Pj.setPrintable(new Printable() {
                            //Graphics pg:绘制打印页面的图形
                            //PageFormat pf:绘制打印页面的格式属性，如页面大小、边距、方向等
                            //int pageNum:打印的页码的索引
                            public int print(Graphics pg, PageFormat pf, int pageNum) {
                                if (pageNum > 0) {//打印多页时，如果页码大于0，则不打印
                                    return Printable.NO_SUCH_PAGE;
                                }
                                Graphics2D g2 = (Graphics2D) pg;
                                //经过测试 -55和-140的偏移刚好去除按钮，只打印快递单内容
                                g2.translate(pf.getImageableX() - 55, pf.getImageableY() - 140);
                                component.print(g2);//打印快递单
                                return Printable.PAGE_EXISTS;
                            }
                        }
        );
        //弹出打印对话框
        if (Pj.printDialog() == false) return;
        try {
            //开始打印
            Pj.print();
        } catch (PrinterException ex) {
        }
    }
}

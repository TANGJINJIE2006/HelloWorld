package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.commons.LoginFrame;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class MainFrame extends JFrame implements ActionListener {
    //主面板
    private JPanel mainPanel;
    //大标题
    private JLabel tittleLabel;
    //用户名
    private JButton userButton;
    //菜单
    private JButton[] btn;
    //底部欢迎标语
    private JLabel welcomeLabel;
    //存取款窗口
    private DepositWithdrawFrame depositWithdrawFrame;
    //定存咨询窗口
    private FixedDepositFrame fixedDepositFrame;
    //贷款咨询窗口
    private LoanFrame loanFrame;
    //未来资产窗口
    private FutureAssetsFrame futureAssetFrame;

    public MainFrame() {
        //1.设置窗体样式
        ComponentStyle.setFrame("ATM自动存取款系统", 1200, 800,
                "src/images/logo.png", this);
        //2.面板
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel);

        //4.银行logo
        ComponentStyle.setPanelBackgroundImg("src/images/logo.png", 0,0,150,120,mainPanel);
        //5.大标题
        tittleLabel = new JLabel("ATM自动存取款系统", JLabel.LEFT);
        ComponentStyle.setFormStyle(tittleLabel, 40, Color.white, 130, 0, 550, 120, mainPanel);
        //6.用户名
        userButton = new JButton(UserSaveTool.getCurrentLoginUsername(), new ImageIcon("src/images/user.png"));
        userButton.setBorderPainted(false);//去除边框
        ComponentStyle.setButtonStyle(userButton,new Color(96,152,243),950,50,200,50,mainPanel);
        //7.菜单按钮--共7个
        btn = new JButton[7];
        btn[0] = new JButton("存取款",new ImageIcon("src/images/deposit.png"));
        ComponentStyle.setMenuButtonStyle(btn[0],new Color(58,199,195),310,250,180,130,mainPanel);
        btn[1] = new JButton("定存咨询",new ImageIcon("src/images/invest.png"));
        ComponentStyle.setMenuButtonStyle(btn[1], new Color(199,27,12), 510, 250, 180, 130, mainPanel);
        btn[2] = new JButton("贷款咨询",new ImageIcon("src/images/loan.png"));
        ComponentStyle.setMenuButtonStyle(btn[2], new Color(81,199,57), 710, 250, 180, 130, mainPanel);
        btn[3] = new JButton("未来资产",new ImageIcon("src/images/info.png"));
        ComponentStyle.setMenuButtonStyle(btn[3], new Color(233,164,52), 310, 400, 180, 130, mainPanel);
        btn[4] = new JButton("账号管理",new ImageIcon("src/images/sphere.png"));
        ComponentStyle.setMenuButtonStyle(btn[4], new Color(36,134,31), 510, 400, 180, 130, mainPanel);
        btn[5] = new JButton("晴",new ImageIcon("src/images/weather.png"));
        ComponentStyle.setMenuButtonStyle(btn[5], new Color(196,105,226), 710, 400, 85, 130, mainPanel);
        btn[6] = new JButton(new ImageIcon("src/images/setting.png"));
        ComponentStyle.setMenuButtonStyle(btn[6], new Color(76,114,226), 805, 400, 85, 130, mainPanel);
        //8.底部欢迎标语
        welcomeLabel = new JLabel("欢迎使用ATM自动存取款系统", JLabel.LEFT);
        welcomeLabel.setFont(new Font("楷体", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.gray);
        welcomeLabel.setBounds(500, 660, 550, 120);
        mainPanel.add(welcomeLabel);
        //9.事件监听
        userButton.addActionListener(this);
        for(int i = 0; i < btn.length; i++) {
            btn[i].addActionListener(this);
        }
        //3.背景图片
        ComponentStyle.setPanelBackgroundImg("src/images/mainbg.jpg", 0,0,1200,800,mainPanel);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == userButton) {//用户名
            //退出登录
            int i = JOptionPane.showConfirmDialog(null, "确定退出登录？","退出系统", JOptionPane.YES_NO_OPTION);
            if(i == 0) {//点击了是（是为0，否为1）
                //隐藏主界面
                this.setVisible(false);
                //清空用户的缓存信息
                UserSaveTool.setCurrentLoginMoney(null);
                UserSaveTool.setCurrentLoginUsername(null);
                UserSaveTool.setCurrentLoginAccountNumber(null);
                UserSaveTool.setCurrentLoginAccountPassword(null);
                //创建登录窗口
                new LoginFrame();
            }
        }else if(e.getSource() == btn[0]) {//存取款
            //第一次点击窗口时，创建窗口
            if(depositWithdrawFrame == null) {
                depositWithdrawFrame = new DepositWithdrawFrame();
            //往后点击窗口显示窗口
            }else if(depositWithdrawFrame != null && !depositWithdrawFrame.isVisible()) {
                depositWithdrawFrame.setVisible(true);
            }
        }else if(e.getSource() == btn[1]) {//定存咨询
            //第一次点击窗口时，创建窗口
            if(fixedDepositFrame == null) {
                fixedDepositFrame = new FixedDepositFrame();
            //往后点击窗口显示窗口
            }else if(fixedDepositFrame != null && !fixedDepositFrame.isVisible()) {
                fixedDepositFrame.setVisible(true);
            }
        }else if(e.getSource() == btn[2]) {//贷款咨询
            //第一次点击窗口时，创建窗口
            if( loanFrame == null) {
                loanFrame = new LoanFrame();
            //往后点击窗口显示窗口
            }else if(loanFrame != null && !loanFrame.isVisible()) {
                loanFrame.setVisible(true);
            }
        }else if(e.getSource() == btn[3]) {//未来资产
            //第一次点击窗口时，创建窗口
            if(futureAssetFrame == null) {
                futureAssetFrame = new FutureAssetsFrame();
            //往后点击窗口显示窗口
            }else if(futureAssetFrame != null && !futureAssetFrame.isVisible()) {
                futureAssetFrame.setVisible(true);
            }
        }else if(e.getSource() == btn[4]) {
            JOptionPane.showMessageDialog(null, "暂未开通");
        }else if(e.getSource() == btn[5]) {
            JOptionPane.showMessageDialog(null, "暂未开通");
        }else if(e.getSource() == btn[6]) {
            JOptionPane.showMessageDialog(null, "暂未开通");
        }
    }
}

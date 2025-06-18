package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LoanFrame extends JFrame implements ActionListener {


    //贷款面板
    private JPanel panel;
    //文字：大标题 用户名 卡号 卡内余额 预计贷款 年利率 每年还款 还清年限 贷款年限
    private JLabel tittle,currentUser,currentCid,loan,annualRate,repayment,clearTime,loanYears;
    //输入框：用户名 卡号 卡内余额 预计贷款 年利率 每年还款 还清年限 贷款年限
    private JTextField currentUserField,currentCidField,loanField,annualRateField,repaymentField,clearTimeField;
    //贷款年限下拉框
    private JComboBox yearComboBox;
    //按钮：清空 确认
    private JButton resetButton,confirmButton;
    //按钮：确认 取消

    public LoanFrame() {
        //1.窗体设置
        ComponentStyle.setFrame("ATM自动存取款系统", 600, 800, "src/images/logo.png", this);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//点击关闭时隐藏窗口，不退出程序
        this.setLayout(null);//更改布局方式
        //2.面板设置
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(DepositWithdrawFrame.ColorUtils.PANEL_BACKGROUND_COLOR);
        panel.setBounds(0, 0, 600, 800);
        this.add(panel);
        //3.大标题
        tittle = new JLabel("贷款咨询");
        ComponentStyle.setFormStyle(tittle, 40, Color.BLACK, 210, 30, 200, 50, panel);
        //4.用户名
        currentUser = new JLabel("用 户 名:", JLabel.CENTER);
        ComponentStyle.setFormStyle(currentUser, 25, Color.BLACK, 50, 110, 200, 50, panel);
        currentUserField = new JTextField(UserSaveTool.getCurrentLoginUsername());
        currentUserField.setEditable(false);//设置面板不可编辑
        ComponentStyle.setFormStyle(currentUserField, 25, Color.BLACK, 250, 110, 280, 50, panel);
        //5.卡号
        currentCid = new JLabel("卡    号:", JLabel.CENTER);
        ComponentStyle.setFormStyle(currentCid, 25, Color.BLACK, 50, 190, 200, 50, panel);
        currentCidField = new JTextField(UserSaveTool.getCurrentLoginAccountNumber());
        currentCidField.setEditable(false);//设置面板不可编辑
        ComponentStyle.setFormStyle(currentCidField, 25, Color.BLACK, 250, 190, 280, 50, panel);
        //6.预计贷款
        loan = new JLabel("预计贷款￥:", JLabel.CENTER);
        ComponentStyle.setFormStyle(loan, 25, Color.BLACK, 50, 270, 200, 50, panel);
        loanField = new JTextField();
        ComponentStyle.setFormStyle(loanField, 25, Color.BLACK, 250, 270, 280, 50, panel);
        //7.贷款年限--贷款类型
        loanYears = new JLabel("贷款年限:", JLabel.CENTER);
        ComponentStyle.setFormStyle(loanYears, 25, Color.BLACK, 50, 350, 200, 50, panel);
        yearComboBox = new JComboBox();
        String[] strArray = {"请选择贷款年限", "1-5年（含）", "3-5年（含）", "5年以上"};
        for (String item : strArray) {
            yearComboBox.addItem(item);
        }
        ComponentStyle.setFormStyle(yearComboBox, 25, Color.BLACK, 250, 350, 280, 50, panel);
        //8.年利率
        annualRate = new JLabel("年利率%:", JLabel.CENTER);
        ComponentStyle.setFormStyle(annualRate, 25, Color.BLACK, 50, 430, 200, 50, panel);
        annualRateField = new JTextField();
        ComponentStyle.setFormStyle(annualRateField, 25, Color.BLACK, 250, 430, 280, 50, panel);
        //9.每年还款
        repayment = new JLabel("每年还款￥:", JLabel.CENTER);
        ComponentStyle.setFormStyle(repayment, 25, Color.BLACK, 50, 510, 200, 50, panel);
        repaymentField = new JTextField();
        ComponentStyle.setFormStyle(repaymentField, 25, Color.BLACK, 250, 510, 280, 50, panel);
        //10.还清年限
        clearTime = new JLabel("还清年限:", JLabel.CENTER);
        ComponentStyle.setFormStyle(clearTime, 25, Color.red, 50, 590, 200, 50, panel);
        clearTimeField = new JTextField();
        clearTimeField.setEditable(false);//设置面板不可编辑
        ComponentStyle.setFormStyle(clearTimeField, 25, Color.red, 250, 590, 280, 50, panel);
        //获取用户的定投年限，并设置年收益率--必须在年收益率表单后写该部分代码
        yearComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int selectedIndex = yearComboBox.getSelectedIndex(); //获取下拉索引框
                    switch (selectedIndex) {
                        case 1:
                            annualRateField.setText("4.75%");
                            break;
                        case 2:
                            annualRateField.setText("4.9%");
                            break;
                        case 3:
                            annualRateField.setText("5.1%");
                            break;
                     }
                    }
                }
        });
        //11.按钮：清空+确认
        resetButton = new JButton("清空");
        ComponentStyle.setButtonStyle(resetButton,Color.orange, 50, 670, 220, 50, panel);
        confirmButton = new JButton("确认");
        ComponentStyle.setButtonStyle(confirmButton,Color.green, 310, 670, 220, 50, panel);
        //为按钮添加事件监听
        resetButton.addActionListener(this);
        confirmButton.addActionListener(this);
        this.setVisible(true);//显示窗体
    }
     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {//清空按钮
            loanField.setText("");
            yearComboBox.setSelectedIndex(0);
            annualRateField.setText("");
            repaymentField.setText("");
            clearTimeField.setText("");
        }else if (e.getSource() == confirmButton) {//确认按钮
            //1.获取输入框的值
            String loan = loanField.getText().trim();
            String annualRate = annualRateField.getText().trim();
            String repay = repaymentField.getText().trim();
            //2.验证输入
            if (validateLoanInput(loan, annualRate, repay)) {
                //3.数据类型转换
                double loanMoney = Double.parseDouble(loan);
                double loanAnnualRate = Double.parseDouble(annualRate.substring(0,annualRate.lastIndexOf("%")))/100;
                double repayMoney = Double.parseDouble(repay);
                //4.计算贷款
                loans(loanMoney, loanAnnualRate, repayMoney);
            }
        }
            }
            /**
             * 验证输入
             * @param loan 预计贷款金额
             * @param annualRate 贷款年限
             * @param repay 每年还款
             * @return
             */
            private boolean validateLoanInput (String loan, String annualRate, String repay){
                if (!loan.matches("^[1-9]\\d{4,}$")) {
                    JOptionPane.showMessageDialog(null, "贷款金额至少是10000元，且不能包含小数！");
                    return false;
                }
                if (annualRate.equals("")) {
                    JOptionPane.showMessageDialog(null, "请选择贷款年限！");
                    return false;
                }
                if (!repay.matches("^[1-9]\\d{2,}$")) {
                    JOptionPane.showMessageDialog(null, "每月还款金额至少是100元，且不能包含小数！");
                    return false;
                }
                if (Double.parseDouble(loan) < Double.parseDouble(repay)) {
                    JOptionPane.showMessageDialog(null, "每月还款金额不能大于贷款金额！");
                    return false;
                }
                return true;
            }
    /**
     * 计算贷款
     * @param loan 贷款金额
     * @param rate 年利率
     * @param pay 每年还款
     */
    private void loans(Double loan, Double rate, Double pay) {
        //如果归还的金额少于利息，则认为还不完
        if (pay < (loan - pay) * rate ) {
            clearTimeField.setText("你是还不完的！");
        } else {
            int count = 0; //还清需要多少年
            while (loan > 0) {
                loan -= pay;//还款后的贷款总金额
                loan *=(1+rate);//剩余贷款 = 剩余贷款产生的利息+剩余贷款
                count += 1;//月数/期数
                clearTimeField.setText(String.valueOf(count) + "年后");
            }
        }
    }
}



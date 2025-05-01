package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.entity.User;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FutureAssetsFrame extends JFrame implements ActionListener {
    //面板
    private JPanel panel;
    //文字：大标题 未来年份 每月定投 定投年收益率 贷款金额 贷款年利率 每年还款 卡内余额 未来资产
    private JLabel tittle, years1, years2, investMoney, investRate, loan, loanRate, repayment, currentMoney, futureMoney;
    //输入框：未来年份 每月定投 贷款金额 贷款年利率 每年还款 卡内余额 未来资产
    private JTextField futureYearsField, investMoneyField, loanField, repaymentField, currentMoneyField, futureMoneyField;
    //定投收益率下拉框 贷款年利率下拉框
    private JComboBox investRateComboBox, loanRateComboBox;
    //按钮：清空+确认
    private JButton clearButton, confirmButton;

    public FutureAssetsFrame() {
        //1.窗体设置
        ComponentStyle.setFrame("ATM自动存取款系统", 600, 900, "src/images/logo.png", this);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//点击关闭时隐藏窗口，不退出程序
        setVisible(true);
        //2.面板设置
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(221, 233, 255));
        this.add(panel);
        //3.大标题
        tittle = new JLabel("未来资产");
        ComponentStyle.setFormStyle(tittle, 40, Color.BLACK, 210, 30, 200, 50, panel);
        setVisible(true);
        //4.未来年份
        years1 = new JLabel("查询第：", JLabel.CENTER);
        ComponentStyle.setFormStyle(years1, 25, Color.BLACK, 50, 110, 200, 50, panel);
        futureYearsField = new JTextField();
        ComponentStyle.setFormStyle(futureYearsField, 25, Color.BLACK, 250, 110, 100, 50, panel);
        years2 = new JLabel("年财务情况", JLabel.CENTER);
        ComponentStyle.setFormStyle(years2, 25, Color.BLACK, 350, 110, 200, 50, panel);
        //5.每月定投
        investMoney = new JLabel("每月定投￥：", JLabel.CENTER);
        ComponentStyle.setFormStyle(investMoney, 25, Color.BLACK, 50, 190, 200, 50, panel);
        investMoneyField = new JTextField();
        ComponentStyle.setFormStyle(investMoneyField, 25, Color.BLACK, 250, 190, 280, 50, panel);
        //6.定投年收益率
        investRate = new JLabel("定投年收益率%：", JLabel.CENTER);
        ComponentStyle.setFormStyle(investRate, 25, Color.BLACK, 50, 270, 200, 50, panel);
        investRateComboBox = new JComboBox();
        String[] strArray = {"请选择定投年收益率", "2%", "2.5%", "3%", "3.05%"};
        for (String item : strArray) {
            investRateComboBox.addItem(item);
        }
        ComponentStyle.setFormStyle(investRateComboBox, 25, Color.BLACK, 250, 270, 280, 50, panel);
        //7.贷款金额
        loan = new JLabel("贷款金额￥：", JLabel.CENTER);
        ComponentStyle.setFormStyle(loan, 25, Color.BLACK, 50, 350, 200, 50, panel);
        loanField = new JTextField();
        ComponentStyle.setFormStyle(loanField, 25, Color.BLACK, 250, 350, 280, 50, panel);
        //8.贷款年利率
        loanRate = new JLabel("贷款年利率%：", JLabel.CENTER);
        ComponentStyle.setFormStyle(loanRate, 25, Color.BLACK, 50, 430, 200, 50, panel);
        loanRateComboBox = new JComboBox();
        String[] strArray2 = {"请选择贷款年利率", "10%", "12%", "15%", "20%"};
        for (String item : strArray2) {
            loanRateComboBox.addItem(item);
        }
        ComponentStyle.setFormStyle(loanRateComboBox, 25, Color.BLACK, 250, 430, 280, 50, panel);
        //9.每年还款
        repayment = new JLabel("每年还款￥：", JLabel.CENTER);
        ComponentStyle.setFormStyle(repayment, 25, Color.BLACK, 50, 510, 200, 50, panel);
        repaymentField = new JTextField();
        ComponentStyle.setFormStyle(repaymentField, 25, Color.BLACK, 250, 510, 280, 50, panel);
        //10.卡内余额
        currentMoney = new JLabel("卡内余额￥：", JLabel.CENTER);
        ComponentStyle.setFormStyle(currentMoney, 25, Color.BLACK, 50, 590, 200, 50, panel);
        currentMoneyField = new JTextField(UserSaveTool.getCurrentLoginMoney());
        currentMoneyField.setEditable(false);//不可编辑
        ComponentStyle.setFormStyle(currentMoneyField, 25, Color.BLACK, 250, 590, 280, 50, panel);
        //11.未来资产
        futureMoney = new JLabel("未来资产￥：", JLabel.CENTER);
        ComponentStyle.setFormStyle(futureMoney, 25, Color.red, 50, 670, 200, 50, panel);
        futureMoneyField = new JTextField();
        futureMoneyField.setEditable(false);//不可编辑
        ComponentStyle.setFormStyle(futureMoneyField, 25, Color.red, 250, 670, 280, 50, panel);
        //12.按钮
        clearButton = new JButton("清空");
        ComponentStyle.setButtonStyle(clearButton, Color.orange, 50, 750, 220, 50, panel);
        confirmButton = new JButton("确认");
        ComponentStyle.setButtonStyle(confirmButton, Color.green, 310, 750, 220, 50, panel);
        //为按钮添加事件监听
        clearButton.addActionListener(this);
        confirmButton.addActionListener(this);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {//清空按钮
            futureYearsField.setText("");
            investMoneyField.setText("");
            investRateComboBox.setSelectedIndex(0);
            loanField.setText("");
            loanRateComboBox.setSelectedIndex(0);
            repaymentField.setText("");
            futureMoneyField.setText("");
        } else if (e.getSource() == confirmButton) {//确认按钮
            //1.获取输入框的值
            String futureYear = futureYearsField.getText().trim();
            String investMoney = investMoneyField.getText().trim();
            int investRateIndex = investRateComboBox.getSelectedIndex();
            String loanMoney = loanField.getText().trim();
            int loanRateIndex = loanRateComboBox.getSelectedIndex();
            String repayment = repaymentField.getText().trim();
            //2.验证输入
            if (validateLoanInput(futureYear, investMoney, investRateIndex, loanMoney, loanRateIndex, repayment)) {
                //3.二次获取输入--收益率+利息
                String investRateString = investRateComboBox.getSelectedItem().toString();
                String loanRateString = loanRateComboBox.getSelectedItem().toString();
                double investRate = Double.parseDouble(investRateString.substring(0, investRateString.lastIndexOf("%"))) / 100;
                double loanRate = Double.parseDouble(loanRateString.substring(0, loanRateString.lastIndexOf("%"))) / 100;
                //4.若还款金额总小于利息，则认为银行根本不会批准这种贷款方式。提示错误消息，结果后续功能
                if (Double.parseDouble(repayment) < (Double.parseDouble(loanMoney) - Double.parseDouble(investMoney)) * loanRate) {
                    futureYearsField.setText("您的还款金额有误！");
                    return;
                }
                //计算第N年后定投本息
                Double income = fixedInvestment(Double.parseDouble(investMoney), investRate, Double.parseDouble(futureYear));
                //计算第N年后贷款剩余
                Double debt = loan(Double.parseDouble(loanMoney), loanRate, Double.parseDouble(repayment), Double.parseDouble(futureYear));
                //计算第N年后的总资产
                Double future = future(Double.parseDouble(futureYear), income, debt);
                //保留2位小数，并显示在未来收益输入框中
                futureMoneyField.setText(String.format("%.2f", future));
            }
        }
    }
        /**
         * 验证输入
         *
         * @param futureYear 未来年份
         * @param investMoney 每月定投
         * @param investRateIndex 定投年收益率
         * @param loanMoney 贷款金额
         * @param loanRateIndex 贷款年利率
         * @param repayment 每年还款
         * @return
         */
        private boolean validateLoanInput (String futureYear, String investMoney, int investRateIndex,
                String loanMoney, int loanRateIndex, String repayment){
            //若未来年份不在1-59之间
            if (!futureYear.matches("^[1-5]\\d{0,1}$")) {
                JOptionPane.showMessageDialog(null, "未来年份为小于60年的整数");
                return false;
            }
            //定投金额 = 0或者>=100
            if (!investMoney.matches("^0|[1-9]\\d{2,}$")) {
                JOptionPane.showMessageDialog(null, "定投金额只能是0或者是100以上的整数");
                return false;
            }
            //定投金额>0，但是收益率=0或未选
            if (!investMoney.equals("0") && investRateIndex <= 1) {
                JOptionPane.showMessageDialog(null, "请选择合适的定投年收益率！");
                return false;
            }
            //定投金额=0，但是收益率不为0
            if (investMoney.equals("0") && investRateIndex != 1) {
                JOptionPane.showMessageDialog(null, "定投收益率应选择0！");
                return false;
            }
            //贷款金额 = 0或者>=10000
            if (!loanMoney.matches("^0|[1-9]\\d{4,}$")) {
                JOptionPane.showMessageDialog(null, "贷款金额只能是0或者是10000以上的整数");
                return false;
            }
            //贷款金额>0，但是利率=0或未选
            if (!loanMoney.equals("0") && loanRateIndex <= 1) {
                JOptionPane.showMessageDialog(null, "请选择合适的贷款年利率！");
                return false;
            }
            //贷款金额=0，但是利率不为0
            if (loanMoney.equals("0") && loanRateIndex != 1) {
                JOptionPane.showMessageDialog(null, "贷款年利率应选择0！");
                return false;
            }
            //贷款金额小于还款金额
            if (Double.parseDouble(loanMoney) < Double.parseDouble(repayment)) {
                JOptionPane.showMessageDialog(null, "贷款金额不能小于还款金额！");
                return false;
            }
            return true;
        }
        /**
         *计算第N年后定投本息
         * @param investment 未来年份
         * @param annualRate 收益率
         * @param years 未来年份
         * @return
         */
        private Double fixedInvestment ( double investment, double annualRate, Double years){
            investment = investment * 12;//一年
            double expected = investment * (1 + annualRate) * (Math.pow((1 + annualRate), years) - 1) / annualRate; //预期本息
            return expected;
        }
        /**
         * 计算第N年后贷款剩余
         *
         * @param loan 贷款金额
         * @param loanRate 年利率
         * @param pay 每年还款
         * @param years 还款
         * @return
         */
        private Double loan ( double loan, double loanRate, double pay, Double years){
            for (int i = 0; i <= years; i++) {
                loan -= pay;// 剩余贷款=贷款-还款
                if (loan == 0) {
                    break;
                } else {
                    //最终剩余贷款 = 还款后剩余贷款+利息
                    loan *= (1 + loanRate);
                }
            }
            return loan;
        }
        /**
         * 计算未来资产
         * @param years 未来年份
         * @param income 定投本息
         * @param debt 剩余贷款
         * @return
         */
        private Double future ( double years, Double income, Double debt){
            //卡内余额 = 定投本息+剩余贷款
            String currentMoneyStr = currentMoneyField.getText().trim();
            double currentMoney = Double.parseDouble(currentMoneyStr);
            //未来资产 = 卡内余额+定投收益-剩余贷款
            return currentMoney + income - debt;
        }
    }




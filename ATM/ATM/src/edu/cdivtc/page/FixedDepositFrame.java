package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.utils.ColorUtils;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FixedDepositFrame extends JFrame implements ActionListener {
    //面板
    private JPanel panel;
    //文字：大标题 用户名 卡号 卡内余额 每月定投 年收益率 定投年限 定投收益
    private JLabel tittle,currentUser,currentCid,currentMoney,fixedMoney,annualRate,years,finalMoney;
    //输入框：用户名 卡号 卡内余额 每月定投 年收益率 定投年限 定投收益
    private JTextField currentUserField,currentCidField,currentMoneyField,fixedMoneyField,annualRateField,finalMoneyField;
    //定投年限下拉框
    private JComboBox yearComboBox;
    //按钮：清空 确认
    private JButton resetButton,confirmButton;
    //年收益率
    private String selectedYears;
    public FixedDepositFrame() {
        //1.窗体设置
        ComponentStyle.setFrame("ATM自动存取款系统", 600, 800, "src/images/logo.png", this);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//点击关闭时隐藏窗口，不退出程序
        this.setLayout(null);//更改布局方式
        //2.设置面板
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(ColorUtils.PANEL_BACKGROUND_COLOR);
        panel.setBounds(0, 0, 600, 800);
        this.add(panel);
        //3.大标题
        tittle = new JLabel("定期咨询");
        ComponentStyle.setFormStyle(tittle, 40, Color.BLACK, 210, 30, 200,50, panel);
        //4.用户名
        currentUser = new JLabel("用  户  名：",JLabel.CENTER);
        ComponentStyle.setFormStyle(currentUser, 25, Color.BLACK, 50, 110, 200, 50, panel);
        currentUserField = new JTextField(UserSaveTool.getCurrentLoginUsername());
        currentUserField.setEditable(false);//不可编辑
        ComponentStyle.setFormStyle(currentUserField, 25, Color.BLACK, 250, 110, 280, 50, panel);
        //5.卡号
        currentCid = new JLabel("卡      号：",JLabel.CENTER);
        ComponentStyle.setFormStyle(currentCid, 25, Color.BLACK, 50, 190, 200, 50, panel);
        currentCidField = new JTextField(UserSaveTool.getCurrentLoginAccountNumber());
        currentCidField.setEditable(false);//不可编辑
        ComponentStyle.setFormStyle(currentCidField, 25, Color.BLACK, 250, 190, 280, 50, panel);
        //6.卡内余额
        currentMoney = new JLabel("卡内余额￥：",JLabel.CENTER);
        ComponentStyle.setFormStyle(currentMoney, 25, Color.BLACK, 50, 270, 200, 50, panel);
        currentMoneyField = new JTextField(UserSaveTool.getCurrentLoginMoney());
        currentMoneyField.setEditable(false);//不可编辑
        ComponentStyle.setFormStyle(currentMoneyField, 25, Color.BLACK, 250, 270, 280, 50, panel);
        //7.每月定投
        fixedMoney = new JLabel("每月定投￥：",JLabel.CENTER);
        ComponentStyle.setFormStyle(fixedMoney, 25, Color.BLACK, 50, 350, 200, 50, panel);
        fixedMoneyField = new JTextField();
        ComponentStyle.setFormStyle(fixedMoneyField, 25, Color.BLACK, 250, 350, 280, 50, panel);
        //8.定投年限
        years = new JLabel("定投年限(年)：",JLabel.CENTER);
        ComponentStyle.setFormStyle(years, 25, Color.BLACK, 50, 430, 200, 50, panel);
        //定投年限
        yearComboBox = new JComboBox();
        String[] strArray = {"请选择定投年限","1","2","3","5"};
        for (String item:strArray) {
            yearComboBox.addItem(item);
        }
        ComponentStyle.setFormStyle(yearComboBox, 25, Color.BLACK, 250, 430, 280, 50, panel);
        //9.年收益率
        annualRate = new JLabel("年收益率%：",JLabel.CENTER);
        ComponentStyle.setFormStyle(annualRate, 25, Color.BLACK, 50, 510, 200, 50, panel);
        annualRateField = new JTextField();
        annualRateField.setEditable(false);//不可编辑
        ComponentStyle.setFormStyle(annualRateField, 25, Color.BLACK, 250, 510, 280, 50, panel);
        //10.定投收益
        finalMoney = new JLabel("定投收益￥：",JLabel.CENTER);
        ComponentStyle.setFormStyle(finalMoney, 25, Color.RED, 50, 590, 200, 50, panel);
        finalMoneyField = new JTextField();
        ComponentStyle.setFormStyle(finalMoneyField, 25, Color.RED, 250, 590, 280, 50, panel);
        //获取用户的定投年限，并设置年收益率--必须在年收益率表单后写该部分代码
        yearComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ItemEvent.SELECTED == e.getStateChange()) {
                    selectedYears = yearComboBox.getSelectedItem().toString();
                    switch (selectedYears) {
                        case "1":
                            annualRateField.setText("2.00%");
                            break;
                        case "2":
                            annualRateField.setText("2.50%");
                            break;
                        case "3":
                            annualRateField.setText("3.00%");
                            break;
                        case "5":
                            annualRateField.setText("3.05%");
                            break;

                    }

                }
            }
        });
        //11.按钮：清空+确认
        resetButton = new JButton("清空");
        ComponentStyle.setButtonStyle(resetButton, Color.orange, 50, 670, 220, 50, panel);
        confirmButton = new JButton("确认");
        ComponentStyle.setButtonStyle(confirmButton, Color.green, 310, 670, 220, 50, panel);
        //为按钮添加事件监听
        resetButton.addActionListener(this);
        confirmButton.addActionListener(this);
        this.setVisible(true);//显示窗体
    }
     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) { //清空
            fixedMoneyField.setText("");
            yearComboBox.setSelectedIndex(0);
            annualRateField.setText("");
            finalMoneyField.setText("");
        }else if (e.getSource() == confirmButton) { //确认
            //1.获取每月定投金额
            String invest = fixedMoneyField.getText().trim();
            //2.定投金额验证--必须输入>100的正数
            if(!invest.matches("[1-9]\\d{2,}$")) {
                JOptionPane.showMessageDialog(null,"定投金额至少是100元，且不能包含小数！");
                return;
            }else {
                //3.获取年收益率并验证年收益率
                String annualRate = annualRateField.getText().trim();
                if (annualRateField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null,"请选择定投年限！");
                    return;
                }else {
                    //4.数据类型转换
                    //年收益率去掉%，%无法直接转换成double类型--增大100倍
                    annualRate = annualRate.substring(0,annualRate.lastIndexOf("%")); //去掉%
                    //数据类型转换
                    double investMoney = Double.parseDouble(invest);
                    double investYears = Double.parseDouble(selectedYears);
                    double investAnnualRate = Double.parseDouble(annualRate)/100; //减小100倍 原倍
                    //5.计算定投收益
                    //一年的金额
                    investMoney = investMoney * 12;
                    //预期收益(本金+利息)= 每期定投金额 * (1+收益率)[-1+(1+收益率)的定投期数次方]/收益率
                    double expected = investMoney * (1+investAnnualRate) * (Math.pow(1+investAnnualRate,investYears)-1)/investAnnualRate;
                    //最终的金额--保留两位小数
                    finalMoneyField.setText(String.format("%.2f",expected));
                }
            }
        }
    }
}

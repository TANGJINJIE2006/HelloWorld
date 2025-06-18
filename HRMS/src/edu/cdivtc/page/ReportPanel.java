package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.theme.ThemeColors;
import edu.cdivtc.theme.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 报表统计功能面板
 */
public class ReportPanel extends JPanel {
    private JTextArea reportArea;
    private JButton generateButton;

    public ReportPanel() {
        setLayout(null); // 使用绝对布局，便于精确控制

        // 设置标题
        JLabel title = new JLabel("运营统计报表");
        title.setFont(UITheme.PANEL_TITLE_FONT);
        title.setForeground(ThemeColors.CONTENT_TITLE);
        ComponentStyle.setFormStyle(title, 24, ThemeColors.CONTENT_TITLE, 30, 20, 300, 40, this);

        // 报表显示区域
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBounds(30, 80, 1000, 500);
        this.add(scrollPane);

        // 生成按钮
        generateButton = new JButton("生成报表");
        ComponentStyle.setButtonStyle(generateButton, ThemeColors.PRIMARY_BUTTON, 30, 600, 150, 40, this);

        // 绑定按钮事件
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        setBackground(ThemeColors.CONTENT_BACKGROUND);
    }

    private void generateReport() {
        // 模拟生成数据（可替换为真实数据库查询逻辑）
        StringBuilder report = new StringBuilder();
        report.append("======= 酒店运营日报表 =======\n");
        report.append("日期：").append(java.time.LocalDate.now()).append("\n\n");
        report.append("总房间数：120 间\n");
        report.append("今日入住：45 间\n");
        report.append("今日退房：30 间\n");
        report.append("当前入住率：37.5%\n");
        report.append("今日收入：￥15,300 元\n");
        report.append("今日订单数：50 单\n");
        report.append("客户满意度：96%\n");
        report.append("员工值班人数：12 人\n");
        report.append("\n==============================\n");

        reportArea.setText(report.toString());
    }
}

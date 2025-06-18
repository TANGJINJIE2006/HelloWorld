package edu.cdivtc.page;

import edu.cdivtc.theme.ThemeColors;
import edu.cdivtc.theme.UITheme;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(ThemeColors.CARD_BACKGROUND);

        JLabel title = new JLabel("系统概览", JLabel.CENTER);
        title.setFont(UITheme.PANEL_TITLE_FONT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // 添加统计卡片
        JPanel statsPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addStatCard(statsPanel, "今日入住", "45", ThemeColors.SUCCESS_BUTTON);
        addStatCard(statsPanel, "今日退房", "32", ThemeColors.DANGER_BUTTON);
        addStatCard(statsPanel, "可用客房", "128", ThemeColors.PRIMARY_BUTTON);
        addStatCard(statsPanel, "预订中", "67", new Color(111, 66, 193));
        addStatCard(statsPanel, "维修中", "5", ThemeColors.WARNING_BUTTON);
        addStatCard(statsPanel, "总收入", "¥58,760", new Color(32, 201, 151));

        add(statsPanel, BorderLayout.CENTER);

        // 添加最近预订表格
        JPanel recentPanel = new JPanel(new BorderLayout());
        recentPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JLabel recentLabel = new JLabel("最近预订");
        recentLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        recentPanel.add(recentLabel, BorderLayout.NORTH);

        String[] columnNames = {"预订ID", "客户姓名", "房间类型", "入住日期", "退房日期", "状态"};
        Object[][] data = {
            {"R1001", "张三", "豪华套房", "2023-05-01", "2023-05-03", "已入住"},
            {"R1002", "李四", "标准间", "2023-05-02", "2023-05-05", "已确认"},
            {"R1003", "王五", "商务房", "2023-05-03", "2023-05-06", "待确认"},
            {"R1004", "赵六", "豪华套房", "2023-05-04", "2023-05-07", "已取消"},
            {"R1005", "钱七", "标准间", "2023-05-05", "2023-05-08", "已入住"}
        };

        JTable recentTable = new JTable(data, columnNames);
        recentTable.setFont(UITheme.TABLE_CONTENT_FONT);
        recentTable.setRowHeight(30);
        recentTable.getTableHeader().setFont(UITheme.TABLE_HEADER_FONT);
        recentTable.getTableHeader().setBackground(ThemeColors.TABLE_HEADER);

        recentPanel.add(new JScrollPane(recentTable), BorderLayout.CENTER);

        add(recentPanel, BorderLayout.SOUTH);
    }

    private void addStatCard(JPanel panel, String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(ThemeColors.CARD_BACKGROUND);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        titleLabel.setForeground(color.darker());

        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        panel.add(card);
    }
}
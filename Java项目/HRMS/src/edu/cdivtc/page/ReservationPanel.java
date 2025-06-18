package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.theme.ThemeColors;
import edu.cdivtc.theme.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private DefaultTableModel reservationTableModel;

    public ReservationPanel() {
        setLayout(new BorderLayout());
        setBackground(ThemeColors.CARD_BACKGROUND);

        // 标题
        JLabel title = new JLabel("预订管理", JLabel.CENTER);
        title.setFont(UITheme.PANEL_TITLE_FONT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // 选项卡
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        // 新增预订面板
        JPanel newReservationPanel = createNewReservationPanel();
        tabbedPane.addTab("新增预订", newReservationPanel);

        // 预订查询面板
        JPanel searchReservationPanel = createSearchReservationPanel();
        tabbedPane.addTab("预订查询", searchReservationPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createNewReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(ThemeColors.CARD_BACKGROUND);

        // 表单面板
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 15, 15));
        formPanel.setBackground(ThemeColors.CARD_BACKGROUND);

        // 客户信息
        JLabel customerLabel = new JLabel("客户信息:");
        JTextField customerField = new JTextField();
        JButton searchCustomerBtn = new JButton("查找客户");
        ComponentStyle.setButtonStyle(searchCustomerBtn, ThemeColors.PRIMARY_BUTTON, formPanel);

        // 房间信息
        JLabel roomTypeLabel = new JLabel("房间类型:");
        JComboBox<String> roomTypeCombo = new JComboBox<>(new String[]{"标准间", "商务房", "豪华套房"});

        // 日期信息
        JLabel checkInLabel = new JLabel("入住日期:");
        JTextField checkInField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        checkInField.setEditable(false);
        JButton checkInCalendarBtn = new JButton("选择");
        ComponentStyle.setButtonStyle(checkInCalendarBtn, ThemeColors.PRIMARY_BUTTON, formPanel);

        JLabel checkOutLabel = new JLabel("退房日期:");
        JTextField checkOutField = new JTextField();
        checkOutField.setEditable(false);
        JButton checkOutCalendarBtn = new JButton("选择");
        ComponentStyle.setButtonStyle(checkOutCalendarBtn, ThemeColors.PRIMARY_BUTTON, formPanel);

        // 其他信息
        JLabel guestsLabel = new JLabel("入住人数:");
        JSpinner guestsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));

        JLabel specialLabel = new JLabel("特殊要求:");
        JTextArea specialArea = new JTextArea(3, 20);
        specialArea.setLineWrap(true);
        JScrollPane specialScroll = new JScrollPane(specialArea);

        // 添加组件到表单
        formPanel.add(customerLabel);
        JPanel customerPanel = new JPanel(new BorderLayout(5, 0));
        customerPanel.add(customerField, BorderLayout.CENTER);
        customerPanel.add(searchCustomerBtn, BorderLayout.EAST);
        formPanel.add(customerPanel);

        formPanel.add(roomTypeLabel);
        formPanel.add(roomTypeCombo);

        formPanel.add(checkInLabel);
        JPanel checkInPanel = new JPanel(new BorderLayout(5, 0));
        checkInPanel.add(checkInField, BorderLayout.CENTER);
        checkInPanel.add(checkInCalendarBtn, BorderLayout.EAST);
        formPanel.add(checkInPanel);

        formPanel.add(checkOutLabel);
        JPanel checkOutPanel = new JPanel(new BorderLayout(5, 0));
        checkOutPanel.add(checkOutField, BorderLayout.CENTER);
        checkOutPanel.add(checkOutCalendarBtn, BorderLayout.EAST);
        formPanel.add(checkOutPanel);

        formPanel.add(guestsLabel);
        formPanel.add(guestsSpinner);

        formPanel.add(specialLabel);
        formPanel.add(specialScroll);

        panel.add(formPanel, BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(ThemeColors.CARD_BACKGROUND);

        JButton checkBtn = new JButton("检查可用房间");
        JButton reserveBtn = new JButton("确认预订");
        JButton clearBtn = new JButton("清空表单");

        ComponentStyle.setButtonStyle(checkBtn, ThemeColors.PRIMARY_BUTTON, buttonPanel);
        ComponentStyle.setButtonStyle(reserveBtn, ThemeColors.SUCCESS_BUTTON, buttonPanel);
        ComponentStyle.setButtonStyle(clearBtn, ThemeColors.DANGER_BUTTON, buttonPanel);

        // 按钮事件
        checkBtn.addActionListener(e -> checkAvailableRooms());
        reserveBtn.addActionListener(e -> confirmReservation());
        clearBtn.addActionListener(e -> clearForm());

        buttonPanel.add(checkBtn);
        buttonPanel.add(reserveBtn);
        buttonPanel.add(clearBtn);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSearchReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(ThemeColors.CARD_BACKGROUND);

        // 搜索栏
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        searchPanel.setBackground(ThemeColors.CARD_BACKGROUND);

        JLabel searchLabel = new JLabel("搜索:");
        JTextField searchField = new JTextField(25);
        JButton searchBtn = new JButton("搜索");
        JButton refreshBtn = new JButton("刷新");

        ComponentStyle.setButtonStyle(searchBtn, ThemeColors.PRIMARY_BUTTON, searchPanel);
        ComponentStyle.setButtonStyle(refreshBtn, ThemeColors.PRIMARY_BUTTON, searchPanel);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(refreshBtn);

        panel.add(searchPanel, BorderLayout.NORTH);

        // 预订表格
        String[] columnNames = {"预订ID", "客户姓名", "房间号", "房间类型", "入住日期", "退房日期", "状态", "操作"};
        Object[][] data = {
            {"R1001", "张三", "201", "商务房", "2023-05-01", "2023-05-03", "已入住", "查看"},
            {"R1002", "李四", "102", "标准间", "2023-05-02", "2023-05-05", "已确认", "查看"},
            {"R1003", "王五", "301", "豪华套房", "2023-05-03", "2023-05-06", "待确认", "查看"},
            {"R1004", "赵六", "302", "豪华套房", "2023-05-04", "2023-05-07", "已取消", "查看"},
            {"R1005", "钱七", "101", "标准间", "2023-05-05", "2023-05-08", "已入住", "查看"}
        };

        reservationTableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // 只有操作列可点击
            }
        };

        JTable reservationTable = new JTable(reservationTableModel);
        reservationTable.setFont(UITheme.TABLE_CONTENT_FONT);
        reservationTable.setRowHeight(35);
        reservationTable.getTableHeader().setFont(UITheme.TABLE_HEADER_FONT);
        reservationTable.getTableHeader().setBackground(ThemeColors.TABLE_HEADER);

        // 设置操作列渲染器和编辑器
        reservationTable.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        reservationTable.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));

        // 状态列渲染
        reservationTable.getColumnModel().getColumn(6).setCellRenderer(new StatusCellRenderer());

        panel.add(new JScrollPane(reservationTable), BorderLayout.CENTER);

        return panel;
    }

    private void checkAvailableRooms() {
        // 实现检查可用房间逻辑
        JOptionPane.showMessageDialog(this, "检查可用房间功能待实现", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmReservation() {
        // 实现确认预订逻辑
        JOptionPane.showMessageDialog(this, "确认预订功能待实现", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
        // 实现清空表单逻辑
        tabbedPane.setSelectedIndex(0);
        Component[] components = ((JPanel)tabbedPane.getComponentAt(0)).getComponents();
        // 这里需要递归清除所有表单字段
    }

    // 状态列渲染器
    private static class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = value.toString();
            switch (status) {
                case "已入住":
                    c.setForeground(new Color(40, 167, 69)); // 绿色
                    break;
                case "已确认":
                    c.setForeground(new Color(0, 123, 255)); // 蓝色
                    break;
                case "待确认":
                    c.setForeground(new Color(255, 193, 7)); // 黄色
                    break;
                case "已取消":
                    c.setForeground(new Color(108, 117, 125)); // 灰色
                    break;
                default:
                    c.setForeground(Color.BLACK);
            }

            setHorizontalAlignment(JLabel.CENTER);
            return c;
        }
    }

    // 按钮列渲染器
    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // 按钮列编辑器
    private static class ButtonEditor extends DefaultCellEditor {
        private String label;
        private JButton button;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // 这里可以执行按钮点击后的操作
                JOptionPane.showMessageDialog(button, "查看预订详情: " + label);
            }
            isPushed = false;
            return label;
        }
    }
}
package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.theme.ThemeColors;
import edu.cdivtc.theme.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomManagementPanel extends JPanel implements ActionListener {
    private JTable roomTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public RoomManagementPanel() {
        setLayout(new BorderLayout());
        setBackground(ThemeColors.CARD_BACKGROUND);

        // 标题
        JLabel title = new JLabel("客房管理", JLabel.CENTER);
        title.setFont(UITheme.PANEL_TITLE_FONT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // 工具栏
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        toolbar.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        toolbar.setBackground(ThemeColors.CARD_BACKGROUND);

        JButton addBtn = new JButton("新增客房");
        JButton editBtn = new JButton("修改信息");
        JButton deleteBtn = new JButton("删除客房");
        JButton refreshBtn = new JButton("刷新");
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("搜索");

        // 设置按钮样式

int borderWidth = 1; // 边框宽度
int borderHeight = 1; // 边框高度
int borderRadiusWidth = 5; // 圆角弧度宽度
int borderRadiusHeight = 5; // 圆角弧度高度

// 设置按钮样式
ComponentStyle.setButtonStyle(addBtn, ThemeColors.PRIMARY_BUTTON, borderWidth, borderHeight, borderRadiusWidth, borderRadiusHeight, toolbar);
ComponentStyle.setButtonStyle(editBtn, ThemeColors.PRIMARY_BUTTON, borderWidth, borderHeight, borderRadiusWidth, borderRadiusHeight, toolbar);
ComponentStyle.setButtonStyle(deleteBtn, ThemeColors.DANGER_BUTTON, borderWidth, borderHeight, borderRadiusWidth, borderRadiusHeight, toolbar);
ComponentStyle.setButtonStyle(refreshBtn, ThemeColors.PRIMARY_BUTTON, borderWidth, borderHeight, borderRadiusWidth, borderRadiusHeight, toolbar);
ComponentStyle.setButtonStyle(searchBtn, ThemeColors.PRIMARY_BUTTON, borderWidth, borderHeight, borderRadiusWidth, borderRadiusHeight, toolbar);

        // 添加事件监听
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        searchBtn.addActionListener(this);

        toolbar.add(addBtn);
        toolbar.add(editBtn);
        toolbar.add(deleteBtn);
        toolbar.add(Box.createHorizontalGlue());
        toolbar.add(searchField);
        toolbar.add(searchBtn);
        toolbar.add(refreshBtn);

        add(toolbar, BorderLayout.CENTER);

        // 客房表格
        String[] columnNames = {"房间号", "房间类型", "楼层", "状态", "价格/晚", "床型", "最大人数", "设施"};
        Object[][] data = {
            {"101", "标准间", "1楼", "可用", "¥299", "双人床", "2", "电视,空调,WIFI"},
            {"102", "标准间", "1楼", "可用", "¥299", "双人床", "2", "电视,空调,WIFI"},
            {"201", "商务房", "2楼", "已预订", "¥499", "大床", "2", "电视,空调,WIFI,办公桌"},
            {"202", "商务房", "2楼", "可用", "¥499", "大床", "2", "电视,空调,WIFI,办公桌"},
            {"301", "豪华套房", "3楼", "维修中", "¥899", "大床+小床", "3", "电视,空调,WIFI,迷你吧,浴缸"},
            {"302", "豪华套房", "3楼", "可用", "¥899", "大床+小床", "3", "电视,空调,WIFI,迷你吧,浴缸"}
        };

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 表格不可编辑
            }
        };

        roomTable = new JTable(tableModel);
        roomTable.setFont(UITheme.TABLE_CONTENT_FONT);
        roomTable.setRowHeight(35);
        roomTable.getTableHeader().setFont(UITheme.TABLE_HEADER_FONT);
        roomTable.getTableHeader().setBackground(ThemeColors.TABLE_HEADER);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 设置状态列的特殊渲染
        roomTable.getColumnModel().getColumn(3).setCellRenderer(new StatusCellRenderer());

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        add(scrollPane, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "新增客房":
                showAddRoomDialog();
                break;
            case "修改信息":
                editSelectedRoom();
                break;
            case "删除客房":
                deleteSelectedRoom();
                break;
            case "搜索":
                searchRooms();
                break;
            case "刷新":
                refreshTable();
                break;
        }
    }

    private void showAddRoomDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("新增客房");
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField roomNoField = new JTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"标准间", "商务房", "豪华套房"});
        JTextField floorField = new JTextField();
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"可用", "已预订", "维修中"});
        JTextField priceField = new JTextField();
        JComboBox<String> bedTypeCombo = new JComboBox<>(new String[]{"单人床", "双人床", "大床", "大床+小床"});
        JTextField maxGuestField = new JTextField();
        JTextField facilitiesField = new JTextField();

        panel.add(new JLabel("房间号:"));
        panel.add(roomNoField);
        panel.add(new JLabel("房间类型:"));
        panel.add(typeCombo);
        panel.add(new JLabel("楼层:"));
        panel.add(floorField);
        panel.add(new JLabel("状态:"));
        panel.add(statusCombo);
        panel.add(new JLabel("价格/晚:"));
        panel.add(priceField);
        panel.add(new JLabel("床型:"));
        panel.add(bedTypeCombo);
        panel.add(new JLabel("最大人数:"));
        panel.add(maxGuestField);
        panel.add(new JLabel("设施:"));
        panel.add(facilitiesField);

        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(evt -> {
            // 这里添加保存逻辑
            Object[] rowData = {
                roomNoField.getText(),
                typeCombo.getSelectedItem(),
                floorField.getText(),
                statusCombo.getSelectedItem(),
                "¥" + priceField.getText(),
                bedTypeCombo.getSelectedItem(),
                maxGuestField.getText(),
                facilitiesField.getText()
            };

            tableModel.addRow(rowData);
            dialog.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveBtn);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }


    private void editSelectedRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要修改的客房", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 实现编辑逻辑
        JOptionPane.showMessageDialog(this, "编辑功能待实现", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteSelectedRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的客房", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "确定要删除选中的客房吗?",
            "确认删除",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
        }
    }

    private void searchRooms() {
        String keyword = searchField.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            return;
        }

        // 简单搜索实现
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            boolean match = false;
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                String cellValue = tableModel.getValueAt(i, j).toString().toLowerCase();
                if (cellValue.contains(keyword)) {
                    match = true;
                    break;
                }
            }

            if (match) {
                roomTable.addRowSelectionInterval(i, i);
            } else {
                roomTable.removeRowSelectionInterval(i, i);
            }
        }
    }

    private void refreshTable() {
        searchField.setText("");
        roomTable.clearSelection();
    }

    // 状态列自定义渲染器
    private static class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = value.toString();
            switch (status) {
                case "可用":
                    c.setForeground(new Color(40, 167, 69)); // 绿色
                    break;
                case "已预订":
                    c.setForeground(new Color(0, 123, 255)); // 蓝色
                    break;
                case "维修中":
                    c.setForeground(new Color(220, 53, 69)); // 红色
                    break;
                default:
                    c.setForeground(Color.BLACK);
            }

            setHorizontalAlignment(JLabel.CENTER);
            return c;
        }
    }
}
package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.theme.ThemeColors;
import edu.cdivtc.theme.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends JPanel implements ActionListener {
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JButton addBtn, editBtn, deleteBtn, searchBtn;
    private JTextField searchField;

    public CustomerPanel() {
        setLayout(new BorderLayout());
        setBackground(ThemeColors.CONTENT_BACKGROUND);

        // 顶部标题
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(ThemeColors.CONTENT_BACKGROUND);
        JLabel titleLabel = new JLabel("客户管理");
        titleLabel.setFont(UITheme.CONTENT_TITLE_FONT);
        titleLabel.setForeground(ThemeColors.CONTENT_TITLE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // 中间表格区域
        createTable();

        // 底部操作按钮区域
        createButtonPanel();
    }

    private void createTable() {
        // 表格模型
        String[] columns = {"客户ID", "姓名", "电话", "身份证号", "会员等级", "注册时间"};
        Object[][] data = {
            {"C1001", "张三", "13800138000", "110101199001011234", "黄金会员", "2023-01-15"},
            {"C1002", "李四", "13900139000", "110101198502028765", "普通会员", "2023-02-20"},
            {"C1003", "王五", "13700137000", "110101199503039876", "白金会员", "2023-03-10"}
        };

        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 表格不可编辑
            }
        };

        customerTable = new JTable(tableModel);
        customerTable.setFont(UITheme.TABLE_CONTENT_FONT);
        customerTable.setRowHeight(30);
        customerTable.getTableHeader().setFont(UITheme.TABLE_HEADER_FONT);
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(customerTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBackground(ThemeColors.CONTENT_BACKGROUND);

        // 搜索框
        searchField = new JTextField(20);
        searchField.setFont(UITheme.TABLE_CONTENT_FONT);

        // 按钮
        addBtn = ComponentStyle.createButton("添加客户", ThemeColors.PRIMARY_BUTTON);
        editBtn = ComponentStyle.createButton("编辑信息", ThemeColors.SECONDARY_BUTTON);
        deleteBtn = ComponentStyle.createButton("删除客户", ThemeColors.DANGER_BUTTON);
        searchBtn = ComponentStyle.createButton("搜索", ThemeColors.INFO_BUTTON);

        // 添加事件监听
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        searchBtn.addActionListener(this);

        buttonPanel.add(searchField);
        buttonPanel.add(searchBtn);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            showCustomerDialog(null);
        } else if (e.getSource() == editBtn) {
            int selectedRow = customerTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "请先选择要编辑的客户", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            showCustomerDialog(selectedRow);
        } else if (e.getSource() == deleteBtn) {
            int selectedRow = customerTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "请先选择要删除的客户", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "确定要删除此客户吗?", "确认删除", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
            }
        } else if (e.getSource() == searchBtn) {
            String keyword = searchField.getText().trim();
            // 这里应该实现搜索逻辑
            JOptionPane.showMessageDialog(this, "搜索: " + keyword, "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showCustomerDialog(Integer row) {
        JDialog dialog = new JDialog();
        dialog.setTitle(row == null ? "添加客户" : "编辑客户信息");
        dialog.setSize(400, 350);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 表单字段
        JLabel nameLabel = new JLabel("姓名:");
        JTextField nameField = new JTextField();

        JLabel phoneLabel = new JLabel("电话:");
        JTextField phoneField = new JTextField();

        JLabel idCardLabel = new JLabel("身份证号:");
        JTextField idCardField = new JTextField();

        JLabel levelLabel = new JLabel("会员等级:");
        JComboBox<String> levelCombo = new JComboBox<>(new String[]{"普通会员", "白银会员", "黄金会员", "白金会员", "钻石会员"});

        JLabel regDateLabel = new JLabel("注册日期:");
        JTextField regDateField = new JTextField();
        regDateField.setEditable(false);

        // 如果是编辑模式，填充现有数据
        if (row != null) {
            nameField.setText(tableModel.getValueAt(row, 1).toString());
            phoneField.setText(tableModel.getValueAt(row, 2).toString());
            idCardField.setText(tableModel.getValueAt(row, 3).toString());
            levelCombo.setSelectedItem(tableModel.getValueAt(row, 4).toString());
            regDateField.setText(tableModel.getValueAt(row, 5).toString());
        } else {
            regDateField.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        }

        // 添加组件
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(idCardLabel);
        panel.add(idCardField);
        panel.add(levelLabel);
        panel.add(levelCombo);
        panel.add(regDateLabel);
        panel.add(regDateField);

        // 按钮
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(e -> {
            // 验证输入
            if (nameField.getText().isEmpty() || phoneField.getText().isEmpty() || idCardField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "请填写所有必填字段", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 保存数据
            if (row == null) {
                // 添加新客户
                String newId = "C" + (1000 + tableModel.getRowCount() + 1);
                tableModel.addRow(new Object[]{
                    newId,
                    nameField.getText(),
                    phoneField.getText(),
                    idCardField.getText(),
                    levelCombo.getSelectedItem(),
                    regDateField.getText()
                });
            } else {
                // 更新现有客户
                tableModel.setValueAt(nameField.getText(), row, 1);
                tableModel.setValueAt(phoneField.getText(), row, 2);
                tableModel.setValueAt(idCardField.getText(), row, 3);
                tableModel.setValueAt(levelCombo.getSelectedItem(), row, 4);
            }

            dialog.dispose();
        });

        JButton cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.add(cancelBtn);
        buttonPanel.add(saveBtn);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
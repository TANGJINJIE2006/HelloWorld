package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.theme.ThemeColors;
import edu.cdivtc.theme.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffPanel extends JPanel implements ActionListener {
    private JTable staffTable;
    private DefaultTableModel tableModel;
    private JButton addBtn, editBtn, deleteBtn, refreshBtn, searchBtn;
    private JTextField searchField;

    public StaffPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(ThemeColors.CONTENT_BACKGROUND);

        // 创建顶部工具栏
        createToolbar();

        // 创建员工表格
        createStaffTable();

        // 创建底部操作按钮
        createOperationButtons();
    }

    private void createToolbar() {
        // 创建工具栏面板
        JPanel toolbarPanel = new JPanel(new BorderLayout());
        // 设置面板边框
        toolbarPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        // 设置面板背景色
        toolbarPanel.setBackground(ThemeColors.CONTENT_BACKGROUND);
        // 标题
        JLabel titleLabel = new JLabel("员工管理");
        // 设置标题字体
        titleLabel.setFont(UITheme.PANEL_TITLE_FONT);
        // 设置标题颜色
        titleLabel.setForeground(ThemeColors.CONTENT_TITLE);
        // 将标题添加到面板左侧
        toolbarPanel.add(titleLabel, BorderLayout.WEST);
        // 搜索区域
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        // 设置搜索区域背景色
        searchPanel.setBackground(ThemeColors.CONTENT_BACKGROUND);
        // 创建搜索框
        searchField = new JTextField();
        // 设置搜索框字体
        searchField.setFont(UITheme.SIDEBAR_BUTTON_FONT);
        // 设置搜索框边框
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ThemeColors.CARD_BORDER),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        // 设置搜索框大小
        searchField.setPreferredSize(new Dimension(250, 40));
        // 创建搜索按钮
        searchBtn = new JButton("搜索");
        // 设置搜索按钮样式
        ComponentStyle.setPrimaryButton(searchBtn);
        // 添加搜索按钮点击事件
        searchBtn.addActionListener(this);
        // 设置搜索按钮大小
        searchBtn.setPreferredSize(new Dimension(100, 40));
        // 将搜索框和搜索按钮添加到搜索区域
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        // 添加员工按钮
        addBtn = new JButton("添加员工");
        // 设置添加员工按钮样式
        ComponentStyle.setPrimaryButton(addBtn);
        // 添加添加员工按钮点击事件
        addBtn.addActionListener(this);
        // 设置添加员工按钮大小
        addBtn.setPreferredSize(new Dimension(120, 40));
        // 创建右侧面板
        JPanel rightPanel = new JPanel(new BorderLayout(15, 0));
        // 设置右侧面板背景色
        rightPanel.setBackground(ThemeColors.CONTENT_BACKGROUND);
        // 将搜索区域添加到右侧面板
        rightPanel.add(searchPanel, BorderLayout.CENTER);
        // 将添加员工按钮添加到右侧面板
        rightPanel.add(addBtn, BorderLayout.EAST);
        // 将右侧面板添加到工具栏面板
        toolbarPanel.add(rightPanel, BorderLayout.CENTER);
        // 将工具栏面板添加到窗口顶部
        this.add(toolbarPanel, BorderLayout.NORTH);
    }
    private void createStaffTable() {
        // 表格模型
        String[] columns = {"员工ID", "姓名", "性别", "职位", "电话", "入职日期", "状态"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 模拟数据
        addSampleData();

        staffTable = new JTable(tableModel);
        staffTable.setFont(UITheme.TABLE_CONTENT_FONT);
        staffTable.setRowHeight(40);
        staffTable.setSelectionBackground(ThemeColors.PRIMARY_BUTTON);
        staffTable.setSelectionForeground(Color.WHITE);
        staffTable.setGridColor(ThemeColors.CARD_BORDER);

        // 表头样式
        JTableHeader header = staffTable.getTableHeader();
        header.setFont(UITheme.TABLE_HEADER_FONT);
        header.setBackground(ThemeColors.TABLE_HEADER);
        header.setForeground(ThemeColors.CONTENT_TITLE);
        header.setBorder(BorderFactory.createLineBorder(ThemeColors.CARD_BORDER));

        JScrollPane scrollPane = new JScrollPane(staffTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(ThemeColors.CARD_BORDER));
        scrollPane.setBackground(Color.WHITE);

        // 内容面板
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        tablePanel.setBackground(ThemeColors.CONTENT_BACKGROUND);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        this.add(tablePanel, BorderLayout.CENTER);
    }
    private void addSampleData() {
        addStaffToTable("1001", "张三", "男", "前台", "13800138001", "2022-01-15", "在职");
        addStaffToTable("1002", "李四", "女", "保洁", "13800138002", "2021-11-20", "在职");
        addStaffToTable("1003", "王五", "男", "经理", "13800138003", "2020-05-10", "在职");
        addStaffToTable("1004", "赵六", "女", "前台", "13800138004", "2022-03-08", "离职");
    }
    private void addStaffToTable(String id, String name, String gender,
                               String position, String phone, String hireDate, String status) {
        tableModel.addRow(new Object[]{id, name, gender, position, phone, hireDate, status});
    }
    private void createOperationButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        buttonPanel.setBackground(ThemeColors.CONTENT_BACKGROUND);

        refreshBtn = new JButton("刷新");
        ComponentStyle.setSecondaryButton(refreshBtn);
        refreshBtn.addActionListener(this);

        editBtn = new JButton("编辑");
        ComponentStyle.setInfoButton(editBtn);
        editBtn.addActionListener(this);

        deleteBtn = new JButton("删除");
        ComponentStyle.setDangerButton(deleteBtn);
        deleteBtn.addActionListener(this);

        buttonPanel.add(refreshBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            showStaffDialog(null);
        } else if (e.getSource() == editBtn) {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow != -1) {
                showStaffDialog(getStaffFromRow(selectedRow));
            } else {
                showMessage("请先选择要编辑的员工", "提示", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == deleteBtn) {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow != -1) {
                confirmAndDeleteRow(selectedRow);
            } else {
                showMessage("请先选择要删除的员工", "提示", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == refreshBtn) {
            refreshTableData();
            showMessage("数据已刷新", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == searchBtn) {
            // 搜索功能实现
            String keyword = searchField.getText().trim();
            if (!keyword.isEmpty()) {
                filterTableData(keyword);
            }
        }
    }

    private void showStaffDialog(Staff staff) {
        JDialog dialog = new JDialog();
        dialog.setTitle(staff == null ? "添加员工" : "编辑员工");
        dialog.setSize(500, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 15, 15));
        formPanel.setBackground(Color.WHITE);
        // 员工ID
        formPanel.add(createFormLabel("员工ID:"));
        JTextField idField = new JTextField();
        if (staff != null) idField.setText(staff.getId());
        idField.setEditable(staff == null);
        formPanel.add(idField);
        // 姓名
        formPanel.add(createFormLabel("姓名:"));
        JTextField nameField = new JTextField();
        if (staff != null) nameField.setText(staff.getName());
        formPanel.add(nameField);
        // 性别
        formPanel.add(createFormLabel("性别:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"男", "女"});
        genderCombo.setFont(UITheme.TABLE_CONTENT_FONT);
        if (staff != null) genderCombo.setSelectedItem(staff.getGender());
        formPanel.add(genderCombo);
        // 职位
        formPanel.add(createFormLabel("职位:"));
        JComboBox<String> positionCombo = new JComboBox<>(new String[]{"经理", "前台", "保洁", "保安", "维修"});
        positionCombo.setFont(UITheme.TABLE_CONTENT_FONT);
        if (staff != null) positionCombo.setSelectedItem(staff.getPosition());
        formPanel.add(positionCombo);
        // 电话
        formPanel.add(createFormLabel("电话:"));
        JTextField phoneField = new JTextField();
        if (staff != null) phoneField.setText(staff.getPhone());
        formPanel.add(phoneField);
        // 入职日期
        formPanel.add(createFormLabel("入职日期:"));
        JTextField dateField = new JTextField();
        if (staff != null) dateField.setText(staff.getHireDate());
        else dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        formPanel.add(dateField);
        // 状态
        formPanel.add(createFormLabel("状态:"));
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"在职", "离职"});
        statusCombo.setFont(UITheme.TABLE_CONTENT_FONT);
        if (staff != null) statusCombo.setSelectedItem(staff.getStatus());
        formPanel.add(statusCombo);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        JButton cancelBtn = new JButton("取消");
        cancelBtn.setFont(UITheme.TABLE_CONTENT_FONT);
        cancelBtn.addActionListener(e -> dialog.dispose());
        JButton saveBtn = new JButton("保存");
        saveBtn.setFont(UITheme.TABLE_CONTENT_FONT);
        ComponentStyle.setPrimaryButton(saveBtn);
        saveBtn.addActionListener(e -> {
            try {
                if (staff == null) {
                    // 添加新员工
                    addStaffToTable(
                        validateId(idField.getText()),
                        validateName(nameField.getText()),
                        (String) genderCombo.getSelectedItem(),
                        (String) positionCombo.getSelectedItem(),
                        validatePhone(phoneField.getText()),
                        validateDate(dateField.getText()),
                        (String) statusCombo.getSelectedItem()
                    );
                } else {
                    // 更新现有员工
                    int row = staffTable.getSelectedRow();
                    tableModel.setValueAt(validateId(idField.getText()), row, 0);
                    tableModel.setValueAt(validateName(nameField.getText()), row, 1);
                    tableModel.setValueAt(genderCombo.getSelectedItem(), row, 2);
                    tableModel.setValueAt(positionCombo.getSelectedItem(), row, 3);
                    tableModel.setValueAt(validatePhone(phoneField.getText()), row, 4);
                    tableModel.setValueAt(validateDate(dateField.getText()), row, 5);
                    tableModel.setValueAt(statusCombo.getSelectedItem(), row, 6);
                }
                dialog.dispose();
            } catch (IllegalArgumentException ex) {
                showMessage(ex.getMessage(), "输入错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(cancelBtn);
        buttonPanel.add(saveBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UITheme.TABLE_CONTENT_FONT);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }

    private Staff getStaffFromRow(int row) {
        return new Staff(
            (String) tableModel.getValueAt(row, 0),
            (String) tableModel.getValueAt(row, 1),
            (String) tableModel.getValueAt(row, 2),
            (String) tableModel.getValueAt(row, 3),
            (String) tableModel.getValueAt(row, 4),
            (String) tableModel.getValueAt(row, 5),
            (String) tableModel.getValueAt(row, 6)
        );
    }

    private void confirmAndDeleteRow(int row) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "确定要删除此员工记录吗?",
            "确认删除",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(row);
            showMessage("员工记录已删除", "成功", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void refreshTableData() {
        tableModel.setRowCount(0);
        addSampleData();
    }

    private void filterTableData(String keyword) {
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        staffTable.setRowSorter(sorter);

        if (keyword.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    // 验证方法
    private String validateId(String id) throws IllegalArgumentException {
        if (id.isEmpty() || !id.matches("\\d+")) {
            throw new IllegalArgumentException("员工ID必须是数字且不能为空");
        }
        return id;
    }

    private String validateName(String name) throws IllegalArgumentException {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空");
        }
        return name;
    }

    private String validatePhone(String phone) throws IllegalArgumentException {
        if (phone.isEmpty() || !phone.matches("\\d{11}")) {
            throw new IllegalArgumentException("电话号码必须是11位数字且不能为空");
        }
        return phone;
    }

    private String validateDate(String date) throws IllegalArgumentException {
        if (date.isEmpty() || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("入职日期格式必须为YYYY-MM-DD且不能为空");
        }
        return date;
    }

    // 员工数据模型
    private static class Staff {
        private String id, name, gender, position, phone, hireDate, status;

        public Staff(String id, String name, String gender, String position,
                    String phone, String hireDate, String status) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.position = position;
            this.phone = phone;
            this.hireDate = hireDate;
            this.status = status;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getGender() { return gender; }
        public String getPosition() { return position; }
        public String getPhone() { return phone; }
        public String getHireDate() { return hireDate; }
        public String getStatus() { return status; }
    }
}
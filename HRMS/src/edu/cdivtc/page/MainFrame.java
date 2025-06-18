package edu.cdivtc.page;

import edu.cdivtc.commons.ComponentStyle;
import edu.cdivtc.theme.ThemeColors;
import edu.cdivtc.theme.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel mainPanel, sidebarPanel, contentPanel;
    private JLabel userLabel;
    private JButton dashboardBtn, roomManageBtn, reservationBtn, customerBtn, staffBtn, reportBtn, logoutBtn;
    private CardLayout cardLayout;

    private DashboardPanel dashboardPanel;
    private RoomManagementPanel roomManagementPanel;
    private ReservationPanel reservationPanel;
    private CustomerPanel customerPanel;
    private StaffPanel staffPanel;
    private ReportPanel reportPanel;

    private String username;

    public MainFrame(String username) {
        this.username = username;

        ComponentStyle.setFrame("蓉城酒店预订管理系统 - 主界面", 1500, 900, "src/images/main_bg.png", this);
        UITheme.initialize();
         // 设置窗口图标
    ImageIcon frameIcon = new ImageIcon("src/images/cd.png");
    this.setIconImage(frameIcon.getImage());

    mainPanel = new JPanel(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(mainPanel);

        createSidebar();
        createContentArea();

        this.setVisible(true);
    }

    private void createSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(ThemeColors.SIDEBAR_BACKGROUND);
        sidebarPanel.setPreferredSize(new Dimension(280, 900));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBackground(ThemeColors.SIDEBAR_BACKGROUND);
        userPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));

        ImageIcon userIcon = new ImageIcon("src/images/user_avatar.png");
        Image scaledUser = userIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel avatarLabel = new JLabel(new ImageIcon(scaledUser));
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel roleLabel = new JLabel("管理员", JLabel.CENTER);
        roleLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        roleLabel.setForeground(ThemeColors.SIDEBAR_TEXT);
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userLabel = new JLabel(username, JLabel.CENTER);
        userLabel.setFont(UITheme.SIDEBAR_TITLE_FONT);
        userLabel.setForeground(ThemeColors.SIDEBAR_TEXT);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userPanel.add(avatarLabel);
        userPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        userPanel.add(roleLabel);
        userPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        userPanel.add(userLabel);

        sidebarPanel.add(userPanel);

        dashboardBtn = createMenuButton("控制面板", "src/images/dashboard.png");
        roomManageBtn = createMenuButton("客房管理", "src/images/room.png");
        reservationBtn = createMenuButton("预订管理", "src/images/reservation.png");
        customerBtn = createMenuButton("客户管理", "src/images/customer.png");
        staffBtn = createMenuButton("员工管理", "src/images/staff.png");
        reportBtn = createMenuButton("报表统计", "src/images/report.png");

        sidebarPanel.add(dashboardBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(60, 10)));
        sidebarPanel.add(roomManageBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(60, 10)));
        sidebarPanel.add(reservationBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(60, 10)));
        sidebarPanel.add(customerBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(60, 10)));
        sidebarPanel.add(staffBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(60, 10)));
        sidebarPanel.add(reportBtn);

        sidebarPanel.add(Box.createVerticalGlue());

        JLabel bottomUsernameLabel = new JLabel("当前用户: " + username);
        bottomUsernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        bottomUsernameLabel.setForeground(ThemeColors.SIDEBAR_TEXT);
        bottomUsernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoutBtn = createMenuButton("退出登录", "src/images/logout.png");
        logoutBtn.setForeground(ThemeColors.LOGOUT_BUTTON_TEXT);

        sidebarPanel.add(bottomUsernameLabel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(logoutBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        mainPanel.add(sidebarPanel, BorderLayout.WEST);
    }

    private JButton createMenuButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(UITheme.SIDEBAR_BUTTON_FONT);
        button.setForeground(ThemeColors.SIDEBAR_TEXT);
        button.setBackground(ThemeColors.SIDEBAR_BACKGROUND);
        button.setBorder(BorderFactory.createEmptyBorder(12, 80, 12, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.setPreferredSize(new Dimension(260, 45));
        button.setMinimumSize(new Dimension(260, 45));
        button.setMaximumSize(new Dimension(260, 45));

        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(20);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ThemeColors.SIDEBAR_BUTTON_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(ThemeColors.SIDEBAR_BACKGROUND);
            }
        });

        button.addActionListener(this);
        return button;
    }

    private void createContentArea() {
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        dashboardPanel = new DashboardPanel();
        roomManagementPanel = new RoomManagementPanel();
        reservationPanel = new ReservationPanel();
        customerPanel = new CustomerPanel();
        staffPanel = new StaffPanel();
        reportPanel = new ReportPanel();

        contentPanel.add(dashboardPanel, "dashboard");
        contentPanel.add(roomManagementPanel, "roomManage");
        contentPanel.add(reservationPanel, "reservation");
        contentPanel.add(customerPanel, "customer");
        contentPanel.add(staffPanel, "staff");
        contentPanel.add(reportPanel, "report");

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 判断事件源是哪个按钮
        if (e.getSource() == dashboardBtn) {
            // 显示仪表盘面板
            cardLayout.show(contentPanel, "dashboard");
        } else if (e.getSource() == roomManageBtn) {
            // 显示房间管理面板
            cardLayout.show(contentPanel, "roomManage");
        } else if (e.getSource() == reservationBtn) {
            // 显示预订面板
            cardLayout.show(contentPanel, "reservation");
        } else if (e.getSource() == customerBtn) {
            // 显示客户面板
            cardLayout.show(contentPanel, "customer");
        } else if (e.getSource() == staffBtn) {
            // 显示员工面板
            cardLayout.show(contentPanel, "staff");
        } else if (e.getSource() == reportBtn) {
            // 显示报告面板
            cardLayout.show(contentPanel, "report");
        } else if (e.getSource() == logoutBtn) {
            // 显示确认对话框，询问是否退出系统
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "确定要退出系统吗?",
                "确认退出",
                JOptionPane.YES_NO_OPTION
            );
            // 如果用户选择是，则关闭当前窗口，打开登录窗口
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new LoginFrame();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("测试用户"));
    }
}

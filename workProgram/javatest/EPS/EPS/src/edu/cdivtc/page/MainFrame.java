package edu.cdivtc.page;
import edu.cdivtc.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    public class MainFrame extends JFrame implements ActionListener {
        private ImageIcon logoIcon;
        private JPanel leftPanel, topPanel, rightPanel, pNorth, pSouth, subMenuContainer;
        private JLabel titleLabel, usernameLabel, bgImageLabel, carImageLabel;
        private JButton item1, item2, item3;
        private JButton[] btn, ctn, dtn;
        private JButton exitButton;

        //无参构造
        public MainFrame() {
            setTitle("快递打印系统");
            setSize(1400, 1000);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setResizable(false);
            setLayout(null);
            //1.实现项目（界面）logo
            logoIcon = new ImageIcon("src/images/logo.png");
            setIconImage(logoIcon.getImage());
            //2.上面板，左面板，右面板布局
            topPanel = new JPanel();
            topPanel.setBackground(new Color(221, 233, 255));
            topPanel.setBounds(0, 0, 1400, 120);
            topPanel.setLayout(null);
            this.add(topPanel);
            leftPanel = new JPanel();
            leftPanel.setBounds(0, 120, 260, 880);
            //leftPanel.setBackground(Color.orange);
            this.add(leftPanel);
            rightPanel = new JPanel();
            rightPanel.setBounds(260, 120, 1140, 880);
            //rightPanel.setBackground(Color.green);
            rightPanel.setLayout(null);
            this.add(rightPanel);
            //5.实现二级菜单
            //5.1实例化二级菜单
            subMenuContainer = new JPanel();
            subMenuContainer.setLayout(new GridLayout(2, 1));
            //3.实现顶部面板样式
            //3.1顶部面板左侧汽车图片
            carImageLabel = new JLabel(new ImageIcon("src/images/car.png"));
            carImageLabel.setBounds(0, 0, 100, 120);
            topPanel.add(carImageLabel);
            //3.2顶部面板大标题
            titleLabel = new JLabel("快递打印系统", JLabel.LEFT);
            titleLabel.setFont(new Font("楷体", Font.BOLD, 40));
            titleLabel.setBounds(100, 0, 550, 120);
            topPanel.add(titleLabel);
            //3.3顶部面板欢迎用户信息
            usernameLabel = new JLabel("您好！" + UserSaveTool.getCurrentLoginUsername());
            usernameLabel.setFont(new Font("楷体", Font.BOLD, 20));
            usernameLabel.setBounds(1200, 70, 150, 50);
            topPanel.add(usernameLabel);
            //3.4退出登录按钮
            exitButton = new JButton("退出");
            exitButton.setBounds(1250, 10, 100, 40);
            exitButton.setBackground(new Color(135, 176, 243));
            exitButton.setFont(new Font("楷体", Font.BOLD, 20));
            topPanel.add(exitButton);
            //4.实现左侧面板一级菜单
            //4.1菜单上下面板，上面板为pNorth，下面板为pSouth
            pNorth = new JPanel();
            pNorth.setLayout(new GridLayout(3, 1, 10, 10));
            pSouth = new JPanel();
            leftPanel.add(pNorth, "North");
            //5.5将二级菜单subMenuContainer添加到左侧leftPanel中
            leftPanel.add(subMenuContainer, "Center");
            leftPanel.add(pSouth, "South");
            //4.2一级菜单按钮
            //实例化一级菜单
            item1 = new JButton("快递单管理");
            item2 = new JButton("打印管理");
            item3 = new JButton("用户管理");
            //4.3一级菜单按钮样式
            item1.setPreferredSize(new Dimension(240, 50));
            item2.setPreferredSize(new Dimension(240, 50));
            item3.setPreferredSize(new Dimension(240, 50));
            item1.setBackground(new Color(221, 233, 255));
            item2.setBackground(new Color(221, 233, 255));
            item3.setBackground(new Color(221, 233, 255));
            item1.setFont(new Font("楷体", Font.BOLD, 25));
            item2.setFont(new Font("楷体", Font.BOLD, 25));
            item3.setFont(new Font("楷体", Font.BOLD, 25));
            //4.4将一级菜单添加到左侧面板的上面板pNorth中
            pNorth.add(item1);
            pNorth.add(item2);
            pNorth.add(item3);
            //5.2一级菜单“快递单管理”的二级菜单
            btn = new JButton[2];
            btn[0] = new JButton("添加快递单");
            btn[1] = new JButton("修改快递单");
            for (int i = 0; i < btn.length; i++) {
                btn[i].setBackground(Color.white);
                btn[i].setPreferredSize(new Dimension(220, 30));
                btn[i].setFont(new Font("楷体", Font.BOLD, 20));
                btn[i].addActionListener(this);//点击事件
            }
            //5.3一级菜单“打印管理”的二级菜单
            ctn = new JButton[1];
            ctn[0] = new JButton("打印快递单");
            for (int i = 0; i < ctn.length; i++) {
                ctn[i].setBackground(Color.white);
                ctn[i].setPreferredSize(new Dimension(220, 30));
                ctn[i].setFont(new Font("楷体", Font.BOLD, 20));
                ctn[i].addActionListener(this);//点击事件
            }
            //5.4一级菜单“用户管理”的二级菜单
            dtn = new JButton[2];
            dtn[0] = new JButton("添加用户");
            dtn[1] = new JButton("修改个人密码");
            for (int i = 0; i < dtn.length; i++) {
                dtn[i].setBackground(Color.white);
                dtn[i].setPreferredSize(new Dimension(220, 30));
                dtn[i].setFont(new Font("楷体", Font.BOLD, 20));
                dtn[i].addActionListener(this);//点击事件
            }
            //6.实现点击一级菜单显示二级菜单功能
            //6.2为一级菜单按钮添加添加事件监听
            item1.addActionListener(this);
            item2.addActionListener(this);
            item3.addActionListener(this);
            //7.主界面右侧面板显示背景图
            bgImageLabel = new JLabel(new ImageIcon("src/images/welcome.png"));
            bgImageLabel.setBounds(0, 0, 1160, 850);
            rightPanel.add(bgImageLabel);
            // 退出登录按钮
            exitButton.addActionListener(this);
            setVisible(true);//设置窗体可见
        }

        //主方法
        public static void main(String[] args) {
            new MainFrame();//实例化主界面窗体MainFrame
        }

        //重置按钮背景颜色--白色
        private void resetMenuButtonBackgroundColor() {
            //设置btn[0]按钮的背景颜色为白色
            btn[0].setBackground(Color.white);
            //设置btn[1]按钮的背景颜色为白色
            btn[1].setBackground(Color.white);
            //设置ctn[0]按钮的背景颜色为白色
            ctn[0].setBackground(Color.white);
            //设置dtn[0]按钮的背景颜色为白色
            dtn[0].setBackground(Color.white);
            //设置dtn[1]按钮的背景颜色为白色
            dtn[1].setBackground(Color.white);
        }

        //自动生成ActionListener的抽象方法及方法体
        @Override
        public void actionPerformed(ActionEvent e) {
            //每次点击按钮时，都先将按钮恢复成白色背景
            resetMenuButtonBackgroundColor();
            //6.3判断点击不同的一级菜单按钮，实现不同的功能
            if (e.getSource() == item1) {
                //System.out.println("快递管理");
                //6.4显示“快递管理”二级菜单
                pSouth.removeAll();
                pNorth.setLayout(new GridLayout(1, 1, 10, 10));
                pSouth.setLayout(new GridLayout(2, 1, 10, 10));
                pNorth.add(item1);
                pSouth.add(item2);
                pSouth.add(item3);
                //将一级菜单“快递管理”的二级菜单添加到二级菜单面板中
                for (int i = 0; i < btn.length; i++) {
                    subMenuContainer.add(btn[i]);
                }
                //将一级菜单“打印管理”和“用户管理”的二级菜单从二级菜单面板中移除
                for (int i = 0; i < ctn.length; i++) {
                    subMenuContainer.remove(ctn[i]);
                }
                for (int i = 0; i < dtn.length; i++) {
                    subMenuContainer.remove(dtn[i]);
                }
                validate();
                getContentPane().repaint();//重绘界面
            } else if (e.getSource() == item2) {
                //    System.out.println("打印管理");
                //6.5显示“打印管理”二级菜单
                pSouth.removeAll();
                pNorth.setLayout(new GridLayout(2, 1, 10, 10));
                pSouth.setLayout(new GridLayout(1, 1, 10, 10));
                pNorth.add(item1);
                pNorth.add(item2);
                pSouth.add(item3);
                //将一根菜单“打印管理”的二级菜单添加到二级菜单面板中
                //并将一级菜单“快递管理”和“用户管理”的二级菜单从二级菜单面板中移除
                for (int i = 0; i < btn.length; i++) {
                    subMenuContainer.remove(btn[i]);
                }
                for (int i = 0; i < ctn.length; i++) {
                    subMenuContainer.add(ctn[i]);
                }
                for (int i = 0; i < dtn.length; i++) {
                    subMenuContainer.remove(dtn[i]);
                }
                //重新布局并重绘界面
                validate();
                getContentPane().repaint();
            } else if (e.getSource() == item3) {
                //    System.out.println("用户管理");
                //6.6显示“用户管理”二级菜单
                pSouth.removeAll();
                pNorth.setLayout(new GridLayout(3, 1, 10, 10));
                pSouth.setLayout(new GridLayout(0, 1, 10, 10));
                pNorth.add(item1);
                pNorth.add(item2);
                pNorth.add(item3);
                //将一级菜单“用户管理”的二级菜单添加到二级菜单面板中
                //并将一级菜单“快递管理”和“打印管理”的二级菜单从二级菜单面板中移除
                for (int i = 0; i < btn.length; i++) {
                    subMenuContainer.remove(btn[i]);
                }
                for (int i = 0; i < ctn.length; i++) {
                    subMenuContainer.remove(ctn[i]);
                }
                for (int i = 0; i < dtn.length; i++) {
                    subMenuContainer.add(dtn[i]);
                }
                validate();
                getContentPane().repaint();
            } else if (e.getSource() == btn[0]) {
                //    System.out.println("添加快递单");
                btn[0].setBackground(new Color(135, 176, 243));
                //显示右侧添加快递单页面
                rightPanel.removeAll();
                AddExpressPanel addExpress = new AddExpressPanel();
                rightPanel.add(addExpress);
                rightPanel.updateUI();
            } else if (e.getSource() == btn[1]) {
                //    System.out.println("修改快递单");
                btn[1].setBackground(new Color(135, 176, 243));
                //显示右侧修改快递单页面
                rightPanel.removeAll();
                UpdateExpressPanel updateExpress = new UpdateExpressPanel();
                rightPanel.add(updateExpress);
                rightPanel.updateUI();
            } else if (e.getSource() == ctn[0]) {
                //    System.out.println("打印快递单");
                ctn[0].setBackground(new Color(135, 176, 243));
                //显示右侧打印快递单页面
                rightPanel.removeAll();
                PrintExpressPanel printExpress = new PrintExpressPanel();
                rightPanel.add(printExpress);
                rightPanel.updateUI();
            } else if (e.getSource() == dtn[0]) {
                //    System.out.println("添加用户");
                dtn[0].setBackground(new Color(135, 176, 243));
                // 显示右侧添加用户页面
                rightPanel.removeAll();
                AddUserPanel addUser = new AddUserPanel();
                rightPanel.add(addUser);
                rightPanel.updateUI();
            } else if (e.getSource() == dtn[1]) {
                //    System.out.println("修改个人密码");
                dtn[1].setBackground(new Color(135, 176, 243));
                //显示右侧修改用户密码页面
                rightPanel.removeAll();
                UpdateUserPasswordPanel updateUser = new UpdateUserPasswordPanel();
                rightPanel.add(updateUser);
                rightPanel.updateUI();
            } else if (e.getSource() == exitButton) {  //退出登录按钮
                //1.清空用户工具类中存储的当前登录用户的信息
                UserSaveTool.getCurrentLoginUsername(null);
                UserSaveTool.setCurrentLoginUserPass(null);
                //2.退出主界面，返回登录界面
                this.setVisible(false);
                new LoginFrame();
            }
        }
    }
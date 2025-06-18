package edu.cdivtc.commons;

import javax.swing.*;
import java.awt.*;

public class ComponentStyle {
    /**
     * 页面窗体样式
     *
     * @param sysTittle 系统名称
     * @param width     窗体宽度
     * @param height    窗体高度
     * @param imgUrl    窗体背景图片地址
     * @param jFrame    窗体对象
     */
    public static void setFrame(String sysTittle, int width, int height, String imgUrl, JFrame jFrame) {
        if (jFrame == null) {
            throw new IllegalArgumentException("jFrame cannot be null");
        }
        jFrame.setTitle(sysTittle); // 设置窗体标题
        jFrame.setSize(width, height); // 设置窗体大小
        jFrame.setLocationRelativeTo(null); // 设置窗体居中显示
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭时退出程序
        jFrame.setResizable(false); // 设置窗体大小不可改变
        // 窗体左上角LOGO图标
        ImageIcon icon = new ImageIcon(imgUrl);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            jFrame.setIconImage(icon.getImage());
        } else {
            System.err.println("Failed to load icon image from: " + imgUrl);
        }
    }

    /**
     * 面板背景图片
     *
     * @param imgUrl 背景图片地址
     * @param x      背景图片x轴坐标
     * @param y      背景图片y轴坐标
     * @param width  背景图片宽度
     * @param height 背景图片高度
     * @param panel  将背景图片添加到哪个面板中
     */
    public static void setPanelBackgroundImg(String imgUrl, int x, int y, int width, int height, JPanel panel) {
        if (panel == null) {
            throw new IllegalArgumentException("panel cannot be null");
        }
        ImageIcon icon = new ImageIcon(imgUrl);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            JLabel imgLabel = new JLabel(icon); // 背景图片
            imgLabel.setBounds(x, y, width, height); // 背景图片位置和大小
            panel.add(imgLabel); // 将背景图片添加到面板中
        } else {
            System.err.println("Failed to load background image from: " + imgUrl);
        }
    }

    /**
     * 设置表单组件样式
     *
     * @param component 组件对象
     * @param size      字体大小
     * @param fontColor 字体颜色
     * @param x         组件x轴坐标
     * @param y         组件y轴坐标
     * @param width     组件宽度
     * @param height    组件高度
     * @param panel     将组件添加到哪个面板中
     */
    public static void setFormStyle(JComponent component, int size, Color fontColor, int x, int y,
                                    int width, int height, JPanel panel) {
        if (component == null || panel == null) {
            throw new IllegalArgumentException("component and panel cannot be null");
        }
        component.setFont(new Font("楷体", Font.BOLD, size)); // 文字
        component.setForeground(fontColor); // 字体颜色
        component.setBounds(x, y, width, height); // 位置与宽高
        panel.add(component); // 将组件添加到面板中
    }

    /**
     * 设置按钮样式
     *
     * @param button 按钮对象
     * @param color  按钮颜色
     * @param x      按钮x轴坐标
     * @param y      按钮y轴坐标
     * @param width  按钮宽度
     * @param height 按钮高度
     * @param panel  将按钮添加到哪个面板中
     */
    public static void setButtonStyle(JButton button, Color color, int x, int y, int width, int height, JPanel panel) {
        if (button == null || panel == null) {
            throw new IllegalArgumentException("button and panel cannot be null");
        }
        button.setBackground(color); // 按钮颜色
        button.setForeground(Color.WHITE); // 字体颜色
        button.setFont(new Font("微软雅黑", Font.PLAIN, 16)); // 文字
        button.setFocusPainted(false); // 取消焦点边框
        button.setBounds(x, y, width, height); // 位置与宽高
        panel.add(button); // 将按钮添加到面板中
    }

    // ... 保留原有方法 ...

    /**
     * 创建风格化按钮
     *
     * @param text    按钮文本
     * @param bgColor 背景颜色
     * @return 配置好的JButton
     */
    public static JButton createButton(String text, Color bgColor) {
        if (text == null || bgColor == null) {
            throw new IllegalArgumentException("text and bgColor cannot be null");
        }
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    // 设置主要按钮样式
    public static void setPrimaryButton(JButton button) {
        if (button == null) {
            throw new IllegalArgumentException("button cannot be null");
        }
        setStyledButton(button, edu.cdivtc.theme.ThemeColors.PRIMARY_BUTTON, Color.WHITE);
    }

    // 设置次要按钮样式
    public static void setSecondaryButton(JButton button) {
        if (button == null) {
            throw new IllegalArgumentException("button cannot be null");
        }
        setStyledButton(button, edu.cdivtc.theme.ThemeColors.SECONDARY_BUTTON, Color.BLACK);
    }

    // 设置成功按钮样式
    public static void setSuccessButton(JButton button) {
        if (button == null) {
            throw new IllegalArgumentException("button cannot be null");
        }
        setStyledButton(button, edu.cdivtc.theme.ThemeColors.SUCCESS_BUTTON, Color.WHITE);
    }

    // 设置危险按钮样式
    public static void setDangerButton(JButton button) {
        if (button == null) {
            throw new IllegalArgumentException("button cannot be null");
        }
        setStyledButton(button, edu.cdivtc.theme.ThemeColors.DANGER_BUTTON, Color.WHITE);
    }

    // 设置警告按钮样式
    public static void setWarningButton(JButton button) {
        if (button == null) {
            throw new IllegalArgumentException("button cannot be null");
        }
        setStyledButton(button, edu.cdivtc.theme.ThemeColors.WARNING_BUTTON, Color.BLACK);
    }

    // 设置信息按钮样式
    public static void setInfoButton(JButton button) {
        if (button == null) {
            throw new IllegalArgumentException("button cannot be null");
        }
        setStyledButton(button, edu.cdivtc.theme.ThemeColors.INFO_BUTTON, Color.WHITE);
    }

    // 设置默认按钮样式
    public static void setDefaultButton(JButton button) {
        if (button == null) {
            throw new IllegalArgumentException("button cannot be null");
        }
        setStyledButton(button, edu.cdivtc.theme.ThemeColors.SECONDARY_BUTTON, Color.BLACK);
    }

    /**
     * 设置按钮的通用样式
     *
     * @param button 按钮对象
     * @param bgColor 按钮背景色
     * @param fontColor 按钮字体颜色
     */
    private static void setStyledButton(JButton button, Color bgColor, Color fontColor) {
        button.setBackground(bgColor);
        button.setForeground(fontColor);
        button.setFont(new Font("楷体", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setBorderPainted(false);
    }
    public static void setButtonStyle(JButton button, Color color, JPanel panel) {
    button.setBackground(color);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
    button.setFocusPainted(false);
    button.setPreferredSize(new Dimension(120, 40)); // 默认宽高
    panel.add(button);
}

}

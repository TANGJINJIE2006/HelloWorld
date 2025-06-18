package edu.cdivtc.theme;

import java.awt.Font;

public class UITheme {
    // 字体定义
    public static Font SIDEBAR_TITLE_FONT;
    public static Font SIDEBAR_BUTTON_FONT;
    public static Font PANEL_TITLE_FONT;
    public static Font TABLE_HEADER_FONT;
    public static Font TABLE_CONTENT_FONT;
    public static final Font CONTENT_TITLE_FONT = new Font("Arial", Font.BOLD, 18);
       // 其他代码...



    public static void initialize() {
        // 初始化字体
        SIDEBAR_TITLE_FONT = new Font("微软雅黑", Font.BOLD, 18);
        SIDEBAR_BUTTON_FONT = new Font("微软雅黑", Font.PLAIN, 16);
        PANEL_TITLE_FONT = new Font("微软雅黑", Font.BOLD, 28);
        TABLE_HEADER_FONT = new Font("微软雅黑", Font.BOLD, 14);
        TABLE_CONTENT_FONT = new Font("微软雅黑", Font.PLAIN, 14);

    }
}
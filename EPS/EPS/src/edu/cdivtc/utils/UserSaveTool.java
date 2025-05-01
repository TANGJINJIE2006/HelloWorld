package edu.cdivtc.utils;
public class UserSaveTool {
    //已登录用户的用户名
    private static String currentLoginUsername = null;
    //已登录用户名的用户密码
    private static String currentLoginUserPass = null;
    //  getter 和 setter方法
    public static String getCurrentLoginUsername() { return currentLoginUsername; }
    public static void getCurrentLoginUsername(String currentLoginUsername) {
        UserSaveTool.currentLoginUsername = currentLoginUsername;
    }
    public static String getCurrentLoginUserPass() { return currentLoginUserPass; }
    public static void setCurrentLoginUserPass(String currentLoginUserPass) {
        UserSaveTool.currentLoginUserPass = currentLoginUserPass;
    }

}


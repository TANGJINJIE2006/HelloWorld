package edu.cdivtc.utils;


/**
 * 该类用于保存登录用户的信息
 */

public class UserSaveTool {
    private static String currentLoginUsername = null;//用户名
    private static String currentLoginAccountNumber = null;//用户卡号
    private static String currentLoginAccountPassword = null;//用户密码
    private static String currentLoginMoney = null;//卡内余额
    public static String getCurrentLoginUsername() {
        return currentLoginUsername;
    }
// 设置当前登录用户名
    public static void setCurrentLoginUsername(String currentLoginUsername) {
        // 将传入的当前登录用户名赋值给UserSaveTool类的currentLoginUsername变量
        UserSaveTool.currentLoginUsername = currentLoginUsername;
    }
// 获取当前登录账户号码
    public static String getCurrentLoginAccountNumber() {
        return currentLoginAccountNumber;
    }
// 设置当前登录账户号码
    public static void setCurrentLoginAccountNumber(String currentLoginAccountNumber) {
        // 将传入的当前登录账户号码赋值给UserSaveTool类的currentLoginAccountNumber属性
        UserSaveTool.currentLoginAccountNumber = currentLoginAccountNumber;
    }
// 获取当前登录账户的密码
    public static String getCurrentLoginAccountPassword() {
        return currentLoginAccountPassword;
    }
// 设置当前登录账户密码
    public static void setCurrentLoginAccountPassword(String currentLoginAccountPassword) {
        // 将传入的密码赋值给当前登录账户密码
        UserSaveTool.currentLoginAccountPassword = currentLoginAccountPassword;
    }
// 获取当前登录用户的金额
    public static String getCurrentLoginMoney() {
        return currentLoginMoney;
    }
// 设置当前登录用户的金额
    public static void setCurrentLoginMoney(String currentLoginMoney) {
        // 将传入的金额赋值给当前登录用户的金额
        UserSaveTool.currentLoginMoney = currentLoginMoney;
    }
}

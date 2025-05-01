package edu.cdivtc.entity;

public class User {
    private String username;
    private String password;
    private String okPwd;
// 无参构造
    public User(){
    }
// 有参构造--参数可根据实际应用选择，不一定全选
    public User(String username, String password, String okPwd) {
        this.username = username;
        this.password = password;
        this.okPwd = okPwd;
    }
// getter和setter方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOkPwd() {
        return okPwd;
    }

    public void setOkPwd(String okPwd) {
        this.okPwd = okPwd;
    }
// toString方法
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", okPwd='" + okPwd + '\'' +
                '}';
    }
}

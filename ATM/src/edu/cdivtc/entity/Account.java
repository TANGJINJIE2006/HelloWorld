package edu.cdivtc.entity;

public class Account {
    private int cid;//卡号id
    private String accountNumber;//卡号
    private String password;//卡密码
    private Double money;//卡内余额
    private int status;//卡的激活状态
    public Account(int cid, String accountNumber, String password, Double money, int status) {
        this.cid = cid;
        this.accountNumber = accountNumber;
        this.password = password;
        this.money = money;
        this.status = status;
    }
    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Account {" +
                "cid=" + cid +
                ", accountNumber=" + accountNumber + '\'' +
                ", password=" + password + '\'' +
                ", money=" + money +
                ", status=" + status +
                '}';
    }
}

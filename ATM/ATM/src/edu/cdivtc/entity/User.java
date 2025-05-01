package edu.cdivtc.entity;

import java.util.List;

public class User {
    private int id;//用户id
    private String username;//用户名
    private String identityCard;//身份证号码
    private String gender;//性别
    private List<Account> accountList;//外键 银行卡id
    public User(int id, String username, String identityCard, String gender, List<Account> accountList) {
        this.id = id;
        this.username = username;
        this.identityCard = identityCard;
        this.gender = gender;
        this.accountList = accountList;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getIdentityCard() {
        return identityCard;
    }
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public List<Account> getAccountList() {
        return accountList;
    }
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", gender='" + gender + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}

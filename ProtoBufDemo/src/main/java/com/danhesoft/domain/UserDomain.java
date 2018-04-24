package com.danhesoft.domain;

/**
 * Created by caowei on 2018/4/20.
 */
public class UserDomain {
    private String userId;
    private String userName;
    private int userAge;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}

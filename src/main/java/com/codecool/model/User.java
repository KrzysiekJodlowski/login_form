package com.codecool.model;


public class User {
    private int userId;
    private String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {return this.userId; }

    public String getUserName() {
        return this.userName;
    }
}

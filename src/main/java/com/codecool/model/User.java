package com.codecool.model;


public class User {
    private String login;
    private  String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }
}

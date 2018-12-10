package com.codecool.model;

import java.util.ArrayList;
import java.util.List;


public class ActiveUsers {
    private static List<User> activeUsers;

    public ActiveUsers() {
        this.activeUsers = new ArrayList<>();
    }

    public void addUser(User user) {
        this.activeUsers.add(user);
    }

    public void removeUser(User user) {
        this.activeUsers.remove(user);
    }
}

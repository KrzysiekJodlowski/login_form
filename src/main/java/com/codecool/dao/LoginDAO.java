package com.codecool.dao;


import com.codecool.model.User;

public interface LoginDAO {

    boolean checkIfUserExists(String username, String password);
    User getUser(String username, String password);
}

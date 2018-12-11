package com.codecool.dao;


import com.codecool.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOimpl implements LoginDAO {
    private DBCPDataSource connectionPool;

    public LoginDAOimpl(DBCPDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean checkIfUserExists(String username, String password) {
        boolean userExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM login_data WHERE user_login = ? AND user_password = ?";

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userExists = true;
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }

    @Override
    public User getUser(String username, String password) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM login_data WHERE user_login = ? AND user_password = ?";

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int userId = resultSet.getInt("user_id");
            String userName = resultSet.getString("user_login");
            user = new User(userId, username);

            preparedStatement.close();
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM login_data WHERE user_id = ?";

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            String userName = resultSet.getString("user_login");
            user = new User(userId, userName);

            preparedStatement.close();
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}

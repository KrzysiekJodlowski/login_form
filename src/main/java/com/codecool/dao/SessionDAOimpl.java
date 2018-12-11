package com.codecool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessionDAOimpl implements SessionDAO {
    private DBCPDataSource connectionPool;

    public SessionDAOimpl(DBCPDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void createSession(int userId, String sessionId) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String query = "INSERT INTO session_data VALUES (?, ?)";

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, sessionId);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeSession(String sessionId) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String query = "DELETE FROM session_data WHERE session_id = ?";

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sessionId);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } /*

    @Override
    public int getUserIdBySession(String sessionId) {
        int userId = -1;
        String query = "SELECT * FROM session WHERE session_id = ?;";

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sessionId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }*/
}

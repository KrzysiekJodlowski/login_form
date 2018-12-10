package com.codecool.dao;


import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DBCPDataSource {
    private static BasicDataSource dataSource;

    public DBCPDataSource(){
        this.dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/login_form");
        dataSource.setUsername("krzysiek");
        dataSource.setPassword("sancassania");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}

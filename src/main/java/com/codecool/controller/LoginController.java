package com.codecool.controller;

import com.codecool.dao.DBCPDataSource;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;


public class LoginController implements HttpHandler {
    private DBCPDataSource connectionPool;

    public LoginController(DBCPDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}

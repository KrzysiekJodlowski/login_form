package com.codecool.controller;

import com.codecool.dao.DBCPDataSource;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;


public class HelloController implements HttpHandler {

    private DBCPDataSource connectionPool;

    public HelloController(DBCPDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}

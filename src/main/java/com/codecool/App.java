package com.codecool;

import com.codecool.controller.HelloController;
import com.codecool.controller.LoginController;
import com.codecool.controller.StaticController;
import com.codecool.controller.UserNotFoundController;
import com.sun.net.httpserver.HttpServer;
import com.codecool.dao.DBCPDataSource;


import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws Exception {

        // Initialize connection pool
        DBCPDataSource connectionPool = new DBCPDataSource();

        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes
        server.createContext("/login", new LoginController(connectionPool));
        server.createContext("/hello", new HelloController(connectionPool));
        server.createContext("/usernotfound", new UserNotFoundController());
        server.createContext("/static", new StaticController());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}

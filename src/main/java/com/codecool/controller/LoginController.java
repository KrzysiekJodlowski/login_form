package com.codecool.controller;

import com.codecool.dao.DBCPDataSource;
import com.codecool.view.TemplateView;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;


public class LoginController implements HttpHandler {
    private DBCPDataSource connectionPool;
    private TemplateView templateView;
    private String templatePath;

    public LoginController(DBCPDataSource connectionPool, TemplateView templateView) {
        this.connectionPool = connectionPool;
        this.templateView = templateView;
        this.templatePath = "templates/login_form.twig";
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        this.templateView.showTemplate(httpExchange, this.templatePath);

    }
}

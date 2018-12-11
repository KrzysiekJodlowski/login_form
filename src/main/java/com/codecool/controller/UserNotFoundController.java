package com.codecool.controller;

import com.codecool.view.TemplateView;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;


public class UserNotFoundController implements HttpHandler {
    private TemplateView templateView;
    private String templatePath;

    public UserNotFoundController(TemplateView templateView) {
        this.templateView = templateView;
        this.templatePath = "templates/user_not_found.twig";
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        this.templateView.showTemplate(httpExchange, this.templatePath);

    }
}

package com.codecool.controller;

import com.codecool.dao.DBCPDataSource;
import com.codecool.dao.SessionDAO;
import com.codecool.dao.SessionDAOimpl;
import com.codecool.view.TemplateView;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.HttpCookie;


public class HelloController implements HttpHandler {
    private DBCPDataSource connectionPool;
    private SessionDAO sessionDAO;
    private RedirectController redirectController;
    private TemplateView templateView;
    private String templatePath;

    public HelloController(DBCPDataSource connectionPool, TemplateView templateView) {
        this.connectionPool = connectionPool;
        this.templateView = templateView;
        this.sessionDAO = new SessionDAOimpl(connectionPool);
        this.redirectController = new RedirectController();
        this.templatePath = "templates/hello.twig";
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if(method.equals("GET")){
            this.templateView.showTemplate(httpExchange, this.templatePath);
        }

        if (method.equals("POST")) {
            String cookieString = httpExchange.getRequestHeaders().getFirst("Cookie");
            if (cookieString != null) {
                HttpCookie sessionIdCookie = HttpCookie.parse(cookieString).get(0);
                sessionDAO.removeSession(sessionIdCookie.getValue());
            }

            HttpCookie cookie = new HttpCookie("sessionId", "token=deleted; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT");
            cookie.setMaxAge(-1);
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());

            redirectController.redirect(httpExchange, "/login", null);
        }
    }
}

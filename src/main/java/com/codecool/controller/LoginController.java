package com.codecool.controller;

import com.codecool.dao.*;
import com.codecool.model.User;
import com.codecool.view.TemplateView;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class LoginController implements HttpHandler {
    private DBCPDataSource connectionPool;
    private LoginDAO loginDAO;
    private SessionDAO sessionDAO;
    private RedirectController redirectController;
    private TemplateView templateView;
    private String templatePath;


    public LoginController(DBCPDataSource connectionPool, TemplateView templateView) {
        this.connectionPool = connectionPool;
        this.loginDAO = new LoginDAOimpl(connectionPool);
        this.sessionDAO = new SessionDAOimpl(connectionPool);
        this.redirectController = new RedirectController();
        this.templateView = templateView;
        this.templatePath = "templates/login_form.twig";
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if(method.equals("GET")){
            this.templateView.showTemplate(httpExchange, this.templatePath);
        }

        if (method.equals("POST")) {
            removeSessionIfCookieExists(httpExchange);

            String context = "";
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = parseFormData(formData);

            String userName = (String) inputs.get("username");
            String password = (String) inputs.get("password");

            boolean userExists = loginDAO.checkIfUserExists(userName, password);

            if (userExists) {
                User user = loginDAO.getUser(userName, password);
                HttpCookie cookie = new HttpCookie("sessionId", UUID.randomUUID().toString());
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                sessionDAO.createSession(user.getUserId(), cookie.getValue());

                if (user != null) {
                    context = "/hello";
                }
            } else {
                context = "/usernotfound";
            }
            redirectController.redirect(httpExchange, context);
        }
    }

    private void removeSessionIfCookieExists(HttpExchange httpExchange) {
        String cookieString = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieString != null) {
            HttpCookie cookie = HttpCookie.parse(cookieString).get(0);
            sessionDAO.removeSession(cookie.getValue());
        }
    }

    public Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");

            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}

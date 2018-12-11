package com.codecool.view;

import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;


public class TemplateView {

    public void showTemplate(HttpExchange httpExchange, String templatePath) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate(templatePath);
        JtwigModel model = JtwigModel.newModel();
        sendTemplate(httpExchange, template, model);
    }

    public void showHello(HttpExchange httpExchange, String templatePath, String userName) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate(templatePath);
        JtwigModel model = JtwigModel.newModel();
        model.with("userName", userName);
        sendTemplate(httpExchange, template, model);
    }

    private void sendTemplate(HttpExchange httpExchange, JtwigTemplate template, JtwigModel model) throws IOException {
        String response = template.render(model);
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

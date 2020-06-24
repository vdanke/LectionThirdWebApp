package org.step.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Init method is called");
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        System.out.println("Service is called with method " + method);
        super.service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hello = request.getParameter("hello");
        System.out.println("Post method is called " + hello);
        response.sendRedirect("/home");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();

        Map<String, String> collect = parameterMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> String.join("/", entry.getValue())
                ));

        collect.forEach((key, value) -> System.out.println(key + " " + value));

        System.out.println("Get method is called " + request.getParameter("hello"));
        response.sendRedirect("/home");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy is called");
        super.destroy();
    }
}

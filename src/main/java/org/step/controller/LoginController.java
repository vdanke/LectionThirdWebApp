package org.step.controller;

import org.step.model.User;
import org.step.security.TokenCreator;
import org.step.service.UserService;
import org.step.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/users/login", "/login-registration"})
public class LoginController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = new ArrayList<>(
                Arrays.asList(
                        new User("full", "user", "password"),
                        new User("sec", "userSec", "passwordSec"))
        );

        req.setAttribute("users", allUsers);

        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Cookie[] cookies = req.getCookies();

        if ((username != null && !username.isEmpty()) && (password != null && !password.isEmpty())) {
            User user = userService.login(username, password);

            HttpSession session = req.getSession(true);
//                        session.setMaxInactiveInterval();
            session.setAttribute("user", user);
            System.out.println(String.format("Successful login by %s", user.getUsername()));
            if (cookies != null) {
                Cookie cookie = new Cookie("Token", TokenCreator.createToken(user));
                cookie.setMaxAge(3600);
                cookie.setHttpOnly(true);
                cookie.setComment("This is security cookie");
                System.out.println("Cookie");
                resp.addCookie(cookie);
            } else {
                System.out.println("Header");
                resp.addHeader("Token", TokenCreator.createToken(user));
            }
        }
        resp.sendRedirect("/home");
    }
}

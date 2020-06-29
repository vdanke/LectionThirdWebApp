package org.step.controller;

import org.step.model.User;
import org.step.service.UserService;
import org.step.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/users/registration"})
public class RegistrationController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");

        User user = new User(fullName, username, password);

        User save = userService.save(user);

        if (save != null) {
            resp.sendRedirect("/home/login-registration");
        } else {
            throw new IllegalStateException("Registration is failed");
        }
    }
}

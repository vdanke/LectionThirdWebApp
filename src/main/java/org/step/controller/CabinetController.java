package org.step.controller;

import org.step.model.User;
import org.step.security.TokenCreator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@WebServlet(urlPatterns = {"/cabinet"})
public class CabinetController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        User user = (User) req.getSession().getAttribute("user");

        if (user != null && cookies != null) {
            Cookie securityCookie = Stream.of(cookies)
                    .filter(cookie -> cookie.getName().equals("Token"))
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Token not found"));

            String token = securityCookie.getValue();

            boolean isValidToken = TokenCreator.validateToken(token, user);

            if (isValidToken) {
                System.out.println("OKEY");
            } else {
                System.out.println("BAD");
            }
        } else {
            throw new IllegalStateException("User is null");
        }
    }
}

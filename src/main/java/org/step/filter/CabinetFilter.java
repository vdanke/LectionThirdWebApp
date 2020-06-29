package org.step.filter;

import org.step.model.User;
import org.step.security.TokenCreator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/cabinet"})
public class CabinetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        Object user = session.getAttribute("user");

        System.out.println("Cabinet filter " + user);

        String token = request.getHeader("Token");

        TokenCreator.validateToken(token, (User) user);

        session.invalidate();

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

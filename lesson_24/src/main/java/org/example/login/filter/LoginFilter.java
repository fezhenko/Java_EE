package org.example.login.filter;

import org.example.login.model.User;
import org.example.login.service.LoginService;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/")
public class LoginFilter implements Filter {

    private LoginService loginService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        loginService = (LoginService) filterConfig.getServletContext().getAttribute("LoginService");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        User user = new User(username, password);

        final HttpSession session = req.getSession();
        if (nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {

            req.getRequestDispatcher("/WEB-INF/postLogin.jsp").forward(req, res);
        }
        else if (loginService.validateUser(user)) {

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/postLogin.jsp").forward(req, res);
        }
        else
        {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, res);
        }
    }
}
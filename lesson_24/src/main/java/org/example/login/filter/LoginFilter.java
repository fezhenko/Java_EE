package org.example.login.filter;

import org.example.login.model.User;
import org.example.login.service.LoginService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            throws IOException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final HttpSession session = req.getSession();
        String getIsLoggedInValue = (String)session.getAttribute("isLoggedIn");

        if(session.getAttribute("isLoggedIn") == null){
            res.sendRedirect("login");
        }
        else if(session.getAttribute("isLoggedIn") != null && Boolean.parseBoolean(getIsLoggedInValue)){
            res.sendRedirect("postLogin");
        }
        else
        {
            res.sendRedirect("registration");
        }

    }
}
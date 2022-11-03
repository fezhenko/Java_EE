package org.example.login.filter;

import org.example.login.service.LoginService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/users")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final HttpSession session = req.getSession();
        if(session.getAttribute("isLoggedIn") != null){
            boolean getIsLoggedInValue = (Boolean) req.getSession().getAttribute("isLoggedIn");
            if(getIsLoggedInValue){
                res.sendRedirect("users");
            }
            else{
                res.sendRedirect("login");
            }
        }
        else {
            res.sendRedirect("registration");
        }
    }
}
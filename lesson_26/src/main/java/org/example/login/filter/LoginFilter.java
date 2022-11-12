package org.example.login.filter;


import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final List<String> filteredPages = new ArrayList<>();
        filteredPages.add("users");
        filteredPages.add("incoming-requests");

        req.getServletPath();

        final HttpSession session = req.getSession();
        if (session.getAttribute("isLoggedIn") != null) {
            final Boolean getIsLoggedInValue = (Boolean) req.getSession().getAttribute("isLoggedIn");
            if (getIsLoggedInValue) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("login");
            }
        } else {
            res.sendRedirect("registration");
        }
    }
}
